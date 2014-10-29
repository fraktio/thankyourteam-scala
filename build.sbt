name := """ThankYourTeam"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.google.inject" % "guice" % "3.0",
  "com.tzavellas" % "sse-guice" % "0.7.1",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "postgresql" % "postgresql" % "9.1-901.jdbc4"
)