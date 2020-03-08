package gdscript.execution.error

import com.intellij.execution.filters.AbstractFileHyperlinkFilter
import com.intellij.execution.filters.ConsoleFilterProvider
import com.intellij.execution.filters.FileHyperlinkRawData
import com.intellij.execution.filters.Filter
import com.intellij.openapi.project.Project

class ErrorLinkFilterProvider : ConsoleFilterProvider {

    override fun getDefaultFilters(project: Project): Array<Filter> {
        val filter = object : AbstractFileHyperlinkFilter(project, "") {

            override fun parse(line: String): List<FileHyperlinkRawData> {
                println("LINE: $line")
                return emptyList()
            }

        }
        return arrayOf(filter)
        // https://github.com/siosio/consoleLink/blob/master/src/main/java/siosio/ConsoleLinkFilter.kt
    }

}