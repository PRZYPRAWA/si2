name := "si-2"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0"
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full)


