//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 02:20:20 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.myhostfamily;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for MyHostFamilyForFieldStaff complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MyHostFamilyForFieldStaff">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="myHostFamily" type="{http://www.ccighgo.com/myhostfamily}MyHostFamilyForFieldStaffDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MyHostFamilyForFieldStaff", propOrder = {
    "myHostFamily"
})
public class MyHostFamilyForFieldStaff
    extends Response
{

    @XmlElement(required = true)
    protected List<MyHostFamilyForFieldStaffDetail> myHostFamily;

    /**
     * Gets the value of the myHostFamily property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the myHostFamily property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMyHostFamily().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MyHostFamilyForFieldStaffDetail }
     * 
     * 
     */
    public List<MyHostFamilyForFieldStaffDetail> getMyHostFamily() {
        if (myHostFamily == null) {
            myHostFamily = new ArrayList<MyHostFamilyForFieldStaffDetail>();
        }
        return this.myHostFamily;
    }

}