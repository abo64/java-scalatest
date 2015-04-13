package org.sandbox.user

import java.lang.{Double => JDouble}
import scala.collection.JavaConversions.seqAsJavaList
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.prop.PropertyChecks
import UserGen.userGen
import scala.util.Try

@RunWith(classOf[org.scalatest.junit.JUnitRunner])
class UserStatsSpec extends FlatSpec with PropertyChecks {
  import UserGen._

  behavior of "UserStats"

  it should "return the avg of all user ages" in {
    val tests = Table(
      ("users", "UserStats.avgAge(users)"),
      (Seq(userWithAge(42)), 42),
      (Seq(userWithAge(42), userWithAge(43)), 42.5),
      (Seq(userWithAge(42), userWithAge(43), userWithAge(44)), 43)
    )

    forAll(tests) { (users, expectedAvg) =>
      // note the implicit conversion due to JavaConversions.seqAsJavaList
      // from Scala's Seq to Java's List
      assert(UserStats.avgAge(users) == expectedAvg)
    }
  }

  private def userWithAge(age: Int): User = {
    val user = userGen.sample.get
    user.setAge(age)
    user
  }

  it should "return the avg of all user ages for random collections" in {
    def avgAge(users: Seq[User]): Option[JDouble] =
      if (users.isEmpty) None
      else Some(users.map(_.getAge.toLong).sum / users.size.toDouble) map Double.box

    // note that ScalaCheck can generate arbitrary Seq[User]'s
    // b/c UserGen.userArb is in scope
    forAll("users") { users: List[User] =>
      println(s"users: $users")
      assert(Option(UserStats.avgAge(users)) == avgAge(users))
    }
  }
}
