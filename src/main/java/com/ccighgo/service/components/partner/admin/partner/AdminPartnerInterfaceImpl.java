/**
 * 
 */
package com.ccighgo.service.components.partner.admin.partner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.CcighgoException;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;

/**
 * @author ravi
 *
 */
@Component
public class AdminPartnerInterfaceImpl implements AdminPartnerInterface {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(AdminPartnerInterfaceImpl.class);

   @Override
   public Response addPartner(AdminAddPartner partner) {
      Response resp = new Response();
      try{
         if(partner==null){
            throw new CcighgoException("cannot add partner, object is null/empty");
         }
         
      }catch(CcighgoException e){
         
      }
      return resp;
   }

}
