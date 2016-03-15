name := "MatchDataReadService"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"

mainClass in (Compile, run) := Some("com.img.App")

mainClass in (Compile, packageBin) := Some("com.img.App")