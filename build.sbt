lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-forms-example""",
    version := "2.2.x",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      guice,
      evolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    ),
    libraryDependencies ++= Seq(
      jdbc
    ),
    libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.7",
    libraryDependencies += "com.h2database" % "h2" % "1.4.192",
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
