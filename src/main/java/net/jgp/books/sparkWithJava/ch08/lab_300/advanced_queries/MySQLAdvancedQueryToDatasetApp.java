package net.jgp.books.sparkWithJava.ch08.lab_300.advanced_queries;

import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * MySQL injection to Spark, using the Sakila sample database.
 * 
 * @author jgp
 */
public class MySQLAdvancedQueryToDatasetApp {

  /**
   * main() is your entry point to the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    MySQLAdvancedQueryToDatasetApp app = new MySQLAdvancedQueryToDatasetApp();
    app.start();
  }

  /**
   * The processing code.
   */
  private void start() {
    SparkSession spark = SparkSession.builder()
        .appName(
            "MySQL to Dataframe using a JDBC Connection")
        .master("local")
        .getOrCreate();

    // Using properties
    Properties props = new Properties();
    props.put("user", "root");
    props.put("password", "Spark<3Java");
    props.put("useSSL", "false");
    props.put("serverTimezone", "EST");

    Dataset<Row> df = spark.read().jdbc(
        "jdbc:mysql://localhost:3306/sakila",
        "film", props);

    // Displays the dataframe and some of its metadata
    df.show(5);
    df.printSchema();
    System.out.println("The dataframe contains " + df
        .count() + " record(s).");
  }
}
