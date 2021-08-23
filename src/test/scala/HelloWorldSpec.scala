import org.scalatest.flatspec.AnyFlatSpec

class HelloWorldSpec extends AnyFlatSpec {
  behavior of "Hello World"

  it should "starts with 'Hello'" in {
    assert("Hello World".startsWith("Hello"))
  }

  it should "ends with 'World'" in {
    assert("Hello World".endsWith("World"))
  }
}