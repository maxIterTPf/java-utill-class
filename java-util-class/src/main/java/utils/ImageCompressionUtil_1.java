package utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;


/**
 * @Auther By Nait
 * @Date: 2021/1/23
 * @version: 1.0
 */
public class ImageCompressionUtil_1 {

    /**
     * 谷歌Thumbnails插件图片压缩
     */
    public static void main(String[] args) {
        ImageCompressionUtil_1.commpressPicForScale("C:\\Users\\Seekcy&Joysuch\\Desktop\\ceshi.jpg",
                "C:\\Users\\Seekcy&Joysuch\\Desktop\\ceshi1222.jpg", 4 * 1024, 0.8); // 图片小于 4M，递归压缩的比率 0.8
    }

    /**
     * 根据指定大小和指定精度压缩图片
     *
     * @param srcPath 源图片地址
     * @param desPath 目标图片地址
     * @param desFileSize 指定图片大小，单位kb
     * @param accuracy 精度，递归压缩的比率，建议小于0.9
     * @return
     */
    public static String commpressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy) {

//        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(srcPath)) {
//            return null;
//        }
        if (!new File(srcPath).exists()) {
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");

            // 1、先转换成jpg
        //   Thumbnails.of(srcPath).scale(1f).toFile(desPath);
            copyImage(srcPath, desPath);
            // 递归压缩，直到目标文件大小小于desFileSize
            commpressPicCycle(desPath, desFileSize, accuracy);

            File desFile = new File(desPath);
            System.out.println("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成！");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static void copyImage(String source, String dest) {
        FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(new File(source)).getChannel();
            output = new FileOutputStream(new File(dest)).getChannel();
            output.transferFrom(input, 0, input.size());
        } catch (Exception e) {
            System.err.println("xxxxxxxxxx error occur while cop xxxxxxxxxx");
        } finally {
        }
    }

    private static void commpressPicCycle(String desPath, long desFileSize,
                                          double accuracy) throws IOException {
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于desFileSize * 1024 kb，不压缩；如果大于等于desFileSize * 1024kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(srcFileJPG);
        int srcWdith = bim.getWidth();
        int srcHeigth = bim.getHeight();
        int desWidth = new BigDecimal(srcWdith).multiply(new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeigth).multiply(new BigDecimal(accuracy)).intValue();

        Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
        commpressPicCycle(desPath, desFileSize, accuracy);
    }

}
