package com.example.somecode.uuid;

import java.security.SecureRandom;
import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取UUID
     * eg: eb0e7e18-05d2-4db7-a3a4-4a380e827102
     * @return --
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 获取UUID
     * 通过给定的字符串名称和命名空间生成UUID对象
     * eg: dc05e22d-59eb-37f3-b01d-9739c8ec7e5a
     * @return --
     */
    public static String getUUID2(){
        //通过给定的字符串名称和命名空间生成UUID对象
        UUID uuid = UUID.nameUUIDFromBytes("example_name".getBytes());
        return uuid.toString();
    }

    /**
     * 获取UUID
     * 将时间戳和随机数作为种子生成UUID
     * eg: 0000018c-9777-a2f4-0000-00001d5c63d1
     * @return --
     */
    public static String getUUID3(){
        long time = System.currentTimeMillis();
        int random = (int) (Math.random() * Integer.MAX_VALUE);
        UUID uuid = new UUID(time, random);
        return uuid.toString();
    }

    /**
     * 获取UUID
     * 利用SecureRandom类生成
     * eg: 95fde307-625e-35f0-8ba9-fdc1659ac0d8
     * @return --
     */
    public static String getUUID4(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return UUID.nameUUIDFromBytes(bytes).toString();
    }

    /**
     * 获取UUID
     * 利用SecureRandom类生成
     * eg: 527561p236254
     * @return --
     */
    public static String getUUID5(){
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(20);
        int n = 6;
        for(int i=0;i<n;i++){
            stringBuilder.append(random.nextInt(10));
        }

        //生成小写字母
        char ranLowLetter = (char) ((random.nextInt(26) + 97));
        //生成大写字母
        char ranUpLetter = (char) ((random.nextInt(26) + 65));

        stringBuilder.append(ranLowLetter);

        for(int i=0;i<n;i++){
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }
}
