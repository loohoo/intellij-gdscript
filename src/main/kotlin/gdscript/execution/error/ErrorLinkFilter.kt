package gdscript.execution.error

import com.intellij.execution.filters.AbstractFileHyperlinkFilter
import com.intellij.execution.filters.FileHyperlinkRawData
import com.intellij.openapi.project.Project

class ErrorLinkFilter(project: Project) : AbstractFileHyperlinkFilter(project, "asasdasd") {

    override fun parse(line: String): MutableList<FileHyperlinkRawData> {
        return emptyList<FileHyperlinkRawData>().toMutableList()
    }

}