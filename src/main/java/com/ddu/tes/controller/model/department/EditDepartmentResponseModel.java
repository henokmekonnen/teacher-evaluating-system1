package com.ddu.tes.controller.model.department;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class EditDepartmentResponseModel extends AbstractResponseModel {
    private String  departmentName;
    private String description;
    private Integer numberOfStaff;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(Integer numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }
}
