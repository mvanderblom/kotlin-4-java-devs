package rationals

import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {

    fun normalize(): Rational {
        val gcd = numerator.gcd(denominator)

        var newNumerator = numerator.abs() / gcd
        val newDenominator = denominator.abs() / gcd

        if ((numerator * denominator) < BigInteger.ZERO) {
            newNumerator = newNumerator.negate();
        }

        return Rational(newNumerator, newDenominator)
    }

    infix fun withSameDenominatorAs(other: Rational): Pair<Rational, Rational> {
        val newDenom = denominator * other.denominator
        return Rational(numerator * other.denominator, newDenom) to Rational(other.numerator * denominator, newDenom)
    }

    operator fun plus(other: Rational): Rational {
        val (left, right) = this withSameDenominatorAs other
        return Rational(left.numerator + right.numerator, right.denominator)
    }

    operator fun minus(other: Rational): Rational {
        val (left, right) = this withSameDenominatorAs other
        return Rational(left.numerator - right.numerator, right.denominator)
    }

    operator fun unaryMinus(): Rational {
        return Rational(-numerator, denominator);
    }

    operator fun times(other: Rational): Rational {
        val (left, right) = this withSameDenominatorAs other
        return Rational(left.numerator * right.numerator, left.denominator * right.denominator).normalize()
    }

    operator fun div(other: Rational): Rational {
        val (left, right) = this withSameDenominatorAs other
        return Rational(left.numerator, right.numerator).normalize()
    }

    override operator fun compareTo(other: Rational): Int {
        val (left, right) = withSameDenominatorAs(other)

        return left.numerator.compareTo(right.numerator)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Rational) {
            val normalizedA = this.normalize()
            val normalizedB = other.normalize()
            return normalizedA.numerator.equals(normalizedB.numerator) && normalizedA.denominator.equals(normalizedB.denominator)
        }
        return false;
    }

    override fun toString(): String {
        val normalizedRational = this.normalize()
        var result = normalizedRational.numerator.toString()

        if (normalizedRational.denominator != BigInteger.ONE) {
            result += "/" + normalizedRational.denominator.toString()
        }
        return result
    }
}


infix fun BigInteger.divBy(denominator: BigInteger): Rational {
    return Rational(this, denominator)
}

infix fun Int.divBy(denominator: Int): Rational {
    return Rational(this.toBigInteger(), denominator.toBigInteger())
}

infix fun Long.divBy(denominator: Long): Rational {
    return Rational(this.toBigInteger(), denominator.toBigInteger())
}

fun String.toRational(): Rational {
    val split = this.split("/")
    if (split.size > 1)
        return Rational(split[0].toBigInteger(), split[1].toBigInteger())
    return Rational(split[0].toBigInteger(), 1.toBigInteger())
}

fun main(args: Array<String>) {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}