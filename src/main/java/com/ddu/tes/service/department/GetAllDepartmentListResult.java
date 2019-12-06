package com.ddu.tes.service.department;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

/**
 * @author GHabtamu
 */
public class GetAllDepartmentListResult extends AbstractResponseModel {

    public List<Map<String, Object>> departmentList;

    public List<Map<String, Object>> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Map<String, Object>> departmentList) {
        this.departmentList = departmentList;
    }
}
