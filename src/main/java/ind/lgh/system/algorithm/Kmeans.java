package ind.lgh.system.algorithm;

import java.util.*;

/**
 * K-Means Clustering in Java.
 * 里面涉及到相似度算法，欧几里得距离算法的实践效果是最优的。
 * 算法的不足：
 * 1.算法只能针对数值类型的属性（维），对于其他类型的除非选定合适的数值度量。
 * 2.k-means是局部最优的，容易受到初始质心的影响；因选择初始质心不恰当而造成次优的聚类结果。
 * 3.k值的选取也会直接影响聚类结果，最优聚类的k值应与样本数据本身的结构信息相吻合，而这种结构信息是很难去掌握，因此选取最优k值是非常困难的
 * 最优的聚类结果应使得SSE达到最小值。
 * 它不能处理非球形簇，不同尺寸和不同密度的簇。对包含离群点（噪声点）的数据进行聚类时，k均值也有问题。
 * k-means++ improves the running time of Lloyd’s algorithm, and the quality of the final solution.
 *
 * @author lgh
 */
public class Kmeans {
    /**
     * 聚类的数目
     */
    private int k = 3;
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
     * 设置异常点阈值参数（每一类初始的最小数目为rows/k^t）
     */
    private double t = 2.0;
    /**
     * 观察值数据集
     */
    private float[][] observations;
    /**
     * 质心
     */
    private float[][] centroids;
    /**
     * 噪声 - 暂时忽略噪声
     */
    private List<Integer> noises;
    /**
     * 聚类
     */
    private List<ArrayList<Integer>> clusters;
    /**
     * 每次迭代的SSE列表，其size刚好等于迭代的次数
     */
    private List<Float> sseList;
    /**
     * 最小的k取值
     */
    private static final Integer MIN_K = 2;

    /**
     * 主函数入口
     * 测试集的文件名称为“测试集.data”,其中有1000*57大小的数据
     * 每一行为一个样本，有57个属性
     * 主要分为两个步骤
     * 1.读取数据
     * 2.进行聚类
     * 最后统计运行时间和消耗的内存
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Kmeans kmeans = new Kmeans();
        // 初始化数据
        float[][] dataSet = new float[][]{{8, 2}, {3, 4}, {2, 5},
                {4, 2}, {7, 3}, {6, 2}, {4, 7}, {6, 3}, {5, 3},
                {6, 3}, {6, 9}, {1, 6}, {3, 9}, {4, 1}, {8, 6}};
        kmeans.initData(5, dataSet);
        // 聚类过程

        float sumTimes = 0;
        float sumSse = 0;
        for (int i = 0; i < 10000000; i++) {
            kmeans.kmeans();
            List<Float> sseList = kmeans.sseList;
            sumTimes += sseList.size();
            sumSse += sseList.get(sseList.size() - 1);
            kmeans.initData(5, dataSet);
        }
        System.out.println("Times avg=" + (sumTimes / 10000000) + ", SSE avg=" + (sumSse / 10000000));

//        kmeans.kmeans();
        // 输出结果
//        kmeans.printConsole();
        long endTime = System.currentTimeMillis();
        System.out.println("Total Time:" + (endTime - startTime) / 1000 + "s");
        System.out.println("Memory Consuming:" + (float) (Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory()) / 1000000 + "MB");
    }

    /**
     * 初始化传入样本数据
     *
     * @param k       聚类数
     * @param dataSet 样本数据集
     */
    private void initData(int k, float[][] dataSet) throws Exception {
        if (k < MIN_K) {
            throw new Exception("错误！请输入有效的k值！");
        }
        if (dataSet == null || dataSet.length < 1) {
            return;
        }
        this.k = k;
        this.rows = dataSet.length;
        this.dimensions = dataSet[0].length;
        this.originalDataSet = dataSet;
        // 质心集合
        centroids = new float[k][dimensions];
        // 聚类集合
        clusters = new ArrayList<>(k);
        // 噪音集合
        noises = new ArrayList<>();
        // SSE列表
        sseList = new ArrayList<>();
        // 原始数据集
        observations = new float[rows][dimensions];
        for (int i = 0; i < dataSet.length; i++) {
            float[] line = dataSet[i];
            for (int j = 0; j < line.length; j++) {
                observations[i][j] = line[j];
            }
        }
    }

    /**
     * 聚类过程，主要分为两步
     * 1.循环找初始点
     * 2.不断调整直到分类不再发生变化
     */
    public void kmeans() throws Exception {
        if (observations.length < 1) {
            throw new Exception("错误！请先初始化样本数据！");
        }
        // 数据归一化
        normalize();
        // 选取k个初始聚类质心
        pickInitialCentroids();
//        pickInitialCentroidsRandom();
        // 收敛条件
        boolean flag = true;
        // 记录迭代次数
        int times = 0;
        // 如果质心发生变化，则继续迭代
        while (flag) {
            times++;
            // 聚类
            cluster();
            // 调整质心，记录质心是否发生变化
            flag = findNewCentroids();
        }
        System.out.println("共计迭代" + times + "次，SSE=" + sseList.get(sseList.size() - 1));
    }

    /**
     * 对数据进行归一化.
     * 1.找每一个属性的最大值
     * 2.对某个样本的每个属性除以其最大值
     */
    public void normalize() {
        // 找最大值
        float[] max = new float[dimensions];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dimensions; j++) {
                if (observations[i][j] > max[j]) {
                    max[j] = observations[i][j];
                }
            }
        }
        // 归一化
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dimensions; j++) {
                observations[i][j] = observations[i][j] / max[j];
            }
        }
    }

    /**
     * 选取初始k个聚类质心.
     * 经典k-means随机选取质心
     * 实际表现：中庸
     */
    public void pickInitialCentroidsRandom() {
        List<Integer> centroidsIndex = new ArrayList<>(k);
        Random r = new Random();
        // 随机选取一个点当作种子点，它将是第一个质心
        int first = r.nextInt(rows);
        centroidsIndex.add(first);
        for (int i = 0; i < k - 1; i++) {
            while (true) {
                int random = r.nextInt(rows);
                if (!centroidsIndex.contains(random)) {
                    centroidsIndex.add(random);
                    break;
                }
            }
        }
        // 将质心记录到质心列表
        for (int i = 0; i < centroidsIndex.size(); i++) {
            centroids[i] = observations[centroidsIndex.get(i)];
        }
    }

    /**
     * 选取初始k个聚类质心.
     * k-means++算法实现：
     * 1.从数据集中随机选择一个点作为质心
     * 2.对数据集中的每个点，计算其与最近质心的距离D
     * 3.选取一个点作为新的质心，原则：距其他质心距离越大，被选择的概率越大
     * 4.重复2和3，直至选出k个质心
     * 5.利用这k个初始质心来运行标准kmeans
     * 实际表现：
     * 样本数据：{{8,2},{3,4},{2,5},{4,2},{7,3},{6,2},{4,7},{6,3},{5,3},{6,3},{6,9},{1,6},{3,9},{4,1},{8,6}}
     * 算法执行次数：10000000
     * k-means的表现：
     * Times avg=2.945982, SSE avg=0.36867538
     * Total Time:95s
     * k-means++的表现：
     * Times avg=3.461144, SSE avg=0.34762496
     * Total Time:134s
     * 可以看出，改进后的k-means++的聚类结果SSE均值高于普通k-means，聚类结果更好，但需要更多的迭代次数，更加耗时
     * 相信是我的实现导致的，代码应该还可以优化更快。不过，想得到更好的聚类结果，不多花点儿时间？
     */
    public void pickInitialCentroids() {
        List<Integer> centroidsIndex = new ArrayList<>(k);
        Random r = new Random();
        // 随机选取一个点当作种子点，它将是第一个质心
        int seed = r.nextInt(rows);
        centroidsIndex.add(seed);
        // 每次挑选一个质心，直到挑选到k个质心
        while (centroidsIndex.size() < k) {
            pickOneMoreCentroids(centroidsIndex);
        }
        // 将质心记录到质心列表
        for (int i = 0; i < centroidsIndex.size(); i++) {
            centroids[i] = observations[centroidsIndex.get(i)];
        }
    }

    /**
     * 挑选一个新的质心.
     * 1.对数据集中每个点，计算其与最近质心的距离D，并求出Sum(D)
     * 2.取一个落在Sum(D)上的随机值R，R-=D，直到R<=0，此时的点就是下一个质心
     *
     * @param centroidsIndex
     */
    public void pickOneMoreCentroids(List<Integer> centroidsIndex) {
        float sumDistance = 0;
        float[] pointToClusterDistance = new float[rows];
        // 对数据集中每个点，计算其与最近质心的距离，并求出Sum(D)
        for (int i = 0; i < observations.length; i++) {
            float minDistance = Float.MAX_VALUE;
            for (int j = 0; j < centroidsIndex.size(); j++) {
                float distance = calcDistance(observations[i], observations[centroidsIndex.get(j)]);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
            pointToClusterDistance[i] = minDistance;
            sumDistance += minDistance;
        }
        // 取一个落在Sum(D)上的随机值R，R-=D，直到R<=0，此时的点就是下一个质心
        Random r = new Random();
        // 注意去重
        outer:
        for (; ; ) {
            int randomInt = r.nextInt((int) sumDistance);
            inner:
            for (int i = 0; ; i++) {
                randomInt -= pointToClusterDistance[i];
                if (randomInt <= 0) {
                    if (!centroidsIndex.contains(i)) {
                        centroidsIndex.add(i);
                        break outer;
                    }
                    break inner;
                }
            }
        }
    }

    /**
     * 对所有数据点按照当前质心，聚类.
     */
    public void cluster() {
        // 只针对第一次聚类，初始化聚类结果集合对象
        if (clusters.size() < 1) {
            for (int m = 0; m < k; m++) {
                ArrayList<Integer> cluster = new ArrayList<>();
                clusters.add(cluster);
            }
        }
        // 重新聚类之前，清空旧的聚类结果
        for (ArrayList<Integer> cluster : clusters) {
            cluster.clear();
        }
        // 开始聚类
        for (int i = 0; i < observations.length; i++) {
            float minDistance = Float.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < k; j++) {
                // 样本点与质心的距离
                float distance = calcDistance(observations[i], centroids[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                    index = j;
                }
            }
            // 距离哪个质心的欧氏距离最近，归到哪个聚类
            ArrayList<Integer> cluster = clusters.get(index);
            if (!cluster.contains(i)) {
                cluster.add(i);
            }
        }
    }

    /**
     * 重新计算k个聚类的质心
     *
     * @return 聚类结果是否最优
     */
    private boolean findNewCentroids() {
        // 收敛条件：质心是否发生变化
        boolean flag = false;
        // 新的质心集合
        float[][] newCentroids = new float[k][dimensions];
        // 对每个聚类重新计算质心
        for (int i = 0; i < k; i++) {
            newCentroids[i] = findCentroid(clusters.get(i));
            // 如果有质心发生变化
            if (!isArrayEqual(centroids[i], newCentroids[i])) {
                flag = true;
            }
        }
        //
        centroids = newCentroids;
        // 计算新的质心下的SSE
        calcSSE();
        // 返回收敛条件
        return flag;
    }

    /**
     * 计算指定聚类的质心.
     *
     * @param cluster
     */
    private float[] findCentroid(ArrayList<Integer> cluster) {
        float[] means = new float[dimensions];
        // 对每个聚类记录的索引，找到其对应的观察值集合的元素，遍历每一个元素的每一个属性维度
        for (Integer index : cluster) {
            for (int j = 0; j < dimensions; j++) {
                means[j] += observations[index][j];
            }
        }
        // 对每个维度计算均值
        for (int j = 0; j < dimensions; j++) {
            means[j] /= cluster.size();
        }
        return means;
    }

    /**
     * 判断两个数组是否相等
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 是否相等
     */
    private boolean isArrayEqual(float[] arr1, float[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr1.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            float a = arr1[i];
            float b = arr2[i];
            if (Math.abs(a - b) > 0.00000001) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算样本数据的平方误差和
     * 对于欧式空间的样本数据，以平方误差和（sum of the squared error, SSE)作为聚类的目标函数，
     * 同时也可以衡量不同聚类结果好坏的指标
     */
    private void calcSSE() {
        float sse = 0;
        for (int i = 0; i < clusters.size(); i++) {
            ArrayList<Integer> cluster = clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                sse += calcSquaredError(observations[cluster.get(j)], centroids[i]);
            }
        }
        sseList.add(sse);
    }

    /**
     * 计算两点的平方误差
     *
     * @param point  样本点
     * @param center 质心
     * @return 平方误差
     */
    private float calcSquaredError(float[] point, float[] center) {
        float squaredError = 0;
        for (int i = 0; i < point.length; i++) {
            squaredError += Math.pow(point[i] - center[i], 2);
        }
        return squaredError;
    }

    /**
     * 计算两个向量间的欧式距离.
     *
     * @param vecA A向量
     * @param vecB B向量
     * @return 欧式距离长度
     */
    private float calcDistance(float[] vecA, float[] vecB) {
        float distance = 0;
        for (int i = 0; i < vecA.length; i++) {
            distance += Math.pow(vecB[i] - vecA[i], 2);
        }
        distance = (float) Math.sqrt(distance);
        return distance;
    }

    /**
     * 测试用 - 打印结果到控制台
     */
    public void printConsole() {
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("第" + (i + 1) + "个聚类：");
            ArrayList<Integer> cluster = clusters.get(i);
            for (int j = 0; j < cluster.size(); j++) {
                float[] observation = originalDataSet[cluster.get(j)];
                for (int m = 0; m < observation.length; m++) {
                    System.out.print(observation[m] + ",");
                }
                System.out.println();
            }
        }
    }
}
