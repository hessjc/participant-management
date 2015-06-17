import com.github.play2war.plugin._

name := "three-rivers.de.teilnehmermanagement"

version := "1.0-SNAPSHOT"

//war

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    javaCore withSources(),
    cache withSources(),
    javaJdbc withSources(),
    javaEbean withSources(),
    filters withSources(),
    "com.typesafe.play" %% "play-mailer" % "2.4.0",
    "mysql" % "mysql-connector-java" % "5.1.34",
    "be.objectify" %% "deadbolt-java" % "2.3.2" withSources() withJavadoc(),
    "ws.securesocial" %% "securesocial" % "master-SNAPSHOT"
)

resolvers ++= Seq(
   	Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns),
   	Resolver.url("Objectify Play Repository - snapshots", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns),
   	Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/repo"))
)