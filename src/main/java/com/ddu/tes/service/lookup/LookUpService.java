package com.ddu.tes.service.lookup;

import com.ddu.tes.data.modle.LookUp;

public interface LookUpService {

    public GetAllLookUpList getAllLookUp();
    public LookUp getLookupById(String lookupId);

}
