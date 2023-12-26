package com.example.somecode.io.readers;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CharReader {

    /**
     * 字节数组流读
     */
    public static void read() throws IOException {
        char[] chars = "我是四郎".toCharArray();
        CharArrayReader reader = new CharArrayReader(chars);
        //读取源bytes中的数据
        int n;
        while ((n = reader.read()) != -1) {
            System.out.print((char) n);
        }
    }

    /**
     * 字节数组流写
     */
    public static void write() throws IOException {
        char[] result;
        char[] chars = "我是四郎".toCharArray();
        CharArrayWriter writer = new CharArrayWriter();
        writer.write(chars);
        result = writer.toCharArray();
        for (char a : chars) {
            System.out.print(a);
        }
        for (char b : result) {
            System.out.print(b);
        }
    }
}
