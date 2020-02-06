package scene

import com.intellij.lang.Language
import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import com.intellij.util.ArrayUtil

class MyLexer(
    private val types: List<IElementType> = emptyList(),
    private val fallback: IElementType = ANY_FALLBACK,
    private val regex: Regex = MATCH_EVERYTHING
) : LexerBase() {

    private var buffer: CharSequence = ArrayUtil.EMPTY_CHAR_SEQUENCE
    private var start = 0
    private var end = 0
    private var type: IElementType? = null

    override fun start(buffer: CharSequence, start: Int, end: Int, state: Int) {
        this.buffer = buffer
        this.start = start
        scan()
    }

    override fun advance() {
        start = end
        scan()
    }

    override fun getTokenType() = type

    override fun getBufferSequence() = buffer

    override fun getTokenStart() = start

    override fun getTokenEnd() = end

    override fun getBufferEnd() = buffer.length

    override fun getState() = 0

    private fun scan() {
        if (finished()) {
            type = null
            end = bufferEnd
            return
        }
        val match = findClosestGroupMatch()
        if (end != match.range.first) {
            type = fallback
            end = match.range.first
            return
        }
        for (name in types) {
            val group = match.groups[name.toString()]
            if (group != null) {
                type = name
                end = group.range.last + 1
                return
            }
        }
    }

    private fun findClosestGroupMatch(): MatchResult =
        regex.find(buffer, start)!!

    private fun finished() =
        end >= buffer.length

    private companion object {

        private val MATCH_EVERYTHING = "(.*?)".toRegex()
        private val ANY_FALLBACK = IElementType("NULL", Language.ANY)

    }

}