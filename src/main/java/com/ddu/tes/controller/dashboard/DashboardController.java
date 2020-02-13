package com.ddu.tes.controller.dashboard;


import com.ddu.tes.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        int noOfDepartments = departmentService.countDepartment();

        model.addAttribute("noOfDepartments", noOfDepartments);


        return "dashboard/dashboard";
    }
}
