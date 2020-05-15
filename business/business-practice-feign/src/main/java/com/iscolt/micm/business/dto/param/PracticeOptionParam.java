package com.iscolt.micm.business.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 活动报名，签到等操作
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/13
 * @see: com.iscolt.micm.business.dto.param
 * @version: v1.0.0
 */
@Data
public class PracticeOptionParam implements Serializable {

    private static final long serialVersionUID = -3393367297876291965L;
    private Integer userId;
    private long practiceId;
}
