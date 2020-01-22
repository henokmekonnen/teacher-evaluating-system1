package com.ddu.tes.security;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.config.UserPrincipal;
import com.ddu.tes.data.modle.LookUp;
import com.ddu.tes.data.modle.UserRole;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.lookup.LookUpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class SecurityInfoMgnImpl implements SecurityInfoMgn {
    private static final Log logger = LogFactory.getLog(SecurityInfoMgnImpl.class);
    @Autowired
    private SqlRepository repository;

    @Autowired
    private LookUpService lookUpService;

    @Override
    public Collection<LookUp> getRolesByUserId(int userId) {

        try {
            UserRole filter = new UserRole();
            filter.setUserId(userId);

            final List<Object> userRoles = repository.find(filter);

            if (userRoles == null) {

                 }

            Collection<LookUp> roleList = new ArrayList<>();

            for (Object role : userRoles) {

                UserRole userRole = (UserRole) role;

                LookUp lookup = lookUpService.getLookupById(userRole.getRoleId().toString());

                if (lookup == null) {
                       logger.error("Error while fetching lookup type status type  ");
                     }

                roleList.add(lookup);
            }


            return roleList;

        }catch (Exception ex){

            logger.error("Error while fetching organization type status type  ", ex);
            throw ex;
        }

    }

    @Override
    public com.ddu.tes.data.modle.User getUserByUsername(String username) {

        try {

            final User filter = new User();
            filter.setUserName(username);

            final Object result = repository.findOne(filter);

            return result != null ? (User) result : null;
        } catch (Exception ex) {

            logger.error("Error fetching user with user name " + username, ex);
            throw ex;
        }

    }

    @Override
    public User getCurrentUser() {

        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (null == authentication) {
                return null;
            }

            UserPrincipal userPrincipal;
            if ((authentication.getPrincipal() instanceof UserPrincipal)) {

                userPrincipal = (UserPrincipal) authentication.getPrincipal();

                final String userName = userPrincipal.getUsername();

                User user = getUserByUsername(userName);

                return user == null ? null : user;
            }


        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return null;
    }

}
