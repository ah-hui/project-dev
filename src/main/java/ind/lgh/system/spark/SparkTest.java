package ind.lgh.system.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @author lgh
 */
public class SparkTest {

    public static void main(String[] args) {
        String master = "spark://192.168.100.11:7077";
        String file = "hdfs://192.168.100.11:8020/test/profile";
        SparkConf conf = new SparkConf().setMaster(master).setAppName("MyTest");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> data = sc.textFile(file);
        System.out.println(data.count());
    }
}
