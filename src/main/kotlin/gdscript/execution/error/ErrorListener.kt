package gdscript.execution.error

import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.util.Key

class ErrorListener(private val service: ErrorService) : ProcessAdapter() {

    private var lastMessage = ""
    private var lastLineNumber = ""
    private var lastFile = ""

    override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
        val line = event.text
        processLine(line)
    }

    private fun processLine(text: String) {
        if (text.matches(ERROR_MESSAGE))
            lastMessage = ERROR_MESSAGE.matchEntire(text)!!.groups["message"]!!.value
        if (text.matches(ERROR_LOCATION)) {
            val groups = ERROR_LOCATION.matchEntire(text)!!.groups
            lastFile = groups["file"]!!.value
            lastLineNumber = groups["lineNumber"]!!.value
            submitError()
        }
    }

    private fun submitError() {
        val error = ErrorOccurrence(
            message = lastMessage,
            lineNumber = lastLineNumber,
            file = lastFile
        )
        service.submitError(error)
    }

    companion object {

        private val ERROR_MESSAGE = ".*Parse Error: (?<message>.*)".toRegex()
        private val ERROR_LOCATION = ".*At: res://(?<file>.*):(?<lineNumber>[0-9]*).".toRegex()

    }

}
