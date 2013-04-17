/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
trait List[T] {
	def isEmpty: Boolean
	def head: T
	def tail: List[T]
}