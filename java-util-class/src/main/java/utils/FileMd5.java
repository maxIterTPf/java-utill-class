package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @Auther By Nait
 * @Date: 2021/1/22
 * @version: 1.0
 */
public class FileMd5 {

    /**
     * 根据文件内容生成md5
     */
    public static void main(String[] args) {
        String path = "C:\\Users\\Seekcy&Joysuch\\Desktop\\user_1_buildInfo(1).txt";
        System.out.println(getFileMd5(path));
    }

    static public String getFileMd5(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return bytesToHexString(md5.digest());
        } catch (Exception e) {
            System.err.println("error");
            return null;
        }
    }

    public static String bytesToHexString(byte[] var0) {
        StringBuilder var1 = new StringBuilder("");
        if (var0 != null && var0.length > 0) {
            for(int var2 = 0; var2 < var0.length; ++var2) {
                String var3;
                if ((var3 = Integer.toHexString(var0[var2] & 255).toUpperCase()).length() < 2) {
                    var1.append(0);
                }
                var1.append(var3);
            }
            return var1.toString();
        } else {
            return null;
        }
    }

}
