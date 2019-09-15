package gdscript

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import gdscript.languages.ScriptLanguage


class ScriptColorSettingsPage : ColorSettingsPage {

    override fun getAdditionalHighlightingTagToDescriptorMap() =
        emptyMap<String,TextAttributesKey>()

    override fun getIcon() =
        ScriptFileType.icon

    override fun getHighlighter() =
        ScriptHighlighterFactory().getSyntaxHighlighter(null, null)

    override fun getDemoText() =
        ScriptColorSettingsPage::class.java.getResource("/demo.gd").readText()

    override fun getAttributeDescriptors() =
        ScriptHighlighterFactory().highlighting.keys
        .map { attr -> AttributesDescriptor(humanize(attr), attr) }
        .toTypedArray()

    override fun getColorDescriptors() =
        emptyArray<ColorDescriptor>()

    override fun getDisplayName() =
        ScriptLanguage.displayName

    private fun humanize(attribute: TextAttributesKey) =
        attribute.externalName
        .trim()
        .split("_")
        .map { it.toLowerCase() }
        .filter { it != "default" }
        .joinToString(" ")
        .capitalize()

}