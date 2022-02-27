package com.legnayet.taxiapp.common;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author dexiang
 * @date 2017年3月25日
 * @details 基础的 Controller
 */
public class BaseController implements MessageSourceAware {


    protected MessageSource messageSource;

    public Map<String, Object> pagedResult(List<?> results, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("results", results);
        map.put("rows", count);
        return map;
    }

    public Map<String, Object> fail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        map.put("code", "0001");
        return map;
    }

    public Map<String, Object> fail(int size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        map.put("code", "0002");
        return map;
    }

    public Map<String, Object> fail(String[] str) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        map.put("code", str[0]);
        return map;
    }

    public Map<String, Object> fail(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        String message = messageSource.getMessage(code, null, null, null);
        if (message == null) {
            message = code;
        }
        map.put("msg", message);
        return map;
    }

    public Map<String, Object> fail(String code, Object args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        map.put("code", code);
        map.put("msg", args);
        return map;
    }

    public Map<String, Object> fail(String code, Object[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("failure", true);
        String message = messageSource.getMessage(code, args, "Fail!", null);
        map.put("msg", message);
        return map;
    }

    public Map<String, Object> data(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("results", data);
        map.put("code", "0000");
        map.put("success", true);
        return map;
    }

    public Map<String, Object> data(String name, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, data);
        map.put("code", "0000");
        map.put("success", true);
        return map;
    }

    public Map<String, Object> fails(String code, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("results", data);
        map.put("code", code);
        map.put("success", false);
        return map;
    }

    /**
     * 成功
     *
     * @return
     */
    public Map<String, Object> success() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("code", "0000");
        return map;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public int getStart(int page, int size) {
        int start = (page - 1) * size;
        return start < 0 ? 0 : start;
    }

}
