package com.pelime.ecms.modules.sys.model;

import java.util.List;

public class MenuModel {
    private Integer sort;
    private String name;
    private String url;
    private String openType;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer type;
    private boolean active;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    private String contextPath;
    private List<MenuModel> childrens;
    /**
     * 开始的字符串
     */
    private String startString;
    /**
     * 子菜单字符串
     */
    private String contentString;

    /**
     * 结束字符串
     */
    private String endString;

    @Override
    public String toString() {
        StringBuilder ssb=new StringBuilder("<li");
        if(this.active){
            ssb.append(" class='active'");
        }

        if(this.type==0){
            ssb.append("><a href='#'>"+this.name+"</a>");
        }
        else {
            ssb.append("><a href='"+this.contextPath+this.url+"'>"+this.name+"</a>");
        }
        this.startString=ssb.toString();
        //构建便签内部代码
        if(this.childrens==null||this.childrens.size()==0){
            this.contentString="";
        }
        else {
            StringBuilder csb=new StringBuilder("<ul>");
            for(MenuModel mm : this.childrens){
                csb.append(mm.toString());
            }
            csb.append("</ul>");
            this.contentString=csb.toString();
        }
        this.endString="</li>";
        return startString+contentString+endString;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<MenuModel> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<MenuModel> childrens) {
        this.childrens = childrens;
    }

}
