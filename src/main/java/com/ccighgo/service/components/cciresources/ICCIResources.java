package com.ccighgo.service.components.cciresources;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Page_Param;
import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Response;
import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Test_Param;

@Service
public interface ICCIResources {

   CCIResources_Response test(CCIResources_Test_Param tParam);

   CCIResources_Response page(CCIResources_Page_Param pParam);

}
