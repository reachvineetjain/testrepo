//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 03:48:23 PM CST 
//


package com.ccighgo.service.transport.sevis;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BatchType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Create.Student"/>
 *     &lt;enumeration value="Update.EV.Validate.usAddress"/>
 *     &lt;enumeration value="Update.EV.Biographical"/>
 *     &lt;enumeration value="Update.EV.Biographical.usAddress"/>
 *     &lt;enumeration value="Update.Student.PersonalInfo.usAddress"/>
 *     &lt;enumeration value="Update.EV.SiteOfActivity.Edit"/>
 *     &lt;enumeration value="Update.EV.Program.Extension"/>
 *     &lt;enumeration value="Update.Student.Program.Extension"/>
 *     &lt;enumeration value="Update.EV.Program.Shorten"/>
 *     &lt;enumeration value="Update.Student.Program.Shorten"/>
 *     &lt;enumeration value="Update.EV.Reprint"/>
 *     &lt;enumeration value="Update.EV.Dependent.Reprint"/>
 *     &lt;enumeration value="Update.Student.Reprint"/>
 *     &lt;enumeration value="Update.Student.Dependent.Reprint"/>
 *     &lt;enumeration value="Update.EV.Dependent.EndStatus"/>
 *     &lt;enumeration value="Update.Student.Status.Complete"/>
 *     &lt;enumeration value="Update.EV.Status.Invalid"/>
 *     &lt;enumeration value="Update.EV.Status.Terminate"/>
 *     &lt;enumeration value="Update.Student.Status.Terminate"/>
 *     &lt;enumeration value="Update.EV.Program.EditSubject"/>
 *     &lt;enumeration value="Update.EV.FinancialInfo"/>
 *     &lt;enumeration value="Update.Student.FinancialInfo"/>
 *     &lt;enumeration value="Update.EV.TIPP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BatchType")
@XmlEnum
public enum BatchType {

    @XmlEnumValue("Create.Student")
    CREATE_STUDENT("Create.Student"),
    @XmlEnumValue("Update.EV.Validate.usAddress")
    UPDATE_EV_VALIDATE_US_ADDRESS("Update.EV.Validate.usAddress"),
    @XmlEnumValue("Update.EV.Biographical")
    UPDATE_EV_BIOGRAPHICAL("Update.EV.Biographical"),
    @XmlEnumValue("Update.EV.Biographical.usAddress")
    UPDATE_EV_BIOGRAPHICAL_US_ADDRESS("Update.EV.Biographical.usAddress"),
    @XmlEnumValue("Update.Student.PersonalInfo.usAddress")
    UPDATE_STUDENT_PERSONAL_INFO_US_ADDRESS("Update.Student.PersonalInfo.usAddress"),
    @XmlEnumValue("Update.EV.SiteOfActivity.Edit")
    UPDATE_EV_SITE_OF_ACTIVITY_EDIT("Update.EV.SiteOfActivity.Edit"),
    @XmlEnumValue("Update.EV.Program.Extension")
    UPDATE_EV_PROGRAM_EXTENSION("Update.EV.Program.Extension"),
    @XmlEnumValue("Update.Student.Program.Extension")
    UPDATE_STUDENT_PROGRAM_EXTENSION("Update.Student.Program.Extension"),
    @XmlEnumValue("Update.EV.Program.Shorten")
    UPDATE_EV_PROGRAM_SHORTEN("Update.EV.Program.Shorten"),
    @XmlEnumValue("Update.Student.Program.Shorten")
    UPDATE_STUDENT_PROGRAM_SHORTEN("Update.Student.Program.Shorten"),
    @XmlEnumValue("Update.EV.Reprint")
    UPDATE_EV_REPRINT("Update.EV.Reprint"),
    @XmlEnumValue("Update.EV.Dependent.Reprint")
    UPDATE_EV_DEPENDENT_REPRINT("Update.EV.Dependent.Reprint"),
    @XmlEnumValue("Update.Student.Reprint")
    UPDATE_STUDENT_REPRINT("Update.Student.Reprint"),
    @XmlEnumValue("Update.Student.Dependent.Reprint")
    UPDATE_STUDENT_DEPENDENT_REPRINT("Update.Student.Dependent.Reprint"),
    @XmlEnumValue("Update.EV.Dependent.EndStatus")
    UPDATE_EV_DEPENDENT_END_STATUS("Update.EV.Dependent.EndStatus"),
    @XmlEnumValue("Update.Student.Status.Complete")
    UPDATE_STUDENT_STATUS_COMPLETE("Update.Student.Status.Complete"),
    @XmlEnumValue("Update.EV.Status.Invalid")
    UPDATE_EV_STATUS_INVALID("Update.EV.Status.Invalid"),
    @XmlEnumValue("Update.EV.Status.Terminate")
    UPDATE_EV_STATUS_TERMINATE("Update.EV.Status.Terminate"),
    @XmlEnumValue("Update.Student.Status.Terminate")
    UPDATE_STUDENT_STATUS_TERMINATE("Update.Student.Status.Terminate"),
    @XmlEnumValue("Update.EV.Program.EditSubject")
    UPDATE_EV_PROGRAM_EDIT_SUBJECT("Update.EV.Program.EditSubject"),
    @XmlEnumValue("Update.EV.FinancialInfo")
    UPDATE_EV_FINANCIAL_INFO("Update.EV.FinancialInfo"),
    @XmlEnumValue("Update.Student.FinancialInfo")
    UPDATE_STUDENT_FINANCIAL_INFO("Update.Student.FinancialInfo"),
    @XmlEnumValue("Update.EV.TIPP")
    UPDATE_EV_TIPP("Update.EV.TIPP");
    private final String value;

    BatchType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchType fromValue(String v) {
        for (BatchType c: BatchType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}