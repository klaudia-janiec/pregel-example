name := "pregel-example"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq.apply(
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-graphx" % "2.3.0"
)
