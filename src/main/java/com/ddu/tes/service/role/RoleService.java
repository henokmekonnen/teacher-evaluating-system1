package com.ddu.tes.service.role;

import com.ddu.tes.data.modle.Role;

public interface RoleService {

    public GetAllRoleList getAllRole();
    public Role getRoleById(Integer roleId);

}
