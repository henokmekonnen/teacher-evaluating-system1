package com.ddu.tes.controller.userrole;


import com.ddu.tes.controller.model.userrole.EditUserRoleRequestModel;
import com.ddu.tes.controller.model.userrole.EditUserRoleResponseModel;
import com.ddu.tes.service.role.GetAllRoleList;
import com.ddu.tes.service.role.RoleService;
import com.ddu.tes.service.user.GetAllUserListResult;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.service.userrole.GetUserRoleId;
import com.ddu.tes.service.userrole.GetUserRoleList;
import com.ddu.tes.service.userrole.UserRoleService;
import com.ddu.tes.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {
    private static final Log logger = LogFactory.getLog(UserRoleController.class);
@Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/editUsersRole/{usrRoleId}", method = RequestMethod.GET)
    public String editUsersRole(Model model, @PathVariable(name = "usrRoleId") Integer roleId) {
        GetUserRoleList userListResult = userRoleService.getAllUsersRole();
        model.addAttribute("userListResult", userListResult.getUserRoleList());
        GetAllRoleList roleList=roleService.getAllRole();
        model.addAttribute("roleListResult", roleList.getRoleList());

        try {
            if(roleId == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide id");
                return "user/user-list";
            }

            GetUserRoleId result =  userRoleService.getUserByRoleId(roleId);

            if (result.getStatusCode() != 0) {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, result.getStatusMessage());
                return "user/user-list";
            }

            EditUserRoleRequestModel editUserRoleRequestModel = new EditUserRoleRequestModel();

            editUserRoleRequestModel.setUserRole(result.getUserRole());
            editUserRoleRequestModel.setRoleId(result.getRoleId());
            editUserRoleRequestModel.setUserId(result.getUserId());



            model.addAttribute("editUserRoleRequestModel", editUserRoleRequestModel);


            return "userrole/edit-userrole";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/user-list";
        }

    }
    @RequestMapping(value = "/confirmEditUserRole", method = RequestMethod.POST)
    public String confirmEditUserRole(@Valid EditUserRoleRequestModel editUserRoleRequestModel, BindingResult result, Model model) {

        try {
            List<String> errorList = new ArrayList<String>();

            GetAllRoleList roleListResult =roleService.getAllRole();
            model.addAttribute("roleListResult", roleListResult.getRoleList());
            model.addAttribute("editUserRoleRequestModel",editUserRoleRequestModel);
            GetAllUserListResult userListResult = userService.getAllUsers();
            model.addAttribute("userListResult", userListResult.getUserList());
            GetUserRoleList userListResult1 = userRoleService.getAllUsersRole();
            model.addAttribute("userListResult", userListResult1.getUserRoleList());
            for(Map<String, Object> user : userListResult.getUserList()){

                if(user.get("usrId").equals(editUserRoleRequestModel.getUserId())){

                    editUserRoleRequestModel.setUserName(user.get("usrFirstName").toString());
                }
            }
            if (editUserRoleRequestModel.getUserId() == null){
                result.rejectValue("userId", "error.userId", "Please provide role name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide role name.");
                return "userrole/edit-userrole";

            }

            for(Map<String, Object> role :roleListResult.getRoleList()){

                if(role.get("roleId").equals(editUserRoleRequestModel.getRoleId())){

                    editUserRoleRequestModel.setRoleName(role.get("roleName").toString());
                }
            }
            if (editUserRoleRequestModel.getRoleId() == null){
                result.rejectValue("roleId", "error.roleId", "Please provide role name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide role name.");
                return "userrole/edit-userrole";

            }

//            GetUserRoleId result1 = userRoleService.getUserByRoleId(editUserRoleRequestModel.getRoleId());
//
//            if (result1.getStatusCode() != 0) {
//
//                result.rejectValue("roleId", "error.roleId", " Already exists change email.");
//                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
//                model.addAttribute(Constant.MESSAGE, "user Already exists.");
//                return "userrole/edit-userrole";
//            }



            return "userrole/edit-userrole-confirm";

        }catch (Exception ex){
            logger.error("error while creating usr"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "userrole/edit-userrole";
        }

    }
    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public String editUsersRole(@ModelAttribute EditUserRoleRequestModel confirmEditUserRole, BindingResult result, Model model) {

        try {
            EditUserRoleResponseModel responseModel= userRoleService.editUserRole(confirmEditUserRole);
            GetAllRoleList roleListResult =roleService.getAllRole();
            model.addAttribute("roleListResult", roleListResult.getRoleList());
            GetAllUserListResult userListResult = userService.getAllUsers();
            model.addAttribute("userListResult", userListResult.getUserList());

            model.addAttribute("editUserRoleRequestModel",confirmEditUserRole);
            model.addAttribute("responseModel", responseModel);

            if(confirmEditUserRole.getUserRole() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid user id");
                return "userrole/edit-userrole";
            }

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("confirmEditUserRole",confirmEditUserRole);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "userrole/edit-userrole";
            }


                      model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "userrole/edit-userrole-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("confirmEditUserRole",confirmEditUserRole);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "userrole/edit-userrole";
        }

    }
}
