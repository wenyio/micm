package com.iscolt.micm.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 个人照片修改
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/4/2020
 * @see: com.iscolt.micm.business.dto.params
 * @version: v1.0.0
 */
@Data
public class FaceParam implements Serializable {

    private static final long serialVersionUID = 194466767847206664L;
    private String username;
    private String path;

}
