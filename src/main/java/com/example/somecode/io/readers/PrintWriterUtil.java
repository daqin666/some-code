package com.example.somecode.io.readers;

import java.io.*;

public class PrintWriterUtil {

    public void test() throws IOException {
        //PrintWriter(String fileName) ：使用指定的文件名创建一个新的PrintWriter，而不需要自动执行行刷新
//        PrintWriter pw = new PrintWriter("D:\\test\\java.txt");

        //PrintWriter(Writer out, boolean autoFlush)：创建一个新的PrintWriter
        PrintWriter pw = new PrintWriter(new FileWriter("D:\\\\test\\\\java.txt"),true);
//        PrintWriter pw = new PrintWriter(new FileWriter("D:\\test\\java.txt"),false);

        pw.println("hello");
        pw.println("world");

        pw.close();
    }

    public void test2() throws IOException {

        /*
        //根据数据源创建字符输入流对象
        BufferedReader br = new BufferedReader(new FileReader("D:\test\Demo.java"));
        //根据目的地创建字符输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\test\Demo\Demo.java"));
        //读写数据，复制文件
        String line;
        while ((line=br.readLine())!=null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        //释放资源
        bw.close();
        br.close();
        */

        //根据数据源创建字符输入流对象
        BufferedReader br = new BufferedReader(new FileReader("D:\\test\\Demo.java"));
        //根据目的地创建字符输出流对象
        PrintWriter pw = new PrintWriter(new FileWriter("D:\\test\\Demo\\Demo.java"),true);

        //读写数据，复制文件
        String line;
        while ((line=br.readLine())!=null) {
            pw.println(line);
        }

        //释放资源
        pw.close();
        br.close();
    }
}
