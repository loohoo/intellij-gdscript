package gdscript.annotation

import ScriptLexer.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.openapi.util.Key
import gdscript.annotation.TypePropagationAnnotator.Companion.putType

class TypePropagationAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        annotateLeaf(element)

        val childrenTypes = element.childrenTypes()
        if (childrenTypes.size == 1 && element.getType() == null)
            element.putType(childrenTypes.first())

        val type = element.getType()
        val text = "'${element.text}'"
        return // breakpoint here
    }

    private fun annotateLeaf(leaf: PsiElement) {
        if (leaf.isToken(NUMBER) || leaf.isToken(FLOAT) || leaf.isToken(INT))
            leaf.putType("number")
    }

    private fun PsiElement.childrenTypes() =
        children.mapNotNull { it.getType() }.distinct()

    companion object {

        private val TYPE_KEY = Key<String>("type")

        fun PsiElement.putType(type: String) {
            putUserData<String>(TYPE_KEY, type)
            println("$this '$text' put $type")
        }

        fun PsiElement.getType() =
            getUserData<String>(TYPE_KEY)

        fun PsiElement.hasType() =
            getType() != null


    }

}