package com.iscolt.micm.commons.constant;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/12
 * @see: com.iscolt.micm.commons.constant
 * @version: v1.0.0
 */
public enum PracticeConstant {

    /**
     * 正常参加: 签到+签退
     */
    HAVE_PARTICIPATED(11, "无法再次签到&&签退！", "无法再次签到&&签退！"),

    /**
     * 缺席： 未签到+未签退
     */
    DID_NOT_PARTICIPATE(0, "签到成功！", "签退成功！"),

    /**
     * 迟到： 未签到
     */
    NOT_CHECKED_IN(1, "已签退，无法签到", "无法再次签退"),

    /**
     * 早退： 未签退
     */
    NOT_CHECKED_OUT(10, "无法再次签到！", "签退成功！"),

    NOT_FOUND(404, "状态未知: 设为未签到状态！", "状态未知: 设为未签到状态！");

    /**
     * 状态
     */
    int code;
    /**
     * 签到操作返回的消息
     */
    String checkInMsg;
    /**
     * 签退操作返回的消息
     */
    String checkOutMsg;

    PracticeConstant(int code, String checkInMsg, String checkOutMsg) {
        this.code = code;
        this.checkInMsg = checkInMsg;
        this.checkOutMsg = checkOutMsg;
    }

    public int code() {
        return code;
    }

    public String checkInMsg() {
        return checkInMsg;
    }

    public String checkOutMsg() {
        return checkOutMsg;
    }
}
