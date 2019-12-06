package com.ddu.tes.service.department;

import com.ddu.tes.controller.model.AbstractResponseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @author GHabtamu
 */
@Getter
@Setter
public class GetDepartmentByNameResult extends AbstractResponseModel {
     private boolean departmentExists;

     public boolean isDepartmentExists() {
          return departmentExists;
     }

     public void setDepartmentExists(boolean departmentExists) {
          this.departmentExists = departmentExists;
     }
}
