package gdscript.execution

import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.util.execution.ParametersListUtil
import gdscript.execution.error.ErrorListener
import gdscript.execution.error.ErrorService
import org.jdom.Element

class RunConfiguration(project: Project, factory: ConfigurationFactory)
    : LocatableConfigurationBase<RunProfileState>(project, factory) {

    var executable = ""
    var workingDirectory = ""
    var parameters = ""

    override fun checkConfiguration() {
        if (executable.isBlank())
            throw RuntimeConfigurationError("Executable is empty")
        if (workingDirectory.isBlank())
            throw RuntimeConfigurationError("Working directory is empty")
    }

    override fun getConfigurationEditor() =
        RunConfigurationPanel(project)

    override fun getState(executor: Executor, environment: ExecutionEnvironment) =
        object : CommandLineState(environment) {

            override fun startProcess(): ProcessHandler {
                val commandLine = createCommandLine()
                val handler = OSProcessHandler(commandLine)
                val errorService = ServiceManager.getService(project, ErrorService::class.java)
                handler.addProcessListener(ErrorListener(errorService))
                ProcessTerminatedListener.attach(handler, environment.project)
                return handler
            }

        }

    override fun readExternal(element: Element) {
        executable = element.getAttributeValue(EXECUTABLE_TAG).orEmpty()
        workingDirectory = element.getAttributeValue(WORKING_DIRECTORY_KEY).orEmpty()
        parameters = element.getAttributeValue(PARAMETERS_KEY).orEmpty()
    }

    override fun writeExternal(element: Element) {
        element.setAttribute(EXECUTABLE_TAG, executable)
        element.setAttribute(WORKING_DIRECTORY_KEY, workingDirectory)
        element.setAttribute(PARAMETERS_KEY, parameters)
    }

    private fun createCommandLine() =
        GeneralCommandLine()
            .withExePath(executable)
            .withWorkDirectory(workingDirectory)
            .withParameters(ParametersListUtil.parse(parameters))

    private companion object {

        const val EXECUTABLE_TAG = "executable"
        const val WORKING_DIRECTORY_KEY = "workingDirectory"
        const val PARAMETERS_KEY = "parameters"

    }

}
