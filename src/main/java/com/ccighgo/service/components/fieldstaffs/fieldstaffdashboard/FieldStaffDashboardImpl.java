package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategory;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.FieldStaffWorkQueueType;
import com.ccighgo.db.entities.FieldStaffWorkQueueTypeAggregate;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoriesRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardCategorieDetails;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardType;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardTypes;
import com.ccighgo.utils.CCIConstants;

@Component
public class FieldStaffDashboardImpl implements FieldStaffDashboardInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffDashboardInterface.class);
   @Autowired
   FieldStaffWorkQueueTypeRepository fieldStaffWorkQueueTypeRepository;
   @Autowired
   FieldStaffWorkQueueTypeAggregateRepository fieldStaffWorkQueueTypeAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoryAggregateRepository fieldStaffWorkQueueCategoryAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoriesRepository fieldStaffWorkQueueCategoriesRepository;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;

   @Override
   public ErdDashboard getErdDashboardWorkQueues(String fieldStaffGoId) {

      ErdDashboard erdDashboard = new ErdDashboard();
      ErdDashboardTypes erdDashboardTypes = new ErdDashboardTypes();
      try {
         List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypeRepository.findAll();
         if (fieldStaffWorkQueueTypes != null)
            for (FieldStaffWorkQueueType type : fieldStaffWorkQueueTypes) {
               ErdDashboardType t = new ErdDashboardType();
               t.setTypeId(type.getFieldStaffWQTypeId());
               t.setType(type.getFieldStaffWQTypeName());
               FieldStaffWorkQueueTypeAggregate aggregate = fieldStaffWorkQueueTypeAggregateRepository.getTypeAggregate(Integer.valueOf(fieldStaffGoId), type
                     .getLookupDepartmentProgram().getLookupDepartmentProgramId(), type.getFieldStaffWQTypeId());
               t.setNumber(aggregate.getFieldStaffWQTypeAggregate().toString());
               List<FieldStaffWorkQueueCategory> FieldStaffWorkQueueCategories = fieldStaffWorkQueueCategoriesRepository.findAllCategoriesByTypeId(type.getFieldStaffWQTypeId());
               for (FieldStaffWorkQueueCategory catagory : FieldStaffWorkQueueCategories) {
                  ErdDashboardCategorieDetails details = new ErdDashboardCategorieDetails();
                  details.setCategorieId(catagory.getFieldStaffWQCategoryId());
                  details.setCategorie(catagory.getFieldStaffWQCategoryName());
                  FieldStaffWorkQueueCategoryAggregate catAggregate = fieldStaffWorkQueueCategoryAggregateRepository.getCategoryAggregate(Integer.valueOf(fieldStaffGoId), catagory
                        .getFieldStaffWorkQueueType().getLookupDepartmentProgram().getLookupDepartmentProgramId(), catagory.getFieldStaffWQCategoryId());
                  details.setNumber(catAggregate.getFieldStaffWQCategoryAggregate().toString());
                  t.getCategories().add(details);
               }
               erdDashboardTypes.getTypes().add(t);
            }
         erdDashboard.setErdDashboardTypes(erdDashboardTypes);
         erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DASHBOARD.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD));
      }
      return erdDashboard;
   }
}
