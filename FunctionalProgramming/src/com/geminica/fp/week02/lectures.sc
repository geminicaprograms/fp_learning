package com.geminica.fp.week02

object lectures {
    def sum(f: Int => Int)(a: Int, b: Int): Int = {
        def loop(a: Int, acc: Int): Int = {
            if (a > b) acc
            else loop(a + 1, acc + f(a))
        }
        loop(a, 0)
    }                                             //> sum: (f: Int => Int)(a: Int, b: Int)Int
    
    sum(x => x * x)(1, 5)                         //> res0: Int = 55
    
    def mapReduce(f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b: Int): Int = {
    	if (a > b) unit
    	else combine(f(a), mapReduce(f, combine, unit)(a + 1, b))
    }                                             //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b:
                                                  //|  Int)Int
    
    def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(x => x, (x, y) => x * y, 1)(a, b)
                                                  //> product: (f: Int => Int)(a: Int, b: Int)Int
    
    product(x => x)(1, 3)                         //> res1: Int = 6
    
    def fact(n: Int) = product(x => x)(1, n)      //> fact: (n: Int)Int
    fact(3)                                       //> res2: Int = 6
    
    
}