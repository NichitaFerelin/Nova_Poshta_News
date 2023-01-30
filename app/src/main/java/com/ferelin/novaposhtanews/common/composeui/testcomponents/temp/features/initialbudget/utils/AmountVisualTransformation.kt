package com.endava.budgetplanner.features.initialbudget.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat

private const val ONE = 1
private const val TWO = 2
private const val THREE = 3
private const val FOUR = 4
private const val FIVE = 5
private const val SIX = 6
private const val SEVEN = 7
private const val EIGHT = 8

class AmountVisualTransformation : VisualTransformation {
    val String.isValidFormattableAmount get(): Boolean = isNotBlank() && isDigitsOnly() && length <= SEVEN

    private fun formatAmountOrMessage(
        input: String
    ): String = if (input.isValidFormattableAmount) {
        DecimalFormat("#,###,###").format(input.toInt())
    } else {
        input
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = formatAmountOrMessage(text.text)

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                if (originalText.isValidFormattableAmount) {
                    val commas = formattedText.count { it == ',' }
                    return when {
                        offset <= ONE -> offset
                        offset <= THREE -> if (commas >= ONE) offset + ONE else offset
                        offset <= SIX -> if (commas == TWO) offset + TWO else offset + ONE
                        offset <= SEVEN -> if (commas == THREE) offset + THREE else offset + TWO
                        else -> EIGHT
                    }
                }
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (originalText.isValidFormattableAmount) {
                    val commas = formattedText.count { it == ',' }
                    return when (offset) {
                        EIGHT -> offset - TWO
                        SEVEN -> if (commas == ONE) SIX else FIVE
                        SIX -> if (commas == ONE) FIVE else FOUR
                        FIVE -> if (commas == ONE) FOUR else if (commas == TWO) THREE else offset
                        FOUR, THREE -> if (commas >= ONE) offset - ONE else offset
                        TWO -> if (commas == TWO) ONE else offset
                        else -> offset
                    }
                }
                return offset
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping
        )
    }
}
