
//Begin region : commonSettings

lazy val commonSettings = Seq(
  organization := "io.github.naviud",
  scalaVersion := "2.12.6",
  crossPaths := false,
  doc in Compile := target.map(_ / "none").value
)

//End region : commonSettings

//Begin region : module project

lazy val module = (project in file("module"))
  .settings(
    commonSettings,
    name := "log-tracker",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.play" %% "play" % "2.6.12",
      "junit" % "junit" % "4.12" % Test,
      guice
    )
  )

//End region : module project

//Begin region : sample project

lazy val sample = (project in file("sample"))
  .settings(
    commonSettings,
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
    name := "log-tracker-root"
  )
  .aggregate(module, sample)

//End region : root project