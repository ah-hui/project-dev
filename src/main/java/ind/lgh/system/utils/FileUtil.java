package ind.lgh.system.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 文件操作工具类
 *
 * @author lgh
 * @since 2017-12-21
 */
public class FileUtil {

    // 水印规格
    /**
     * 字体
     */
    private static final String FONT_FAMILY = "微软雅黑";
    /**
     * 字体加粗
     */
    private static final int FONT_STYLE = Font.BOLD;
    /**
     * 字体大小
     */
    private static final int FONT_SIZE = 20;
    /**
     * 水印透明度
     * 0.0 完全透明; 1.0 完全不透明
     */
    private static final float ALPHA = 0.5F;
    /**
     * 水印与图片边缘间距
     */
    private static final int SPACING = 10;
    /**
     * 文字水印颜色
     */
    private static final Color DEFAULT_COLOR = Color.white;
    /**
     * 默认水印文字
     */
    private static final String DEFUALT_TEXT_WATERMARK = "北京象翌";
    /**
     * 默认图片水印路径,暂空
     */
    private static final String DEFUALT_LOGO_PATH = "";
    /**
     * 图片水印大小
     */
    private static final int LOGO_WIDTH = 100;


    public static void main(String[] args) throws IOException {

//    	watermarkWithImage();
        watermarkWithText();

    }

    // 图片水印
    public static void watermarkWithImage() throws IOException {
        /**
         * getRGB()的最后一个参数：
         * scansize 是图像中相邻两行中具有相同行索引的像素的索引偏移值。如果这个值与要提取的区域的宽度相同，那么一行的第一个像素就会存储在数组中前一行最后一个像素后 面的索引位置。如果这个值大于提取区域的宽度，那么数组中，在一行最后和下一行开始之间就会有一些未使用的索引。
         */
        File srcFile = new File("E:\\tmp\\22.jpg");
        File logoFile = new File("E:\\tmp\\TextWatermarked.jpg");
        String targetPath = "E:\\tmp\\WatermarkedImage.jpg";

        // 读取原图
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        int width = bufferedImage.getWidth();//图片宽度
        int height = bufferedImage.getHeight();//图片高度
        // 从图片中读取RGB数据到数组
        int[] imageArr = new int[width * height];
        imageArr = bufferedImage.getRGB(0, 0, width, height, imageArr, 0, width);
        // 读取logo图片
        BufferedImage logoBufferedImage = ImageIO.read(logoFile);
        int logoWidth = logoBufferedImage.getWidth();// logo原始宽度
        int logoHeight = logoBufferedImage.getHeight();// logo原始高度
        // 开始合成原图和水印
        BufferedImage targetBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 合成图片设置透明背景  
        Graphics2D g2d = targetBufferedImage.createGraphics();
        targetBufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        // 将原图RGB数据直接set到合成图片缓冲区 - 保证不失真的关键！
        targetBufferedImage.setRGB(0, 0, width, height, imageArr, 0, width);
        // 比例缩放logo
        int finalLogoWidth = LOGO_WIDTH;
        int finalLogoHeight = (LOGO_WIDTH * logoBufferedImage.getHeight()) / logoBufferedImage.getWidth();
        int x = width - (finalLogoWidth + SPACING);
        int y = height - (finalLogoHeight + SPACING);
        // 打开绘图句柄 - 测试发现setRGB前后必须关闭再打开
        g2d = targetBufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
        // 绘制logo - logo无法像原图一样保证完全不失真,因为logo图片无法在“以原图为背景”的情况下直接setRGB(只有三种背景参数可选)
        g2d.drawImage(logoBufferedImage, x, y, logoWidth, logoHeight, null);
        g2d.dispose();
        // 保存合成图片 - 使用ImageIO保存为png格式是要兼容图片透明,后缀名并不变(可能是jpg等)
        File newFile = new File(targetPath);
        ImageIO.write(targetBufferedImage, "png", newFile);
        System.out.println("watemark2 --- " + newFile.getPath() + " created successfully!");
    }

    // 文字水印
    public static void watermarkWithText() throws IOException {
        /**
         * getRGB()的最后一个参数：
         * scansize 是图像中相邻两行中具有相同行索引的像素的索引偏移值。如果这个值与要提取的区域的宽度相同，那么一行的第一个像素就会存储在数组中前一行最后一个像素后 面的索引位置。如果这个值大于提取区域的宽度，那么数组中，在一行最后和下一行开始之间就会有一些未使用的索引。
         */
        File srcFile = new File("E:\\tmp\\22.png");
        String contents = DEFUALT_TEXT_WATERMARK;
        String targetPath = "E:\\tmp\\WatermarkedImage0000.jpg";

        // 读取原图
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        int width = bufferedImage.getWidth();//图片宽度
        int height = bufferedImage.getHeight();//图片高度
        // 从图片中读取RGB数据到数组
        int[] imageArr = new int[width * height];
        imageArr = bufferedImage.getRGB(0, 0, width, height, imageArr, 0, width);
        // 开始合成原图和水印
        BufferedImage targetBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 合成图片设置透明背景  
        Graphics2D g2d = targetBufferedImage.createGraphics();
        targetBufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        // 将原图RGB数据直接set到合成图片缓冲区 - 保证不失真的关键！
        targetBufferedImage.setRGB(0, 0, width, height, imageArr, 0, width);
        // 开始绘制水印文字
        // 水印宽高
        int strWidth = FONT_SIZE * contents.length();
        int strHeight = FONT_SIZE;
        // 水印位置
        int x = width - (strWidth + SPACING);
        int y = height - strHeight;
        // 打开绘图句柄 - 测试发现setRGB前后必须关闭再打开
        g2d = targetBufferedImage.createGraphics();
        // 设置“抗锯齿”的属性
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置颜色
        g2d.setColor(DEFAULT_COLOR);
        // 设置字体
        g2d.setFont(new Font(FONT_FAMILY, FONT_STYLE, FONT_SIZE));
        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
        g2d.drawString(contents, x, y);
        g2d.dispose();
        // 保存合成图片 - 使用ImageIO保存为png格式是要兼容图片透明,后缀名并不变(可能是jpg等)
        File newFile = new File(targetPath);
        ImageIO.write(targetBufferedImage, "png", newFile);
        System.out.println("watemark5 --- " + newFile.getPath() + " created successfully!");
    }

}
