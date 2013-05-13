package com.geminica.fp.week05

object functions {
  def init[T](xs: List[T]): List[T] = xs match {
	  case List() => throw new Error("init of Empty list")
	  case List(x) => List()
	  case y :: ys => y :: init(ys)
	}                                         //> init: [T](xs: List[T])List[T]
	
	def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
		case List() => ys
		case z :: zs => z :: concat(zs, ys)
		//case z :: zs => concat(zs, z :: ys)
	}                                         //> concat: [T](xs: List[T], ys: List[T])List[T]
	
	def reverse[T](xs: List[T]): List[T] = xs match {
		case List() => xs
		case y :: ys => reverse(ys) ++ List(y)
	}                                         //> reverse: [T](xs: List[T])List[T]
	
	def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop n + 1)
                                                  //> removeAt: [T](n: Int, xs: List[T])List[T]

	def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
		val half = xs.length/2
		if (0 == half) xs
		else {
			def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
				case (Nil, ys) => ys
				case (xs, Nil) => xs
				case (x :: xsRemaining, y :: ysRemaining) => {
					if (ord.lt(x, y)) x :: merge(xsRemaining, ys)
					else y :: merge(xs, ysRemaining)
				}
			}

			val (left, right) = xs splitAt half
			merge(msort(left), msort(right))
		}
	}                                         //> msort: [T](xs: List[T])(implicit ord: Ordering[T])List[T]
	
	def squareList(xs: List[Int]): List[Int] = xs match {
		case Nil => xs
		case x :: xs => x * x :: squareList(xs)
	}                                         //> squareList: (xs: List[Int])List[Int]
	
	def squareMap(xs: List[Int]): List[Int] =
		xs map (x => x * x)               //> squareMap: (xs: List[Int])List[Int]
	
	val list: List[Int] = List(1, 2, 3, 4)    //> list  : List[Int] = List(1, 2, 3, 4)
	init(list)                                //> res0: List[Int] = List(1, 2, 3)
	
	val list2: List[Int] = List(5, 6, 7)      //> list2  : List[Int] = List(5, 6, 7)
	val conList: List[Int] = concat(list, list2)
                                                  //> conList  : List[Int] = List(1, 2, 3, 4, 5, 6, 7)
                                                  
	val revList: List[Int] = reverse(conList) //> revList  : List[Int] = List(7, 6, 5, 4, 3, 2, 1)
	removeAt(2, revList)                      //> res1: List[Int] = List(7, 6, 4, 3, 2, 1)
	val sorted = msort(revList)               //> sorted  : <error> = List(1, 2, 3, 4, 5, 6, 7)
	squareList(sorted)                        //> res2: List[Int] = List(1, 4, 9, 16, 25, 36, 49)
	squareMap(sorted)                         //> res3: List[Int] = List(1, 4, 9, 16, 25, 36, 49)
	
	val sList = List("pigs", "cats", "dogs", "chickens")
                                                  //> sList  : List[String] = List(pigs, cats, dogs, chickens)
	msort(sList)                              //> res4: <error> = List(cats, chickens, dogs, pigs)
}