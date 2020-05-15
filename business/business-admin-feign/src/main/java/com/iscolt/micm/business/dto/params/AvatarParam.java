package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 个人头像修改
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/1/2020
 * @see: com.iscolt.micm.business.dto
 * @version: v1.0.0
 */
@Data
public class AvatarParam implements Serializable {

    private static final long serialVersionUID = -4928237548230936718L;
    private String username;
    private String path;

}
