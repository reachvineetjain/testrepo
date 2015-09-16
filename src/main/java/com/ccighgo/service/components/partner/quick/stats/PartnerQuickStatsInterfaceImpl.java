/**
 * 
 */
package com.ccighgo.service.components.partner.quick.stats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.partner.beans.partnerquickstats.PartnerQuickStatCategory;
import com.ccighgo.service.transport.partner.beans.partnerquickstats.PartnerQuickStatType;
import com.ccighgo.service.transport.partner.beans.partnerquickstats.PartnerQuickStats;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class PartnerQuickStatsInterfaceImpl implements PartnerQuickStatsInterface {
   
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;

   @Override
   public PartnerQuickStats getPartnerQuickStats(String partnerId) {
      PartnerQuickStats partnerQuickStats = new PartnerQuickStats();
      partnerQuickStats.setPartnerId(1);
      List<PartnerQuickStatType> partnerQuickStatTypes = new ArrayList<PartnerQuickStatType>();
      
      List<PartnerQuickStatCategory> partnerQuickStatCategories = new ArrayList<PartnerQuickStatCategory>();
      PartnerQuickStatCategory category1 = new PartnerQuickStatCategory();
      category1.setPartnerId(1);
      category1.setPartnerQuickStatCategoryName("Participant Review");
      category1.setPartnerQuickStatCategoryNo(20);
      
      PartnerQuickStatCategory category2 = new PartnerQuickStatCategory();
      category2.setPartnerId(1);
      category2.setPartnerQuickStatCategoryName("Partner Review");
      category2.setPartnerQuickStatCategoryNo(30);
      
      PartnerQuickStatCategory category3 = new PartnerQuickStatCategory();
      category3.setPartnerId(1);
      category3.setPartnerQuickStatCategoryName("Greenheart Review");
      category3.setPartnerQuickStatCategoryNo(5);
      
      PartnerQuickStatCategory category4 = new PartnerQuickStatCategory();
      category4.setPartnerId(1);
      category4.setPartnerQuickStatCategoryName("Accepted");
      category4.setPartnerQuickStatCategoryNo(1);
      
      partnerQuickStatCategories.add(category1);
      partnerQuickStatCategories.add(category2);
      partnerQuickStatCategories.add(category3);
      partnerQuickStatCategories.add(category4);
      
      PartnerQuickStatType quickStatType1 = new PartnerQuickStatType();
      quickStatType1.setPartnerId(1);
      quickStatType1.setPartnerQuickStatTypeName("Lorem Ipsum");
      quickStatType1.setPartnerQuickStatTypeNo(20);
      quickStatType1.getPartnerQuickStatCategories().addAll(partnerQuickStatCategories);
      
      PartnerQuickStatType quickStatType2 = new PartnerQuickStatType();
      quickStatType2.setPartnerId(1);
      quickStatType2.setPartnerQuickStatTypeName("Participant");
      quickStatType2.setPartnerQuickStatTypeNo(30);
      quickStatType2.getPartnerQuickStatCategories().addAll(partnerQuickStatCategories);
      
      PartnerQuickStatType quickStatType3 = new PartnerQuickStatType();
      quickStatType3.setPartnerId(1);
      quickStatType3.setPartnerQuickStatTypeName("F1 Host Family Placements");
      quickStatType3.setPartnerQuickStatTypeNo(3);
      quickStatType3.getPartnerQuickStatCategories().addAll(partnerQuickStatCategories);
      
      PartnerQuickStatType quickStatType4 = new PartnerQuickStatType();
      quickStatType4.setPartnerId(1);
      quickStatType4.setPartnerQuickStatTypeName("F1 school placemets");
      quickStatType4.setPartnerQuickStatTypeNo(34);
      quickStatType4.getPartnerQuickStatCategories().addAll(partnerQuickStatCategories);
      
      partnerQuickStatTypes.add(quickStatType1);
      partnerQuickStatTypes.add(quickStatType2);
      partnerQuickStatTypes.add(quickStatType3);
      partnerQuickStatTypes.add(quickStatType4);
      partnerQuickStats.getPartnerQuickStatTypes().addAll(partnerQuickStatTypes);
      partnerQuickStats.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
            messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      return partnerQuickStats;
   }

}
