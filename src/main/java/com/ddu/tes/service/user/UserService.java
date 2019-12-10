package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.user.CreateUserRequestModel;
import com.ddu.tes.controller.model.user.CreateUserResponseModel;

public interface UserService {
    public CreateUserResponseModel createUser(CreateUserRequestModel confirmCreateUser);
    public GetUserByNameResult getUserByName (final String userNameme);
    public GetUserByEmailResult getUserByEmail (final String email);
    public GetUserByPhoneResult getUserByPhone(final String phoneNumber);
    public GetAllUserListResult getAllUsers();
}
