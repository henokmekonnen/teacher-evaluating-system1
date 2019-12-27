package com.ddu.tes.controller.user;

import com.ddu.tes.controller.model.user.CreateUserRequestModel;
import com.ddu.tes.controller.model.user.CreateUserResponseModel;
import com.ddu.tes.controller.model.user.EditUserRequestModel;
import com.ddu.tes.controller.model.user.EditUserResponseModel;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.department.DepartmentService;
import com.ddu.tes.service.department.GetAllDepartmentListResult;
import com.ddu.tes.service.phonenumber.PhoneNumberService;
import com.ddu.tes.service.role.GetAllRoleList;
import com.ddu.tes.service.role.RoleService;
import com.ddu.tes.service.user.GetAllUserListResult;
import com.ddu.tes.service.user.GetUserByEmailResult;
import com.ddu.tes.service.user.GetUserByPhoneResult;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.service.userrole.GetUserRoleList;
import com.ddu.tes.service.userrole.UserRoleService;
import com.ddu.tes.service.validation.ValidationService;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.ddu.tes.service.user.GetUserByEmailResult;

@Controller
@RequestMapping("/user")
@SessionAttributes({"createUserRequestModel, editUserRequestModel"})
public class UserController {
    private static final Log logger = LogFactory.getLog(UserController.class);
@Autowired
    RoleService roleService;

 @Autowired
    SqlRepository sqlRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    public ValidationService validationService;
    @Autowired
    public PhoneNumberService phoneNumberService;


    final int maxAgentAgeAllowed=18;
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult userListResult=userService.getAllUsers();

            GetAllRoleList roleListResult = roleService.getAllRole();
            CreateUserRequestModel createUserRequestModel = new CreateUserRequestModel();
            model.addAttribute("roleListResult", roleListResult.getRoleList());
            model.addAttribute("createUserRequestModel",createUserRequestModel);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
            model.addAttribute("userListResult", userListResult);


            return "user/create-user";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/create-user";
        }

    }

    @RequestMapping(value = "/backtocreate", method = RequestMethod.GET)
    public String back(@ModelAttribute CreateUserRequestModel confirmCreateUser, Model model, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("createUserRequestModel",confirmCreateUser);
            return "user/create-user";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/create-user";
        }

    }


    @RequestMapping(value = "/confirmCreateUser", method = RequestMethod.POST)
    public String confirmCreateUser(@Valid CreateUserRequestModel confirmCreateUser, BindingResult result, Model model) {

        try {
            List<String> errorList = new ArrayList<String>();
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllRoleList roleListResult = roleService.getAllRole();

            model.addAttribute("createUserRequestModel",confirmCreateUser);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
            model.addAttribute("roleListResult", roleListResult.getRoleList());
            if (StringUtils.isBlank(confirmCreateUser.getFirstName())){
                result.rejectValue("firstName", "error.firstName", "name must be valid.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user.");
                return "user/create-user";

            }

            if (!validationService.isValidName(confirmCreateUser.getFirstName())){
                result.rejectValue("firstName", "error.firstName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/create-user";

            }

            if (StringUtils.isBlank(confirmCreateUser.getEmail())){
                result.rejectValue("email", "error.email", "Please provide email");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide email.");
                return "user/create-user";

            }
            if (!validationService.isEmailValid(confirmCreateUser.getEmail())) {
                result.rejectValue("email", "error.email", "Invalid email format");

                errorList.add("Invalid email format");
                return "user/create-user";
            }

            if (StringUtils.isBlank(confirmCreateUser.getPhoneNumber())){
                result.rejectValue("phonenumber", "error.phoneNumber", "Please provide phonenumber");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide phonenumber.");
                return "user/create-user";

            }
            boolean userEmailExist = userService.userEmailExist(confirmCreateUser.getEmail());

            if(!userEmailExist){

                result.rejectValue("email", "error.email", "user eamil Already exists change email.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "user Already exists.");
                return "user/create-user";
            }
            GetUserByPhoneResult phonevalue=userService.getUserByPhone(confirmCreateUser.getPhoneNumber());
            if(phonevalue.getStatusCode() !=0){
                result.rejectValue("phoneNumber", "error.phoneNumber", "user phone number Already exists change phone Number.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "user Already exists.");
                return "user/create-user";           
            }
            if (!phoneNumberService.validatePhoneNumber(confirmCreateUser.getPhoneNumber())) {
                result.rejectValue("phoneNumber", "error.phoneNumber", "Invalid phone number format");

                errorList.add("Invalid phone number format");
                return "user/create-user";
            }

            if (StringUtils.isBlank(confirmCreateUser.getLastName())){
                result.rejectValue("lastname", "error.lastname", "Please provide lastname.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user.");
                return "user/create-user";

            }

            if (!validationService.isValidName(confirmCreateUser.getLastName())){
                result.rejectValue("lastName", "error.lastName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/create-user";

            }

            if (StringUtils.isBlank(confirmCreateUser.getGrandFatherName())){
                result.rejectValue("grandfathername", "error.grandfathername", "Please provide grandfathername");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide grandfathername.");
                return "user/create-user";

            }

            if (!validationService.isValidName(confirmCreateUser.getGrandFatherName())){
                result.rejectValue("grandFatherName", "error.grandFatherName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/create-user";

            }
            
            if (StringUtils.isNotBlank(confirmCreateUser.getDateOfBirth())){
                String[] dob = confirmCreateUser.getDateOfBirth().split("-");

                LocalDate userDob = new LocalDate(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]), Integer.parseInt(dob[2]));
                LocalDate now = new LocalDate();
                Years age = Years.yearsBetween(userDob, now);

                if (maxAgentAgeAllowed > age.getYears() ) {

                    result.rejectValue("dateOfBirth", "error.dateOfBirth", "User must be above 18 years old.");

                    errorList.add("User must be above 18 years old.");
                    model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                    model.addAttribute(Constant.MESSAGE, "User must be above 18 years old.");
                    return "user/create-user";
                }

            }
            if (StringUtils.isBlank(confirmCreateUser.getGender())){
                result.rejectValue("gender", "error.gender", "Please provide gender");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide dateOfBirth.");
                return "user/create-user";

            }

            if (confirmCreateUser.getDepartmentId() == null){
                result.rejectValue("departmentId", "error.departmentId", "Please provide Departmentname");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide Department name.");
                return "user/create-user";

            }


            for(Map<String, Object> deparment : departmentListResult.getDepartmentList()){

                if(deparment.get("dptId").equals(confirmCreateUser.getDepartmentId())){

                    confirmCreateUser.setDepartmentName(deparment.get("dptName").toString());
                }
            }

            for(Map<String, Object> role : roleListResult.getRoleList()){

                if(role.get("roleId").equals(confirmCreateUser.getRoleId())){

                    confirmCreateUser.setRoleName(role.get("roleName").toString());
                }
            }
            if (confirmCreateUser.getRoleId() == null){
                result.rejectValue("roleId", "error.roleId", "Please provide role name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide role name.");
                return "user/create-user";

            }


            return "user/create-user-confirm";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/create-user";
        }

    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute CreateUserRequestModel confirmCreateUser, BindingResult result, Model model) {

        try {
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
             GetAllRoleList roleListResult=roleService.getAllRole();
            model.addAttribute("createUserRequestModel",confirmCreateUser);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
            model.addAttribute("roleListResult",roleListResult.getRoleList());
            CreateUserResponseModel responseModel = userService.createUser(confirmCreateUser);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("createUserRequestModel",confirmCreateUser);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "user/create-user";
            }

            confirmCreateUser= new CreateUserRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "user/create-user-success";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
            model.addAttribute("createUserRequestModel",confirmCreateUser);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/create-user";
        }

    }
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            GetAllUserListResult userListResult = userService.getAllUsers();
            model.addAttribute("userListResult", userListResult.getUserList());
            GetAllRoleList roleListResult =roleService.getAllRole();
            GetUserRoleList userRoleList=userRoleService.getAllUsersRole();
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
//            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
//
//            model.addAttribute("roleListResult", roleListResult.getRoleList());
            model.addAttribute("userRoleList", userRoleList.getUserRoleList());
            return "user/user-list";

        } catch (Exception ex) {
            logger.error("error while creating usr" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/user-list";
        }

    }
    @RequestMapping(value = "/editUsers/{usrEmail}", method = RequestMethod.GET)
    public String editUsers(Model model, @PathVariable(name = "usrEmail") String email) {


        GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
        model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
GetAllRoleList roleList=roleService.getAllRole();
        model.addAttribute("roleList", roleList.getRoleList());

        try {
            if(email == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide id");
                return "user/user-list";
            }

            GetUserByEmailResult result =  userService.getUserByEmail(email);

            if (result.getStatusCode() != 0) {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, result.getStatusMessage());
                return "user/user-list";
            }

            EditUserRequestModel editUserRequestModel = new EditUserRequestModel();

            editUserRequestModel.setFirstName(result.getUsrFirstName());
            editUserRequestModel.setLastName(result.getUsrLastName());
            editUserRequestModel.setGrandFatherName(result.getUsrGrandFatherName());
            editUserRequestModel.setEmail(result.getUsrEmail());
            editUserRequestModel.setPhoneNumber(result.getUsrPhoneNumber());
            editUserRequestModel.setGender(result.getUsrGender());
            editUserRequestModel.setDepartmentId(result.getUsrDepartmentId());
            editUserRequestModel.setDateOfBirth(result.getUsrDateOfBirth());
            editUserRequestModel.setUsrId(result.getUsrId());


            model.addAttribute("editUserRequestModel", editUserRequestModel);


            return "user/edit-user";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/user-list";
        }

    }

    @RequestMapping(value = "/confirmEditUser", method = RequestMethod.POST)
    public String confirmEditUser(@Valid EditUserRequestModel editUserRequestModel, BindingResult result, Model model) {

        try {
            List<String> errorList = new ArrayList<String>();
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllRoleList roleListResult =roleService.getAllRole();
            model.addAttribute("roleListResult", roleListResult.getRoleList());
            model.addAttribute("editUserRequestModel",editUserRequestModel);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());

            if (StringUtils.isBlank(editUserRequestModel.getFirstName())){
                result.rejectValue("firstName", "error.firstName", "name must be valid.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user.");
                return "user/edit-user";

            }

            if (!validationService.isValidName(editUserRequestModel.getFirstName())){
                result.rejectValue("firstName", "error.firstName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/edit-user";

            }

            if (StringUtils.isBlank(editUserRequestModel.getEmail())){
                result.rejectValue("email", "error.email", "Please provide email");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide email.");
                return "user/edit-user";

            }
            if (!validationService.isEmailValid(editUserRequestModel.getEmail())) {
                result.rejectValue("email", "error.email", "Invalid email format");

                errorList.add("Invalid email format");
                return "user/edit-user";
            }

            if (StringUtils.isBlank(editUserRequestModel.getPhoneNumber())){
                result.rejectValue("phonenumber", "error.phoneNumber", "Please provide phonenumber");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide phonenumber.");
                return "user/edit-user";

            }
            GetUserByEmailResult result1 = userService.getUserByEmail(editUserRequestModel.getEmail());

            if (result1.getStatusCode() != 0) {

                result.rejectValue("email", "error.email", "user eamil Already exists change email.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "user Already exists.");
                return "user/edit-user";
            }

            if (!phoneNumberService.validatePhoneNumber(editUserRequestModel.getPhoneNumber())) {
                result.rejectValue("phoneNumber", "error.phoneNumber", "Invalid phone number format");

                errorList.add("Invalid phone number format");
                return "user/edit-user";
            }

            if (StringUtils.isBlank(editUserRequestModel.getLastName())){
                result.rejectValue("lastname", "error.lastname", "Please provide lastname.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user.");
                return "user/edit-user";

            }

            if (!validationService.isValidName(editUserRequestModel.getLastName())){
                result.rejectValue("lastName", "error.lastName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/edit-user";

            }

            if (StringUtils.isBlank(editUserRequestModel.getGrandFatherName())){
                result.rejectValue("grandfathername", "error.grandfathername", "Please provide grandfathername");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide grandfathername.");
                return "user/edit-user";

            }

            if (!validationService.isValidName(editUserRequestModel.getGrandFatherName())){
                result.rejectValue("grandFatherName", "error.grandFatherName", "name must be valid.");

                errorList.add("name must be valid");
                return "user/edit-user";

            }

            if (StringUtils.isNotBlank(editUserRequestModel.getDateOfBirth())){
                String[] dob = editUserRequestModel.getDateOfBirth().split("-");

                LocalDate userDob = new LocalDate(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]), Integer.parseInt(dob[2]));
                LocalDate now = new LocalDate();
                Years age = Years.yearsBetween(userDob, now);

                if (maxAgentAgeAllowed > age.getYears() ) {

                    result.rejectValue("dob", "error.dob", "User must be above 18 years old.");

                    errorList.add("User must be above 18 years old.");
                    model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                    model.addAttribute(Constant.MESSAGE, "User must be above 18 years old.");
                    return "user/edit-user";
                }

            }
            if (StringUtils.isBlank(editUserRequestModel.getGender())){
                result.rejectValue("gender", "error.gender", "Please provide gender");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide dateOfBirth.");
                return "user/edit-user";

            }

            if (editUserRequestModel.getDepartmentId() == null){
                result.rejectValue("departmentId", "error.departmentId", "Please provide Departmentname");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide Department name.");
                return "user/edit-user";

            }


            for(Map<String, Object> deparment : departmentListResult.getDepartmentList()){

                if(deparment.get("dptId").equals(editUserRequestModel.getDepartmentId())){

                    editUserRequestModel.setDepartmentName(deparment.get("dptName").toString());
                }
            }
            for(Map<String, Object> role : roleListResult.getRoleList()){

                if(role.get("roleId").equals(editUserRequestModel.getUsrRoleId())){

                    editUserRequestModel.setUsrRoleName(role.get("roleName").toString());
                }
            }
            if (editUserRequestModel.getUsrRoleId() == null){
                result.rejectValue("usrRoleId", "error.usrRoleId", "Please provide role name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide role name.");
                return "user/edit-user";

            }
            return "user/edit-user-confirm";

        }catch (Exception ex){
            logger.error("error while creating usr"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/edit-user";
        }

    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute EditUserRequestModel confirmEditUser, BindingResult result, Model model) {
        EditUserResponseModel responseModel= userService.editUser(confirmEditUser);
        GetAllRoleList roleListResult =roleService.getAllRole();
        model.addAttribute("roleListResult", roleListResult.getRoleList());
        GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
        model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
        try {
            model.addAttribute("editUserRequestModel",confirmEditUser);
            model.addAttribute("responseModel", responseModel);
            if(confirmEditUser.getUsrId() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid user id");
                return "user/edit-user";
            }
            if(confirmEditUser.getUsrUserRole() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid user id");
                return "user/edit-user";
            }

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("editUserRequestModel",confirmEditUser);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "user/edit-user";
            }



            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "user/edit-user-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("EditUserRequestModel",confirmEditUser);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/edit-user";
        }

    }

}

















//
//@RestController
//@RequestMapping("/user")
//@SessionAttributes({"createUserRequestModel"})
//public class UserController {
//
//    private static final Log logger = LogFactory.getLog(UserController.class);
//
//
//   @Autowired
//   DepartmentService departmentService;
//   @Autowired
//   public UserService userService;
//    @RequestMapping(value = "/confirmCreateUser", method = RequestMethod.GET)
//    public String createUser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//
//       final Log logger = LogFactory.getLog(UserController.class);
//
//        try {
//
//            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
//             model.addAttribute("departmentListResult", departmentListResult);
//
//
//            CreateUserRequestModel createUserRequestModel = new CreateUserRequestModel();
//            model.addAttribute("createUserRequestModel", createUserRequestModel);
//          model.addAttribute("departmentListResult", departmentListResult);
//            return "user/user-home-page";
//
//        } catch (Exception ex) {
//            logger.error("error homepage" + ex, ex.getCause());
//            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
//            model.addAttribute(Constant.MESSAGE, ex.getMessage());
//            return "user/user-home-page";
//        }
//
//    }
//}