package com.pelime.ecms.modules.sys.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "sys_role")
public class SysRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false,columnDefinition = "varchar(40)",unique = true)
    private String roleName;

    /**
     * 备注
     */
    @Column(columnDefinition = "varchar(100)")
    private String remark;

    private Date createTime;

    @ManyToMany(mappedBy = "roles")
    private List<SysUserEntity> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_role_menu",joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name="menu_id")},foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<SysMenuEntity> menus;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SysUserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<SysUserEntity> users) {
        this.users = users;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SysMenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenuEntity> menus) {
        this.menus = menus;
    }

}
