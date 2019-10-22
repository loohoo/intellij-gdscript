package gdscript.completion.lookups

import GodotApi
import com.intellij.codeInsight.completion.util.ParenthesesInsertHandler
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementBuilder.create
import com.intellij.util.PlatformIcons

object InvokeLookups {

    fun createConstructor(it: GodotApi.Class.Method): LookupElement =
        create(it.name)
            .withIcon(PlatformIcons.CLASS_ICON)
            .withTypeText(it.type)
            .withArgumentsTail(it.arguments)
            .withParenthesesInsertHandler(it.arguments.isNotEmpty())

    fun createPrimitiveConstructor(it: GodotApi.Class.Method): LookupElement =
        create(it.name)
            .withArgumentsTail(it.arguments)
            .withParenthesesInsertHandler(it.arguments.isNotEmpty())
            .bold()

    fun createFunction(it: GodotApi.Class.Method): LookupElement =
        create(it.name)
            .withIcon(PlatformIcons.FUNCTION_ICON)
            .withTypeText(it.type)
            .withArgumentsTail(it.arguments)
            .withParenthesesInsertHandler(it.arguments.isNotEmpty())
            .bold()

    private fun LookupElementBuilder.withArgumentsTail(arguments: List<GodotApi.Class.Method.Argument>) =
        withTailText(arguments.joinToString(", ", "(", ")") { "${it.name}: ${it.type}" })

    private fun LookupElementBuilder.withParenthesesInsertHandler(hasParameters: Boolean): LookupElementBuilder =
        if (hasParameters)
            withInsertHandler(ParenthesesInsertHandler.WITH_PARAMETERS)
        else
            withInsertHandler(ParenthesesInsertHandler.NO_PARAMETERS)

}