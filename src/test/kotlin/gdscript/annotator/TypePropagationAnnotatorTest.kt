package gdscript.annotator

import gdscript.BaseTest
import uitlities.openCode
import uitlities.checkInfoHighlighting

class TypePropagationAnnotatorTest : BaseTest() {

    fun `test asdasd`() {
        environment.openCode("x = 1 + true")
        environment.checkHighlighting()
    }

}