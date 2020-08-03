package com.pelime.ecms.api;

import com.pelime.ecms.common.R;
import com.pelime.ecms.modules.sys.entity.SysRoleEntity;
import com.pelime.ecms.modules.sys.service.SysRoleSevice;
import com.pelime.ecms.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/role")
public class RoleApi {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleSevice sysRoleSevice;

    @RequestMapping("/add")
    public R add( @RequestParam("roleName") String roleName){
        try {
            SysRoleEntity roleEntity=new SysRoleEntity();
            roleEntity.setRoleName(roleName);
            roleEntity.setCreateTime(new Date());
            sysRoleSevice.createOrUpdateRole(roleEntity);
            return R.ok("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @RequestMapping("delete")
    public R delete( @RequestParam("roleName") String roleName){
        try {
            sysRoleSevice.deleteRoleByRoleName(roleName);
            return R.ok("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
