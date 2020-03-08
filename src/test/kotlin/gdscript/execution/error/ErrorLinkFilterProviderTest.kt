package gdscript.execution.error

import junit.framework.TestCase

class ErrorLinkFilterProviderTest : TestCase() {


    fun `test asd`() {
        //    At: res://paddle.gd:4.

        val filter = ErrorLinkFilterProvider()
        filter.getDefaultFilters()
    }


}