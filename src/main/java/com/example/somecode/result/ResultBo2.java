package com.example.somecode.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 接口返回结果
 */
@Setter
@Getter
public class ResultBo2 {

    // 接口返回状态
    private int code;

    // 提示信息
    private String message;

    // 具体返回对象数据
    private Object data;
}
