package com.example.somecode.io.other;

import java.io.*;
import java.util.Properties;

public class Property {

    public static void main(String[] args) throws IOException {
        //把集合中的数据保存到文件
        myStore();

        //把文件中的数据保存到集合
        myLood();
    }

    private static void myLood() throws IOException{
        Properties prop = new Properties();

        FileReader fr = new FileReader("D:\\test\\java.txt");
        //void load(Reader reader)
        prop.load(fr);
//        prop.load(new FileInputStream("D:\\test\\java.txt"));
        fr.close();

        System.out.println(prop); //{003=沸羊羊, 002=懒羊羊, 001=喜羊羊}
    }

    private static void myStore() throws IOException {
        Properties prop = new Properties();

        prop.setProperty("001", "喜羊羊");
        prop.setProperty("002", "懒羊羊");
        prop.setProperty("003", "沸羊羊");

        //void store(Writer writer, String comments)
        FileWriter fw = new FileWriter("D:\\test\\java.txt");
        prop.store(fw,null);//第二个值为注释
//        prop.store(new FileOutputStream("D:\\test\\java.txt"),null);//第二个值为注释
        fw.close();
    }
}
