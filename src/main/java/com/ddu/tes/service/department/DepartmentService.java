package com.ddu.tes.service.department;

import com.ddu.tes.controller.model.department.CreateDepartmentRequestModel;
import com.ddu.tes.controller.model.department.CreateDepartmentResponseModel;
import com.ddu.tes.data.modle.Department;

/**
 * @author GHabtamu
 */
public interface DepartmentService {
     public GetDepartmentByNameResult getDepartmentByName (final String departmentName);
     public CreateDepartmentResponseModel createDepartment(CreateDepartmentRequestModel confirmCreateDepartment);
     public GetAllDepartmentListResult getAllDepartments();
}
