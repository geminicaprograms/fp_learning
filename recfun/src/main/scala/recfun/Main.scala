package recfun
import common._

object Main {
    def main(args: Array[String]) {
        println("Pascal's Triangle")
        for (row <- 0 to 10) {
            for (col <- 0 to row)
                print(pascal(col, row) + " ")
            println()
        }
    }

    /**
     * Exercise 1
     */
    def pascal(c: Int, r: Int): Int = {
        if ((0 == c) || (0 == r) || (c == r)) 1
        else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }

    /**
     * Exercise 2
     */
    def balance(chars: List[Char]): Boolean = {

        def balanced(p_openCount: Int, chars: List[Char]): Boolean = {
            if (0 > p_openCount) false
            else if (chars.isEmpty) 0 == p_openCount
            else {
                val currentChar: Char = chars.head
                if (')' == currentChar)
                    balanced(p_openCount - 1, chars.tail)
                else if ('(' == currentChar)
                    balanced(p_openCount + 1, chars.tail)
                else
                    balanced(p_openCount, chars.tail)
            }
        }

        balanced(0, chars)
    }

    /**
     * Exercise 3
     */
    def countChange(money: Int, coins: List[Int]): Int = {
        
        def checkCombination(p_destMoney: Int, p_coins: List[Int]): Int = {
            if ((0 > p_destMoney) || (p_coins.isEmpty)) {
	            0
            }
	        else if (0 == p_destMoney) {
	            1
	        }
	        else {
	            checkCombination(p_destMoney - p_coins.head, p_coins) +
	            checkCombination(p_destMoney, p_coins.tail)
	        }
        }
        
    
        if ((0 > money) || (coins.isEmpty))
            0
        else
            checkCombination(money - coins.head, coins) + countChange(money, coins.tail)
    }
}
