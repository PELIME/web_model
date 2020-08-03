package com.pelime.ecms.modules.sys.dao;

import com.pelime.ecms.modules.sys.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends JpaRepository<SysRoleEntity,Long> {
    List<SysRoleEntity> findAllByRoleNameIn(List<String> names);
    SysRoleEntity findByRoleName(String roleName);
    void deleteByRoleName(String roleName);
}
