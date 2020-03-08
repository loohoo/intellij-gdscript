package gdscript.annotation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.components.ServiceManager
import com.intellij.psi.PsiElement
import gdscript.execution.error.ErrorService

class ErrorAnnotator  : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val errorService = attachedErrorService(holder)
        println(errorService)
    }

    private fun attachedErrorService(holder: AnnotationHolder): ErrorService {
        val project = holder.currentAnnotationSession.file.project
        return ServiceManager.getService(project, ErrorService::class.java)
    }

}