package rationals

import org.junit.Assert
import org.junit.Test

class TestRationals {
    @Test
    fun test1Sum() {
        val sum: Rational = (1 divBy 2) + (1 divBy 3)
        Assert.assertEquals("Wrong result for sum", sum, 5 divBy 6)
    }

    @Test
    fun test2Difference() {
        val difference: Rational = (1 divBy 2) - (1 divBy 3)
        Assert.assertEquals("Wrong result for difference", difference, 1 divBy 6)
    }

    @Test
    fun test3Product() {
        val product: Rational = (1 divBy 2) * (1 divBy 3)
        Assert.assertEquals("Wrong result for product", product, 1 divBy 6)
    }

    @Test
    fun test4Quotient() {
        val quotient: Rational = (1 divBy 2) / (1 divBy 3)
        Assert.assertEquals("Wrong result for quotient", quotient, 3 divBy 2)
    }

    @Test
    fun test5Negation() {
        val negation: Rational = -(1 divBy 2)
        Assert.assertEquals("Wrong result for negation", negation, -1 divBy 2)
    }

    @Test
    fun test6Integer() {
        Assert.assertEquals("Wrong string representation for integer number",
                "2", (2 divBy 1).toString())
    }

    @Test
    fun test7NormalizedForm() {
        Assert.assertEquals("13/122", "117/1098".toRational().toString())
        Assert.assertEquals("-1/2", (-2 divBy 4).toString())
        Assert.assertEquals("461/771", "-578136305229133309744/-966904753430936619984".toRational().toString())
        Assert.assertEquals("-31/541", "31/-541".toRational().toString())
        Assert.assertEquals("17617266896778903272923516079952426936739/884359508704835805965897828865092484822",
                ("828099487587993325537/44002379163849686934".toRational() + "597728771407450572129/542645811175759848891".toRational()).toString())
    }

    @Test
    fun test8Comparison() {
        Assert.assertTrue("Wrong result for comparison", (1 divBy 2) < (2 divBy 3))
    }

    @Test
    fun test9InRange() {
        Assert.assertTrue("Wrong result for checking belonging to a range",
                (1 divBy 2) in (1 divBy 3)..(2 divBy 3))
    }

    @Test
    fun test10Long() {
        Assert.assertEquals("Wrong result for normalization of '2000000000L divBy 4000000000L'",
                2000000000L divBy 4000000000L, 1 divBy 2)
    }

    @Test
    fun test11BigInteger() {
        Assert.assertEquals("Wrong result for normalization of\n" +
                "\"912016490186296920119201192141970416029\".toBigInteger() divBy\n" +
                "\"1824032980372593840238402384283940832058\".toBigInteger()",
                "912016490186296920119201192141970416029".toBigInteger() divBy
                        "1824032980372593840238402384283940832058".toBigInteger(), 1 divBy 2)
    }
}