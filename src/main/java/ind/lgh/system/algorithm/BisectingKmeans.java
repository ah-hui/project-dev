package ind.lgh.system.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 二分k-means.
 * 核心思路是：
 * 将初始的一个簇一分为二计算出误差平方和最大的那个簇，对他进行再一次的二分。直至切分的簇的个数为k个停止。
 * <p>
 * 实际表现对比：
 * 样本数据：kmeans_test.csv，150条4维向量
 * 算法执行次数：1000000
 * 普通kmeans：
 * Times avg=7.552902, SSE avg=3.8109033
 * Total Time:156s
 * 二分kmeans：
 * SSE avg=2.276657
 * Total Time:161s
 * <p>
 * 实验发现，二分kmeans的聚类结果更优（高的多），耗时时间略高kmeans。
 *
 * @author lgh
 */
public class BisectingKmeans {
    /**
     * 聚类的数目
     */
    private int k;
    /**
     * 原始数据集
     */
    private float[][] originalDataSet;
    /**
     * 样本行数
     */
    private int rows = 0;
    /**
     * 样本参与聚类分析的属性数目 - 维度
     */
    private int dimensions = 0;
    /**
     * 聚类
     */
    private List<Cluster> clusters;
    /**
     * SSE
     */
    private float sumOfSquaredError;
    /**
     * 二分
     */
    private static final Integer MIN_K = 2;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
//        float[][] dataSet = new float[][]{{8, 2}, {3, 4}, {2, 5},
//                {4, 2}, {7, 3}, {6, 2}, {4, 7}, {6, 3}, {5, 3},
//                {6, 3}, {6, 9}, {1, 6}, {3, 9}, {4, 1}, {8, 6}};
        String rootPath = System.getProperty("user.dir");
        float[][] dataSet = readDataFile(rootPath + "\\src\\main\\java\\ind\\lgh\\system\\algorithm\\data\\kmeans_test.csv");
        BisectingKmeans bisectingKmeans = new BisectingKmeans();
        // 初始化数据
        bisectingKmeans.initData(5, dataSet);

        float sumSse = 0;
        for (int i = 0; i < 1000000; i++) {
            bisectingKmeans.bisectingKmeans();
            sumSse += bisectingKmeans.sumOfSquaredError;
            bisectingKmeans.initData(5, dataSet);
        }
        System.out.println("SSE avg=" + (sumSse / 1000000));

        // 开始聚类
//        bisectingKmeans.bisectingKmeans();

        //
        long endTime = System.currentTimeMillis();
        System.out.println("Total Time:" + (endTime - startTime) / 1000 + "s");
    }

    /**
     * 初始化传入样本数据
     *
     * @param k       聚类数
     * @param dataSet 样本数据集
     */
    public void initData(int k, float[][] dataSet) throws Exception {
        if (k < MIN_K) {
            throw new Exception("错误！请输入有效的k值！");
        }
        if (dataSet == null || dataSet.length < 1) {
            return;
        }
        this.k = k;
        this.originalDataSet = dataSet;
        this.rows = dataSet.length;
        this.dimensions = dataSet[0].length;
        this.clusters = new ArrayList<>();
    }

    /**
     * 读取数据文件，转换为可处理的格式.
     *
     * @param filePath 文件路径
     * @return 原始数据集
     */
    private static float[][] readDataFile(String filePath) {
        File file = new File(filePath);
        float[][] dataSet = new float[150][4];
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            int m = -1;
            while ((str = in.readLine()) != null) {
                m++;
                String[] split = str.split(",");
                float[] arr = new float[4];
                for (int i = 0; i < split.length; i++) {
                    arr[i] = Float.parseFloat(split[i]);
                }
                dataSet[m] = arr;
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    /**
     * 算法核心
     * 实质上是不断对选中的簇做k=2的kmeans
     */
    public void bisectingKmeans() throws Exception {
        // 不断二分迭代，直到得到k个簇
        for (; ; ) {
            // 对选中的簇做 k=2 的kmeans
            Kmeans kmeans = new Kmeans();
            kmeans.initData(2, originalDataSet);
            kmeans.kmeans();
            // 解析结果
            List<float[]> clusterSseList = kmeans.getClusterSseList();
            List<ArrayList<Integer>> clusters = kmeans.getClusters();
            int clusterSize = clusters.size();
            for (int i = 0; i < clusterSize; i++) {
                ArrayList<Integer> cluster = clusters.get(i);
                // 将全部聚类加入到集合
                Cluster c = new Cluster();
                c.setSse(clusterSseList.get(clusterSseList.size() - 1)[i]);
                int vectorSize = cluster.size();
                float[][] parsedCluster = new float[vectorSize][dimensions];
                for (int j = 0; j < vectorSize; j++) {
                    parsedCluster[j] = originalDataSet[cluster.get(j)];
                }
                c.setCluster(parsedCluster);
                this.clusters.add(c);
            }
            // 判断是否得到k个簇，是则停止，否则选择簇继续二分
            if (this.clusters.size() >= k) {
                break;
            }
            // 选择SSE最大的聚类，二分
            // originalDataSet = SSE max
            float maxSSE = -1;
            int maxSSEIndex = -1;
            for (int i = 0; i < this.clusters.size(); i++) {
                Cluster cluster = this.clusters.get(i);
                if (cluster.getSse() > maxSSE) {
                    maxSSE = cluster.getSse();
                    maxSSEIndex = i;
                }
            }
            // SSE最大，将二分
            originalDataSet = this.clusters.get(maxSSEIndex).getCluster();
            // 从聚类集合删除将要二分的聚类
            this.clusters.remove(maxSSEIndex);
        }
        // 计算最终结果的SSE
        sumOfSquaredError = 0;
        for (int i = 0; i < this.clusters.size(); i++) {
            sumOfSquaredError += this.clusters.get(i).getSse();
        }
        System.out.println(sumOfSquaredError);
    }

    @Getter
    @Setter
    class Cluster {
        private float[][] cluster;
        private float sse;
    }
}
