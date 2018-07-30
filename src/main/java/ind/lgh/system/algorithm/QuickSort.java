package ind.lgh.system.algorithm;

/**
 * 快速排序
 * 从大到小排序
 *
 * @author lgh
 * @since 2018-07-30
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {12, 4, 67, 2, 6, 1, 58, 10, 30, 20};
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i <= arr.length - 1; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    /**
     * 快速排序 - 递归
     *
     * @param arr   排序数组
     * @param start 起始下标
     * @param end   结束下标
     */
    public static void sort(int[] arr, int start, int end) {
        // 递归停止条件 - 区间只有一个数
        if (start < end) {
            // 阶段性排序，保证第i个有序，且比i大的全在左边，比i小的全在右边
            int i = core(arr, start, end);
            // 左区间递归快排
            sort(arr, start, i - 1);
            // 右区间递归快排
            sort(arr, i + 1, end);
        }
    }

    /**
     * 每次的执行的结果：
     * 1.确定末尾元素是第i大，并交换到第i个位置，返回i
     * 2.保证i左边的都比i大，i右边的都比i小
     *
     * @param arr   排序数组
     * @param start 开始下标
     * @param end   结束下标
     * @return 已有序的第i个位置
     */
    private static int core(int[] arr, int start, int end) {
        // 选择最后一个元素作为枢轴
        int x = arr[end];
        // 枢轴应该在的位置
        int i = start;
        // 正向遍历，单侧扫描逼近，直至确定枢轴应该在的位置
        // （记录交换次数i决定最后一个元素是第i大，然后将它交换到第i个位置）
        for (int j = start; j <= end - 1; j++) {
            // 遍历与枢轴比较，更大的与枢轴位置交换，枢轴位置+1
            if (arr[j] >= x) {
                swap(arr, i, j);
                i++; // 交换了几次
            }
        }
        // 把第i大的放到i的位置
        swap(arr, i, end);
        return i;
    }

    /**
     * 交换数组的指定下标的两个元素
     *
     * @param arr 数组
     * @param i   第一个下标
     * @param j   第二个下标
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
