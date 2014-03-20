name := "tt2"

version := "1.0"

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

autoScalaLibrary := false

crossPaths := false

libraryDependencies += "com.google.guava" % "guava" % "15.0"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

scalaSource in Compile := baseDirectory.value / "src"

javaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "test"

javaSource in Test := baseDirectory.value / "test"