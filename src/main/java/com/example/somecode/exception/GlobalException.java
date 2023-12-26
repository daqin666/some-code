package com.example.somecode.exception;

import com.example.somecode.result.ResultBo;
import com.example.somecode.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *  全局异常处理
 */
//@ControllerAdvice
@RestControllerAdvice
public class GlobalException {

    private final static Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBo myExceptionHandle(MyException ex) {
        logger.error("【系统异常】{}", ex);
        return ResultUtil.failure("系统异常");
    }
}
