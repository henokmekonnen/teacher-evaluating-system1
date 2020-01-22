package com.ddu.tes.config;

import com.ddu.tes.data.modle.LookUp;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.security.SecurityInfoMgn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailService implements UserDetailsService {
    /**
     *
     */
    @Autowired
    @Qualifier("securityInfoMgnImpl")
    public SecurityInfoMgn securityManager;

    private static final int MAX_ATTEMPTS = 3;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = securityManager.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

//        if (user.getLocked()) {
//            throw new CredentialsExpiredException("Your account is locked");
//        }
//
//        if (!user.getEnabled()) {
//            throw new DisabledException("Account is Disabled");
//        }
//
//        if (user.getLoginAttempt() + 1 >= MAX_ATTEMPTS) {
//
//            throw new LockedException("Account has been locked contact administrator!");
//        }


        Collection<LookUp> roleLookUp =  securityManager.getRolesByUserId(user.getUserId());

        List<GrantedAuthority> authorities =new ArrayList<>();
        if(roleLookUp!=null) {
            authorities = roleLookUp.stream().map(role ->
                    new SimpleGrantedAuthority("ROLE_" + role.getLookUpId())
            ).collect(Collectors.toList());
        }

        UserDetails userDetails = UserPrincipal.create(user, authorities);
        return userDetails;
    }

}
