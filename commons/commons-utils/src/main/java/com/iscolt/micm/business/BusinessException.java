package com.iscolt.micm.business;

/**
 * 全局业务异常
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business
 * @version: v1.0.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2134460000803492942L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(BusinessStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
