package com.ccighgo.service.components.sevis;

public enum StudentReprintReason {
   /*
    * <xs:simpleType name="StudentReprintRequestReasonType"> <xs:annotation>
    * <xs:documentation>Reprint Request Reason</xs:documentation>
    * </xs:annotation> <xs:restriction base="xs:string"> <xs:enumeration
    * value="01"/> <xs:enumeration value="02"/> <xs:enumeration value="03"/>
    * <xs:enumeration value="04"/> <xs:enumeration value="50"/> <xs:enumeration
    * value="53"/> </xs:restriction> </xs:simpleType>
    */

   ONE("01"), TWO("02"), THREE("03"), FOUR("04"), FIFTY("50"), FIFTY_THREE("53");

   private String code;

   private StudentReprintReason(String code) {
      this.code = code;
   }

   public String getCode() {
      return code;
   }

}
