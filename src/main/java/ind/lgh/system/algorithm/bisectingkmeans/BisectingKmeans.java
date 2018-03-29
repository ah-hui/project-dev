package ind.lgh.system.algorithm.bisectingkmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分k均值。实际上是对一个集合做多次的k=2的kmeans划分。
 * 每次划分后会对sse值较大的簇再进行二分。 终于使得或分出来的簇的个数为k个则停止
 *
 * @author lgh
 */
public class BisectingKmeans {

    /**
     * 分成多少簇
     */
    private int k;
    /**
     * 当前要被二分的簇
     */
    private List<float[]> dataSet;
    /**
     * 簇
     */
    private List<ClusterSet> cluster;

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 初始化一个Kmean对象，将k置为10
        BisectingKmeans bkm = new BisectingKmeans(5);
        // 初始化试验集
        ArrayList<float[]> dataSet = new ArrayList<float[]>();

        dataSet.add(new float[]{1, 2});
        dataSet.add(new float[]{3, 3});
        dataSet.add(new float[]{3, 4});
        dataSet.add(new float[]{5, 6});
        dataSet.add(new float[]{8, 9});
        dataSet.add(new float[]{4, 5});
        dataSet.add(new float[]{6, 4});
        dataSet.add(new float[]{3, 9});
        dataSet.add(new float[]{5, 9});
        dataSet.add(new float[]{4, 2});
        dataSet.add(new float[]{1, 9});
        dataSet.add(new float[]{7, 8});
        // 设置原始数据集
        bkm.setDataSet(dataSet);
        // 运行算法
        bkm.execute();
        // 得到聚类结果
        // ArrayList<ArrayList<float[]>> cluster = bkm.getCluster();
        // 查看结果
        // for (int i = 0; i < cluster.size(); i++) {
        // bkm.printDataArray(cluster.get(i), "cluster[" + i + "]");
        // }

    }

    public BisectingKmeans(int k) {
        // 比2还小有啥要划分的意义么
        if (k < 2) {
            k = 2;
        }
        this.k = k;

    }

    /**
     * 设置需分组的原始数据集
     *
     * @param dataSet
     */

    public void setDataSet(ArrayList<float[]> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * 运行算法
     */
    public void execute() {
        long startTime = System.currentTimeMillis();
        System.out.println("BisectingKmeans begins");
        BisectingKmeans();
        long endTime = System.currentTimeMillis();
        System.out.println("BisectingKmeans running time="
                + (endTime - startTime) + "ms");
        System.out.println("BisectingKmeans ends");
        System.out.println();
    }

    /**
     * 初始化
     */
    private void init() {

        int dataSetLength = dataSet.size();
        if (k > dataSetLength) {
            k = dataSetLength;
        }
    }

    /**
     * 初始化簇集合
     *
     * @return 一个分为k簇的空数据的簇集合
     */
    private ArrayList<ArrayList<float[]>> initCluster() {
        ArrayList<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
        for (int i = 0; i < k; i++) {
            cluster.add(new ArrayList<float[]>());
        }

        return cluster;
    }

    /**
     * Kmeans算法核心过程方法
     */
    private void BisectingKmeans() {
        init();

        if (k < 2) {
            // 小于2 则原样输出数据集被觉得是仅仅分了一个簇
            ClusterSet cs = new ClusterSet();
            cs.setClu(dataSet);
            cluster.add(cs);
        }
        // 调用kmeans进行二分
        cluster = new ArrayList();

        while (cluster.size() < k) {
            List<ClusterSet> clu = kmeans(dataSet);

            for (ClusterSet cl : clu) {

                cluster.add(cl);

            }

            if (cluster.size() == k)
                break;
            else// 顺序计算他们的误差平方和
            {

                float maxerro = 0f;
                int maxclustersetindex = 0;
                int i = 0;
                for (ClusterSet tt : cluster) {
                    //计算误差平方和并得出误差平方和最大的簇
                    float erroe = CommonUtil.countRule(tt.getClu(), tt
                            .getCenter());
                    tt.setErro(erroe);

                    if (maxerro < erroe) {
                        maxerro = erroe;
                        maxclustersetindex = i;
                    }
                    i++;
                }

                dataSet = cluster.get(maxclustersetindex).getClu();
                cluster.remove(maxclustersetindex);

            }
        }
        int i = 0;
        for (ClusterSet sc : cluster) {
            CommonUtil.printDataArray(sc.getClu(), "cluster" + i);
            i++;
        }


    }

    /**
     * 调用kmeans得到两个簇。
     *
     * @param dataSet
     * @return
     */
    private List<ClusterSet> kmeans(List<float[]> dataSet) {
        Kmeans k = new Kmeans(2);

        // 设置原始数据集
        k.setDataSet(dataSet);
        // 运行算法
        k.execute();
        // 得到聚类结果
        List<List<float[]>> clus = k.getCluster();

        List<ClusterSet> clusterset = new ArrayList<ClusterSet>();

        int i = 0;
        for (List<float[]> cl : clus) {
            ClusterSet cs = new ClusterSet();
            cs.setClu(cl);
            cs.setCenter(k.getCenter().get(i));
            clusterset.add(cs);
            i++;
        }

        return clusterset;
    }

    class ClusterSet {
        private float erro;
        private List<float[]> clu;
        private float[] center;

        public float getErro() {
            return erro;
        }

        public void setErro(float erro) {
            this.erro = erro;
        }

        public List<float[]> getClu() {
            return clu;
        }

        public void setClu(List<float[]> clu) {
            this.clu = clu;
        }

        public float[] getCenter() {
            return center;
        }

        public void setCenter(float[] center) {
            this.center = center;
        }

    }
}
