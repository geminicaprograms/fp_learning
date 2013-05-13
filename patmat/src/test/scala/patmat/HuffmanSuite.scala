package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {  
    
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Fork(Leaf('c',4), Leaf('d',5), List('c','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("times(\"abcdaab\")") {
    assert(times(string2Chars("abcdaab")) === List(('a', 3), ('b', 2), ('c', 1), ('d', 1)))
  }

  
  test("makeOrderedLeafList for some frequency table") {
//    println(makeOrderedLeafList(times(string2Chars("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."))))
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }
  
  test("singleton cases") {
    assert(singleton(List(Leaf('a', 5))) === true)
    assert(singleton(List(Leaf('a', 5), Leaf('b', 7))) === false)
    assert(singleton(List()) === false)
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    println(combine(leaflist))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("until on leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton, combine)(leaflist) === Fork(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4), List('e', 't', 'x'), 7))
  }
  
  test("create code list") {
    print(createCodeTree(string2Chars("Contrary to popular belief, Lorem Ipsum is not simply random text. " +
    		"It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years " +
    		"old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up " +
    		"one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through " +
    		"the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum " +
    		"comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes " +
    		"of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of " +
    		"ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum " +
    		"dolor sit amet..\", comes from a line in section 1.10.32.")), "", "    ")
    assert(createCodeTree(string2Chars("abcdaab")) ===
      Fork(Leaf('a', 3), Fork(Fork(Leaf('c', 1), Leaf('d', 1), List('c', 'd'), 2), Leaf('b', 2)
          , List('c', 'd', 'b'), 4), List('a', 'c', 'd', 'b'), 7))
  }
  
  test("decode secret") {
      assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t2, encode(t2)("abdddda".toList)) === "abdddda".toList)
    }
  }
  
  test("convert test") {
    new TestTrees {
      assert(convert(t2) === List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1))))
    }
  }
  
  test("mergeCodeTables test") {
    new TestTrees {
      assert(mergeCodeTables(convert(t1), convert(t3)) === List(('c', List(0)), ('d', List(1)), ('a', List(0)), ('b', List(1))))
    }
  }
  
  test("quickEncode test") {
    assert(quickEncode(Huffman.frenchCode)("huffmanestcool".toList) === List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1))
  }
}
