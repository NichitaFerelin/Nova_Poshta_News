package com.endava.budgetplanner.features.initialbudget.utils

private const val MAX_INPUT_LENGTH = 7
private const val NUMBER_REGEX_PATTERN: String = "([0-9]+)"
private const val MAX_INPUT_VALUE = 1_000_000

private val numberValidator = Regex(NUMBER_REGEX_PATTERN)

fun inputValidator(input: String): Boolean =
    if (input.isNotEmpty()) {
        if (numberValidator.matches(input)) {
            input.length <= MAX_INPUT_LENGTH && input.toInt() <= MAX_INPUT_VALUE && input.toInt() > 0
        } else {
            false
        }
    } else {
        true
    }
