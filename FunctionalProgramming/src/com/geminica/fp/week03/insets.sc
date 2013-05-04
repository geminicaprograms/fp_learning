package com.geminica.fp.week03

object insets {
    val t1 = new NonEmpty(3, Empty, Empty)        //> t1  : com.geminica.fp.week03.NonEmpty = {.3.}
    val t2 = t1 incl 4                            //> t2  : com.geminica.fp.week03.IntSet = {.3{.4.}}
    t1 union new NonEmpty(5, Empty, Empty)        //> res0: com.geminica.fp.week03.IntSet = {{.3.}5.}
    t1 union t2                                   //> res1: com.geminica.fp.week03.IntSet = {.3{.4.}}
    
    Empty contains 1                              //> res2: Boolean = false
    t1 contains 3                                 //> res3: Boolean = true
}
