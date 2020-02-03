package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "user_role")
public class UserRole extends BaseModel{
    private Integer userRole;
    private String description;
    private Integer roleId;
    private Integer userId;
    private String createdBy;


    @Id
    @Column(name = "user_role", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    @Transient
    public Integer getId() {
        return getUserRole();
    }

    @Override
    public void setId(Integer id) {
       setUserRole(id);
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = true, length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Basic
    @Column(name = "RoleId", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "UserId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole1 = (UserRole) o;
        return Objects.equals(userRole, userRole1.userRole) &&
                Objects.equals(createdDate, userRole1.createdDate) &&
                Objects.equals(createdBy, userRole1.createdBy) &&
                Objects.equals(description, userRole1.description) &&
                Objects.equals(roleId, userRole1.roleId) &&
                Objects.equals(userId, userRole1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRole, createdDate, createdBy, description, roleId, userId);
    }
}
