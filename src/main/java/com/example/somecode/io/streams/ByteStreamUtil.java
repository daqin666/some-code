package com.example.somecode.io.streams;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ByteStreamUtil {

    /**
     * 字节数组流读
     */
    public static void read() {
        //定义字节数组输入流的源
        byte[] bytes = "我是四郎".getBytes(StandardCharsets.UTF_8);
        //根据源创建字节数组输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        //读取源bytes中的数据
        int n;
        while ((n=inputStream.read())!=-1){
            System.out.print((char)n);
        }
    }

    /**
     * 字节数组流写
     */
    public static void write() throws IOException {
        byte[] result;
        byte[] bytes = "我是四郎".getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes);
        result = outputStream.toByteArray();
        for (byte a:bytes){
            System.out.print(a);
        }
        for (byte b:result){
            System.out.print(b);
        }
    }

}
