package mastermind

data class Evaluation(var rightPosition: Int, var wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val evaluation = Evaluation(0, 0)

    var secretArray = secret.toCharArray();
    var guessArray = guess.toCharArray();

    // Right position
    for ((index, guessChar) in guess.withIndex()) {
        if (secret.get(index) == guessChar) {
            secretArray[index] = ' '
            guessArray[index] = '_'
            evaluation.rightPosition++

            println("rightPosition: $index, ${secretArray.joinToString()}")
        }
    }

    // Wrong position
    for (guessChar in guessArray) {
        if (secretArray.contains(guessChar)) {
            val indexOf = secretArray.indexOf(guessChar)
            secretArray[indexOf] = ' '
            evaluation.wrongPosition++
            println("wrongPosition: $indexOf, ${secretArray.joinToString()}")
        }
    }

    return evaluation
}

fun main(args: Array<String>) {
    println(evaluateGuess("ABCD", "ABCD")) // 4 0
    println(evaluateGuess("ABCD", "CDBA")) // 0 4
    println(evaluateGuess("ABCD", "ABDC")) // 2 2
    println(evaluateGuess("AABC", "ADFE")) // 1 0
    println(evaluateGuess("AABC", "DEAA")) // 0 2
    println(evaluateGuess("ADFE", "AABC")) // 1 0
}