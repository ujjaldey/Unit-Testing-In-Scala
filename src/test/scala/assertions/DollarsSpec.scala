package assertions

import com.h2.entities.Dollars
import org.scalatest.flatspec.AnyFlatSpec

class DollarsSpec extends AnyFlatSpec {
  behavior of "A Dollar"

  it should "create a correct Dollar object for number 10 as input" in {
    val tenDollars = Dollars(10)
    assert("$10" === tenDollars.toString)
  }

  it should "correctly identify that $10 > $5" in {
    val tenDollars = Dollars(10)
    val fiveDollars = Dollars(5)

    assert(tenDollars > fiveDollars) // because in Dollars class we have extends Ordered[Dollars] and override compare()
  }

  it should "correctly identify that $2 < $10" in {
    val tenDollars = Dollars(10)
    val twoDollars = Dollars(2)

    assert(twoDollars < tenDollars) // because in Dollars class we have extends Ordered[Dollars] and override compare()
  }

  it should "correctly add two Dollars amount" in {
    val tenDollars = Dollars(10)
    val twoDollars = Dollars(2)

    assertResult("$12") {
      (tenDollars + twoDollars).toString
    }
  }

  it should "correctly subtract two Dollars amount" in {
    val tenDollars = Dollars(10)
    val twoDollars = Dollars(2)

    assertResult("$8") {
      (tenDollars - twoDollars).toString
    }
  }

  it should "correctly identify that $4 == $4" in {
    val fourDollars = Dollars(4)

    assertResult(true) {
      fourDollars === fourDollars // both == and === works
    }
  }

  it should "throw an exception when an invalid integer is provided to create Dollars" in {
    assertThrows[ArithmeticException] { // test will fail if we use IllegalArgumentException
      Dollars(10 / 0)
    }
  }

  it should "have every Dollar more than 0" in {
    // even dollars has List.empty, the unit test will return success
    // so we should use assume so that the tests get CANCELLED if the list is empty
    // val dollars: List[Dollars] = List(Dollars(1), Dollars(10), Dollars(10))
    val dollars: List[Dollars] = List.empty

    assume(dollars.nonEmpty, "The dollars coming from API should not be empty")

    dollars foreach { d =>
      assert(d.amount > 0)
    }
  }
}