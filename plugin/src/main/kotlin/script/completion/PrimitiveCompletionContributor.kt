package script.completion

import GodotApi
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType.BASIC
import com.intellij.codeInsight.lookup.LookupElementBuilder.create
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext
import script.psi.elements.TypePsiElement


class PrimitiveCompletionContributor : CompletionContributor() {

    init {
        extend(BASIC, psiElement().inside(TypePsiElement::class.java), TypeProvider)
    }

    private object TypeProvider : CompletionProvider<CompletionParameters>() {

        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            for (name in GodotApi.PRIMITIVE_CLASSES.map { it.name })
                result.addElement(create(name).bold().withCaseSensitivity(false))
        }

    }

}
