package com.pelime.ecms.modules.sys.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity(name = "sys_user")
public class SysUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false,columnDefinition = "varchar(100)")
    private String password;

    @Column(columnDefinition = "varchar(50)")
    @Pattern(regexp = "^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$",message = "邮箱格式有误")
    private String email;

    @Column(columnDefinition = "varchar(11)")
    @Pattern(regexp = "1[0-9]{10}",message = "手机号码格式错误")
    private String phone;

    private String salt;
    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")},foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
        inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<SysRoleEntity> roles;

    public SysRoleEntity getActiveRole() {
        if(activeRole==null){
            activeRole=this.getRoles().get(0);
        }
        return activeRole;
    }

    public void setActiveRole(SysRoleEntity activeRole) {
        this.activeRole = activeRole;
    }

    @Transient
    private SysRoleEntity activeRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SysRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleEntity> roles) {
        this.roles = roles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
