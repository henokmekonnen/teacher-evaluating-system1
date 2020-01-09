package com.ddu.tes.service.lookup;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllLookUpList extends AbstractResponseModel {

    public List<Map<String, Object>> lookUpList;

    public List<Map<String, Object>> getLookUpList() {
        return lookUpList;
    }

    public void setLookUpList(List<Map<String, Object>> lookUpList) {
        this.lookUpList = lookUpList;
    }


}
