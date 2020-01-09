package com.ddu.tes.service.lookup;

import com.ddu.tes.data.modle.LookUp;
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
public class LookUpServiceImpl implements LookUpService {
    private static final Log logger = LogFactory.getLog(LookUpServiceImpl.class);
@Autowired
    SqlRepository sqlRepository;

    @Override
    public GetAllLookUpList getAllLookUp(){


        GetAllLookUpList responseModel = new GetAllLookUpList();
            try {

                List<Object> lookUpList = sqlRepository.findAll(LookUp.class);

                if (lookUpList == null){
                    responseModel.setStatusCode(1000);
                    responseModel.setStatusMessage("No lookUpList found");
                    return  responseModel;
                }

                List<Map<String, Object>> selectedRoleList = new ArrayList<>();

                for(Object lookUp : lookUpList){

                    Map<String, Object> lookUpMap = new HashMap<>();
                    lookUp  = (LookUp)lookUp;
                    lookUpMap.put("lookUpId",((LookUp) lookUp).getLookUpId());
                    lookUpMap.put("descrption",((LookUp) lookUp).getDescription());
                    selectedRoleList.add(lookUpMap);
                }
                responseModel.setStatusCode(0);
                responseModel.setStatusMessage(" lookUpList found");
                responseModel.setLookUpList(selectedRoleList);
                return  responseModel;

            }catch (Exception ex) {
                logger.error(ex);
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage(ex.getMessage());
                return  responseModel;
            }
        }

}
