package org.sandbox.user

import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbLong
import org.scalacheck.Gen

object UserGen {

  private val nonEmptyStringGen: Gen[String] =
//    Gen.choose(1, 10) flatMap { size => Gen.listOfN(size, Gen.alphaNumChar) map (_.mkString) }
    // for-comprehensions are usually easier to read and write
    for {
      size <- Gen.choose(1, 10)
      chars <- Gen.listOfN(size, Gen.alphaNumChar)
    } yield chars.mkString

  private val emailGen: Gen[String] = {
    val topLevelDomainGen = Gen.oneOf("org", "com", "de", "pl")
    for {
      localPart <- nonEmptyStringGen
      hostName <- nonEmptyStringGen
      topLevelDomain <- topLevelDomainGen
    } yield s"$localPart@$hostName.$topLevelDomain"
  }

  private val ageGen: Gen[Int] = //Gen.choose(0, 120)
    Gen.chooseNum(0, Int.MaxValue)
  private val firstNameGen = Gen.oneOf("Eric", "Stan", "Kenny", "Butters", "Kyle")
  private val lastNameGen = Gen.oneOf("Cartman", "Marsh", "McCormick", "Stotch", "Broflovski")

  val userGen: Gen[User] =
    for {
      id <- arbLong.arbitrary
      firstName <- firstNameGen
      lastName <- lastNameGen
      email <- emailGen
      age <- ageGen
    } yield new User(id, firstName, lastName, email, age)

  implicit val userArb: Arbitrary[User] = Arbitrary(userGen)
}
