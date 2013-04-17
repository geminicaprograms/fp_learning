/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
	def isEmpty: Boolean = false
	
	def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
}