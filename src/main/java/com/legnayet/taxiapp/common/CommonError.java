package com.legnayet.taxiapp.common;

import lombok.Getter;

/**
 * 公用状态
 * @author: rice
 * @company: 上海大学新兴产业研究院
 * @date: 2021/4/26
 */
@Getter
public enum CommonError {

    /**
     * 公交报错类
     */
    YUNMA_ERROR("请求云码接口失败", 999),
    FAIL("失败", 99),
    NONE("不存在", -1),
    SUCCESS("操作成功", 200),;

    private final String text;

    private final int type;


    CommonError(String text, int type) {
        this.text = text;
        this.type = type;
    }
}
