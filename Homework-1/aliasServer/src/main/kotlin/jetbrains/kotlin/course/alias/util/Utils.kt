package jetbrains.kotlin.course.alias.util

typealias Identifier = Int

class IdentifierFactory {
    // This will store the last unique identifier number.
    private var counter: Int = 0

    // This function will return the next unique identifier
    fun uniqueIdentifier(): Identifier {
        return ++counter // Increment counter and return the new value
    }
}