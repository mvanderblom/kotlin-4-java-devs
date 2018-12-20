package nicestring

val vowels = "aeiou";


fun String.containsForbiddenSubstring(): Boolean {
    return this.contains("bu") || this.contains("ba") || this.contains("be")
}

fun String.containsAtLeastThreeVowels(): Boolean {
    return this.count { c -> vowels.contains(c) } >= 3
}


fun String.containsDoubleLetter(): Boolean {
    return this.withIndex().any { (i, c) -> this.length > i + 1 && this[i + 1] == c }
}

fun String.isNice(): Boolean {
    return listOf(!this.containsForbiddenSubstring(), this.containsAtLeastThreeVowels(), this.containsDoubleLetter())
            .count { it == true } >= 2
}