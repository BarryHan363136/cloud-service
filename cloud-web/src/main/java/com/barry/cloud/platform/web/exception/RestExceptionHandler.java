package com.barry.cloud.platform.web.exception;

import com.barry.cloud.platform.web.utils.ResultGenerator;
import com.barry.cloud.platform.web.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description: 全局异常处理
 * @date 2018/9/5 15:19
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * 默认统一异常处理
     * @param e,Exception,异常对象
     *
     * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result runtimeExceptionHandler(Exception e){
        return ResultGenerator.genFailResult(e.getMessage());
    }




}
