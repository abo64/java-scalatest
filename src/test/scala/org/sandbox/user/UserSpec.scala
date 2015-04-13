package org.sandbox.user

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec
import org.scalatest.GivenWhenThen

@RunWith(classOf[org.scalatest.junit.JUnitRunner])
class UserSpec extends FlatSpec with BeforeAndAfterEach with GivenWhenThen {

  var user: User = _

  override def beforeEach = {
    // note that Scala numbers are automagically converted to their resp. Java types
    user = new User(666, "Eric", "Cartman", "eric.cartman@southpark.org", 10)
  }

  behavior of "User.setAge"

  it should "throw an IllegalArgumentException if age < 0" in {
    intercept[IllegalArgumentException] {
      user.setAge(-1)
    }
  }

  it should "set the age" in {
    Given("a user")
    val stan = new User(999, "Stan", "Marsh", "stan.marsh@southpark.org", 10)

    When("the age is set to 11")
    stan.setAge(11)

    Then("the new age should be 11")
    assert(stan.getAge() == 11)

    And("all the other fields should remain the same")
    assert(stan == new User(999, "Stan", "Marsh", "stan.marsh@southpark.org", 11))
    info("Exciting!")
  }
}
