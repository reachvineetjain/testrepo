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

import com.ccighgo.db.entities.HostFamilyGeneralQuestion;
import com.ccighgo.db.entities.HostFamilyHome;
import com.ccighgo.db.entities.HostFamilyPhoto;
import com.ccighgo.db.entities.HostFamilyPotentialReference;
import com.ccighgo.db.entities.HostFamilyReference;
import com.ccighgo.db.entities.HostFamilySeasonCategory;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.HostFamilyGeneralQuestionRepository;
import com.ccighgo.jpa.repositories.HostFamilyHomeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosRepository;
import com.ccighgo.jpa.repositories.HostFamilyPhotosTypeRepository;
import com.ccighgo.jpa.repositories.HostFamilyPotentialReferenceRepository;
import com.ccighgo.jpa.repositories.HostFamilyReferenceRepository;
import com.ccighgo.jpa.repositories.HostFamilyRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonCategoryRepository;
import com.ccighgo.jpa.repositories.HostFamilySeasonRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photo;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.Photos;
import com.ccighgo.service.transport.hostfamily.beans.application.potential.hostfamily.PotentialHostFamily;
import com.ccighgo.service.transport.hostfamily.beans.application.references.HostFamilyReferences;
import com.ccighgo.service.transport.hostfamily.beans.application.references.Reference;
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
   @Autowired HostFamilyRepository hostFamilyRepository;
   @Autowired StateRepository stateRepository;
   @Autowired UserTypeRepository userTypeRepository;
   @Autowired HostFamilyPotentialReferenceRepository hostFamilyPotentialReferenceRepository;
   @Autowired HostFamilyGeneralQuestionRepository hostFamilyGeneralQuestionRepository;
   @Autowired HostFamilyReferenceRepository hostFamilyReferenceRepository;

   @Override
   @Transactional
   public WhyHost createWhyHost(String applicationCategoryId, WhyHost whyHost) {
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
         if (applicationCategoryId == null) {
            throw new CcighgoException("invalid parameters");
         }
         if (whyHost == null) {
            throw new CcighgoException("cannot create update record");
         }
         if (whyHost.getFieldsFilled() == 0 || whyHost.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to update the record");
         }
         HostFamilyHome hfHome = hostFamilyHomeRepository.findOne(whyHost.getHostFamilyHomeId());
         if (hfHome != null) {
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
         } else {
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
         if (hfApplicationUploadPhotos.getPhotos() != null) {
            for (Photo ph : hfApplicationUploadPhotos.getPhotos().getPhotos()) {
               HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
               hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
               hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
               hfPhoto.setFileName(ph.getName());
               hfPhoto.setFilePath(ph.getPhotoUrl());
               hfPhoto.setPhotoName(ph.getName());
               hfPhoto.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
               hfPhoto.setIsOptional(CCIConstants.INACTIVE);
               hfPhoto.setCreatedBy(hfApplicationUploadPhotos.getLoginId());
               hfPhoto.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hfPhoto.setActive(CCIConstants.ACTIVE);
               hfPhoto.setSubmittedToCCI(CCIConstants.INACTIVE);
               hfPhoto.setApprovedByCCI(CCIConstants.INACTIVE);
               hfPhoto.setRejectedByCCI(CCIConstants.INACTIVE);
               hostFamilyPhotoList.add(hfPhoto);
            }
         }
         if (hfApplicationUploadPhotos.getOptionalPhotos() != null) {
            for (Photo ph : hfApplicationUploadPhotos.getOptionalPhotos().getPhotos()) {
               HostFamilyPhoto hfPhoto = new HostFamilyPhoto();
               hfPhoto.setHostFamilySeason(hostFamilySeasonRepository.findOne(hfApplicationUploadPhotos.getHfSeasonId()));
               hfPhoto.setHostFamilyPhotosType(hostFamilyPhotosTypeRepository.findOne(ph.getType().getTypeId()));
               hfPhoto.setFileName(ph.getName());
               hfPhoto.setFilePath(ph.getPhotoUrl());
               hfPhoto.setPhotoName(ph.getName());
               hfPhoto.setDescription(ph.getDescription() != null ? ph.getDescription() : "");
               hfPhoto.setIsOptional(CCIConstants.ACTIVE);
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
         updatedObject = getHFPhotos(String.valueOf(hfApplicationUploadPhotos.getHfSeasonId()));
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
      HFApplicationUploadPhotos uploadPhotoList = new HFApplicationUploadPhotos();
      try {
         if (hfSeasonId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<HostFamilyPhoto> hfPhotos = hostFamilyPhotosRepository.findPhotosBySeasonId(Integer.valueOf(hfSeasonId));
         if (hfPhotos != null) {
            Photos photos = new Photos();
            Photos optionalPhotos = new Photos();
            List<Photo> optionalPhotoList = new ArrayList<Photo>();
            List<Photo> photoList = new ArrayList<Photo>();
            for (HostFamilyPhoto hfp : hfPhotos) {
               if (hfp.getIsOptional().equals(CCIConstants.ACTIVE)) {
                  Photo opl = new Photo();
                  opl.setPhotoId(hfp.getHostFamilyPhotoId());
                  opl.setName(hfp.getPhotoName());
                  opl.setPhotoUrl(hfp.getFilePath());
                  opl.setDescription(hfp.getDescription());
                  opl.setOptional(true);
                  optionalPhotoList.add(opl);
               } else {
                  Photo pl = new Photo();
                  pl.setPhotoId(hfp.getHostFamilyPhotoId());
                  pl.setName(hfp.getPhotoName());
                  pl.setPhotoUrl(hfp.getFilePath());
                  pl.setDescription(hfp.getDescription());
                  pl.setOptional(true);
                  photoList.add(pl);
               }
            }
            photos.getPhotos().addAll(photoList);
            optionalPhotos.getPhotos().addAll(optionalPhotoList);
            uploadPhotoList.setPhotos(photos);
            uploadPhotoList.setOptionalPhotos(optionalPhotos);
            uploadPhotoList.setHfSeasonId(Integer.valueOf(hfSeasonId));
            uploadPhotoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            uploadPhotoList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         uploadPhotoList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return uploadPhotoList;
   }

   @Override
   @Transactional
   @Modifying
   public Response deletePhoto(String photoId) {
      Response resp = new Response();
      try {
         if (photoId == null) {
            throw new CcighgoException("invalid photo id");
         }
         hostFamilyPhotosRepository.delete(Integer.valueOf(photoId));
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), "error deleting photo"));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   @Transactional
   public Response addPotentialReference(PotentialHostFamily potentialHostFmaily) {
      Response response = new Response();
      try {
         if (potentialHostFmaily == null) {
            throw new CcighgoException("cannot create reference with empty data");
         }
         if (potentialHostFmaily.getLoginId() == 0 || potentialHostFmaily.getLoginId() < 0) {
            throw new CcighgoException("invalid login id");
         }
         if (potentialHostFmaily.getGoId() == 0 || potentialHostFmaily.getGoId() < 0) {
            throw new CcighgoException("go id is required to create reference");
         }
         HostFamilyPotentialReference potentialReference = new HostFamilyPotentialReference();
         potentialReference.setHostFamily(hostFamilyRepository.findOne(potentialHostFmaily.getGoId()));
         potentialReference.setReferenceFirstName(potentialHostFmaily.getReferenceFirstName());
         potentialReference.setReferenceLastName(potentialHostFmaily.getReferenceLastName());
         potentialReference.setReferenceReason(potentialHostFmaily.getReferenceReason());
         potentialReference.setReferencePhone(potentialHostFmaily.getReferencePhone());
         potentialReference.setReferenceEmail(potentialHostFmaily.getReferenceEmail());
         potentialReference.setMailingAddress(potentialHostFmaily.getReferenceMailingAddress());
         potentialReference.setReferenceStreetAddress(potentialHostFmaily.getReferenceStreetAddress());
         potentialReference.setReferenceCity(potentialHostFmaily.getReferenceCity());
         potentialReference.setLookupUsstate1(stateRepository.findOne(potentialHostFmaily.getReferenceStateId()));
         potentialReference.setReferenceZipCode(potentialHostFmaily.getReferenceZip());
         potentialReference.setFirstName(potentialHostFmaily.getRefereeFirstName());
         potentialReference.setLastName(potentialHostFmaily.getRefereeLastName());
         potentialReference.setUserType(userTypeRepository.findOne(potentialHostFmaily.getRefereeUserType()));
         potentialReference.setEmail(potentialHostFmaily.getRefereeEmail());
         potentialReference.setMailingAddress(potentialHostFmaily.getRefereeMailingAddress());
         potentialReference.setStreetAddress(potentialHostFmaily.getRefereeStreetAddress());
         potentialReference.setCity(potentialHostFmaily.getRefereeCity());
         potentialReference.setLookupUsstate2(stateRepository.findOne(potentialHostFmaily.getRefereeStateId()));
         potentialReference.setZipCode(potentialHostFmaily.getRefereeZip());
         potentialReference.setCreatedBy(potentialHostFmaily.getLoginId());
         potentialReference.setCretatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         potentialReference.setModifiedBy(potentialHostFmaily.getLoginId());
         potentialReference.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         hostFamilyPotentialReferenceRepository.saveAndFlush(potentialReference);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return response;
   }

   @Override
   @Transactional
   public HostFamilyReferences createHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      HostFamilyReferences hfReferences = new HostFamilyReferences();
      try {
         if (hostFamilyReferences.getLoginId() == 0 || hostFamilyReferences.getLoginId() < 0) {
            throw new CcighgoException("login id is missing");
         }
         if (hostFamilyReferences.getSeasonId() == 0 || hostFamilyReferences.getSeasonId() < 0) {
            throw new CcighgoException("host family season is required");
         }
         if (hfReferences.getReferences() == null) {
            throw new CcighgoException("cannot create record with empty objects");
         }
         if (hostFamilyReferences.getFieldsFilled() == 0 || hostFamilyReferences.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to create the record");
         }
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(hostFamilyReferences.getSeasonId(),
               Integer.valueOf(applicationCategoryId));
         hostFamilySeasonCategory.setFilledMandatoryFields(hostFamilyReferences.getFieldsFilled());
         hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
         if (hfReferences.getReferences() == null) {
            // if null create empty records
            HostFamilyReference hRef1 = new HostFamilyReference();
            hRef1.setActive(CCIConstants.ACTIVE);
            hRef1.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
            hRef1.setCreatedBy(hostFamilyReferences.getLoginId());
            hRef1.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hRef1.setModifiedBy(hostFamilyReferences.getLoginId());
            hRef1.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hostFamilyReferenceRepository.saveAndFlush(hRef1);
            HostFamilyReference hRef2 = new HostFamilyReference();
            hRef2.setActive(CCIConstants.ACTIVE);
            hRef2.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
            hRef2.setCreatedBy(hostFamilyReferences.getLoginId());
            hRef2.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hRef2.setModifiedBy(hostFamilyReferences.getLoginId());
            hRef2.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            hostFamilyReferenceRepository.saveAndFlush(hRef2);
         } else {
            for (Reference ref : hfReferences.getReferences()) {
               HostFamilyReference hRef = new HostFamilyReference();
               hRef.setActive(CCIConstants.ACTIVE);
               hRef.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
               hRef.setFirstName(ref.getFirstName());
               hRef.setLastName(ref.getLastName());
               hRef.setAddress(ref.getStreet());
               hRef.setCity(ref.getCity());
               hRef.setLookupUsstate(stateRepository.findOne(ref.getState()));
               hRef.setZipCode(ref.getZip());
               hRef.setPhone(ref.getPhone());
               hRef.setRelationship(ref.getRelationshipToFamily());
               hRef.setPersonNotRelatedToBlood(ref.isBloodRelated() ? CCIConstants.INACTIVE : CCIConstants.ACTIVE);
               hRef.setSecondReferenceRelationtoFirst(hfReferences.isSecondReferenceRelatedToFirst() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               hRef.setCreatedBy(hostFamilyReferences.getLoginId());
               hRef.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hRef.setModifiedBy(hostFamilyReferences.getLoginId());
               hRef.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               hostFamilyReferenceRepository.saveAndFlush(hRef);
            }
         }
         HostFamilyGeneralQuestion questions = new HostFamilyGeneralQuestion();
         questions.setPreviousHostingWithCCI(hostFamilyReferences.isPreviouslyHosted() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         questions.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
         questions.setInternet(hostFamilyReferences.getInternet());
         questions.setInternetOthers(hostFamilyReferences.getOtherWebsites());
         questions.setCommunity(hostFamilyReferences.getCommunity());
         questions.setCommunityEvent(hostFamilyReferences.getEvent());
         questions.setCommunityMagazine(hostFamilyReferences.getMagazine());
         questions.setCommunityOthers(hostFamilyReferences.getOtherCommunity());
         hostFamilyGeneralQuestionRepository.saveAndFlush(questions);
         hfReferences = getHFReference(String.valueOf(hostFamilyReferences.getSeasonId()), applicationCategoryId);
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfReferences;
   }

   @Override
   @Transactional
   public HostFamilyReferences updateHFReference(String applicationCategoryId, HostFamilyReferences hostFamilyReferences) {
      HostFamilyReferences upadatedObject = new HostFamilyReferences();
      try {
         if (applicationCategoryId == null) {
            throw new CcighgoException("invalid parameters");
         }
         if (hostFamilyReferences == null) {
            throw new CcighgoException("cannot create update record");
         }
         if (hostFamilyReferences.getLoginId() == 0 || hostFamilyReferences.getLoginId() < 0) {
            throw new CcighgoException("login info is required to update the record");
         }
         if (hostFamilyReferences.getSeasonId() == 0 || hostFamilyReferences.getSeasonId() < 0) {
            throw new CcighgoException("season infi is required to update the record");
         }
         if (hostFamilyReferences.getFieldsFilled() == 0 || hostFamilyReferences.getFieldsFilled() < 0) {
            throw new CcighgoException("number of fields filled is mandatory to update the record");
         }
         List<HostFamilyReference> hfRefList = hostFamilyReferenceRepository.findBySeasonId(hostFamilyReferences.getSeasonId());
         if (hfRefList != null) {
            for (HostFamilyReference hRef : hfRefList) {
               for (Reference ref : hostFamilyReferences.getReferences()) {
                  if (hRef.getHostFamilyReferenceId() == ref.getHostFamilyReferenceId()) {
                     hRef.setActive(CCIConstants.ACTIVE);
                     hRef.setFirstName(ref.getFirstName());
                     hRef.setLastName(ref.getLastName());
                     hRef.setAddress(ref.getStreet());
                     hRef.setCity(ref.getCity());
                     hRef.setLookupUsstate(stateRepository.findOne(ref.getState()));
                     hRef.setZipCode(ref.getZip());
                     hRef.setPhone(ref.getPhone());
                     hRef.setRelationship(ref.getRelationshipToFamily());
                     hRef.setPersonNotRelatedToBlood(ref.isBloodRelated() ? CCIConstants.INACTIVE : CCIConstants.ACTIVE);
                     hRef.setSecondReferenceRelationtoFirst(hostFamilyReferences.isSecondReferenceRelatedToFirst() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     hRef.setModifiedBy(hostFamilyReferences.getLoginId());
                     hRef.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     hostFamilyReferenceRepository.saveAndFlush(hRef);
                  }
               }
            }
            HostFamilyGeneralQuestion questions = hostFamilyGeneralQuestionRepository.getBySeasonId(hostFamilyReferences.getSeasonId());
            HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(hostFamilyReferences.getSeasonId(),
                  Integer.valueOf(applicationCategoryId));
            hostFamilySeasonCategory.setFilledMandatoryFields(hostFamilyReferences.getFieldsFilled());
            hostFamilySeasonCategoryRepository.saveAndFlush(hostFamilySeasonCategory);
            if (questions != null) {
               questions.setPreviousHostingWithCCI(hostFamilyReferences.isPreviouslyHosted() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               questions.setHostFamilySeason(hostFamilySeasonRepository.findOne(hostFamilyReferences.getSeasonId()));
               questions.setInternet(hostFamilyReferences.getInternet());
               questions.setInternetOthers(hostFamilyReferences.getOtherWebsites());
               questions.setCommunity(hostFamilyReferences.getCommunity());
               questions.setCommunityEvent(hostFamilyReferences.getEvent());
               questions.setCommunityMagazine(hostFamilyReferences.getMagazine());
               questions.setCommunityOthers(hostFamilyReferences.getOtherCommunity());
               hostFamilyGeneralQuestionRepository.saveAndFlush(questions);
               upadatedObject = getHFReference(String.valueOf(hostFamilyReferences.getSeasonId()), applicationCategoryId);
               upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                     "no question details found to update"));
            }
         } else {
            upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  "no reference details found to update"));
         }
      } catch (CcighgoException e) {
         upadatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return upadatedObject;
   }

   @Override
   @Transactional(readOnly = true)
   public HostFamilyReferences getHFReference(String hfSeasonId, String applicationCategoryId) {
      HostFamilyReferences hfReferences = new HostFamilyReferences();
      try {
         if (hfSeasonId == null || applicationCategoryId == null) {
            throw new CcighgoException("invalid search parameters");
         }
         List<Reference> references = new ArrayList<Reference>();
         List<HostFamilyReference> hfRefList = hostFamilyReferenceRepository.findBySeasonId(Integer.valueOf(hfSeasonId));
         for (HostFamilyReference hfr : hfRefList) {
            Reference r = new Reference();
            r.setHostFamilyReferenceId(hfr.getHostFamilyReferenceId());
            r.setFirstName(hfr.getFirstName() != null ? hfr.getFirstName() : CCIConstants.EMPTY);
            r.setLastName(hfr.getLastName() != null ? hfr.getLastName() : CCIConstants.EMPTY);
            r.setStreet(hfr.getAddress() != null ? hfr.getAddress() : CCIConstants.EMPTY);
            r.setCity(hfr.getCity() != null ? hfr.getCity() : CCIConstants.EMPTY);
            if (hfr.getLookupUsstate() != null) {
               r.setState(hfr.getLookupUsstate().getUsStatesId());
            }
            r.setZip(hfr.getZipCode() != null ? hfr.getZipCode() : CCIConstants.EMPTY);
            r.setPhone(hfr.getPhone() != null ? hfr.getPhone() : CCIConstants.EMPTY);
            r.setRelationshipToFamily(hfr.getRelationship() != null ? hfr.getRelationship() : CCIConstants.EMPTY);
            r.setBloodRelated(hfr.getPersonNotRelatedToBlood().equals(CCIConstants.ACTIVE) ? false : true);
            hfReferences.setSecondReferenceRelatedToFirst(hfr.getSecondReferenceRelationtoFirst().equals(CCIConstants.ACTIVE) ? true : false);
            references.add(r);
         }
         HostFamilyGeneralQuestion question = hostFamilyGeneralQuestionRepository.getBySeasonId(Integer.valueOf(hfSeasonId));
         HostFamilySeasonCategory hostFamilySeasonCategory = hostFamilySeasonCategoryRepository.getHFSeasonCategoryBySeasonIdAndCategoryId(Integer.valueOf(hfSeasonId),
               Integer.valueOf(applicationCategoryId));
         if (question != null) {
            hfReferences.setGoId(question.getHostFamilySeason().getHostFamily().getHostFamilyGoId());
            hfReferences.setSeasonId(question.getHostFamilySeason().getSeason().getSeasonId());
            hfReferences.setHostFamilyGenQuestionId(question.getHostFamilyGeneralQuestionsId());
            hfReferences.getReferences().addAll(references);
            hfReferences.setPreviouslyHosted(question.getPreviousHostingWithCCI().equals(CCIConstants.ACTIVE) ? true : false);
            hfReferences.setInternet(question.getInternet() != null ? question.getInternet() : CCIConstants.EMPTY);
            hfReferences.setOtherWebsites(question.getInternetOthers() != null ? question.getInternetOthers() : CCIConstants.EMPTY);
            hfReferences.setCommunity(question.getCommunity() != null ? question.getCommunity() : CCIConstants.EMPTY);
            hfReferences.setEvent(question.getCommunityEvent() != null ? question.getCommunityEvent() : CCIConstants.EMPTY);
            hfReferences.setMagazine(question.getCommunityMagazine() != null ? question.getCommunityMagazine() : CCIConstants.EMPTY);
            hfReferences.setOtherCommunity(question.getCommunityOthers() != null ? question.getCommunityOthers() : CCIConstants.EMPTY);
            hfReferences
                  .setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
         } else {
            hfReferences.setGoId(0);
            hfReferences.setSeasonId(0);
            hfReferences.setHostFamilyGenQuestionId(0);
            hfReferences.getReferences().addAll(references);
            hfReferences.setPreviouslyHosted(false);
            hfReferences.setInternet(CCIConstants.EMPTY);
            hfReferences.setOtherWebsites(CCIConstants.EMPTY);
            hfReferences.setCommunity(CCIConstants.EMPTY);
            hfReferences.setEvent(CCIConstants.EMPTY);
            hfReferences.setMagazine(CCIConstants.EMPTY);
            hfReferences.setOtherCommunity(CCIConstants.EMPTY);
            hfReferences
                  .setPercentUpdate(CCIUtils.getFormFilledPercentage(hostFamilySeasonCategory.getTotalMandatoryFields(), hostFamilySeasonCategory.getFilledMandatoryFields()));
         }
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         hfReferences.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_HF_PHOTOS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return hfReferences;
   }
}
