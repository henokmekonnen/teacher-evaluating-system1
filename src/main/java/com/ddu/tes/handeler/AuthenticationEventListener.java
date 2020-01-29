package com.ddu.tes.handeler;

import com.ddu.tes.data.modle.User;
import com.ddu.tes.security.SecurityInfoMgn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {

    @Autowired
    SecurityInfoMgn securityInfoManager;
    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        try {
            String username = (String) event.getAuthentication().getPrincipal();

            User user = securityInfoManager.getUserByUsername(username);

            if(user == null){

                throw  new UsernameNotFoundException("User not found");
            }

//            securityInfoManager.updateFailAttempts(user);

        }catch (Exception ex){
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}
