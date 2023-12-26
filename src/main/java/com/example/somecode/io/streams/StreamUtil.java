package com.example.somecode.io.streams;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class StreamUtil {


    /**
     * 字节流数据读写
     * @param inPath 输入文件路径
     * @param outPath 输出文件路径
     */
    public static void streamIO(String inPath, String outPath){
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inPath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath, true))) {
            byte[] bys = new byte[1024];
            int len;
            while ((len = bis.read(bys)) != -1) {
                bos.write(bys, 0, len);
            }
        } catch (IOException ex) {
            log.error("streamIO error...", ex);
        }
    }

    /**
     * 字节流数据读
     * @param inPath 输入文件路径
     * @return --
     */
    public static String streamRead(String inPath){
        StringBuilder sb = new StringBuilder(1024);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inPath))) {
            byte[] bys = new byte[1024];
            int len;
            while ((len = bis.read(bys)) != -1) {
                sb.append(new String(bys, 0, len));
            }
        } catch (IOException ex) {
            log.error("streamRead error...", ex);
        }
        return sb.toString();
    }

    /**
     * 字节流数据写
     * @param data 输出文件路径
     * @param outPath 输出文件路径
     */
    public static void streamWrite(String data, String outPath) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath, true))) {
            bos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            log.error("streamWrite error...", ex);
        }
    }



}
