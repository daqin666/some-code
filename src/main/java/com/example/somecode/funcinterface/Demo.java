package com.example.somecode.funcinterface;

public class Demo {


    public void test() {
        String param = "aa";
        execute(param, new ConnectHandler() {
            @Override
            public void handle(String param) {
                System.out.println(param);
            }
        });
    }

    public void execute(String param, ConnectHandler handler) {
        // 1.获取连接

        // 2.执行逻辑
        handler.handle(param);

        // 3.关闭连接
    }
}
