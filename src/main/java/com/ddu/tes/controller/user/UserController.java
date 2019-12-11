package com.ddu.tes.controller.user;

import com.ddu.tes.controller.model.user.CreateUserRequestModel;
import com.ddu.tes.controller.model.user.CreateUserResponseModel;
import com.ddu.tes.controller.model.user.EditUserRequestModel;
import com.ddu.tes.controller.model.user.EditUserResponseModel;
import com.ddu.tes.data.modle.Department;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.department.DepartmentService;
import com.ddu.tes.service.department.GetAllDepartmentListResult;
import com.ddu.tes.service.user.*;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

//import com.ddu.tes.service.user.GetUserByEmailResult;

@Controller
@RequestMapping("/user")
@SessionAttributes({"createUserRequestModel"})
public class UserController {
    private static final Log logger = LogFactory.getLog(UserController.class);
 @Autowired
    SqlRepository sqlRepository;

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;



    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult userListResult=userService.getAllUsers();
            CreateUserRequestModel createUserRequestModel = new CreateUserRequestModel();
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
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();

            model.addAttribute("createUserRequestModel",confirmCreateUser);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
            if (StringUtils.isBlank(confirmCreateUser.getFirstName())){
                result.rejectValue("FirstName", "error.FirstName", "Please provide first name.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user name.");
                return "user/create-user";

            }

            if (StringUtils.isBlank(confirmCreateUser.getEmail())){
                result.rejectValue("email", "error.email", "Please provide email");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide email.");
                return "user/create-user";

            }
            if (StringUtils.isBlank(confirmCreateUser.getPhoneNumber())){
                result.rejectValue("phonenumber", "error.phoneNumber", "Please provide phonenumber");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide phonenumber.");
                return "user/create-user";

            }
            boolean useremailExist = userService.useremailExist(confirmCreateUser.getEmail());

            if(!useremailExist){

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

            if (StringUtils.isBlank(confirmCreateUser.getLastName())){
                result.rejectValue("lastname", "error.lastname", "Please provide lastname.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide user.");
                return "user/create-user";

            }

            if (StringUtils.isBlank(confirmCreateUser.getGrandFatherName())){
                result.rejectValue("grandfathername", "error.grandfathername", "Please provide grandfathername");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide grandfathername.");
                return "user/create-user";

            }


            
            if (StringUtils.isBlank(confirmCreateUser.getDateOfBirth())){
                result.rejectValue("dd/mm/yyyy", "error.dateobirth", "Please provide dateOfBirth");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide dateOfBirth.");
                return "user/create-user";

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
            model.addAttribute("createUserRequestModel",confirmCreateUser);
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());

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
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
            model.addAttribute("userList", userListResult.getUserList());
            return "user/user-list";

        } catch (Exception ex) {
            logger.error("error while creating usr" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/user-list";
        }

    }

    @RequestMapping(value = "/editUsers/{usrEmail}", method = RequestMethod.GET)
    public String editUsers(Model model, @PathVariable(name = "usrEmail") String userEmail) {
        GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
        GetAllUserListResult userListResult = userService.getAllUsers();
        model.addAttribute("userList", userListResult.getUserList());
        model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());

        try {
            if(StringUtils.isBlank(userEmail)){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide email");
                return "user/user-list";
            }

            GetUserByEmailResult result =  userService.getUserByEmail(userEmail);

            if (result.getStatusCode() != 0) {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "user Already exists.");
                return "user/user-list";
            }
            EditUserRequestModel editUserRequestModel = new EditUserRequestModel();

            model.addAttribute("editUserRequestModel", editUserRequestModel);
            editUserRequestModel.setUserId(result.getUserId());
            editUserRequestModel.setFirstName(result.getFirstName());
            editUserRequestModel.setLastName(result.getLastName());
            editUserRequestModel.setGrandFatherName(result.getGrandFatherName());
            editUserRequestModel.setDateOfBirth(result.getDateOfBirth());
            editUserRequestModel.setEmail(result.getEmail());
            editUserRequestModel.setPhoneNumber(result.getPhoneNumber());
            editUserRequestModel.setGender(result.getGender());
            editUserRequestModel.setDepartmentId(result.getDepartmentId());

            return "user/edit-user";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "user/user-list";
        }

    }
    @RequestMapping(value = "/confirmEditUser", method = RequestMethod.POST)
    public String confirmEditUser(@Valid EditUserRequestModel confirmEditUser, BindingResult result, Model model) {


        try {
    GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
    GetAllUserListResult userListResult = userService.getAllUsers();
    model.addAttribute("confirmEditUser", confirmEditUser);
    model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());
    if (StringUtils.isBlank(confirmEditUser.getFirstName())) {
        result.rejectValue("FirstName", "error.FirstName", "Please provide first name.");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide user name.");
        return "user/edit-user";

    }

    if (StringUtils.isBlank(confirmEditUser.getEmail())) {
        result.rejectValue("email", "error.email", "Please provide email");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide email.");
        return "user/edit-user";

    }
    if (StringUtils.isBlank(confirmEditUser.getPhoneNumber())) {
        result.rejectValue("phonenumber", "error.phoneNumber", "Please provide phonenumber");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide phonenumber.");
        return "user/edit-user";

    }
    GetUserByEmailResult result1 =userService.getUserByEmail(confirmEditUser.getEmail());

    if(result1.getStatusCode() != 0) {

        result.rejectValue("email", "error.email", "user eamil Already exists change email.");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "user Already exists.");
        return "user/edit-user";
    }
//    GetUserByPhoneResult phonevalue = userService.getUserByPhone(confirmEditUser.getPhoneNumber());
//    if (phonevalue.getStatusCode() != 0) {
//        result.rejectValue("phoneNumber", "error.phoneNumber", "user phone number Already exists change phone Number.");
//        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
//        model.addAttribute(Constant.MESSAGE, "user Already exists.");
//        return "user/edit-user";
//    }

    if (StringUtils.isBlank(confirmEditUser.getLastName())) {
        result.rejectValue("lastname", "error.lastname", "Please provide lastname.");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide user.");
        return "user/edit-user";

    }

    if (StringUtils.isBlank(confirmEditUser.getGrandFatherName())) {
        result.rejectValue("grandfathername", "error.grandfathername", "Please provide grandfathername");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide grandfathername.");
        return "user/edit-user";

    }


    if (StringUtils.isBlank(confirmEditUser.getDateOfBirth())) {
        result.rejectValue("dd/mm/yyyy", "error.dateobirth", "Please provide dateOfBirth");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide dateOfBirth.");
        return "user/edit-user";

    }
    if (StringUtils.isBlank(confirmEditUser.getGender())) {
        result.rejectValue("gender", "error.gender", "Please provide gender");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide dateOfBirth.");
        return "user/edit-user";

    }

    if (confirmEditUser.getDepartmentId() == null) {
        result.rejectValue("departmentId", "error.departmentId", "Please provide Departmentname");
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, "Please provide Department name.");
        return "user/edit-user";

    }

    for(Map<String, Object> deparment : departmentListResult.getDepartmentList()){

        if(deparment.get("dptId").equals(confirmEditUser.getDepartmentId())){

            confirmEditUser.setDepartmentName(deparment.get("dptName").toString());
        }
    }
        return "user/edit-user-confirm";
    }catch(Exception ex){

        logger.error("error while creating dept" + ex, ex.getCause());
        model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
        model.addAttribute(Constant.MESSAGE, ex.getMessage());
        return "user/edit-user";
    }

}
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute EditUserRequestModel confirmEditUser, BindingResult result, Model model) {

        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();

            model.addAttribute("departmentListResult", departmentListResult.getDepartmentList());

            model.addAttribute("confirmEditUser",confirmEditUser);

            if(confirmEditUser.getUserId() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid user id");
                return "user/edit-user";
            }

            EditUserResponseModel responseModel = userService.editUser(confirmEditUser);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("EditUserRequestModel",confirmEditUser);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "user/edit-user";
            }

            confirmEditUser = new EditUserRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "user/edit-user-success";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
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