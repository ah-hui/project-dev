package ind.lgh.system.algorithm.bisectingkmeans;

import java.util.List;

/**
 * 把计算距离和误差的公式抽离出来
 *
 * @author lgh
 */
public class CommonUtil {

    /**
     * 计算两个点之间的距离
     *
     * @param element 点1
     * @param center  点2
     * @return 距离
     */
    public static float distance(float[] element, float[] center) {
        float distance = 0.0f;
        float x = element[0] - center[0];
        float y = element[1] - center[1];
        float z = x * x + y * y;
        distance = (float) Math.sqrt(z);

        return distance;
    }

    /**
     * 求两点误差平方的方法
     *
     * @param element 点1
     * @param center  点2
     * @return 误差平方
     */
    public static float errorSquare(float[] element, float[] center) {
        float x = element[0] - center[0];
        float y = element[1] - center[1];

        float errSquare = x * x + y * y;

        return errSquare;
    }

    /**
     * 计算误差平方和准则函数方法
     */
    public static float countRule(List<float[]> cluster, float[] center) {
        float jcF = 0;

        for (int j = 0; j < cluster.size(); j++) {
            jcF += CommonUtil.errorSquare(cluster.get(j), center);

        }

        return jcF;
    }

    /**
     * 打印数据。測试用
     *
     * @param dataArray     数据集
     * @param dataArrayName 数据集名称
     */
    public static void printDataArray(List<float[]> dataArray, String dataArrayName) {
        for (int i = 0; i < dataArray.size(); i++) {
            System.out.println("print:" + dataArrayName + "[" + i + "]={"
                    + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
        }
        System.out.println("===================================");
    }
}