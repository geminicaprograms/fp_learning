package com.geminica.fp.week03

import com.geminica.fp.week01._
import com.geminica.fp.week02.{ Rational }
import com.geminica.fp.week03.Hello

object scratch {
    new Rational(1, 2)                            //> res0: com.geminica.fp.week02.Rational = 1/2

    def error(msg: String) = throw new Error(msg) //> error: (msg: String)Nothing
    val x = null                                  //> x  : Null = null
    val y: String = x                             //> y  : String = null
    //    error("error test!!!")

    if (true) 1 else false                        //> res1: AnyVal = 1

    def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)com.geminica.fp.week03.Cons[T]
    val intS = singleton[Int](1)                  //> intS  : com.geminica.fp.week03.Cons[Int] = com.geminica.fp.week03.Cons@76eb2
                                                  //| 35
    singleton(true)                               //> res2: com.geminica.fp.week03.Cons[Boolean] = com.geminica.fp.week03.Cons@747
                                                  //| 5b962
   	def nth[T](n: Int, list: List[T]): T =
   		if ((0 > n) || (true == list.isEmpty))
   			throw new IndexOutOfBoundsException("out of given list range")
      else if (0 == n)
      	list.head
      else
      	nth(n-1, list.tail)                       //> nth: [T](n: Int, list: com.geminica.fp.week03.List[T])T
      	
    nth(0, intS)                                  //> res3: Int = 1
    
    List(1,2)                                     //> res4: com.geminica.fp.week03.List[Int] = com.geminica.fp.week03.Cons@400bdd7
                                                  //| f
}