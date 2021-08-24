package assertions

import com.h2.entities.Email
import org.scalatest.flatspec.AnyFlatSpec

class EmailSpec extends AnyFlatSpec {
  behavior of "An Email"

  it should "return an Email object for a valid String" in {
    val email = Email("johndoe@gmail.com")
    assert(email.localPart === "johndoe", "expected localPart to be 'johndoe'")
    assert(email.domain === "gmail.com")

    //    fail("failing the test intentionally")
  }

  it should "return another Email object for another valid String" in {
    assertResult("another.email", "expected localPart is 'another.email'") {
      Email("another.email@gmail.com").localPart
    }
  }

  // alternate way of writing
  it.should("throw an exception when an email does not contain '@' symbol").in {
    //assertThrow does not have 2nd arg to pass the failure clue as above
    withClue("expected exception since email does not have '@' symbol") {
      assertThrows[IllegalArgumentException] {
        Email("gmail.com")
      }
    }
  }

  it.should("throw an exception when an email contains more than one '@' symbol").in {
    assertThrows[IllegalArgumentException] {
      Email("john@doe@gmail.com")
    }
  }

  it should "intercept the correct error message when an email does not contain '@' symbol" in {
    val exception = intercept[IllegalArgumentException] {
      Email("gmail.com")
    }

    assert(exception.isInstanceOf[IllegalArgumentException])
    assert(exception.getMessage.contains("does not contain '@' symbol"))
  }

  it should "intercept the correct error message when an email contains more than one '@' symbol" in {
    val exception = intercept[IllegalArgumentException] {
      Email("john@doe@gmail.com")
    }

    assert(exception.isInstanceOf[IllegalArgumentException])
    assert(exception.getMessage.contains("should not contain '@' symbol more than once"))
  }

  ignore should "ignore the test" in {
    val email = Email("johndoe@gmail.com")
    assert(email.localPart === "johndoe")
    assert(email.domain === "gmail.com")
  }
}
