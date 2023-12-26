package com.example.somecode.clone;

import com.esotericsoftware.kryo.Kryo;
import org.springframework.beans.BeanUtils;

public class Client {

    // 线程安全
    static private final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 在此处配置kryo对象的使用示例，如循环引用等
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    public static void main(String[] args) {
        Student stu = new Student();
        stu.setName("zhangsan");
        stu.setAge(18);

        Classroom cr = new Classroom();
        cr.setRoomNum("001");
        cr.setStudent(stu);
        System.out.println("cr :" + cr + "  stu:" + cr.getStudent());

        Classroom cr1 = new Classroom();
        BeanUtils.copyProperties(cr, cr1);
        System.out.println("cr1:" + cr1 + "  stu:" + cr1.getStudent());

        Classroom cr2 = cr.clone();
        System.out.println("cr2:" + cr2 + "  stu:" + cr2.getStudent());

//        Kryo kryo = new Kryo();
//        kryo.setRegistrationRequired(false); // 不要会报错

        Kryo kryo = kryos.get();
        Classroom cr3 = kryo.copy(cr);
        System.out.println("cr3:" + cr3 + "  stu:" + cr3.getStudent());

    }
}
