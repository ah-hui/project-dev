package ind.lgh;

import java.io.*;
import java.util.HashSet;

/**
 * 在这里随便玩
 *
 * @author lgh
 * @since 2017-12-21
 */
public class CountJavaFileLine {

    public static String FILE_DIR;

    public static HashSet<String> ALL_FILE = new HashSet<>();

    public static int COUNT = 0;

    public static void main(String[] args) {
        FILE_DIR = args[0];
        getListFile(FILE_DIR);
        for (String fileName : ALL_FILE) {
            try {
                COUNT += getFileLineCount(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("该文件目录java文件总行数为：" + COUNT);
    }

    /**
     * 对每个文件计数
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static int getFileLineCount(String fileName) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
        while (reader.readLine() != null) {
        }
        System.out.println(fileName + "---" + reader.getLineNumber());
        return reader.getLineNumber();
    }

    /**
     * 递归获取文件下所有的java文件信息
     *
     * @param path 文件路径
     * @return 文件实体集合
     */
    public static void getListFile(String path) {
        // 若是目录, 采用递归的方法遍历子目录
        File file = new File(path);
        // 如果是文件夹，则取出其中的文件
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                String pathDemo = file2.getPath();
                getListFile(pathDemo);
            }
        } else {// 如果是文件则读取文件
            String filePath = file.getParent() + "/";
            String fileName = file.getName();
            if (fileName.endsWith(".java")) {
                ALL_FILE.add(filePath + file.getName());
            }
        }

    }
}