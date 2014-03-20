name := "tt2"

version := "1.0"

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

autoScalaLibrary := false

crossPaths := false

scalaSource in Compile := baseDirectory.value / "src"

javaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "test"

javaSource in Test := baseDirectory.value / "test"