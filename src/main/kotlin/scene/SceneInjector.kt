package scene

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import gdscript.ScriptLanguage
import scene.psi.StringNode

class SceneInjector : MultiHostInjector {

    override fun elementsToInjectIn() =
        mutableListOf(StringNode::class.java)

    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        val eee = context as StringNode
        val moved = eee.textRange
        registrar.startInjecting(ScriptLanguage)
            .addPlace(null, null, eee, moved)
            .doneInjecting()
        println("XD")
    }

}