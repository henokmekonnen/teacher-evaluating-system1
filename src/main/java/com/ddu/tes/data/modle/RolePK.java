package com.ddu.tes.data.modle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author GHabtamu
 */
public class RolePK implements Serializable {
    private Integer roleId;
    private String lookUpId;

    @Column(name = "RoleId", nullable = false)
    @Id
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "LookUpId", nullable = false, length = 10)
    @Id
    public String getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(String lookUpId) {
        this.lookUpId = lookUpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePK rolePK = (RolePK) o;
        return Objects.equals(roleId, rolePK.roleId) &&
                Objects.equals(lookUpId, rolePK.lookUpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, lookUpId);
    }
}
