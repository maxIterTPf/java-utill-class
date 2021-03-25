package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @Auther By Nait
 * @Date: 2021/1/22
 * @version: 1.0
 */
public class CopyFile {

    /**
     * 文件复制
     */
    public static void main(String[] args) {
        copyNio("sourceFilePath.fileName", "targetFilePath.fileName");
    }

    public static void copyNio(String source, String dest) {
        FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(new File(source)).getChannel();
            output = new FileOutputStream(new File(dest)).getChannel();
            output.transferFrom(input, 0, input.size());
            System.out.println("copy succeed");
        } catch (Exception e) {
            System.out.println("error occur while cop");
        } finally {
            System.out.println("copyNio");
            System.out.println("copyNio");
        }
    }


}
