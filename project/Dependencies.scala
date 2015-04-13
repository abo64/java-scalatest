import sbt._

object Version {
  val scala                = "2.11.6"
  val scalaMock            = "3.2.1"
  val scalaTest            = "2.2.4"
  val scalaCheck           = "1.12.1"
}

object Library {
  val scalaMock            = "org.scalamock"       %% "scalamock-scalatest-support"    % Version.scalaMock
  val scalaTest            = "org.scalatest"       %% "scalatest"                      % Version.scalaTest
  val scalaCheck           = "org.scalacheck"      %% "scalacheck"                     % Version.scalaCheck
}

