package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.user.CreateUserRequestModel;
import com.ddu.tes.controller.model.user.CreateUserResponseModel;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.utils.Constant;
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
public class UserServiceImpl implements UserService {


    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    SqlRepository sqlRepository;

    @Override
    public GetUserByEmailResult getUserByEmail(String email) {

        GetUserByEmailResult result = new GetUserByEmailResult();

        try {

            if (StringUtils.isBlank(email)) {
                result.setStatusCode(1000);
                result.setStatusMessage("user email not found");
                result.setUserExists(Boolean.FALSE);
                return null;
            }

            User filter = new User();
            filter.setEmail(email);

            User user = (User) sqlRepository.findOne(filter);

            if (user != null) {
                result.setStatusCode(1000);
                result.setStatusMessage("user already exists");
                result.setUserExists(Boolean.FALSE);
                return result;
            }

            result.setStatusCode(0);
            result.setStatusMessage("Found");
            result.setUserExists(Boolean.TRUE);
            return result;

        } catch (Exception ex) {
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setUserExists(Boolean.FALSE);
            return result;
        }
    }
@Override
public GetUserByPhoneResult getUserByPhone(String phoneNumber) {
    GetUserByPhoneResult phoneuser = new GetUserByPhoneResult();

    try {
        if (StringUtils.isBlank(phoneNumber)) {
            phoneuser.setStatusCode(1000);
            phoneuser.setStatusMessage("user already exists");
            phoneuser.setUserPhoneExists(Boolean.FALSE);
            return phoneuser;
        }
    User filter = new User();
        filter.setPhoneNumber(phoneNumber);

    User useru = (User) sqlRepository.findOne(filter);
    if (useru != null) {
        phoneuser.setStatusCode(1000);
        phoneuser.setStatusMessage("user already exists");
        phoneuser.setUserPhoneExists(Boolean.FALSE);
        return phoneuser;
    }
    phoneuser.setStatusCode(0);
    phoneuser.setStatusMessage("Found");
    phoneuser.setUserPhoneExists(Boolean.TRUE);
    return phoneuser;
}
 catch (Exception ex) {
        logger.error(ex);
        phoneuser.setStatusCode(1000);
        phoneuser.setStatusMessage(ex.getMessage());
        phoneuser.setUserPhoneExists(Boolean.FALSE);
        return phoneuser;
        }
}

    @Override
    public CreateUserResponseModel createUser(CreateUserRequestModel confirmCreateUser) {
        CreateUserResponseModel responseModel = new CreateUserResponseModel();

        try {
            User newUser = new User();
            newUser.setFirstName(confirmCreateUser.getFirstName());
            newUser.setLastName(confirmCreateUser.getLastName());
            newUser.setGrandFatherName(confirmCreateUser.getGrandFatherName());
            newUser.setEmail(confirmCreateUser.getEmail());
            newUser.setPhoneNumber(confirmCreateUser.getPhoneNumber());
            newUser.setGender(confirmCreateUser.getGender());
            newUser.setDateOfBirth(confirmCreateUser.getDateOfBirth());
            newUser.setDepartmentId(confirmCreateUser.getDepartmentId());
            newUser.setUserName(Constant.USER_SYSTEM);
            newUser.setPassword(Constant.PASSWORD_SYSTEM);
            newUser.setChangePasswordRequired(Boolean.TRUE);
            newUser.setLocked(Boolean.FALSE);
            newUser.setEnabled(Boolean.TRUE);
            newUser.setCreatedBy(Constant.SYSTEM);
            newUser.setUuid(Constant.generatenumber());
            newUser = (User) sqlRepository.insert(newUser);

            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully created user");
            responseModel.setFirstName(confirmCreateUser.getFirstName());
            responseModel.setLastName(confirmCreateUser.getLastName());
            responseModel.setGrandFatherName(confirmCreateUser.getGrandFatherName());
            responseModel.setEmail(confirmCreateUser.getEmail());
            responseModel.setPhoneNumber(confirmCreateUser.getPhoneNumber());
            responseModel.setGender(confirmCreateUser.getGender());
            responseModel.setDateOfBirth(confirmCreateUser.getDateOfBirth());
            responseModel.setDepartmentId(confirmCreateUser.getDepartmentId());

            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }

    @Override
    public GetAllUserListResult getAllUsers() {

        GetAllUserListResult responseModel = new GetAllUserListResult();
        try {

            List<Object> userList = sqlRepository.findAll(User.class);

            if (userList == null) {
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No user found");
                return responseModel;
            }

            List<Map<String, Object>> selectedUserList = new ArrayList<>();

            for (Object user : userList) {

                Map<String, Object> userMap = new HashMap<>();
                user = (User) user;
                userMap.put("usrId", ((User) user).getUserId());
                userMap.put("usrEmail", ((User) user).getEmail());

                selectedUserList.add(userMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" user found");
            responseModel.setUserList(selectedUserList);
            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }


}
//    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
//
//    @Autowired
//    SqlRepository sqlRepository;
//
//    @Override
//    public CreateUserResponseModel createUser(CreateUserRequestModel confirmCreateUser) {
//        CreateUserResponseModel responseModel = new CreateUserResponseModel();
//        try {
//
//         User newUser = new User();
//         newUser.setFirstName(confirmCreateUser.getFirstName());
//         newUser.setLastName(confirmCreateUser.getLastName());
//         newUser.setGrandFatherName(confirmCreateUser.getGrandFatherName());
//         newUser.setEmail(confirmCreateUser.getEmail());
//         newUser.setPhoneNumber(confirmCreateUser.getPhoneNuber());
//         newUser.setGender(confirmCreateUser.getGender());
//         newUser.setDepartmentId(confirmCreateUser.getDepartmentId());
//         newUser.setDateOfBirth(confirmCreateUser.getDateOfBirth());
//
//
//            newUser = (User) sqlRepository.insert(newUser);
//
//            responseModel.setStatusCode(0);
//            responseModel.setStatusMessage("Successfully created department");
//            responseModel.setFirstName(newUser.getFirstName());
//            responseModel.setLastName(newUser.getLastName());
//            responseModel.setGrandFatherName(newUser.getGrandFatherName());
//            responseModel.setEmail(newUser.getEmail());
//            responseModel.setPhoneNuber(newUser.getPhoneNumber());
//            responseModel.setGender(newUser.getGender());
//            responseModel.setDepartmentId(newUser.getDepartmentId());
//            responseModel.setDateOfBirth(newUser.getDateOfBirth());
//
//               return  responseModel;
//
//        }catch (Exception ex) {
//            logger.error(ex);
//            responseModel.setStatusCode(1000);
//            responseModel.setStatusMessage(ex.getMessage());
//            return  responseModel;
//        }
//    }}
//
