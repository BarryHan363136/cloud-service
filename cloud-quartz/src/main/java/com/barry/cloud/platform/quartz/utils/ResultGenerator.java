package com.barry.cloud.platform.quartz.utils;


import com.barry.cloud.platform.quartz.vo.Result;
import com.barry.cloud.platform.quartz.vo.ResultCodeEnum;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/16 15:11
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    //成功
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCodeEnum.FAIL)
                .setMessage(message);
    }

    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResultCodeEnum.UNAUTHORIZED)
                .setMessage("权限不足！");
    }

}