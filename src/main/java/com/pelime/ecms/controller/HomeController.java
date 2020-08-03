package com.pelime.ecms.controller;

import com.pelime.ecms.modules.sys.entity.SysRoleEntity;
import com.pelime.ecms.modules.sys.entity.SysUserEntity;
import com.pelime.ecms.modules.sys.service.SysMenuService;
import com.pelime.ecms.modules.sys.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping({"/","/index"})
    public String index(Model model,@RequestParam(value = "role",defaultValue = "") String role){
        SysUserEntity user=ShiroUtils.getUserEntity();
        model.addAttribute("homeActive",true);
        if(user!=null){
            model.addAttribute("user",user);
            model.addAttribute("roles",user.getRoles());
            if(!role.equals("")){
                List<SysRoleEntity> roles= user.getRoles();
                for(SysRoleEntity r : roles){
                    if(r.getRoleName().equals(role)){
                        user.setActiveRole(r);
                    }
                }
            }
            model.addAttribute("activeRole",user.getActiveRole());
            model.addAttribute("menuString",sysMenuService.getMenuHtml(user.getActiveRole().getRoleName(),"首页"));
        }
        //获取该角色菜单

        return "index";
    }

    @GetMapping("/index/welcome")
    public String welcome(){
        return "home/welcome";
    }

    @GetMapping("index-tmp")
    public  String test(){
        return "index-tmp";
    }
}
