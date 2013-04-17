/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
class Nil[T] extends List[T] {
	def isEmpty: Boolean = true
	
	def head: Nothing = throw new NoSuchElementException("Nil.head")
	
	def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}