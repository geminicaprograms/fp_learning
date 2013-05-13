package com.geminica.fp.week05

object listFun {
  val sList = List("pigs", "cats", "dogs", "chickens")
                                                  //> sList  : List[String] = List(pigs, cats, dogs, chickens)
  val nList = List(2, -5, 7, 3, 5, 1)             //> nList  : List[Int] = List(2, -5, 7, 3, 5, 1)

  nList filter (x => x > 0)                       //> res0: List[Int] = List(2, 7, 3, 5, 1)
  nList filterNot (x => x > 0)                    //> res1: List[Int] = List(-5)
  nList partition (x => x > 0)                    //> res2: (List[Int], List[Int]) = (List(2, 7, 3, 5, 1),List(-5))

  nList takeWhile (x => x > 0)                    //> res3: List[Int] = List(2)
  nList dropWhile (x => x > 0)                    //> res4: List[Int] = List(-5, 7, 3, 5, 1)
  
  nList span (x => x > 0)                         //> res5: (List[Int], List[Int]) = (List(2),List(-5, 7, 3, 5, 1))
  
  def pack[T] (xs: List[T]): List[List[T]] = xs match {
  	case Nil => Nil
  	case x :: xs1 => {
  		val (left, right) = xs span (y => y == x)
  		left :: pack(right)
  	}
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  def encode[T] (xs: List[T]): List[(T, Int)] = xs match {
  	case Nil => Nil
  	case x :: xs1 => {
  		val (left, right) = xs span (y => y == x)
  		(x, left.length) :: encode(right)
  	}
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]
  
  def encodeMap[T] (xs: List[T]): List[(T, Int)] =
  	pack(xs) map (ys => (ys.head, ys.length)) //> encodeMap: [T](xs: List[T])List[(T, Int)]
  
  val letters = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> letters  : List[String] = List(a, a, a, b, c, c, a)
  pack(letters)                                   //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  encode(letters)                                 //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
  encodeMap(letters)                              //> res8: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
}