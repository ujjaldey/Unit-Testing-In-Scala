package matchers

import com.h2.services.CustomerService

class StringSpec extends UnitSpec {
  val customerService: CustomerService = new CustomerService {}

  behavior of "Customer Service for Strings"

  it should "correctly match the customer email starting with first name" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should startWith(first.toLowerCase())
  }

  it should "correctly match the customer email ending with '.com'" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should endWith(".com")
  }

  it should "correctly match the customer email including '@'" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should include("@")
  }

  it should "correctly match the customer dateOfBirth as fullyMatch regular expression" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.dateOfBirth.toString should fullyMatch regex """[0-9]{4}-[0-9]{2}-[0-9]{2}"""
  }

  it should "correctly match the customer email as regular expression" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should include regex "[a-z]+[@.]com"
  }
}
