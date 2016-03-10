name := """bootstrapForm"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "com.typesafe.play"   %%   "play-slick"              %   "1.1.1",
  "com.h2database"    % 	   "h2"                    %   "1.4.187" ,
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P24-B3-SNAPSHOT",
  "ch.qos.logback"       %     "logback-classic"          %      "1.1.3",
  "org.scalatest"        %%    "scalatest"    	      %      "2.2.5"     %    "test",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "com.typesafe.slick"   %%    "slick"            	      %      "3.1.1"

)

javaOptions in Test += "-Dconfig.file=conf/test.conf"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
