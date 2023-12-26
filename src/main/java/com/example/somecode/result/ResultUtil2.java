package com.example.somecode.result;

/**
 * 接口返回结果Result的工具类
 */
public class ResultUtil2 {

    public static ResultBo2 success() {
        ResultBo2 result = new ResultBo2();
        result.setCode(200);
        return result;
    }

    public static ResultBo2 success(String message) {
        ResultBo2 result = new ResultBo2();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    public static ResultBo2 success(Object obj) {
        ResultBo2 result = new ResultBo2();
        result.setCode(200);
        result.setData(obj);
        return result;
    }

    public static ResultBo2 success(Object obj, String message) {
        ResultBo2 result = new ResultBo2();
        result.setCode(200);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }

    public static ResultBo2 failure() {
        ResultBo2 result = new ResultBo2();
        result.setCode(-200);
        return result;
    }

    public static ResultBo2 failure(String message) {
        ResultBo2 result = new ResultBo2();
        result.setCode(-200);
        result.setMessage(message);
        return result;
    }

    public static ResultBo2 failure(Object obj) {
        ResultBo2 result = new ResultBo2();
        result.setCode(-200);
        result.setData(obj);
        return result;
    }

    public static ResultBo2 failure(Object obj, String message) {
        ResultBo2 result = new ResultBo2();
        result.setCode(-200);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }
}
