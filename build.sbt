name := "trainscheduler"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.3"