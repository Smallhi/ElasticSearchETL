


import _root_.sbtassembly.Plugin.AssemblyKeys
import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

assemblySettings

name := "ElasticSearchETL"

version := "0.1"

scalaVersion := "2.11.10"

// Resolvers
resolvers += "SnowPlow Repo" at "http://maven.snplow.com/releases/"
resolvers += "Twitter Maven Repo" at "http://maven.twttr.com/"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-hive" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-graphx" % "2.0.0" % "provided",
  "org.apache.spark" %% "spark-mllib" % "2.0.0" % "provided",
  "io.searchbox" % "jest" % "1.0.3"

)