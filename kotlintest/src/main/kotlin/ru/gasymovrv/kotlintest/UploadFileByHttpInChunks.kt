package ru.gasymovrv.kotlintest

import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import kotlin.random.Random

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
fun main() {
    val boundary = "===" + System.currentTimeMillis() + "==="
    val lineEnd = "\r\n"
    val twoHyphens = "--"
    val charset = "UTF-8"
    val url: URL = URI.create("http://localhost:8904/v1/attachments/upload").toURL()
    val file = File("test-image.gif")
    val buffer = ByteArray(1024) // 1 KB buffer

    val con = url.openConnection() as HttpURLConnection
    try {
        con.requestMethod = "POST"
        con.doOutput = true
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")

        val entityId = Random.nextInt(1, 100)
        val entityType = "CUSTOMER_REQUEST"

        DataOutputStream(con.outputStream).use { dos ->
            // Write entityId
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"entityId\"$lineEnd")
            dos.writeBytes("Content-Type: text/plain; charset=$charset$lineEnd")
            dos.writeBytes(lineEnd)
            dos.writeBytes(entityId.toString() + lineEnd)

            // Write entityType
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"entityType\"$lineEnd")
            dos.writeBytes("Content-Type: text/plain; charset=$charset$lineEnd")
            dos.writeBytes(lineEnd)
            dos.writeBytes(entityType + lineEnd)

            // Write file content
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"${file.name}\"$lineEnd")
            dos.writeBytes("Content-Type: application/octet-stream$lineEnd")
            dos.writeBytes(lineEnd)

            FileInputStream(file).use { fis ->
                var bytesRead: Int
                while (fis.read(buffer).also { bytesRead = it } != -1) {
                    dos.write(buffer, 0, bytesRead)
                    println("Bytes read: $bytesRead")
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
