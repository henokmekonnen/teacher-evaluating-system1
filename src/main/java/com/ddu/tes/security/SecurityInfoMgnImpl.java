package com.ddu.tes.security;

import com.ddu.tes.config.UserPrincipal;
import com.ddu.tes.controller.ChangePasswordRequest;
import com.ddu.tes.controller.ChangePasswordResponse;
import com.ddu.tes.data.modle.Role;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.modle.UserRole;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.role.RoleService;
import com.ddu.tes.service.validation.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SecurityInfoMgnImpl implements SecurityInfoMgn{




     private static final Log logger = LogFactory.getLog(SecurityInfoMgnImpl.class);


    @Autowired
    private SqlRepository repository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Collection<Role> getRolesByUserId(int userId) {

        try {
            UserRole filter = new UserRole();
            filter.setUserId(userId);

            final List<Object> userRoles = repository.find(filter);

            if (userRoles == null) {

                 }

            Collection<Role> roleList = new ArrayList<>();

            for (Object role : userRoles) {

                UserRole userRole = (UserRole) role;

                Role role2 = roleService.getRoleById(userRole.getRoleId());

                if (role2 == null) {
                       logger.error("Error while fetching lookup type status type  ");
                     }

                roleList.add(role2);
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

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {

        ChangePasswordResponse restResponse = new ChangePasswordResponse();

        User user = getCurrentUser();

        if(user == null) {

            restResponse.setHasError(true);
            restResponse.setDescription("User not found");
            return restResponse;
        }

        if(StringUtils.isBlank(changePasswordRequest.getOldPassword())) {

            restResponse.setHasError(true);
            restResponse.setDescription("Please provide old password");
            return restResponse;

        }

        if(StringUtils.isBlank(changePasswordRequest.getNewPassword())) {

            restResponse.setHasError(true);
            restResponse.setDescription("Please provide new password");
            return restResponse;

        }

        //Validate old password
        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {

            restResponse.setHasError(true);
            restResponse.setDescription("Please must be different from existing one");
            return restResponse;

        }

        // validate new password
        if(!validationService.isPasswordValid(changePasswordRequest.getNewPassword())) {

            restResponse.setHasError(true);
            restResponse.setDescription("Please follow the password standard ");
            return restResponse;
        }

        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {


        }

        try {

            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            /*user.setTempPassword(null);
            user.setPasswordChangeRequired(false);*/

            repository.update(user);

            restResponse.setDescription("Successfully changed password");

            return restResponse;

        } catch (Exception ex) {

            restResponse.setHasError(true);
            restResponse.setDescription(StringUtils.isNotBlank(ex.getMessage()) ? ex.getMessage() : "error changing password, please try again later");

            return restResponse;
        }

    }

}
