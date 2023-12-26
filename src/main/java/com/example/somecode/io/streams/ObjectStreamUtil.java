package com.example.somecode.io.streams;

import com.example.somecode.io.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ObjectStreamUtil {

    /**
     * 操作的对象必须实现了序列话
     */

    public static void read() {
        try (FileInputStream fis = new FileInputStream("person.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // 从文件中读取 Person 对象
            Person person = (Person) ois.readObject();
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try (FileOutputStream fos = new FileOutputStream("person.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            Person person = new Person("zhangsan", 18);
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
