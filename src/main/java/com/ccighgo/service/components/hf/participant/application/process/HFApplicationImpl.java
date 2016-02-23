/**
 * 
 */
package com.ccighgo.service.components.hf.participant.application.process;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.HostFamilyHome;
import com.ccighgo.db.entities.HostFamilyPhoto;
import com.ccighgo.db.entities.HostFamilySeasonCategory;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.HostFamilyHomeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonCategoryRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photo;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photos;
import com.ccighgo.service.transport.hostfamily.beans.application.whyhost.WhyHost;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;

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

   @Autowired LoginRepository loginRepository;
   @Autowired HostFamilyHomeRepository hostFamilyHomeRepository;
   @Autowired HostFamilySeasonRepository hostFamilySeasonRepository;
   @Autowired HostFamilySeasonCategoryRepository hostFamilySeasonCategoryRepository;
   @Autowired HostFamilyPhotosRepository hostFamilyPhotosRepository;
   @Autowired HostFamilyPhotosTypeRepository hostFamilyPhotosTypeRepository;

   @Override
   @Transactional
   public WhyHost createWhyHost( String applicationCategoryId, WhyHost whyHost) {
      WhyHost updatedObject = new WhyHost();
      try {
         if (whyHost == null) {
            throw new CcighgoException("cannot create empty record");
         }
         if (whyHost.getFieldsFilled() == 0 || whyHost.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to create the record");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getSeasonId(),
               Integer.valueOf(applicationCategoryId));
         hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
         hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         HostFamilyHome hfHome = new HostFamilyHome();
         hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
         hfHome.setHopeToLearn(whyHost.getAspectsOfAmericanCultureYouWillShare());
         hfHome.setExtraActivities(whyHost.getActivitiesPlanned());
         hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
         hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
         hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
         hfHome.setHostFamilySeason(hostFamilySeasonRepository.findOne(whyHost.getSeasonId()));
         hfHome.setCreatedBy(whyHost.getLoginId());
         hfHome.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         hfHome.setModifiedBy(whyHost.getLoginId());
         hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
         updatedObject = getWhyHost(String.valueOf(hfHome.getHostFamilyHomeId()), String.valueOf(whyHost.getSeasonId()), applicationCategoryId);
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

   @Override
   @Transactional(readOnly = true)
   public WhyHost getWhyHost(String hfHomeId, String hfSeasonId, String applicationCategoryId) {
      WhyHost whyHost = new WhyHost();
      try {
         if (hfHomeId == null || hfSeasonId == null || applicationCategoryId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         HostFamilyHome hfHome = hostFamilyHomeRepository.getHFHomebyIdAndSeasonId(Integer.valueOf(hfHomeId), Integer.valueOf(hfSeasonId));
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(Integer.valueOf(hfSeasonId),
               Integer.valueOf(applicationCategoryId));
         if (hfHome != null && hostFamilySeasonCategory != null) {
            whyHost.setGoId(hfHome.getHostFamilySeason().getHostFamily().getHostFamilyGoId());
            whyHost.setSeasonId(hfHome.getHostFamilySeason().getHostFamilySeasonId());
            whyHost.setHostFamilyHomeId(hfHome.getHostFamilyHomeId());
            whyHost.setWhyFamilyInterestedInHosting(hfHome.getHostingReason());
            whyHost.setAspectsOfAmericanCultureYouWillShare(hfHome.getHopeToLearn());
            whyHost.setActivitiesPlanned(hfHome.getExtraActivities());
            whyHost.setWillYouBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorOther().equals(CCIConstants.ACTIVE) ? true : false);
            whyHost.setForWhomYouWillBeWorkingasLCForAnotherOrg(hfHome.getLocalCoordinatorDetails());
            whyHost.setHaveYouHostedForAnotherOrg(hfHome.getHostedOther().equals(CCIConstants.ACTIVE) ? true : false);
            whyHost.setIfYesForWhomAndHowManyYears(hfHome.getHostedOtherDetails());
            whyHost.setFamilyExpectationOnStudentResponsibility(hfHome.getStudentsResponsibilities());
            whyHost.setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
            whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            whyHost.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         whyHost.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return whyHost;
   }

   @Override
   @Transactional
   public WhyHost updateWhyHost(String applicationCategoryId, WhyHost whyHost) {
      WhyHost updatedObject = new WhyHost();
      try {
         if (applicationCategoryId==null) {
            throw new CcighgoException("invalid parameters");
         }
         if (whyHost == null) {
            throw new CcighgoException("cannot create update record");
         }
         if (whyHost.getFieldsFilled() == 0 || whyHost.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to update the record");
         }
         HostFamilyHome hfHome = hostFamilyHomeRepository.findOne(whyHost.getHostFamilyHomeId());
         if(hfHome!=null){
            HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(whyHost.getSeasonId(),
                  Integer.valueOf(applicationCategoryId));
            hostFamilySeasonCategory.setFilledMandatoryFields(whyHost.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
            hfHome.setHostingReason(whyHost.getWhyFamilyInterestedInHosting());
            hfHome.setHopeToLearn(whyHost.getAspectsOfAmericanCultureYouWillShare());
            hfHome.setExtraActivities(whyHost.getActivitiesPlanned());
            hfHome.setLocalCoordinatorOther(whyHost.isWillYouBeWorkingasLCForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setLocalCoordinatorDetails(whyHost.getForWhomYouWillBeWorkingasLCForAnotherOrg());
            hfHome.setHostedOther(whyHost.isHaveYouHostedForAnotherOrg() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            hfHome.setHostedOtherDetails(whyHost.getIfYesForWhomAndHowManyYears());
            hfHome.setStudentsResponsibilities(whyHost.getFamilyExpectationOnStudentResponsibility());
            hfHome.setModifiedBy(whyHost.getLoginId());
            hfHome.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hfHome = hostFamilyHomeRepository.saveAndFlush(hfHome);
            updatedObject = getWhyHost(String.valueOf(hfHome.getHostFamilyHomeId()), String.valueOf(whyHost.getSeasonId()), applicationCategoryId);
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }else{
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }

   @Override
   public HFApplicationUploadPhotos uploadHFPhotos(HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      HFApplicationUploadPhotos updatedObject = new HFApplicationUploadPhotos();
      try {
         if (hfApplicationUploadPhotos == null) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_UPDATE_PHOTO_OBJECT));
         }
         if (hfApplicationUploadPhotos.getLoginId() == 0 || hfApplicationUploadPhotos.getLoginId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_OR_NULL_LOGIN_ID));
         }
         if (hfApplicationUploadPhotos.getHfSeasonId() == 0 || hfApplicationUploadPhotos.getHfSeasonId() < 0) {
            throw new CcighgoException("season information is required to save photos");
         }
         List<HostFamilyPhoto> hostFamilyPhotoList = new ArrayList<HostFamilyPhoto>();
         if(hfApplicationUploadPhotos.getPhotos()!=null){
            for(Photo ph:hfApplicationUploadPhotos.getPhotos().getPhotos()){
               HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
               hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
               hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
               hfPhoto.setFileName(ph.getName());
               hfPhoto.setFilePath(ph.getPhotoUrl());
               hfPhoto.setPhotoName(ph.getName());
               hfPhoto.setDescription(ph.getDescription()!=null?ph.getDescription():"");
               hfPhoto.setIsOptional("0");
               hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
               hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hfPhoto.setActive(CCIConstants.ACTIVE);
               hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
               hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
               hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
               hostFamilyPhotoList.add(hfPhoto);
            }
         }
         if(hfApplicationUploadPhotos.getOptionalPhotos()!=null){
            for(Photo ph:hfApplicationUploadPhotos.getOptionalPhotos().getPhotos()){
               HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
               hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
               hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
               hfPhoto.setFileName(ph.getName());
               hfPhoto.setFilePath(ph.getPhotoUrl());
               hfPhoto.setPhotoName(ph.getName());
               hfPhoto.setDescription(ph.getDescription()!=null?ph.getDescription():"");
               hfPhoto.setIsOptional("1");
               hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
               hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hfPhoto.setActive(CCIConstants.ACTIVE);
               hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
               hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
               hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
               hostFamilyPhotoList.add(hfPhoto);
            }
         }
         hostFamilyPhotosRepository.save(hostFamilyPhotoList);
         hostFamilyPhotosRepository.flush();
         updatedObject=hfApplicationUploadPhotos;
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return updatedObject;
   }
   
   @Override
   public HFApplicationUploadPhotos getHFPhotos(String hfSeasonId) {
      HFApplicationUploadPhotos photoList = new HFApplicationUploadPhotos();
      try{
         if (hfSeasonId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<HostFamilyPhoto> hfPhotos = hostFamilyPhotosRepository.findPhotosBySeasonId(Integer.valueOf(hfSeasonId));
         if(hfPhotos!=null){
            
            
         }else{
            photoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      }catch (CcighgoException e) {
         photoList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return photoList;
   }

   @Override
   @Transactional
   @Modifying
   public Response deletePhoto(String photoId) {
     Response resp = new Response();
     try{
        if (photoId == null) {
           throw new CcighgoException("invalid photo id");
        }
        hostFamilyPhotosRepository.delete(Integer.valueOf(photoId));
        resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
              messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
     }catch (CcighgoException e) {
        resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), "error deleting photo"));
        LOGGER.error(e.getMessage());
     }
      return resp;
   }

}
