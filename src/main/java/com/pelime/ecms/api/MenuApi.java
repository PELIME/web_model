package com.pelime.ecms.api;

import com.pelime.ecms.common.R;
import com.pelime.ecms.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/menu")
public class MenuApi {

    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping("/add")
    public R add(String name,String url,Integer order,Long parentId,Integer type){
        try {
            sysMenuService.addMenu(name,url,order,parentId,type);
            return R.ok("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @RequestMapping("/update")
    public R update(Long id,String name,String url,Integer order,Long parentId,Integer type){
        try {
            sysMenuService.updateMenu(id,name,url,order,parentId,type);
            return R.ok("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    @RequestMapping("/delete")
    public R delete(Long id){
        try {
            sysMenuService.deleteMenuById(id);
            return R.ok("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
