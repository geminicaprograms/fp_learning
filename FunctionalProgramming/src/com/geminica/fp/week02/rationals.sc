package com.geminica.fp.week02

object rationals {
    val rat = new Rational(1, 2)                  //> rat  : com.geminica.fp.week02.Rational = 1/2
    rat.numerator                                 //> res0: Int = 1
    rat.denominator                               //> res1: Int = 2

    def addRational(r: Rational, s: Rational): Rational =
        new Rational(r.numerator * s.denominator + s.numerator * r.denominator, r.denominator * s.denominator)
                                                  //> addRational: (r: com.geminica.fp.week02.Rational, s: com.geminica.fp.week02.
                                                  //| Rational)com.geminica.fp.week02.Rational
    def makeString(r: Rational) =
        r.numerator + "/" + r.denominator         //> makeString: (r: com.geminica.fp.week02.Rational)String

    makeString(addRational(new Rational(1, 2), new Rational(2, 3)))
                                                  //> res2: String = 7/6

    rat + new Rational(2, 3)                      //> res3: com.geminica.fp.week02.Rational = 7/6
    -rat                                          //> res4: com.geminica.fp.week02.Rational = -1/2

    val x = new Rational(1, 3)                    //> x  : com.geminica.fp.week02.Rational = 1/3
    val y = new Rational(5, 7)                    //> y  : com.geminica.fp.week02.Rational = 5/7
    val z = new Rational(3, 2)                    //> z  : com.geminica.fp.week02.Rational = 3/2

    x - y - z                                     //> res5: com.geminica.fp.week02.Rational = -79/42
    y + y                                         //> res6: com.geminica.fp.week02.Rational = 10/7

    x < y                                         //> res7: Boolean = true
    y < z                                         //> res8: Boolean = true
    x max y                                       //> res9: com.geminica.fp.week02.Rational = 5/7

    x * x + y * y                                 //> res10: com.geminica.fp.week02.Rational = 274/441

    new Rational(2)                               //> res11: com.geminica.fp.week02.Rational = 2/1
}

class Rational(x: Int, y: Int) {
    require(0 != y, "denominator has to be different then 0!!!")

    def this(x: Int) = this(x, 1)

    private def gcd(a: Int, b: Int): Int = if (0 == b) a else gcd(b, a % b)
    private def abs(a: Int): Int = if (0 > a) -a else a
    private val g = abs(gcd(x, y))
    def numerator = x / g
    def denominator = y / g

    def <(p_that: Rational): Boolean =
        numerator * p_that.denominator < p_that.numerator * denominator

    def max(p_that: Rational): Rational = if (this < p_that) p_that else this

    def +(p_that: Rational): Rational =
        new Rational(numerator * p_that.denominator + p_that.numerator * denominator, denominator * p_that.denominator)

    def *(p_that: Rational): Rational =
        new Rational(numerator * p_that.numerator, denominator * p_that.denominator)

    def -(p_that: Rational): Rational =
        this + -p_that

    def unary_- : Rational =
        new Rational(-numerator, denominator)

    override def toString = numerator + "/" + denominator
}