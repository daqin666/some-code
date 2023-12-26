package com.example.somecode.enums;

public enum TestEnum {
    RED("RED", 1),
    GREEN("GREEN", 2),
    BLACK("BLACK", 3);

    private String color;
    private int ori;

    // 构造方法
    private TestEnum(String color, int ori) {
        this.color = color;
        this.ori = ori;
    }

    // 主方法
    public static void main(String[] args) {
        TestEnum[] values = TestEnum.values();
        for (TestEnum value : values) {
            System.out.println("value: " + value +
                    " ,ori: " + value.ordinal() +
                    " ,name: " + value.name());
        }

        System.out.println("======================");
        System.out.println("TestEnum: " + TestEnum.valueOf("RED"));
        System.out.println("======================");
        System.out.println(RED.compareTo(BLACK));
        System.out.println(BLACK.compareTo(GREEN));
    }
}

