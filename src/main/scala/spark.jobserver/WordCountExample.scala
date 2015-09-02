package spark.jobserver

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark._

import scala.util.Try

/**
 * A super-simple Spark job example that implements the SparkJob trait and can be submitted to the job server.
 *
 * Set the config with the sentence to split or count:
 * input.string = "adsfasdf asdkf  safksf a sdfa"
 *
 * validate() returns SparkJobInvalid if there is no input.string
 */
object WordCountExample extends SparkJob {
  def main(args: Array[String]) {
    val sc = new SparkContext("local[4]", "WordCountExample")
    val config = ConfigFactory.parseString("input.string = \"adsfasdf asdkf  safksf a sdfa\"")
    val results = runJob(sc, config)
    println("Result is " + results)
  }

  override def validate(sc: SparkContext, config: Config): SparkJobValidation = {
    Try(config.getString("input.string"))
      .map(x => SparkJobValid)
      .getOrElse(SparkJobInvalid("No input.string config param"))
  }

  override def runJob(sc: SparkContext, config: Config): Any = {
    val dd = sc.parallelize(config.getString("input.string").split(" ").toSeq)
//    dd.map((_, 1)).reduceByKey(_ + _).collect().toMap
    val a1 = dd.map(word => (word, 1))
    val a2 =  a1.reduceByKey(_ + _)
    val a3 = a2.collect()
    a3.toMap
  }
}

