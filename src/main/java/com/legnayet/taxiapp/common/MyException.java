package com.legnayet.taxiapp.common;

/**
 * Created by yechenhao
 * Description 自定义参数非法异常
 * Date 2021/8/2 14:00
 */
public class MyException extends RuntimeException {

    protected String message;


    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
