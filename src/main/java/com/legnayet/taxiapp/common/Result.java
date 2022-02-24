package com.legnayet.taxiapp.common;

import com.legnayet.taxiapp.constant.CodeConstant;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 200;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = CodeConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    public Result<T> okData(T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setResult(data);
        return r;
    }


    public static Result<Object> ok() {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> strToResult(String str) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setResult(str);
        return r;
    }


    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setResult(data);
        return r;
    }

    public static Result<Object> ok(CommonError data) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(data.getType());
        r.setResult(data.getText());
        return r;
    }


    public static Result<Object> ok(String msg, Object data) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SC_OK_200);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static Result error(String msg) {
        return error(CodeConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }


    public Result<T> error500(String message) {
        this.message = message;
        this.code = CodeConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noAuth(String msg) {
        return error(CodeConstant.SC_NO_AUTH, msg);
    }


}
