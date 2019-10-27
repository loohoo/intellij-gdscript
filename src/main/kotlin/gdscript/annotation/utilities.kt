package gdscript.annotation

import ScriptLexer
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import gdscript.options.ColorTextAttributeKey
import org.antlr.intellij.adaptor.lexer.TokenIElementType


fun AnnotationHolder.createColorAnnotation(element: PsiElement, color: ColorTextAttributeKey) =
    createInfoAnnotation(element, null).also { it.textAttributes = color.key }!!

fun PsiElement.isIdentifier() =
    isToken(ScriptLexer.IDENTIFIER)

fun PsiElement.isToken(expected: Int) =
    ((this as? LeafPsiElement)?.elementType as? TokenIElementType)?.antlrTokenType == expected