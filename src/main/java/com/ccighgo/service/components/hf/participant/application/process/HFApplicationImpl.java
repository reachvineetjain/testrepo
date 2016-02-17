/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class HFApplicationImpl implements HFApplication {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(HFApplicationImpl.class);
   
   @Autowired EntityManager entityManager;
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Override
   public HFApplicationUploadPhotos uploadHFPhotos(String goId, String seasonId, HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      HFApplicationUploadPhotos updatedObject = new HFApplicationUploadPhotos();
      try{
         if (hfApplicationUploadPhotos == null ) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_UPDATE_PHOTO_OBJECT));
         }
         if (hfApplicationUploadPhotos.getLoginId() ==  0 || hfApplicationUploadPhotos.getLoginId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (goId == null || Integer.valueOf(goId) == 0 || Integer.valueOf(goId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
         }
         if (seasonId == null || Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_SEASONID));
         }
         
         
         
      }catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

}
