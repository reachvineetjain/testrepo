/**
 * 
 */
package com.ccighgo.service.enums;

import java.io.Serializable;

/**
 * @author ravi
 *
 */
public enum SuperRegion implements Serializable {
   WEST(1, "West"), CENTRAL(2, "Central"), EAST(3, "East");

   private int superRegionId;
   private String superRegionName;

   private SuperRegion(int superRegionId, String superRegionName) {
      this.superRegionId = superRegionId;
      this.superRegionName = superRegionName;
   }

   public Integer superRegionId() {
      return superRegionId;
   }

   public String superRegionIdString() {
      return (new Integer(superRegionId)).toString();
   }

}
