package com.ccighgo.utils.reuse.function;

import org.springframework.stereotype.Service;

import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Service
public interface ReusedFunctions {

   UserInformationOfCreatedBy getPartnerCreatedByInformation(Integer createdBy);

}
