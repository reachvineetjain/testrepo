package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaffWorkQueueCategory;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.FieldStaffWorkQueueType;
import com.ccighgo.db.entities.FieldStaffWorkQueueTypeAggregate;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoriesRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeRepository;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardcategories.ErdDashboardCategorieDetails;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardcategories.ErdDashboardCategories;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardtype.ErdDashboardTypeDetails;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardtype.ErdDashboardTypes;
import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

@Component
public class FieldStaffDashboardImpl implements FieldStaffDashboardInterface {

   @Autowired
   FieldStaffWorkQueueTypeRepository fieldStaffWorkQueueTypeRepository;
   @Autowired
   FieldStaffWorkQueueTypeAggregateRepository fieldStaffWorkQueueTypeAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoryAggregateRepository fieldStaffWorkQueueCategoryAggregateRepository;
   @Autowired
   FieldStaffWorkQueueCategoriesRepository fieldStaffWorkQueueCategoriesRepository;

   @Override
   public ErdDashboardTypes getErdDashboardWorkQueuesType(String programId, String fieldStaffGoId) {

      ErdDashboardTypes erdDashboardTypes = new ErdDashboardTypes();
      List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypeRepository.getFieldStaffWorkQueueTypesByDepartmentProgramId(Integer.valueOf(programId));
      if(fieldStaffWorkQueueTypes!=null)
      for (FieldStaffWorkQueueType type : fieldStaffWorkQueueTypes) {
         ErdDashboardTypeDetails detatil = new ErdDashboardTypeDetails();
         detatil.setTypeId(type.getFieldStaffWQTypeId());
         detatil.setType(type.getFieldStaffWQTypeName());
         FieldStaffWorkQueueTypeAggregate aggregate = fieldStaffWorkQueueTypeAggregateRepository.getTypeAggregate(Integer.valueOf(fieldStaffGoId), Integer.valueOf(programId),
               type.getFieldStaffWQTypeId());
         detatil.setNumber(aggregate.getFieldStaffWQTypeAggregate().toString());
         erdDashboardTypes.getTypes().add(detatil);
      }
      return erdDashboardTypes;
   }

   @Override
   public ErdDashboardCategories getErdDashboardWorkQueuesCategories(String typeId, String fieldStaffGoId) {

      ErdDashboardCategories erdDashboardCategories = new ErdDashboardCategories();
      List<FieldStaffWorkQueueCategory> FieldStaffWorkQueueCategories = fieldStaffWorkQueueCategoriesRepository.findAllCategoriesByTypeId(Integer.valueOf(typeId));
      for (FieldStaffWorkQueueCategory catagory : FieldStaffWorkQueueCategories) {
         ErdDashboardCategorieDetails details = new ErdDashboardCategorieDetails();
         details.setCategorieId(catagory.getFieldStaffWQCategoryId());
         details.setCategorie(catagory.getFieldStaffWQCategoryName());
         FieldStaffWorkQueueCategoryAggregate aggregate = fieldStaffWorkQueueCategoryAggregateRepository.getCategoryAggregate(Integer.valueOf(fieldStaffGoId), catagory
               .getFieldStaffWorkQueueType().getLookupDepartmentProgram().getLookupDepartmentProgramId(), catagory.getFieldStaffWQCategoryId());
         details.setNumber(aggregate.getFieldStaffWQCategoryAggregate().toString());
         erdDashboardCategories.getCategories().add(details);

      }
      return erdDashboardCategories;
   }

}
