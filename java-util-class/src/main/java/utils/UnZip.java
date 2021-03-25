package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Auther By Nait
 * @Date: 2021/1/20
 * @version: 1.0
 */
public class UnZip {
    /**
     * 解压 Zip 包
     */
    public static void main(String[] args) {
        try {
            UnZip.UnZipFolder("D:\\缓存\\code\\200036map.zip","D:\\缓存\\code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean UnZipFolder(String zipFileString, String outPathString) throws Exception {
        boolean unZipResult = false;
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        ZipEntry zipEntry;
        String szName = "";
        try {
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    // get the folder name of the widget
                    szName = szName.substring(0, szName.length() - 1);
                    File folder = new File(outPathString + File.separator + szName);
                    folder.mkdirs();
                } else {
                    if (szName.contains("/")) {
                        String fileRoot = szName.substring(0, szName.indexOf("/"));
                        File folder = new File(outPathString + File.separator + fileRoot);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                    }
                    File file = new File(outPathString + File.separator + szName);
                    file.createNewFile();
                    // get the output stream of the file
                    FileOutputStream out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    // read (len) bytes into buffer
                    while ((len = inZip.read(buffer)) != -1) {
                        // write (len) byte from buffer at the position 0
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    out.close();
                }
            }
            inZip.close();
            unZipResult = true;
            System.out.println("unZip successed !");
        } catch (IOException e) {
            System.out.println("unZip failed !");
            e.printStackTrace();
        }
        return unZipResult;
    }

}
