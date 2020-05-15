package com.iscolt.micm.commons.model.dto;

/**
 * 响应状态码
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.commons.dto
 * @version: v1.0.0
 */
public enum ResponseCode {

    /**
     * 成功
     */
    OK(true, 20000, "操作成功!"),

    /**
     * 失败
     */
    FAIL(false, 20002, "操作失败!"),

    /**
     * 熔断请求
     */
    BREAKING(false, 20004, "熔断请求!"),

    /**
     * 非法请求
     */
    ILLEGAL_REQUEST(false, 50000, "非法请求!"),

    /**
     * 非法的 Token
     */
    ILLEGAL_TOKEN(false, 50008, "非法的 Token!"),

    /**
     * 其他账号已登录
     */
    OTHER_CLIENTS_LOGGED_IN(false, 50012, "其他账号已登录!"),

    /**
     * Token 超时
     */
    TOKEN_EXPIRED(false, 50014, "Token 超时!");

    /**
     * 操作是否成功
     */
    boolean success;

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;

    ResponseCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 操作是否成功
     * @return
     */
    public boolean success() {
        return success;
    }

    /**
     * 获取操作码
     * @return
     */
    public int code() {
        return code;
    }

    /**
     * 获取提示信息
     * @return
     */
    public String message() {
        return message;
    }
}
