//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.04 at 01:45:15 PM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for DepartmentResourceGroups complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepartmentResourceGroups">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="departmentResourceGroup" type="{http://www.ccighgo.com/departmentresourcegroup}DepartmentResourceGroupTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepartmentResourceGroups", propOrder = {
    "departmentResourceGroup"
})
public class DepartmentResourceGroups
    extends Response
{

    @XmlElement(required = true)
    protected List<DepartmentResourceGroupTO> departmentResourceGroup;

    /**
     * Gets the value of the departmentResourceGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departmentResourceGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartmentResourceGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepartmentResourceGroupTO }
     * 
     * 
     */
    public List<DepartmentResourceGroupTO> getDepartmentResourceGroup() {
        if (departmentResourceGroup == null) {
            departmentResourceGroup = new ArrayList<DepartmentResourceGroupTO>();
        }
        return this.departmentResourceGroup;
    }

}
