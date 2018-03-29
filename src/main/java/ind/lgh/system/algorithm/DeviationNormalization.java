package ind.lgh.system.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Scaling 离差规范化.
 *
 * @author lgh
 */
public class DeviationNormalization implements Normalizable {

    @Override
    public Double[] normalize(Double[] arr) throws Exception {
        if (arr == null || arr.length == 0) {
            throw new Exception("异常！离差规范化的输入参数不能为空！");
        }
        Double[] result = new Double[arr.length];
        List<Double> list = Arrays.asList(arr);
        double min = Collections.min(list);
        double max = Collections.max(list);
        for (int i = 0; i < arr.length; i++) {
            result[i] = deviation(arr[i], min, max);
        }
        return result;
    }

    private double deviation(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    public static void main(String[] args) throws Exception {
        Double[] arr = new Double[]{60.0, 75.0, 99.0, 88.0, 65.0, 72.0, 75.0,72.0, 87.0, 65.0};
        System.out.println("==arr=============>");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        Double[] normalize = new DeviationNormalization().normalize(arr);
        System.out.println("==normalize=============>");
        for (int i = 0; i < normalize.length; i++) {
            System.out.print(normalize[i] + ",");
        }
    }

}
