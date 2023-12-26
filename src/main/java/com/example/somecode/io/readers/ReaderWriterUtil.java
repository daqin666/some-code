package com.example.somecode.io.readers;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.DocumentProperties;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ReaderWriterUtil {

    public static void readWrite(String source, String dest) {
        try (FileReader reader = new FileReader(source);
             BufferedReader br = new BufferedReader(reader);
             FileWriter writer = new FileWriter(dest);
             BufferedWriter bw = new BufferedWriter(writer)) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String source) {
        StringBuilder sb = new StringBuilder(1024);
        try (FileReader reader = new FileReader(source);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void write(String str, String dest) {
        try (FileWriter writer = new FileWriter(dest);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void streamToReader() {
        try(OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("D:\\test.txt"),"GBK");
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream("D:\\test.txt"),"GBK")) {
            osw.write("中国");

            int ch;
            while((ch= isr.read()) !=-1) {
                System.out.print((char)ch); //1:�й�;2:中国,因为第二次和编码格式GBK一样
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
