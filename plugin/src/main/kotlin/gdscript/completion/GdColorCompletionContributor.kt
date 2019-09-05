package gdscript.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.patterns.PlatformPatterns.psiElement
import gdscript.completion.utilities.PrioritizedLookupCompletionProvider
import com.intellij.codeInsight.completion.CompletionType.BASIC
import com.intellij.codeInsight.lookup.LookupElementBuilder
import gdscript.icons.ColorIcon
import java.awt.Color

class GdColorCompletionContributor : CompletionContributor() {

    init {
        val color = Library.load().classes.find { it.name == "Color" }!!
        for (colorConstant in color.constants) {
            val lookup = createLookupFromConstant(colorConstant)
            extend(BASIC, psiElement(), PrioritizedLookupCompletionProvider(listOf(lookup)))
        }
    }

    private fun createLookupFromConstant(constant: Library.Class.Constant): LookupElementBuilder {
        val color = parseColor(constant.value)
        val icon = ColorIcon(color)
        val tail = constant.value
        return LookupElementBuilder.create(constant.name)
            .withIcon(icon)
            .withTailText(tail)
            .withTypeText("Color")
    }

    private fun parseColor(script: String): Color {
        val channels = readColorChannels(script)
        return Color(channels[0], channels[1], channels[2], channels[3])
    }

    private fun readColorChannels(script: String) = script
        .replace("Color", "")
        .removeSurrounding("(", ")")
        .split(",")
        .map { it.trim() }
        .map { it.toFloat() }

}