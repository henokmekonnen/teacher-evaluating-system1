package com.ddu.tes.controller.department;

import com.ddu.tes.controller.model.department.CreateDepartmentRequestModel;
import com.ddu.tes.controller.model.department.CreateDepartmentResponseModel;
import com.ddu.tes.service.department.DepartmentService;
import com.ddu.tes.service.department.GetDepartmentByNameResult;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author GHabtamu
 */
@Controller
@RequestMapping("/department")
@SessionAttributes({"createDepartmentRequestModel"})
public class DepartmentController {
    private static final Log logger = LogFactory.getLog(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

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
        try {

            model.addAttribute("createDepartmentRequestModel",confirmCreateDepartment);
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

            GetDepartmentByNameResult result1 = departmentService.getDepartmentByName(confirmCreateDepartment.getDepartmentName());

            if(result1.getStatusCode() != 0){

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

}
