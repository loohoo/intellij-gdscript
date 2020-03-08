package gdscript.execution.error

class ErrorService {

    val errors: Set<ErrorOccurrence> =
        mutableSetOf()

    fun submitError(error: ErrorOccurrence) {
        errors as MutableSet<ErrorOccurrence> += error
    }

}