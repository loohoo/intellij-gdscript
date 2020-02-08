import gdscript.ScriptQuoteHandlerTest
import utils.openScene

class SceneInjectorTest : BaseTest() {

    fun `test aaaaa`() {
        environment.openScene("""
            code = "extends Node<caret>"
            """.trimIndent())
        environment.type(ScriptQuoteHandlerTest.BACKSPACE)
    }

}