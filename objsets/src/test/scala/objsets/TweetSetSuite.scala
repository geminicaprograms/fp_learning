package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
    trait TestSets {
        val set1 = new Empty
        val set2 = set1.incl(new Tweet("a", "a body", 20))
        val set3 = set2.incl(new Tweet("b", "b body", 20))
        val c = new Tweet("c", "c body", 7)
        val d = new Tweet("d", "d body", 9)
        val set4c = set3.incl(c)
        val set4d = set3.incl(d)
        val set5 = set4c.incl(d)
        val set6 = set1.incl(new Tweet("me", "google bum android", 300))
    }

    def asSet(tweets: TweetSet): Set[Tweet] = {
        var res = Set[Tweet]()
        tweets.foreach(res += _)
        res
    }

    def size(set: TweetSet): Int = asSet(set).size

    test("filter: on empty set") {
        new TestSets {
            assert(size(set1.filter(tw => tw.user == "a")) === 0)
        }
    }

    test("filter: a on set5") {
        new TestSets {
            assert(size(set5.filter(tw => tw.user == "a")) === 1)
        }
    }

    test("filter: 20 on set5") {
        new TestSets {
            assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
        }
    }

    test("union: set4c and set4d") {
        new TestSets {
            assert(size(set4c.union(set4d)) === 4)
        }
    }

    test("union: with empty set (1)") {
        new TestSets {
            assert(size(set5.union(set1)) === 4)
        }
    }

    test("union: with empty set (2)") {
        new TestSets {
            assert(size(set1.union(set5)) === 4)
        }
    }

    test("union: advanced") {
        new TestSets {
            var nId: Int = 0
            var tSet: TweetSet = new Empty()
            while(50000 > nId) {
                nId += 1
                tSet = tSet.incl(new Tweet("user", "whatever " + nId, nId))
                tSet = tSet.incl(new Tweet("user", nId + " whatever", nId))
            }
            
//            println("tSet")
//            tSet foreach println
            
//            println("union")
            val uSet = tSet union set5
//            (uSet) foreach println
            assert(size(uSet) === 100004)
        }
    }
    
    test("descending: set5") {
        new TestSets {
            val trends = set5.descendingByRetweet
            assert(!trends.isEmpty)
            assert(trends.head.user == "a" || trends.head.user == "b")

            GoogleVsApple.google foreach println
            
            println(size(TweetReader.tweetSets.head))
            val filtered = TweetReader.tweetSets.head.filter(x => x.text.contains("Kindle"))
            println(size(filtered))
            filtered foreach println

            set6.filter(x => {
                def recContains(x: Tweet, list: List[String]): Boolean =
                    if (list.isEmpty) false
                    else x.text.contains(list.head) || recContains(x, list.tail)
                recContains(x, GoogleVsApple.google)
            }) foreach println
        }
    }
}
