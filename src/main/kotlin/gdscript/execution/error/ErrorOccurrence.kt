package gdscript.execution.error

data class ErrorOccurrence(
    val message: String,
    val lineNumber: String,
    val file: String
)