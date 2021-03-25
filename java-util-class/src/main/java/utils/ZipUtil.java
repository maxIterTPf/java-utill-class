package utils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther By Nait
 * @Date: 2021/1/22
 * @version: 1.0
 */
public class ZipUtil {

    /**
     * 创建zip压缩包
     */
    public static void main(String[] args) {
        ZipUtil.createZip("C:\\Users\\Seekcy&Joysuch\\Desktop\\203150\\203150", "C:\\Users\\Seekcy&Joysuch\\Desktop\\203150\\203150map.zip");
    }

    private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 创建ZIP文件
     * @param sourcePath 文件或文件夹路径
     * @param zipPath    生成的zip文件存在路径（包括文件名）
     */
    public static boolean createZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            return writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            logger.error("创建ZIP文件失败", e);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                logger.error("创建ZIP文件失败", e);
            }
        }
        return false;
    }

    private static boolean writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            int count = 0;
            if (file.isDirectory()) {// 处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        writeZip(f, parentPath, zos);
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[2048];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        count = count + len;
                        zos.write(content, 0, len);
                        zos.flush();
                    }
                } catch (FileNotFoundException e) {
                    logger.error("创建ZIP文件失败", e);
                } catch (IOException e) {
                    logger.error("创建ZIP文件失败", e);
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        logger.error("创建ZIP文件失败", e);
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
