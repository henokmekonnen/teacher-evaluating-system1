package com.ddu.tes.security;

import com.ddu.tes.data.modle.LookUp;
import com.ddu.tes.data.modle.User;

import java.util.Collection;

public interface SecurityInfoMgn {
    public Collection<LookUp> getRolesByUserId(int userId);
    public User getUserByUsername(String username);
    public User getCurrentUser();
}
