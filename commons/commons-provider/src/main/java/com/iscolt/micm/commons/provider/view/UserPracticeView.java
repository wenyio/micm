package com.iscolt.micm.commons.provider.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 用户报名的活动信息及是否参加等信息
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/12
 * @see: com.iscolt.micm.commons.provider.view
 * @version: v1.0.0
 */
@Data
public class UserPracticeView implements Serializable {

    private static final long serialVersionUID = -5122256240096240891L;
    // 用户信息
    private BigInteger id; // service_user_practice 表id
    private Integer status; // 1正常参加，0缺席，2未签到（迟到），3未签退（早退）
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created; // 报名时间

    // 活动信息
    private BigInteger practice_id;
    private String title;
    private String icon;
    private Integer integral; // 积分

    // 活动分类信息
    private String category_name;

    public UserPracticeView() {
    }

    public UserPracticeView(BigInteger id, Integer status, Timestamp created, BigInteger practice_id, String title, String icon, Integer integral, String category_name) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.practice_id = practice_id;
        this.title = title;
        this.icon = icon;
        this.integral = integral;
        this.category_name = category_name;
    }
}
