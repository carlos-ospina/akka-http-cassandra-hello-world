name := "akka-hello-world"

version := "1.0"

scalaVersion := "2.12.5"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaV       = "2.5.11"
  val akkahttpV   = "10.1.0"
  val scalaTestV  = "2.5.11"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % scalaTestV % Test,
    "com.typesafe.akka" %% "akka-http"   % akkahttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkahttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkahttpV,
    "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "0.18",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2" % "test",
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0",
    "com.datastax.cassandra" % "cassandra-driver-mapping" % "3.3.0",
    "com.datastax.cassandra" % "cassandra-driver-extras" % "3.3.0" % "optional",
    "com.typesafe" % "config" % "1.3.2",
    "com.github.pureconfig" %% "pureconfig" % "0.9.1"

  )
}