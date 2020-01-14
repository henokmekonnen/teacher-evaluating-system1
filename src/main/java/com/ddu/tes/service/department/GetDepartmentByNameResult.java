package com.ddu.tes.service.department;

import com.ddu.tes.controller.model.AbstractResponseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GHabtamu
 */
@Getter
@Setter
public class GetDepartmentByNameResult extends AbstractResponseModel {

     private boolean departmentExists;
     private String dptName;
     private String description;
     private Integer numberOfStaff;
     private Integer dptId;

     public Integer getDptId() {
          return dptId;
     }

     public void setDptId(Integer dptId) {
          this.dptId = dptId;
     }

     public String getDptName() {
          return dptName;
     }

     public void setDptName(String dptName) {
          this.dptName = dptName;
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

     public boolean isDepartmentExists() {
          return departmentExists;
     }

     public void setDepartmentExists(boolean departmentExists) {
          this.departmentExists = departmentExists;
     }
}
