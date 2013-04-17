/**
 *
 */
package com.geminica.fp.week03

/**
 * @author Devil
 *
 */
trait Planar {
	def height: Int
	def width: Int
	def surface = height * width
}