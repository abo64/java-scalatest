package org.sandbox.user

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.concurrent.IntegrationPatience
import org.scalatest.concurrent.ScalaFutures

/**
 * Demonstrates ScalaTest's support for Future (Scala/JavaFutures traits),
 * both Scala's and Java's Future -
 * although the latter is next to useless in everyday programming.
 * The game changes to somehow w/ Java 8 CompletableFuture.
 */
@RunWith(classOf[org.scalatest.junit.JUnitRunner])
class UserDAOSpec extends FlatSpec with ScalaFutures
  with IntegrationPatience // causes whenReady to be "more patient" before giving up
{
  behavior of "UserDAO.findById"

  it should "return a User for a valid id" in {
    val userFuture: Future[User] = findByIdAsync(1)
    whenReady(userFuture) { user =>
      assert(user != null)
    }
  }

  it should "return null for an invalid id" in {
    val userFuture: Future[User] = findByIdAsync(-1)
    whenReady(userFuture) { user =>
      assert(user == null)
    }
  }

  // execute in separate Thread
  private def findByIdAsync(id: Long): Future[User] =
    Future(UserDAO.findById(id))
}