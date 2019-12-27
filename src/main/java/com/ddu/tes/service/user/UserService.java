package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.user.CreateUserRequestModel;
import com.ddu.tes.controller.model.user.CreateUserResponseModel;
import com.ddu.tes.controller.model.user.EditUserRequestModel;
import com.ddu.tes.controller.model.user.EditUserResponseModel;

public interface UserService {
    public CreateUserResponseModel createUser(CreateUserRequestModel confirmCreateUser);
    public boolean userEmailExist(String email );
    public EditUserResponseModel editUser(EditUserRequestModel confirmEditUser);

    public GetUserByEmailResult getUserByEmail (final String email);
    public GetUserByPhoneResult getUserByPhone(final String phoneNumber);
    public GetAllUserListResult getAllUsers();

}
