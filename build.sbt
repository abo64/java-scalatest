name := """java-scalatest"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  Library.scalaTest       % "test",
  Library.scalaMock       % "test",
  Library.scalaCheck      % "test",
  "junit" % "junit" % "4.12" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

EclipseKeys.withSource := true

