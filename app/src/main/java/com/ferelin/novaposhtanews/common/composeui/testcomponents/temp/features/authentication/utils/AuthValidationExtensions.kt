package com.endava.budgetplanner.features.authentication.utils

const val EMAIL_MAX_LENGTH = 64
const val EMAIL_MIN_LENGTH = 1
const val DOMAIN_MAX_LENGTH = 20
const val DOMAIN_MIN_LENGTH = 1
const val EMAIL_SPECIAL_CHARACTER = '@'

private const val EMAIL_REGEX = "^[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@[A-Z0-9.]+\\.[A-Za-z]+\$"
private const val EMAIL_SUBSTRING_REGEX = "^[A-Za-z0-9@.]{0,64}$"

private const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,32}\$"

fun String.isValidEmail(): Boolean {
    return this.length in EMAIL_MIN_LENGTH..EMAIL_MAX_LENGTH &&
        this.contains(Regex(EMAIL_REGEX, RegexOption.IGNORE_CASE)) &&
        this.substringAfter(EMAIL_SPECIAL_CHARACTER).length in DOMAIN_MIN_LENGTH..DOMAIN_MAX_LENGTH
}

fun String.couldBeSubstringOfEmail(): Boolean {
    return this.contains(Regex(EMAIL_SUBSTRING_REGEX, RegexOption.IGNORE_CASE))
}

fun String.isValidPassword(): Boolean {
    return this.contains(Regex(PASSWORD_REGEX))
}
