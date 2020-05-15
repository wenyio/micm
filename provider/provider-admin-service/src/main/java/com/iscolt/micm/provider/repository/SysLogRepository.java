package com.iscolt.micm.provider.repository;

import com.iscolt.micm.provider.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

/**
 * xx
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/14/2020
 * @see: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
@Transactional
public interface SysLogRepository extends JpaRepository<SysLog, Integer> {

}
