package scene

import com.intellij.psi.tree.IElementType
import junit.framework.TestCase


class MyLexerTest : TestCase() {

    fun `test asd`() {
        val lexer = MyLexer(
            types = listOf(
                IElementType("IDENTIFIER", SceneLanguage),
                IElementType("NUMBER", SceneLanguage),
                IElementType("WHITESPACE", SceneLanguage)
            ),
            fallback = IElementType("UNKNOWN", SceneLanguage),
            regex = "(?<IDENTIFIER>[a-zA-Z]+)|(?<NUMBER>[0-9]+)|(?<WHITESPACE>\\s+)".toRegex()
        )
        val code = "name  /// text123"
        println(code)
        lexer.start(code)
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
        lexer.advance()
        printState(lexer)
    }

    private fun printState(lexer: MyLexer) {
        println("{start = ${lexer.tokenStart}, end = ${lexer.tokenEnd}, type = ${lexer.tokenType}, text = '${lexer.tokenText}'}")
    }

}

/*
fun tokenize(language: Language, lexer: org.antlr.v4.runtime.Lexer, code: String): List<Token> {
    @Suppress("DEPRECATION")
    PSIElementTypeFactory.defineLanguageIElementTypes(language, lexer.tokenNames, lexer.ruleNames)
    val adaptor = ANTLRLexerAdaptor(language, lexer)
    adaptor.start(code)
    val tokens = ArrayList<Token>()
    do {
        val type = adaptor.tokenType as? TokenIElementType
            ?: return tokens
        tokens.add(Token(type.antlrTokenType, adaptor.tokenText))
        adaptor.advance()
    } while (true)
}

data class Token(val type: Int, val text: String)
 */