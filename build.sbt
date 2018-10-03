
lazy val commonSettings = Seq(
  organization := "org.something"
)

lazy val core = project.in(file("core"))
  .settings(commonSettings)
  .settings(
    name := "example-sbt-spacial"
  )

lazy val testCore = project.in(file("test"))
  .settings(commonSettings)
  .settings(
    name := "core-tests",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.6.5"
  )
  .dependsOn(core)
  .testProject

lazy val root = project.aggregate(core)
