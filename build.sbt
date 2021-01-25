import org.scalajs.linker.interface.ModuleSplitStyle

name := "scalajs-demo-project"

scalaVersion in ThisBuild := "2.13.1"

lazy val commonSettings = Seq(
  organization := "snichele",
  version := "0.1.0",
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-encoding", "UTF-8",
    "-deprecation", // warning and location for usages of deprecated APIs
    "-feature", // warning and location for usages of features that should be imported explicitly
    "-unchecked", // additional warnings where generated code depends on assumptions
    "-Xlint", // recommended additional warnings
    // "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
    // "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
    // "-Ywarn-inaccessible",
    "-Ywarn-dead-code",
    "-language:postfixOps",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:existentials",
    //    "-Xmacro-settings:print-codecs"
  ),
  resolvers += Resolver.jcenterRepo,
  resolvers += "jitpack" at "https://jitpack.io"
)


lazy val scalajsDemoProject = crossProject(JSPlatform, JVMPlatform).in(file(".")).
  settings(commonSettings: _*).
  settings(
    name := "scalajs-demo-project",
    version := "0.1-SNAPSHOT"
  ).jvmSettings(
  libraryDependencies ++=
    Seq(
      "dev.zio" %% "zio" % "1.0.1",
      "dev.zio" %% "zio-streams" % "1.0.1",
      "dev.zio" %% "zio-interop-cats" % "2.1.4.0",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.scalatest" %% "scalatest" % "3.1.2" % "test",
      "org.scalacheck" %% "scalacheck" % "1.14.1" % "test",
      "dev.zio" %% "zio-test" % "1.0.1" % "test",
      "dev.zio" %% "zio-test-sbt" % "1.0.1" % "test"
    ),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
).
  jsSettings(
    libraryDependencies ++=
      Seq(
        "org.scala-js" %%% "scalajs-dom" % "1.0.0",
        "com.lihaoyi" %%% "scalatags" % "0.9.2",
        "io.monix" %%% "monix" % "3.2.2",
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % "2.6.0",
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % "2.6.0",
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % "2.6.0" % "compile-internal",
        "com.github.fdietze.scala-js-d3v4" %%% "scala-js-d3v4" % "6223ea32c4",
        /* ZIO weights too much in js...*/
        /*"dev.zio" %%% "zio" % "1.0.1",
        "org.scala-js" %%% "scalajs-java-time" % "1.0.0",*/
        "org.webjars.npm" % "purecss" % "1.0.0",
        "org.scalatest" %%% "scalatest" % "3.1.2" % "test",
        "org.scalacheck" %%% "scalacheck" % "1.14.3" % "test"
      ),
    artifactPath in fastOptJS in Compile := new java.io.File("./js/assets/js-libs/" + ((moduleName in fastOptJS).value + "-fastopt.js")),
    artifactPath in fullOptJS in Compile := new java.io.File("./js/assets/js-libs/" + ((moduleName in fullOptJS).value + "-fullopt.js")),
    /* beware it clears directory content on each rebuild !*/
    scalaJSLinkerOutputDirectory in fastLinkJS in Compile := new java.io.File("./js/assets/js-libs/app/"),
    scalaJSLinkerOutputDirectory in fullLinkJS in Compile := new java.io.File("./js/assets/js-libs/app/"),
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.ESModule)),
    scalaJSLinkerConfig ~= (_.withModuleSplitStyle(ModuleSplitStyle.FewestModules))
  )


