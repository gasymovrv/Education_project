package httpstreaming

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import kotlin.random.Random

fun main() = runBlocking {
    Thread.sleep(10000)
    // Set system properties for Keep-Alive, it is needed when there are a lot of outgoing requests
    System.setProperty("http.keepAlive", "true")
    System.setProperty("sun.net.www.protocol.http.HttpURLConnection.maxConnections", "500")

    for (i in 1..500) {
        launch(Dispatchers.IO) {
            sendAttachmentInChunks(
                AttachmentUploadRequest(
                    entityId = Random.nextInt(1, 100),
                    entityType = "CUSTOMER_REQUEST",
                    file = File("_kotlin_/src/main/resources/testfile.jpg"),
                )
            )
            println("$i done")
        }
        println("$i sent")
    }
}

/**
 * Boundary: A unique string used to separate parts in the multipart request.
 *
 * Form Data Parts:
 *  - entityId: Sent as a text part.
 *  - entityType: Sent as a text part.
 *  - file: Sent as a file part.
 *
 * DataOutputStream: Used to write the multipart form data to the connection's output stream.
 *
 * Buffered Reading: The FileInputStream reads the file in small chunks (1 KB in this case)
 * using a buffer.
 *
 * Incremental Writing: Each chunk is written to the DataOutputStream as it is read,
 * meaning only a small portion of the file is in memory at any given time.
 */
private fun sendAttachmentInChunks(request: AttachmentUploadRequest, bufferSize: Int = 1024) {
    val boundary = "===" + System.currentTimeMillis() + "==="
    val lineEnd = "\r\n"
    val twoHyphens = "--"
    val charset = "UTF-8"
    val url: URL = URI.create("http://localhost:8904/v1/attachments/upload").toURL()
    val buffer = ByteArray(bufferSize)

    val con = url.openConnection() as HttpURLConnection
    try {
        // It enables sending bytes within the connection in chunks
        // But it requires to support this feature by server
        con.setChunkedStreamingMode(bufferSize)

        con.requestMethod = "POST"
        con.doOutput = true
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")

        DataOutputStream(con.outputStream).use { dos ->
            // Write entityId
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"entityId\"$lineEnd")
            dos.writeBytes("Content-Type: text/plain; charset=$charset$lineEnd")
            dos.writeBytes(lineEnd)
            dos.writeBytes(request.entityId.toString() + lineEnd)

            // Write entityType
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"entityType\"$lineEnd")
            dos.writeBytes("Content-Type: text/plain; charset=$charset$lineEnd")
            dos.writeBytes(lineEnd)
            dos.writeBytes(request.entityType + lineEnd)

            // Write file content
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"${request.file.name}\"$lineEnd")
            dos.writeBytes("Content-Type: application/octet-stream$lineEnd")
            dos.writeBytes(lineEnd)

            FileInputStream(request.file).use { fis ->
                var bytesRead: Int
                while (fis.read(buffer).also { bytesRead = it } != -1) {
                    dos.write(buffer, 0, bytesRead)
                }
            }

            dos.writeBytes(lineEnd)
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)
            println("Form data sent")
        }

        val responseCode = con.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            println("File uploaded successfully")
        } else {
            println("Failed to upload file. Response code: $responseCode")
        }
    } finally {
        con.disconnect()
    }
}
