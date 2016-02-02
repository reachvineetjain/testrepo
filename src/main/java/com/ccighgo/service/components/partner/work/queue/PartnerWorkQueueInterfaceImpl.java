/**
 * 
 */
package com.ccighgo.service.components.partner.work.queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.partner.beans.partneruser.PartnerWorkQueue;
import com.ccighgo.service.transport.partner.beans.partneruser.PartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partneruser.PartnerWorkQueueType;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class PartnerWorkQueueInterfaceImpl implements PartnerWorkQueueInterface {
   
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;

   @Override
   public PartnerWorkQueue getPartnerWorkQueues(String partnerId) {
      PartnerWorkQueue partnerWorkQueue = new PartnerWorkQueue();
      partnerWorkQueue.setPartnerId(1);
      List<PartnerWorkQueueType> partnerWorkQueueTypes = new ArrayList<PartnerWorkQueueType>();
      
      List<PartnerWorkQueueCategory> partnerWorkQueueCategories = new ArrayList<PartnerWorkQueueCategory>();
      
      PartnerWorkQueueCategory category1 = new PartnerWorkQueueCategory();
      category1.setPartnerId(1);
      category1.setPartnerWorkQueueCategoryName("Evaluation");
      category1.setPartnerWorkQueueCategoryNo(20);
      
      PartnerWorkQueueCategory category2 = new PartnerWorkQueueCategory();
      category2.setPartnerId(1);
      category2.setPartnerWorkQueueCategoryName("Monthly Reports");
      category2.setPartnerWorkQueueCategoryNo(23);
      
      PartnerWorkQueueCategory category3 = new PartnerWorkQueueCategory();
      category3.setPartnerId(1);
      category3.setPartnerWorkQueueCategoryName("Monitoring Updates");
      category3.setPartnerWorkQueueCategoryNo(32);
      
      PartnerWorkQueueCategory category4 = new PartnerWorkQueueCategory();
      category4.setPartnerId(1);
      category4.setPartnerWorkQueueCategoryName("Release Forms");
      category4.setPartnerWorkQueueCategoryNo(12);
      
      PartnerWorkQueueCategory category5 = new PartnerWorkQueueCategory();
      category5.setPartnerId(1);
      category5.setPartnerWorkQueueCategoryName("Travel Request Forms");
      category5.setPartnerWorkQueueCategoryNo(204);
      
      partnerWorkQueueCategories.add(category1);
      partnerWorkQueueCategories.add(category2);
      partnerWorkQueueCategories.add(category3);
      partnerWorkQueueCategories.add(category4);
      partnerWorkQueueCategories.add(category5);
      
      
      PartnerWorkQueueType partnerWorkQueueType1 = new PartnerWorkQueueType();
      partnerWorkQueueType1.setPartnerId(1);
      partnerWorkQueueType1.setPartnerWorkQueueTypeName("Application");
      partnerWorkQueueType1.setPartnerWorkQueueTypeNo(20);
      partnerWorkQueueType1.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
      
      PartnerWorkQueueType partnerWorkQueueType2 = new PartnerWorkQueueType();
      partnerWorkQueueType2.setPartnerId(1);
      partnerWorkQueueType2.setPartnerWorkQueueTypeName("Change");
      partnerWorkQueueType2.setPartnerWorkQueueTypeNo(33);
      partnerWorkQueueType2.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
      
      PartnerWorkQueueType partnerWorkQueueType3 = new PartnerWorkQueueType();
      partnerWorkQueueType3.setPartnerId(1);
      partnerWorkQueueType3.setPartnerWorkQueueTypeName("Placement");
      partnerWorkQueueType3.setPartnerWorkQueueTypeNo(25);
      partnerWorkQueueType3.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
      
      PartnerWorkQueueType partnerWorkQueueType4 = new PartnerWorkQueueType();
      partnerWorkQueueType4.setPartnerId(1);
      partnerWorkQueueType4.setPartnerWorkQueueTypeName("Monitoring");
      partnerWorkQueueType4.setPartnerWorkQueueTypeNo(35);
      partnerWorkQueueType4.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
      
      PartnerWorkQueueType partnerWorkQueueType5 = new PartnerWorkQueueType();
      partnerWorkQueueType5.setPartnerId(1);
      partnerWorkQueueType5.setPartnerWorkQueueTypeName("Flights");
      partnerWorkQueueType5.setPartnerWorkQueueTypeNo(12);
      partnerWorkQueueType5.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
      
      partnerWorkQueueTypes.add(partnerWorkQueueType1);
      partnerWorkQueueTypes.add(partnerWorkQueueType2);
      partnerWorkQueueTypes.add(partnerWorkQueueType3);
      partnerWorkQueueTypes.add(partnerWorkQueueType4);
      partnerWorkQueueTypes.add(partnerWorkQueueType5);
      
      partnerWorkQueue.getPartnerWorkQueueTypes().addAll(partnerWorkQueueTypes);
      
      partnerWorkQueue.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
            messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      return partnerWorkQueue;
   }

}
