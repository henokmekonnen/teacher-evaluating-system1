package com.ddu.tes.controller.model.department;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GHabtamu
 */

public class CreateDepartmentRequestModel {
    private String  departmentName;
    private String description;
    private Integer numberOfStaff;
    private Integer deptHeadId;
    private String deptHeadName;


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

    public Integer getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(Integer deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    public String getDeptHeadName() {
        return deptHeadName;
    }

    public void setDeptHeadName(String deptHeadName) {
        this.deptHeadName = deptHeadName;
    }
}
