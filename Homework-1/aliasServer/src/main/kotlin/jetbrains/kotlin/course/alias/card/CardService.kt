package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.words // Import the words list
import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.IdentifierFactory

@Service
class CardService {
    // Add a property identifierFactory to generate identifiers for each card
    private val identifierFactory: IdentifierFactory = IdentifierFactory()

    // Add a property cards that stores a list of cards, initialized by generateCards
    private val cards: List<Card> = generateCards()

    companion object {
        // Constant for the number of words in a card
        const val WORDS_IN_CARD = 4

        // Calculate the possible number of cards (cardsAmount)
        val cardsAmount = words.size / WORDS_IN_CARD
    }

    // Extension function for List<String> to convert each element to a Word
    private fun List<String>.toWords(): List<Word> {
        return this.map { Word(it) }
    }

    // Method to generate cards
    private fun generateCards(): List<Card> {
        // Shuffle the words list and split it into chunks
        val shuffledWords = words.shuffled() // Use the imported words list
        val chunks = shuffledWords.chunked(WORDS_IN_CARD) // Divide into chunks of 4 words each

        // Ensure we only take the number of cards we need (cardsAmount)
        val cardsToGenerate = chunks.take(cardsAmount) // Take the required number of chunks

        // Generate a new Card for each chunk
        return cardsToGenerate.map { chunk ->
            val id = identifierFactory.uniqueIdentifier() // Generate a unique identifier for the card
            Card(id, chunk.toWords()) // Create a Card with the unique ID and words
        }
    }

    // Method to get a card by its index
    fun getCardByIndex(index: Int): Card {
        // Check if the index is valid, otherwise throw an error
        if (index < 0 || index >= cards.size) {
            throw IndexOutOfBoundsException("Card not found for index: $index")
        }
        return cards[index] // Return the card at the specified index
    }
}