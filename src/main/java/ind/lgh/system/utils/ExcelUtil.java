package ind.lgh.system.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * POI操作EXCEL对象
 * HSSF：操作Excel 97(.xls)格式
 * XSSF：操作Excel 2007 OOXML (.xlsx)格式，操作EXCEL内存占用高于HSSF
 * SXSSF:从POI3.8 beta3开始支持，基于XSSF，低内存占用。其原理是可以设置或者手动将内存中的EXCEL行写到硬盘中，这样内存中只保存了少量的EXCEL行进行操作。
 * SXSSFWorkbook 是专门用来处理大量数据写入 Excel2007的问题的,读取仍然是“XSSFWorkbook”，写入则为“SXSSFWorkbook ”
 * <p>
 * Maven依赖：poi-ooxml和poi-ooxml-schemas
 *
 * @author lgh
 * @since 2017-12-21
 */
public class ExcelUtil {

    /**
     * 商品数据Excel - 的商品id列数和商品标题列数
     */
    public static final int INDEX_ID_COLUMN = 1, INDEX_TITLE_COLUMN = 2;
    /**
     * 每日商品数据目录
     */
    public static final String PRODUCT_EXCEL_DIR = "E:\\tmp\\excel\\input";
    /**
     * 订单文件路径 （商品ID汇总在这里）
     */
    public static final String ORDER_EXCEL_FILE = "E:\\tmp\\excel\\input2\\2016年宝贝.xlsx";
    /**
     * 处理后的订单文件输出路径
     */
    public static final String EXPORT_EXCEL_FILE = "E:\\tmp\\excel\\input2\\2016年宝贝_修改.xlsx";
    /**
     * 从每日商品目录读取的全部excel文件名
     */
    public static HashSet<String> allExcelFileName = new HashSet<String>();
    /**
     * 读取全部商品到HashMap-商品名为key
     */
    public static HashMap<String, String> allProducts = new HashMap<>();

    public static void main(String[] args) {
        try {
            // 准备商品数据
            getAllFileName();
            // 处理商品ID和Title到HashMap
            for (String fileName : allExcelFileName) {
                mergeExcelX(fileName);
            }
            System.out.println("=============商品总条数：" + allProducts.size() + "=============");
            // 遍历订单Excel与上一步的HashMap比对标题，对应则在最后列+1列上设置商品ID
            modifyOrder();

            // 使用前请修改两个目录：输入路径PRODUCT_EXCEL_DIR和输出路径EXPORT_EXCEL_FILE
            // 汇总每日报表到新Excel
//            mergeExcelXAndExport();

            System.out.println("=============成功处理完毕！==============");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 事实上不是修改，而是生成一个新的excel
     * 使用XSSFWorkbook操作处理，仅使用SXSSFWorkbook输出
     *
     * @throws IOException
     */
    public static void modifyOrder() throws IOException {
        // 读取Excel文件
        FileInputStream input = new FileInputStream(ORDER_EXCEL_FILE);
        // 打开工作簿
        XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
        // 开始操作sheet
        Sheet srcSheet = wb.getSheetAt(0);
        int rowNum = srcSheet.getLastRowNum();
        // 原始做法 - 每行做处理
        HashSet<String> findSet = new HashSet<>();
        HashSet<String> notFindSet = new HashSet<>();
        for (int i = 0; i < rowNum; i++) {
            Row row = srcSheet.getRow(i);
            int columnNum = row.getLastCellNum();
            Cell cell = row.getCell(1);
            String orderTitle = cell.getStringCellValue();
            // 找到对应商品
            if (allProducts.containsKey(orderTitle)) {
                if (!findSet.contains(orderTitle)) {
                    findSet.add(orderTitle);
                    System.out.println("********************找到" + orderTitle);
                }
                Cell idCell = row.createCell(columnNum + 1, Cell.CELL_TYPE_STRING);
                idCell.setCellValue(allProducts.get(orderTitle));
            } else {
                if (!notFindSet.contains(orderTitle)) {
                    notFindSet.add(orderTitle);
                    System.out.println("====================未找到对应商品===" + orderTitle);
                }
            }
        }
        //1.继承wb创建输出工作簿 - 设置缓存(内存保留10000条数据，以免内存溢出，其余写入硬盘)
        SXSSFWorkbook workbook = new SXSSFWorkbook(wb, 10000);
        workbook.setCompressTempFiles(true);
        //输出到盘符
        FileOutputStream outputStream = new FileOutputStream(EXPORT_EXCEL_FILE);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    /**
     * 非递归方式读取文件
     */
    public static void getAllFileName() {
        File f = new File(PRODUCT_EXCEL_DIR);
        if (!f.exists()) {
            System.out.println("目录不存在！" + PRODUCT_EXCEL_DIR);
            return;
        }
        File[] arr = f.listFiles();
        for (int i = 0; i < arr.length; i++) {
            File file = arr[i];
            if (!file.isDirectory()) {
                allExcelFileName.add(PRODUCT_EXCEL_DIR + "\\" + file.getName());
            }
        }
    }

    /**
     * 读取一个文件将商品ID和Title汇总到HashMap
     *
     * @param fileName
     * @throws IOException
     */
    public static void mergeExcelX(String fileName) throws IOException {
        // 欲读取的sheet名
        HashSet<String> needSheets = new HashSet<String>() {{
            add("PC端商品数据");
            add("无线端商品数据");
        }};
        // 读取Excel文件
        FileInputStream input = new FileInputStream(fileName);
        // 打开工作簿
        XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
        // 开始操作sheet
        int sheetNumber = wb.getNumberOfSheets();
        System.out.println("================" + fileName + "共有" + sheetNumber + "个sheet页================");
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheet = wb.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            boolean flag = false;
            for (String need : needSheets) {
                if (sheetName.indexOf(need) > -1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("*****处理的sheet名：" + sheetName);
                // 读取全部行
                // 获取行数
                int rowNumber = sheet.getLastRowNum();
                for (int j = 0; j < rowNumber; j++) {
                    Row row = sheet.getRow(j);
                    Cell idCell = row.getCell(INDEX_ID_COLUMN);
                    idCell.setCellType(Cell.CELL_TYPE_STRING);
                    Cell titleCell = row.getCell(INDEX_TITLE_COLUMN);
                    titleCell.setCellType(Cell.CELL_TYPE_STRING);
                    String id = idCell.getStringCellValue();
                    String title = titleCell.getStringCellValue();
                    allProducts.put(title, id);
                }
            }
        }
    }

    /**
     * 读取一个文件将商品ID和Title汇总去重并导出到Excel
     *
     * @throws IOException
     */
    public static void mergeExcelXAndExport() throws IOException {
        // 欲读取的sheet名
        HashSet<String> needSheets = new HashSet<String>() {{
            add("PC端商品数据");
            add("无线端商品数据");
        }};
        // 目标Excel
        XSSFWorkbook targetWorkbook = new XSSFWorkbook();
        Sheet targetSheet = targetWorkbook.createSheet("sheet1");
        // 读取每日数据报表目录
        getAllFileName();
        // 去重依据
        HashMap<String, String> prods = new HashMap<>();
        int count = 0;
        // 开始处理
        for (String fileName : allExcelFileName) {
            count++;
            // 读取Excel文件
            FileInputStream input = new FileInputStream(fileName);
            // 打开工作簿
            XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
            // 开始操作sheet
            int sheetNumber = wb.getNumberOfSheets();
            System.out.println("================正在处理第" + count + "个文件" + fileName + "共有" + sheetNumber + "个sheet页================");
            for (int i = 0; i < sheetNumber; i++) {
                Sheet sheet = wb.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                boolean flag = false;
                for (String need : needSheets) {
                    if (sheetName.indexOf(need) > -1) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    // 读取全部行
                    int rowNumber = sheet.getLastRowNum();
                    for (int j = 0; j < rowNumber; j++) {
                        Row row = sheet.getRow(j);
                        Cell idCell = row.getCell(INDEX_ID_COLUMN);
                        if (idCell == null) continue;
                        idCell.setCellType(Cell.CELL_TYPE_STRING);

                        Cell titleCell = row.getCell(INDEX_TITLE_COLUMN);
                        if (titleCell == null) continue;
                        titleCell.setCellType(Cell.CELL_TYPE_STRING);

                        String id = idCell.getStringCellValue();
                        String title = titleCell.getStringCellValue();
                        // 去重保存
                        if (prods.get(title) == null) {
                            prods.put(title, id);
                            if (prods.get(title) != id) {
                                System.out.println("************警报，去重时title一样但id不一样，title=" + title);
                            }
                            // 存到目标Excel
                            Row targetRow = targetSheet.createRow(targetSheet.getLastRowNum() + 1);
                            Cell targetTitleCell = targetRow.createCell(0, Cell.CELL_TYPE_STRING);
                            targetTitleCell.setCellValue(title);
                            Cell targetIdCell = targetRow.createCell(1, Cell.CELL_TYPE_STRING);
                            targetIdCell.setCellValue(id);
                        }
                    }
                }
            }
        }
        //1.继承targetWorkbook创建输出工作簿 - 设置缓存(内存保留10000条数据，以免内存溢出，其余写入硬盘)
        SXSSFWorkbook workbook = new SXSSFWorkbook(targetWorkbook, 10000);
        workbook.setCompressTempFiles(true);
        //输出到盘符
        FileOutputStream outputStream = new FileOutputStream(EXPORT_EXCEL_FILE);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
