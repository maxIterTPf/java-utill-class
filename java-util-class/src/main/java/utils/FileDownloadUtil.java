package utils;

import java.io.*;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Auther By Nait
 * @Date: 2021/1/22
 * @version: 1.0
 */
//public class FileDownloadUtil {
//
//    public static void downloadZip(HttpServletResponse response, byte[] content) throws IOException {
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
//        response.setDateHeader("Expires", 0);
//        response.setContentLength(content.length);
//        response.setContentType("application/octet-stream");
//        InputStream is = null;
//        try {
//            is = new ByteArrayInputStream(content);
//            OutputStream out = response.getOutputStream();
//            byte[] buf = new byte[1024];
//            int len = -1;
//            while ((len = is.read(buf)) != -1) {
//                out.write(buf, 0, len);
//            }
//            out.flush();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * @param response
//     * @param zipFilename  zip的文件名
//     * @param contentList  每个文件的字节流
//     * @param filenameList 每个文件的文件名（如果想包含文件夹可命名为   文件夹名称/文件名.png 例:  高一(1)班/小陈.png）
//     * @throws IOException
//     */
//    public static void downloadZip(HttpServletResponse response,
//                                   String zipFilename,
//                                   List<byte[]> contentList,
//                                   List<String> filenameList) throws IOException {
//        response.reset();
//        response.setContentType("application/x-msdownload;");
//        response.setHeader("Content-Disposition", "attachment;filename="
//                + zipFilename);
//
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        CheckedOutputStream cos = new CheckedOutputStream(output, new CRC32());
//        ZipOutputStream zos = new ZipOutputStream(cos);
//
//
//        for (int i = 0; i < contentList.size(); i++) {
//            byte[] content = contentList.get(i);
//            String filename = filenameList.get(i);
//
//            //构建输入流
//            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(content));
//
//            //创建文件（zip里面的文件）
//            ZipEntry entry = new ZipEntry(filename);
//            //放入文件
//            zos.putNextEntry(entry);
//            //写入文件
//            IOUtils.copy(bis, zos);
//            //关闭流
//            bis.close();
//        }
//
//        zos.closeEntry();
//        zos.close();
//        //设置返回信息
//        response.setHeader("Content-Length", String.valueOf(output.size()));
//        IOUtils.copy(new ByteArrayInputStream(output.toByteArray()), response.getOutputStream());
//
//        //创建完压缩文件后关闭流
//        cos.close();
//        output.close();
//
//    }
//
//
//    /**
//     * 功能描述: 图片下载处理
//     *
//     * @auther: seekcy
//     * @date: 2019/7/16 19:51
//     */
//    public static void downJpgFile(HttpServletResponse response, byte[] data) throws IOException {
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg;charset=UTF-8");
//        InputStream is = null;
//        try {
//            is = new ByteArrayInputStream(data);
//            OutputStream out = response.getOutputStream();
//            byte[] buf = new byte[1024];
//            int len = -1;
//            while ((len = is.read(buf)) != -1) {
//                out.write(buf, 0, len);
//            }
//            out.flush();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
