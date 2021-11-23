name := "property-based-testing-poc"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.6.1",
  "org.typelevel" %% "cats-effect" % "3.2.8",
  "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,
  "com.disneystreaming" %% "weaver-cats" % "0.7.7" % Test,
  "com.disneystreaming" %% "weaver-scalacheck" % "0.7.7" % Test
)

testFrameworks += new TestFramework("weaver.framework.CatsEffect")
