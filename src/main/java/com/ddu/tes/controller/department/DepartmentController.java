package com.ddu.tes.controller.department;

import com.ddu.tes.controller.model.department.CreateDepartmentRequestModel;
import com.ddu.tes.controller.model.department.CreateDepartmentResponseModel;
import com.ddu.tes.controller.model.department.EditDepartmentRequestModel;
import com.ddu.tes.controller.model.department.EditDepartmentResponseModel;
import com.ddu.tes.service.department.DepartmentService;
import com.ddu.tes.service.department.GetAllDepartmentListResult;
import com.ddu.tes.service.department.GetDepartmentByNameResult;
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

/**
 * @author GHabtamu
 */
@Controller
@RequestMapping("/department")
@SessionAttributes({"createDepartmentRequestModel", "editDepartmentRequestModel"})
public class DepartmentController {
    private static final Log logger = LogFactory.getLog(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/departmentList", method = RequestMethod.GET)
    public String departmentList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());
            return "department/department-list";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/department-list";
        }

    }

    @RequestMapping(value = "/createDepartment", method = RequestMethod.GET)
    public String createDepartment(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {

            CreateDepartmentRequestModel createDepartmentRequestModel = new CreateDepartmentRequestModel();
            model.addAttribute("createDepartmentRequestModel",createDepartmentRequestModel);
            return "department/create-department";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/create-department";
        }

    }

    @RequestMapping(value = "/backtocreate", method = RequestMethod.GET)
    public String back(@ModelAttribute CreateDepartmentRequestModel confirmCreateDepartment, Model model, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        confirmCreateDepartment = (CreateDepartmentRequestModel) session.getAttribute(Constant.Back_To_Create);

        try {

            model.addAttribute(Constant.Back_To_Create,confirmCreateDepartment);
            return "department/create-department";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/create-department";
        }

    }


    @RequestMapping(value = "/confirmCreateDepartmen", method = RequestMethod.POST)
    public String confirmCreateDepartmen(@Valid CreateDepartmentRequestModel confirmCreateDepartment, BindingResult result, Model model) {

        try {

            model.addAttribute("createDepartmentRequestModel",confirmCreateDepartment);

            if (StringUtils.isBlank(confirmCreateDepartment.getDepartmentName())){
                result.rejectValue("departmentName", "error.departmentName", "Please provide department name.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide department name.");
                return "department/create-department";

            }

            boolean departmentExist = departmentService.departmentExist(confirmCreateDepartment.getDepartmentName());

            if(!departmentExist){

                result.rejectValue("departmentName", "error.departmentName", "Department Already exists.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Department Already exists.");
                return "department/create-department";
            }

            if (StringUtils.isBlank(confirmCreateDepartment.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "department/create-department";

            }

            if (confirmCreateDepartment.getNumberOfStaff() <= 0){
                result.rejectValue("noOfStaff", "error.noOfStaff", "Please provide valid number of depertment users.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide valid number of depertment users.");
                return "department/create-department";

            }

            return "department/create-department-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/create-department";
        }

    }

    @RequestMapping(value = "/createDepartment", method = RequestMethod.POST)
    public String createDepartment(@ModelAttribute CreateDepartmentRequestModel confirmCreateDepartment, BindingResult result, Model model) {

        try {

            model.addAttribute("createDepartmentRequestModel",confirmCreateDepartment);

            CreateDepartmentResponseModel responseModel = departmentService.createDepartment(confirmCreateDepartment);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("createDepartmentRequestModel",confirmCreateDepartment);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "department/create-department";
            }

            confirmCreateDepartment = new CreateDepartmentRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "department/create-department-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("createDepartmentRequestModel",confirmCreateDepartment);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/create-department";
        }

    }


    @RequestMapping(value = "/editDepartmenent/{dptName}", method = RequestMethod.GET)
    public String editDepartmenent(Model model, @PathVariable(name = "dptName") String dptName) {

        GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
        model.addAttribute("departmentList", departmentListResult.getDepartmentList());

        try {
            if(dptName == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide id");
                return "department/department-list";
            }

            GetDepartmentByNameResult result =  departmentService.getDepartmentByName(dptName);

            if (result.getStatusCode() != 0) {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, result.getStatusMessage());
                return "department/department-list";
            }
            EditDepartmentRequestModel editDepartmentRequestModel = new EditDepartmentRequestModel();

            model.addAttribute("editDepartmentRequestModel", editDepartmentRequestModel);

            editDepartmentRequestModel.setDepartmentName(result.getDptName());
            editDepartmentRequestModel.setNumberOfStaff(result.getNumberOfStaff());
            editDepartmentRequestModel.setDescription(result.getDescription());
            editDepartmentRequestModel.setDptId(result.getDptId());



            return "department/edit-department";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/department-list";
        }

    }

    @RequestMapping(value = "/confirmEditDepartment", method = RequestMethod.POST)
    public String confirmEditDepartment(@Valid EditDepartmentRequestModel confirmEditDepartment, BindingResult result, Model model) {

        try {

            model.addAttribute("confirmEditDepartment",confirmEditDepartment);

            if (StringUtils.isBlank(confirmEditDepartment.getDepartmentName())){
                result.rejectValue("departmentName", "error.departmentName", "Please provide department name.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide department name.");
                return "department/edit-department";

            }

            GetDepartmentByNameResult result1 = departmentService.getDepartmentByName(confirmEditDepartment.getDepartmentName());

            if (result1.getStatusCode() != 0) {
                result.rejectValue("departmentName", "error.departmentName", "Department Already exists.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Department Already exists.");
                return "department/edit-department";
            }

            if (StringUtils.isBlank(confirmEditDepartment.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "department/edit-department";

            }

            if (confirmEditDepartment.getNumberOfStaff() <= 0){
                result.rejectValue("noOfStaff", "error.noOfStaff", "Please provide valid number of depertment users.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide valid number of depertment users.");
                return "department/edit-department";

            }

            return "department/edit-department-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/edit-department";
        }

    }

    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    public String editDepartment(@ModelAttribute EditDepartmentRequestModel confirmEditDepartment, BindingResult result, Model model) {

        try {

            model.addAttribute("EditDepartmentRequestModel",confirmEditDepartment);

            if(confirmEditDepartment.getDptId() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid department id");
                return "department/edit-department";
            }

            EditDepartmentResponseModel responseModel = departmentService.editDepartment(confirmEditDepartment);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("EditDepartmentRequestModel",confirmEditDepartment);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "department/edit-department";
            }

            confirmEditDepartment = new EditDepartmentRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "department/edit-department-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("EditDepartmentRequestModel",confirmEditDepartment);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/edit-department";
        }

    }
}