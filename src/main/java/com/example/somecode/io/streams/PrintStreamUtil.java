package com.example.somecode.io.streams;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrintStreamUtil {

    public static void test() {
        //public static final PrintStream out：标准输出流
        PrintStream ps = System.out;

        //能够方便地打印各种数据值
//        ps.print("hello");
//        ps.print(100);

//        ps.println("hello");
//        ps.println(100);

        //System.out的本质是一个字节输出流
        System.out.println("hello");
        System.out.println(100);

        System.out.println();
//        System.out.print();
    }

    public void test2() throws FileNotFoundException {

        //PrintStream(String fileName)：使用指定的文件名创建新的打印流
        PrintStream ps = new PrintStream("D:\\test\\Demo.java");

        //写数据
        //字节输出流有的方法
//        ps.write(97);

        //使用特有方法写数据
//        ps.print(97);
//        ps.println();
//        ps.print(98);
        ps.println(97);
        ps.println(98);

        //释放资源
        ps.close();
    }
}
