package com.geminica.fp.week01

object session {

    def abs(x: Double): Double = if (x < 0) -x else x
                                                  //> abs: (x: Double)Double
    def sqrt(x: Double): Double = {
        def sqrtIter(guess: Double): Double =
            if (isGoodEnough(guess)) guess
            else sqrtIter(improve(guess))

        def isGoodEnough(guess: Double): Boolean =
            abs(guess * guess - x) / x < 0.001

        def improve(guess: Double): Double =
            (guess + x / guess) / 2

        sqrtIter(1.0)
    }                                             //> sqrt: (x: Double)Double

    sqrt(2)                                       //> res0: Double = 1.4142156862745097
    sqrt(4)                                       //> res1: Double = 2.000609756097561

    sqrt(1e-6)                                    //> res2: Double = 0.0010000001533016628
    sqrt(1e60)                                    //> res3: Double = 1.0000788456669446E30

    def factorial(n: Int): Int = {
        def loop(acc: Int, n: Int): Int =
        	if(0 == n) acc
        	else loop(acc * n, n-1)
        	
       	loop(1, n)
    }                                             //> factorial: (n: Int)Int

    factorial(5)                                  //> res4: Int = 120
}