
//Begin region : commonSettings

lazy val commonSettings = Seq(
  organization := "io.github.naviud",
  organizationName := "naviud",
  organizationHomepage := Some(url("https://naviud.github.io/")),
  scalaVersion := "2.12.6",
  crossPaths := false,
  doc in Compile := target.map(_ / "none").value,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/naviud/playframework-log-tracker"),
      "scm:git@github.com:naviud/playframework-log-tracker.git"
    )
  ),
  developers := List(
    Developer(
      id    = "naviud",
      name  = "Udayanga Silva",
      email = "udayanga.navindra@gmail.com",
      url   = url("https://github.com/naviud")
    )
  ),
  description := "LogTracker is a logger module done for Play framework which prepends a unique UUID(tracker id) for the log messages that are generated for a particular request. Tracker id can be passed from a HTTP header or will be selected randomly when a request is initiated.",
  licenses := List("MIT" -> new URL("https://raw.githubusercontent.com/naviud/playframework-log-tracker/master/LICENSE")),
  homepage := Some(url("https://naviud.github.io/playframework-log-tracker/")),
  pomIncludeRepository := { _ => false },
  publishMavenStyle := true,
  publishArtifact in Test := false,
  (for {
    username <- Option(System.getenv().get("SONATYPE_USERNAME"))
    password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
  } yield
    credentials += Credentials(
      "Sonatype Nexus Repository Manager",
      "oss.sonatype.org",
      username,
      password)
    ).getOrElse(credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")),
    pgpPublicRing := file("./travis/local.pubring.asc"),
    pgpSecretRing := file("./travis/local.secring.asc")
)


//End region : commonSettings

//Begin region : module project

lazy val module = (project in file("module"))
  .settings(
    commonSettings,
    publishArtifact := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    name := "log-tracker",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.play" %% "play" % "2.6.12",
      "junit" % "junit" % "4.12" % Test,
      "org.mockito" % "mockito-all" % "1.10.19",
      "org.powermock" % "powermock-mockito-release-full" % "1.6.4",
      guice
    )
  )

//End region : module project

//Begin region : sample project

lazy val sample = (project in file("sample"))
  .settings(
    commonSettings,
    publish := {},
    publishLocal := {},
    publishArtifact := false,
    publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo"))),
    name := "log-tracker-sample",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      guice
    )
  )
  .enablePlugins(PlayJava)
  .dependsOn(module)

//End region : sample project

//Begin region : root project

lazy val root = (project in file("."))
  .settings(
    name := "log-tracker-root",
    publish := {},
    publishLocal := {},
    publishArtifact := false,
    publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo"))),
  )
  .aggregate(module, sample)

//End region : root project