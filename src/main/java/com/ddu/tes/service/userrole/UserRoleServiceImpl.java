package com.ddu.tes.service.userrole;

import com.ddu.tes.controller.model.userrole.EditUserRoleRequestModel;
import com.ddu.tes.controller.model.userrole.EditUserRoleResponseModel;
import com.ddu.tes.data.modle.Department;
import com.ddu.tes.data.modle.Role;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.modle.UserRole;
import com.ddu.tes.data.repository.SqlRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    private static final Log logger = LogFactory.getLog(UserRoleServiceImpl.class);

    @Autowired
    SqlRepository sqlRepository;

    @Override
    public GetUserRoleList getAllUsersRole(){


        GetUserRoleList responseModel = new GetUserRoleList();
        try {

            List<Object> userList = sqlRepository.findAll(User.class);
             List<Object> userRole=sqlRepository.findAll(UserRole.class);
              List<Object> roleUser=sqlRepository.findAll(Role.class);
            if (userRole == null) {
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No user found");
                return responseModel;
            }

            List<Map<String, Object>> selectedUserRoleList = new ArrayList<>();

            for (Object user_Role : userRole) {

                Map<String, Object> userRoleMap = new HashMap<>();
                user_Role = (UserRole) user_Role;
                userRoleMap.put("usrUserRole", ((UserRole) user_Role).getUserRole());
                userRoleMap.put("usrRoleId", ((UserRole) user_Role).getRoleId());
                userRoleMap.put("usrUserId", ((UserRole) user_Role).getUserId());
                User filterUser = new User();
                filterUser.setUserId(((UserRole) user_Role).getUserId());
                filterUser = (User) sqlRepository.findOne(filterUser);

                if (filterUser != null){
                    userRoleMap.put("usrId",filterUser .getUserId());
                    userRoleMap.put("usrUuid",filterUser.getUuid());
                    userRoleMap.put("usrFirstName", filterUser.getFirstName());
                    userRoleMap.put("usrLastName", filterUser.getLastName());
                    userRoleMap.put("usrGrandFatherName", filterUser.getGrandFatherName());
                    userRoleMap.put("usrDOB",filterUser.getDateOfBirth());
                    userRoleMap.put("usrPhoneNumber", filterUser.getPhoneNumber());
                    userRoleMap.put("usrEmail", filterUser.getEmail());
                    userRoleMap.put("usrGender",filterUser.getGender());
                    userRoleMap.put("usrDeaprtmentId",filterUser.getDepartmentId());
                    Department filterDepartment = new Department();
                    filterDepartment.setDepartmentId(((User) filterUser).getDepartmentId());
                    filterDepartment = (Department) sqlRepository.findOne(filterDepartment);

                    if (filterDepartment != null){
                        userRoleMap.put("deptName", filterDepartment.getDepartmentName());
                    }
                }
                Role filterRole = new Role();
                filterRole.setRoleId(((UserRole) user_Role).getRoleId());
                filterRole = (Role) sqlRepository.findOne(filterRole);
                if (filterRole != null){
                    userRoleMap.put("roleName", filterRole.getName());
                }
                selectedUserRoleList.add(userRoleMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" user found");
            responseModel.setUserRoleList(selectedUserRoleList);
            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }
    @Override
    public EditUserRoleResponseModel editUserRole(EditUserRoleRequestModel confirmEditUserRole){
        EditUserRoleResponseModel responseModel = new EditUserRoleResponseModel();

        try {

            UserRole filterUserRole = new UserRole();

            filterUserRole.setUserRole(confirmEditUserRole.getUserRole());
            filterUserRole = (UserRole) sqlRepository.findOne(filterUserRole);

            if(filterUserRole == null){

                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("user not found");
                return responseModel;
            }
            filterUserRole.setUserId(confirmEditUserRole.getUserId());
            filterUserRole.setRoleId(confirmEditUserRole.getRoleId());
            sqlRepository.update(filterUserRole);


            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully Updated user");
            responseModel.setRoleId(filterUserRole.getRoleId());
            responseModel.setUserId(filterUserRole.getUserId());
            return  responseModel;

        }catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return  responseModel;
        }
    }
    @Override
    public GetUserRoleId getUserByRoleId(Integer roleId) {

        GetUserRoleId result = new GetUserRoleId();

        try {

            if(StringUtils.isBlank(roleId.toString())){
                result.setStatusCode(1000);
                result.setStatusMessage("department name not found");
                result.setUserRoleExists(Boolean.FALSE);
                return  null;
            }


            UserRole filter = new UserRole();
            filter.setRoleId(roleId);

            UserRole user = (UserRole) sqlRepository.findOne(filter);


            if(user == null ){
                result.setStatusCode(1000);
                result.setStatusMessage("role not found");
                result.setUserRoleExists(Boolean.FALSE);
                return  result;
            }


            result.setStatusCode(0);
            result.setUserRole(user.getUserRole());
            result.setRoleId(user.getRoleId());
            result.setUserId(user.getUserId());



            result.setStatusMessage("Found");
            result.setUserRoleExists(Boolean.TRUE);
            return  result;

        } catch (Exception ex) {
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setUserRoleExists(Boolean.FALSE);
            return result;
        }
    }
}
