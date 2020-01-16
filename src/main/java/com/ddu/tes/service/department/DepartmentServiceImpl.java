package com.ddu.tes.service.department;


import com.ddu.tes.controller.model.department.CreateDepartmentRequestModel;
import com.ddu.tes.controller.model.department.CreateDepartmentResponseModel;
import com.ddu.tes.controller.model.department.EditDepartmentRequestModel;
import com.ddu.tes.controller.model.department.EditDepartmentResponseModel;
import com.ddu.tes.data.modle.Department;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GHabtamu
 */
@Service
public class DepartmentServiceImpl implements  DepartmentService{

    private static final Log logger = LogFactory.getLog(DepartmentServiceImpl.class);
    @Autowired
     SqlRepository sqlRepository;

    @Override
    public GetDepartmentByNameResult getDepartmentByName(String departmentName) {

        GetDepartmentByNameResult result = new GetDepartmentByNameResult();

        try {

            if(StringUtils.isBlank(departmentName)){
                result.setStatusCode(1000);
                result.setStatusMessage("department name not found");
                result.setDepartmentExists(Boolean.FALSE);
                return  null;
            }

            Department filter = new Department();
            filter.setDepartmentName(departmentName);

            Department department = (Department) sqlRepository.findOne(filter);

           if(department == null ){
                result.setStatusCode(1000);
                result.setStatusMessage("department not found");
                result.setDepartmentExists(Boolean.FALSE);
                return  result;
            }

            result.setStatusCode(0);
            result.setDescription(department.getDescription());
            result.setDptName(department.getDepartmentName());
            result.setNumberOfStaff(department.getNumberOfStaff());
            result.setDptId(department.getDepartmentId());
            result.setStatusMessage("Found");
            result.setDepartmentExists(Boolean.TRUE);
            return  result;

        }catch (Exception ex){
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setDepartmentExists(Boolean.FALSE);
            return  result;
        }
    }


    @Override
    public boolean departmentExist (String name ){
        GetDepartmentByNameResult result = new GetDepartmentByNameResult();

        try {

            if(StringUtils.isBlank(name)){
               return  false;
            }

            Department filter = new Department();
            filter.setDepartmentName(name);

            Department department = (Department) sqlRepository.findOne(filter);

            if(department != null ){
                return  false;
            }

          return  true;

        }catch (Exception ex){
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setDepartmentExists(Boolean.FALSE);
          throw  ex;
        }
    }
    @Override
    public CreateDepartmentResponseModel createDepartment(CreateDepartmentRequestModel confirmCreateDepartment) {
         CreateDepartmentResponseModel responseModel = new CreateDepartmentResponseModel();

         try {
              Department newDepartment = new Department();

              newDepartment.setDepartmentName(confirmCreateDepartment.getDepartmentName());
              newDepartment.setNumberOfStaff(confirmCreateDepartment.getNumberOfStaff());
              newDepartment.setDescription(confirmCreateDepartment.getDescription());
              newDepartment.setCreatedBy(Constant.SYSTEM);

             newDepartment = (Department)  sqlRepository.insert(newDepartment);

             responseModel.setStatusCode(0);
             responseModel.setStatusMessage("Successfully created department");
             responseModel.setDepartmentName(newDepartment.getDepartmentName());
             responseModel.setDescription(newDepartment.getDescription());
             responseModel.setNumberOfStaff(newDepartment.getNumberOfStaff());

             return  responseModel;

         }catch (Exception ex) {
             logger.error(ex);
             responseModel.setStatusCode(1000);
             responseModel.setStatusMessage(ex.getMessage());
             return  responseModel;
         }
    }
    @Override
    public EditDepartmentResponseModel editDepartment(EditDepartmentRequestModel confirmEditDepartment) {
        EditDepartmentResponseModel responseModel = new EditDepartmentResponseModel();

        try {
            Department filterdept = new Department();

            filterdept.setDepartmentId(confirmEditDepartment.getDptId());

            filterdept = (Department) sqlRepository.findOne(filterdept);

            if(filterdept == null){

                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("Department not found");
                return responseModel;
            }

            filterdept.setDepartmentName(confirmEditDepartment.getDepartmentName());
            filterdept.setNumberOfStaff(confirmEditDepartment.getNumberOfStaff());
            filterdept.setDescription(confirmEditDepartment.getDescription());

            sqlRepository.update(filterdept);

            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully Updated department");
            responseModel.setDepartmentName(filterdept.getDepartmentName());
            responseModel.setDescription(filterdept.getDescription());
            responseModel.setNumberOfStaff(filterdept.getNumberOfStaff());
            return  responseModel;

        }catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return  responseModel;
        }
    }
    @Override
    public GetAllDepartmentListResult getAllDepartments() {

        GetAllDepartmentListResult responseModel = new GetAllDepartmentListResult();
        try {

            List<Object> departmentList = sqlRepository.findAll(Department.class);

            if (departmentList == null){
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No department found");
                return  responseModel;
            }

            List<Map<String, Object>> selectedDepartmentList = new ArrayList<>();

            for(Object department : departmentList){

                Map<String, Object> departmentMap = new HashMap<>();
                department  = (Department)department;
                departmentMap.put("dptId",((Department) department).getDepartmentId());
                departmentMap.put("dptName",((Department) department).getDepartmentName());
                departmentMap.put("dptNoStaff",((Department) department).getNumberOfStaff());
                departmentMap.put("dptDescription",((Department) department).getDescription());
                selectedDepartmentList.add(departmentMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" department found");
            responseModel.setDepartmentList(selectedDepartmentList);
            return  responseModel;

       }catch (Exception ex) {
        logger.error(ex);
        responseModel.setStatusCode(1000);
        responseModel.setStatusMessage(ex.getMessage());
        return  responseModel;
    }
    }

    @Override
    public Map<Integer, String> getDepartmentUsersByDepartmentId(String departmentId) {
        Map<Integer, String> dptUser = new HashMap<>();

        dptUser.put(1, "girum");
        dptUser.put(2, "henok");
        dptUser.put(3, "dan");

        return dptUser;
    }
}
