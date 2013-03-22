// Main settings

name := "dsname-main"

version in ThisBuild := "0.1.0-SNAPSHOT"

organization in ThisBuild := "org.bitbucket.inkytonik.dsname"

// Scala compiler settings

scalaVersion in ThisBuild := "2.10.0"

scalacOptions in ThisBuild := Seq ("-deprecation", "-unchecked")

scalacOptions in ThisBuild in Compile <<= (scalaVersion, scalacOptions) map {
    (version, options) =>
        val versionOptions =
            if (version.startsWith ("2.10"))
                Seq ("-feature")
            else
                Seq ()
        options ++ versionOptions
}

scalacOptions in ThisBuild in Test <<= (scalaVersion, scalacOptions) map {
    (version, options) =>
        val versionOptions =
            if (version.startsWith ("2.10"))
                Seq ("-feature")
            else
                Seq ()
        options ++ versionOptions
}

// Dependencies

libraryDependencies in ThisBuild <++= scalaVersion {
    version =>
        Seq (
            "org.scala-lang" % "scala-reflect" % version
        )
}

// Interactive settings

logLevel in ThisBuild := Level.Info

shellPrompt in ThisBuild <<= (name, version) { (n, v) =>
     _ => n + " " + v + "> "
}

// No main class since dsprofile is a library

mainClass in ThisBuild := None

// Don't run tests in parallel because some bits are not thread safe yet

parallelExecution in ThisBuild in Test := false
