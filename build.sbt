name := "spike-spark-app"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"

libraryDependencies ++= Seq (
  "spark.jobserver" %% "job-server-api" % "0.5.1",
  "org.apache.spark" %% "spark-core" % "1.3.1"
)