/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
trait List[+T] {
	def isEmpty: Boolean
	def head: T
	def tail: List[T]
	def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

object List {
    def apply[T](x: T, list: List[T]): List[T] = new Cons(x, list)
	def apply[T](x1: T, x2: T): List[T] = new Cons(x1, apply(x2))
	def apply[T](x: T): List[T] = new Cons(x, Nil)
	def apply[T]() = Nil
}