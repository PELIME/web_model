package com.pelime.ecms.modules.sys.service;

import com.pelime.ecms.modules.sys.dao.SysMenuDao;
import com.pelime.ecms.modules.sys.dao.SysRoleDao;
import com.pelime.ecms.modules.sys.entity.SysMenuEntity;
import com.pelime.ecms.modules.sys.entity.SysRoleEntity;
import com.pelime.ecms.modules.sys.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuService {

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysMenuDao sysMenuDao;

    @Value("${server.servlet.context-path}")
    String contextPath;
    /**
     * 用于缓存菜单信息，减少数据库访问
     * 注意：更新角色与菜单对后需要刷新该缓存
     */
    private Map<String,List<MenuModel>
            > memoryMenuByRoleName=new HashMap<>();

    private Map<String,String> menuHtmlCache=new HashMap<>();

    public String getMenuHtml(String roleName,String activeName){
        //先从缓存里区
        if(menuHtmlCache.containsKey(roleName)){
            return menuHtmlCache.get(roleName);
        }
        List<MenuModel> models=getMenu(roleName,activeName);
        StringBuilder sb=new StringBuilder();
        for(MenuModel mm : models){
            sb.append(mm.toString());
        }
        String menuString=sb.toString();
        menuHtmlCache.put(roleName,menuString);
        return menuString;
    }
    public List<MenuModel> getMenu(String roleName,String activeName){
        if(memoryMenuByRoleName.containsKey(roleName)){
            return memoryMenuByRoleName.get(roleName);
        }
        SysRoleEntity roleEntity= sysRoleDao.findByRoleName(roleName);
        //如果该角色名为“超级管理员” 将获取所有目录
        List<SysMenuEntity> menuEntities=roleName.equals("超级管理员")?sysMenuDao.findAll():roleEntity.getMenus();
        List<MenuModel> menuModels=new LinkedList<>();
        buildMenuMoles(menuModels,menuEntities,0l,activeName);
        memoryMenuByRoleName.put(roleName,menuModels);
        return menuModels;
    }

    private void buildMenuMoles(List<MenuModel> models,List<SysMenuEntity> menuEntities,Long nodeId,String activeName){
        //获取所有一级目录
        for(SysMenuEntity sme : menuEntities){
            if(sme.getMenuId()!=nodeId&&sme.getParentId()==nodeId){
                MenuModel mm=new MenuModel();
                mm.setName(sme.getName());
                if(activeName.equals(sme.getName())){
                    mm.setActive(true);
                }
                else {
                    mm.setActive(false);
                }
                mm.setType(sme.getType());
                mm.setContextPath(contextPath);
                mm.setOpenType(sme.getOpenMode());
                mm.setUrl(sme.getUrl());
                mm.setSort(sme.getOrderNum());
                mm.setChildrens(new LinkedList<MenuModel>());
                models.add(mm);
                buildMenuMoles(mm.getChildrens(),menuEntities,sme.getMenuId(),activeName);
            }
        }
    }

    public SysMenuEntity addMenu(String name,String url,Integer order,Long parentId,Integer type){
        SysMenuEntity menuEntity=new SysMenuEntity();
        menuEntity.setName(name);
        menuEntity.setOrderNum(order);
        menuEntity.setParentId(parentId);
        menuEntity.setType(type);
        menuEntity.setUrl(url);
        return sysMenuDao.save(menuEntity);
    }

    public SysMenuEntity updateMenu(Long id,String name,String url,Integer order,Long parentId,Integer type){
        SysMenuEntity menuEntity=new SysMenuEntity();
        menuEntity.setMenuId(id);
        menuEntity.setName(name);
        menuEntity.setOrderNum(order);
        menuEntity.setParentId(parentId);
        menuEntity.setType(type);
        menuEntity.setUrl(url);
        return sysMenuDao.save(menuEntity);
    }
    /**
     * 将联级删除子目录
     * @param id
     */
    public void deleteMenuById(Long id){
        List<SysMenuEntity> ch=sysMenuDao.findAllByParentId(id);
        if(ch.size()==0){
            sysMenuDao.deleteById(id);
        }
        else {
            for(SysMenuEntity se : ch){
                deleteMenuById(se.getMenuId());
            }
        }
    }








}
