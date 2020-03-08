package gdscript.execution.error

import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.util.Key
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase


class ErrorListenerTest : TestCase() {

    fun `test error parsing`() {
        val service = ErrorService()
        val listener = ErrorListener(service)
        listener.onTextAvailable(mockEvent("ERROR: Parse Error: Test."), mockKey())
        listener.onTextAvailable(mockEvent("   At: res://paddle.gd:4."), mockKey())
        val error = service.errors.first()
        assertEquals(error.message, "Test.")
        assertEquals(error.file, "paddle.gd")
        assertEquals(error.lineNumber, "4")
    }

    fun `test error is not duplicated on second occurrence`() {
        val service = ErrorService()
        val listener = ErrorListener(service)
        repeat(2) {
            listener.onTextAvailable(mockEvent("ERROR: Parse Error: Test."), mockKey())
            listener.onTextAvailable(mockEvent("   At: res://paddle.gd:4."), mockKey())
        }
        assertEquals(service.errors.size, 1)
    }

    private fun mockEvent(message: String) =
        mockk<ProcessEvent> {
            every { text } returns message
        }

    private fun mockKey() =
        mockk<Key<*>>()

    /*
        fun `test asdasd`() {
        val listener = OutputListener()
        listener.onTextAvailable(mockedEvent("Godot Engine v3.2.stable.custom_build - https://godotengine.org"), mockedKey())
        mockedEvent("OpenGL ES 3.0 Renderer: Quadro M2000M/PCIe/SSE2")
        mockedEvent("")
        mockedEvent("SCRIPT ERROR: GDScript::reload: Parse Error: The class \"Node\" shadows a native class.")
        mockedEvent("   At: res://paddle.gd:4.")
        mockedEvent("ERROR: emit_signal: Error calling method from signal 'area_entered': 'Area2D(paddle.gd)::_on_area_entered': Method not found..")
        mockedEvent("   At: core/object.cpp:1228.")
    }
     */
}