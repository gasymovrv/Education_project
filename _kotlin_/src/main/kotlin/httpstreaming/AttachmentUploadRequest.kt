package httpstreaming

import java.io.File

data class AttachmentUploadRequest(
    val entityId: Int,
    val entityType: String,
    val file: File
)
