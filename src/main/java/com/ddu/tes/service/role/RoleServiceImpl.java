package com.ddu.tes.service.role;

import com.ddu.tes.data.modle.Role;
import com.ddu.tes.data.repository.SqlRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl implements RoleService {
    private static final Log logger = LogFactory.getLog(RoleServiceImpl.class);
@Autowired
    SqlRepository sqlRepository;

    @Override
    public GetAllRoleList getAllRole(){


        GetAllRoleList responseModel = new GetAllRoleList();
            try {

                List<Object> roleList = sqlRepository.findAll(Role.class);

                if (roleList == null){
                    responseModel.setStatusCode(1000);
                    responseModel.setStatusMessage("No role found");
                    return  responseModel;
                }

                List<Map<String, Object>> selectedRoleList = new ArrayList<>();

                for(Object role : roleList){

                    Map<String, Object> roleMap = new HashMap<>();
                    role  = (Role)role;
                    roleMap.put("roleId",((Role) role).getRoleId());
                    roleMap.put("roleName",((Role) role).getName());
                    roleMap.put("roledscrpt",((Role) role).getDescription());

                    selectedRoleList.add(roleMap);
                }
                responseModel.setStatusCode(0);
                responseModel.setStatusMessage(" role found");
                responseModel.setRoleList(selectedRoleList);
                return  responseModel;

            }catch (Exception ex) {
                logger.error(ex);
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage(ex.getMessage());
                return  responseModel;
            }
        }

    @Override
    public Role getRoleById(Integer roleId) {

        try {

            if (roleId == null) {

                System.err.print("null value");
            }

            Role filter = new Role();
            filter.setRoleId(roleId);

            Role result = (Role) sqlRepository.findOne(filter);

            return  result != null ? result : null;

        } catch (Exception ex) {
            logger.error("Error while fetching organization type status type  ", ex);
            throw ex;
        }

    }


}
