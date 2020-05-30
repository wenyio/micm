package com.iscolt.micm.provider.repository;

import com.iscolt.micm.commons.base.repository.BaseRepository;
import com.iscolt.micm.provider.entity.SysService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 2020/5/16
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
public interface SysServiceRepository extends BaseRepository<SysService, Integer>, JpaRepository<SysService, Integer> {
}
