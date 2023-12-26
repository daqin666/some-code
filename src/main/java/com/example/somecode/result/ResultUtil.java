package com.example.somecode.result;

/**
 * 接口返回结果Result的工具类
 */
public class ResultUtil {

    public static ResultBo success() {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static ResultBo success(String message) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.TRUE);
        result.setMessage(message);
        return result;
    }

    public static ResultBo success(Object obj) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.TRUE);
        result.setData(obj);
        return result;
    }

    public static ResultBo success(Object obj, String message) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.TRUE);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }

    public static ResultBo failure() {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.FALSE);
        return result;
    }

    public static ResultBo failure(String message) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.FALSE);
        result.setMessage(message);
        return result;
    }

    public static ResultBo failure(Object obj) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.FALSE);
        result.setData(obj);
        return result;
    }

    public static ResultBo failure(Object obj, String message) {
        ResultBo result = new ResultBo();
        result.setSuccess(Boolean.FALSE);
        result.setData(obj);
        result.setMessage(message);
        return result;
    }
}
