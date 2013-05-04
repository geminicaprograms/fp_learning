/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
object Nil extends List[Nothing] {
	def isEmpty: Boolean = true
	
	def head: Nothing = throw new NoSuchElementException("Nil.head")
	
	def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}