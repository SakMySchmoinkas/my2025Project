package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.IdentifierFactory

@Service
class CardService {
    private val identifierFactory: IdentifierFactory = IdentifierFactory()

    private val cards: List<Card> = generateCards()

    companion object {
        const val WORDS_IN_CARD = 4

        val cardsAmount = words.size / WORDS_IN_CARD
    }

    private fun List<String>.toWords(): List<Word> {
        return this.map { Word(it) }
    }

    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        val chunks = shuffledWords.chunked(WORDS_IN_CARD)

        val cardsToGenerate = chunks.take(cardsAmount)

        return cardsToGenerate.map { chunk ->
            val id = identifierFactory.uniqueIdentifier()
            Card(id, chunk.toWords())
        }
    }

    fun getCardByIndex(index: Int): Card {
        if (index < 0 || index >= cards.size) {
            throw IndexOutOfBoundsException("Card not found for index: $index")
        }
        return cards[index]
    }
}