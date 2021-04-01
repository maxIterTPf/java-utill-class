package utils;

import com.sun.istack.internal.NotNull;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 功能描述: zip下载工具类
 */
public class FileDownloadUtil {

    public static void downloadZip(HttpServletResponse response, @NotNull byte[] content) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        response.setDateHeader("Expires", 0);
        response.setContentLength(content.length);
        response.setContentType("application/octet-stream");
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(content);
            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 功能描述: 图片下载处理
     *
     * @auther: seekcy
     * @date: 2019/7/16 19:51
     */
    public static void downJpgFile(HttpServletResponse response, byte[] data) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg;charset=UTF-8");
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(data);
            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
