package com.ddu.tes.service.userrole;

import com.ddu.tes.controller.model.userrole.EditUserRoleRequestModel;
import com.ddu.tes.controller.model.userrole.EditUserRoleResponseModel;

public interface UserRoleService {
    public GetUserRoleList getAllUsersRole();
    public EditUserRoleResponseModel editUserRole(EditUserRoleRequestModel confirmEditUserRole);
    public GetUserRoleId getUserByRoleId(Integer roleId);
}
