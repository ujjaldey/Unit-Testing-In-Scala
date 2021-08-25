package matchers

import com.h2.services.CustomerService

import java.util.UUID

class LengthAndSizeSpec extends UnitSpec {
  val customerService: CustomerService = new CustomerService {}

  behavior of "CustomerService for length"

  it should "return correct length of customer's first and last name" in {
    val (first, last, email, dateOfBirth) = ("John", "Doe", "john.doe@gmail.com", "2000/01/01")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.first should have length first.length
    customer.last should have length last.length
  }

  behavior of "CustomerService for size"

  it should "return correct size for number of customers created" in {
    val newCustomers: Seq[(String, String, String, String)] = List(
      ("John", "Doe", "john.doe@gmail.com", "2000/01/01"),
      ("Adam", "Eves", "adam.eves@gmail.com", "1980/12/31")
    )

    val customerIds: Seq[UUID] = newCustomers.map(customer =>
      customerService.createNewCustomer(customer._1, customer._2, customer._3, customer._4))

    customerIds should have size 2
  }
}
