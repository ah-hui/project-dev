//package ind.lgh.system.spark;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.hive.HiveContext;
//import org.apache.spark.sql.SQLContext;
//import org.apache.spark.sql.Row;
//
///**
// * @author lgh
// */
//public class SparkTest {
//
//    public static void main(String[] args) {
//        String master = "spark://192.168.100.11:7077";
//        String file = "file:///D:/test.txt";
//        SparkConf conf = new SparkConf().setMaster(master).setAppName("MyTest");
//        JavaSparkContext sc = new JavaSparkContext(conf);
//
//        SQLContext sqlContext = new HiveContext(sc);
//        Dataset<Row> rowDataset = sqlContext.jsonFile(file);
//        rowDataset.registerTempTable("tweets");
//
//        rowDataset.printSchema();
//
//        Dataset<Row> topTweets = sqlContext.sql("SELECT text, retweetCount FROM " +
//                "tweets ORDER BY retweetCount LIMIT 10");
//        JavaRDD<String> text = (JavaRDD<String>) topTweets.toJavaRDD().map(new Function<Row, String>() {
//            @Override
//            public String call(Row row) throws Exception {
//                return row.getString(0);
//            }
//        });
////        JavaRDD<String> data = sc.textFile(file);
////        System.out.println(data.count());
//    }
//}
