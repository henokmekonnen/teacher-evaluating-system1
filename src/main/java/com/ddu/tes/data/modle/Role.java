package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "role")
public class Role extends  BaseModel{
    private Integer roleId;
    private String name;
    private String description;


    @Id
    @Column(name = "RoleId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Transient
    @Override
    public Integer getId() {
        return getRoleId();
    }

    @Override
    public void setId(Integer id) {
      setRoleId(id);
    }
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(createdDate, role.createdDate) &&
                Objects.equals(createdBy, role.createdBy) &&
                Objects.equals(description, role.description) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, createdDate, createdBy, description);
    }
}
