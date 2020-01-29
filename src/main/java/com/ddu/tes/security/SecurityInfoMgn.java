package com.ddu.tes.security;

import com.ddu.tes.controller.ChangePasswordRequest;
import com.ddu.tes.controller.ChangePasswordResponse;
import com.ddu.tes.data.modle.Role;
import com.ddu.tes.data.modle.User;

import java.util.Collection;


public interface SecurityInfoMgn {
    public Collection<Role> getRolesByUserId(int userId);
    public User getUserByUsername(String username);
    public User getCurrentUser();
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);
}
