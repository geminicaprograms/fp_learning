/**
 *
 */
package com.geminica.fp.week02

/**
 * @author Devil
 *
 */
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