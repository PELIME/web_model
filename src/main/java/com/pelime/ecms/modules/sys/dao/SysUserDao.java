package com.pelime.ecms.modules.sys.dao;

import com.pelime.ecms.modules.sys.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao extends JpaRepository<SysUserEntity,Long> {
    SysUserEntity findByUsername(String  username);

    @Query(value = "SELECT m.perms FROM sys_user_role ur " +
            "LEFT JOIN sys_role_menu rm ON ur.role_id=rm.role_id " +
            "LEFT JOIN sys_menu m ON rm.menu_id=m.menu_id " +
            "WHERE ur.user_id=?0",nativeQuery = true)
    List<String> findAllPerms(Long userId);
}
