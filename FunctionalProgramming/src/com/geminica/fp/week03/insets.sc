package com.geminica.fp.week03

object insets {
    val t1 = new NonEmpty(3, Empty, Empty)        //> t1  : com.geminica.fp.week03.NonEmpty = {.3.}
    val t2 = t1 incl 4                            //> t2  : com.geminica.fp.week03.IntSet = {.3{.4.}}
    t1 union new NonEmpty(5, Empty, Empty)        //> res0: com.geminica.fp.week03.IntSet = {{.3.}5.}
    t1 union t2                                   //> res1: com.geminica.fp.week03.IntSet = {.3{.4.}}
    
    Empty contains 1                              //> res2: Boolean = false
    t1 contains 3                                 //> res3: Boolean = true
}

abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
}

object Empty extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
    def union(p_other: IntSet): IntSet = p_other
    
    override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

    def contains(x: Int): Boolean =
        if (x < elem) left contains x
        else if (x > elem) right contains x
        else true

    def incl(x: Int): IntSet =
        if (x < elem) new NonEmpty(elem, left incl x, right)
        else if (x > elem) new NonEmpty(elem, left, right incl x)
        else this

		def union(p_other: IntSet): IntSet =
			((left union right) union p_other) incl elem

    override def toString = "{" + left + elem + right + "}"
}