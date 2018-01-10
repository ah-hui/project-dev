package ind.lgh;


import java.io.*;
import java.util.HashSet;

/**
 * 工作相关：
 * 读取指定目录下的全部文件（非递归）并计算每个文件的行数
 *
 * @author lgh
 * @since 2017-12-21
 */
public class Anything {

//    public static final String FILE_DIR = "C:\\Users\\BJQT\\Desktop\\项目代码检查\\20180105总结\\javafile";

    public static final String FILE_DIR = "C:\\Users\\BJQT\\Desktop\\项目代码检查\\20180104 - 4\\循环场项目组代码检查\\抽查";

    public static HashSet<String> ALL_FILE = new HashSet<String>();

    public static void main(String[] args) {
        getAllFileName();
        System.out.println("文件数量：" + ALL_FILE.size());
        for (String fileName : ALL_FILE) {
            try {
                getFileLineCount(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getFileLineCount(String fileName) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
        String lineRead = "";
        while ((lineRead = reader.readLine()) != null) {
        }
        System.out.println(fileName + "---" + reader.getLineNumber());
    }

    /**
     * 非递归方式读取文件
     */
    public static void getAllFileName() {
        File f = new File(FILE_DIR);
        if (!f.exists()) {
            System.out.println("目录不存在！" + FILE_DIR);
            return;
        }
        File[] arr = f.listFiles();
        for (int i = 0; i < arr.length; i++) {
            File file = arr[i];
            if (!file.isDirectory()) {
                ALL_FILE.add(FILE_DIR + "\\" + file.getName());
            }
        }
    }
}