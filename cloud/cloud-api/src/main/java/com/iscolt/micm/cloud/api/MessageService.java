package com.iscolt.micm.cloud.api;

import com.iscolt.micm.cloud.dto.SysLogDTO;
import com.iscolt.micm.cloud.dto.UmsAdminLoginLogDTO;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.cloud.api
 * @version: v1.0.0
 */
public interface MessageService {

    boolean sendSysLog(SysLogDTO dto);

    boolean sendEmail(String email);
}
