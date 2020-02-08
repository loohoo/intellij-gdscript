package scene

import com.intellij.psi.tree.IElementType
import junit.framework.TestCase


class MyLexerTest : TestCase() {

    fun `test asd`() {
        val lexer = MyLexer(
            types = setOf(
                IElementType("IDENTIFIER", SceneLanguage),
                IElementType("NUMBER", SceneLanguage),
                IElementType("STRING", SceneLanguage),
                IElementType("COMMA", SceneLanguage),
                IElementType("WHITESPACE", SceneLanguage)
            ),
            fallbackType = IElementType("UNKNOWN", SceneLanguage),
            regex = "(?<IDENTIFIER>[a-zA-Z_]+)|(?<NUMBER>-?\\d*\\.?\\d+)|(?<STRING>\".*?\")|(?<COMMA>,)|(?<WHITESPACE>\\s+)".toRegex()
        )
        lexer.start("""
            config_version=4
            flags/filter=true
            viewport/default_clear_color = Color(0, 0, 0, 1)
            path="res://icon.stex"
        """.trimMargin())
        printState(lexer)
        do {
            lexer.advance()
            printState(lexer)
        } while (lexer.tokenType != null)
    }

    private fun printState(lexer: MyLexer) {
        println("'${lexer.tokenText}' ${lexer.tokenType}") // {${lexer.tokenStart}, ${lexer.tokenEnd}}
    }

}
