package ind.lgh.system.algorithm;

/**
 * Scaling z-score规范化.
 *
 * @author lgh
 */
public class ZScoreNormalization implements Normalizable {

    @Override
    public Double[] normalize(Double[] arr) throws Exception {
        if (arr == null || arr.length == 0) {
            throw new Exception("异常！z-score规范化的输入参数不能为空！");
        }
        Double[] result = new Double[arr.length];
        double avg = getAverage(arr);
        double sigma = getStandardDeviation(arr);
        System.out.println("avg=" + avg + ",sigma=" + sigma + "fangcha=" + Math.pow(sigma, 2));
        for (int i = 0; i < arr.length; i++) {
            result[i] = zScore(arr[i], avg, sigma);
        }
        return result;
    }

    private double zScore(double value, double avg, double sigma) {
        return (value - avg) / sigma;
    }

    private double getStandardDeviation(Double[] arr) {
        double avg = getAverage(arr);
        int len = arr.length;
        double deviationSum = 0;
        for (int i = 0; i < len; i++) {
            deviationSum += Math.pow((arr[i] - avg), 2);
        }
        return Math.sqrt(deviationSum / avg);
    }

    private double getAverage(Double[] arr) {
        double sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        return sum / len;
    }

    public static void main(String[] args) throws Exception {
        Double[] arr = new Double[]{60.0, 75.0, 99.0, 88.0, 65.0, 72.0, 75.0,72.0, 87.0, 65.0};

        System.out.println("==arr=============>");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        Double[] normalize = new ZScoreNormalization().normalize(arr);
        System.out.println("==normalize=============>");
        for (int i = 0; i < normalize.length; i++) {
            System.out.print(normalize[i] + ",");
        }
    }
}
