package scene.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode

class StringNode(node: ASTNode) : ANTLRPsiNode(node), PsiLanguageInjectionHost {

    override fun updateText(text: String): PsiLanguageInjectionHost {
        return this
    }

    override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost> {
        return object : LiteralTextEscaper<StringNode>(this) {

            override fun isOneLine(): Boolean {
                return true
            }

            override fun getOffsetInHost(offsetInDecoded: Int, rangeInsideHost: TextRange): Int {
                return 0
            }

            override fun decode(rangeInsideHost: TextRange, outChars: StringBuilder): Boolean {
                return true
            }

        }
    }

    override fun isValidHost(): Boolean {
        return true
    }

}