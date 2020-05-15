package com.iscolt.micm.provider.repository;

import com.iscolt.micm.provider.entity.UmsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * xx
 *
 * @Author: https://github.com/isColt
 * @Date: 3/30/2020
 * @Description: com.iscolt.micm.provider.repository
 * @version: v1.0.0
 */
public interface UmsAdminRepository extends JpaRepository<UmsAdmin, Integer> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    UmsAdmin findByUsername(String username);

}
