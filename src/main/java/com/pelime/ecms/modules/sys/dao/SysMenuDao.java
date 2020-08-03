package com.pelime.ecms.modules.sys.dao;

import com.pelime.ecms.modules.sys.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDao extends JpaRepository<SysMenuEntity,Long> {
    List<SysMenuEntity> findAllByParentId(Long parentId);

}
