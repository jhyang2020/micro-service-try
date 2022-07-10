package com.fh.tryutil.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author jhYang
 * @Date 2022/7/10 0010 16:26
 * @Discription todo
 */
public class FileHandleUtils {

    /**
     * 将字符内容输出到文件
     * @param bytes  字节内容
     * @param fileName 文件名
     */
    public static void getFileByBytes(byte[] bytes ,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File file = new File(fileName);

            //输出流
            fos = new FileOutputStream(file);

            //缓冲流
            bos = new BufferedOutputStream(fos);

            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
