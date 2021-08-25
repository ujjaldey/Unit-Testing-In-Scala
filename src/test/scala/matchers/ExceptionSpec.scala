package matchers

import com.h2.services.Currency

class ExceptionSpec extends UnitSpec {
  behavior of "Currency during exception"

  it should "throw an exception when invalid number is provided in the currency string" in {
    a[NumberFormatException] should be thrownBy Currency.stringToCurrency("Two USD")
    // a or an are same
    an[NumberFormatException] should be thrownBy Currency.stringToCurrency("Two USD")
  }

  it should "provide a detailed description of exception" in {
    val execption = the[NumberFormatException] thrownBy Currency.stringToCurrency("Two USD")
    execption.getMessage should include("Two")
  }
}
