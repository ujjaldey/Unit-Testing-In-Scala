package fixtures

import com.h2.entities.Customer
import com.h2.services.CustomerService
import matchers.UnitSpec
import org.scalatest.BeforeAndAfterAll

class BeforeAndAfterAllSpec extends UnitSpec with BeforeAndAfterAll {
  var customers: List[Customer] = List.empty

  override protected def beforeAll(): Unit = {
    val service = new CustomerService {}
    val johnId = service.createNewCustomer("John", "Doe", "doe@google.com", "1985/02/12")
    val amyId = service.createNewCustomer("Amy", "Williams", "amy@google.com", "1982/12/10")

    customers = List(johnId, amyId).map(id => service.getCustomer(id).get)
    info("BeforeAndAfterAllSpec: Customer Created, Starting Tests")
  }

  override protected def afterAll(): Unit = {
    info("BeforeAndAfterAllSpec: All Tests Completed, Cleaning up memory")
    customers = List.empty
  }

  behavior of "BeforeAndAfterAllSpec"
  it should "report 2 existing customers available" in {
    customers should have size 2
  }

  it should "correctly match that all customers have email from 'google'" in {
    customers.map(c =>
      c.email.toString should include("google")
    )
  }
}
