package scene

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class SceneHighlighter : SyntaxHighlighterBase() {

    override fun getTokenHighlights(element: IElementType): Array<TextAttributesKey> =
        emptyArray()

    override fun getHighlightingLexer(): Lexer =
        MyLexer()

}
