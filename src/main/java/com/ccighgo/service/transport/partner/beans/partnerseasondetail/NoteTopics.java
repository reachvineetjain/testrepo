//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 11:35:51 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasondetail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NoteTopics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NoteTopics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="topicCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="topicList" type="{http://www.ccighgo.com/partnerseasondetail}Topic" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoteTopics", propOrder = {
    "topicCount",
    "topicList"
})
public class NoteTopics {

    protected int topicCount;
    protected List<Topic> topicList;

    /**
     * Gets the value of the topicCount property.
     * 
     */
    public int getTopicCount() {
        return topicCount;
    }

    /**
     * Sets the value of the topicCount property.
     * 
     */
    public void setTopicCount(int value) {
        this.topicCount = value;
    }

    /**
     * Gets the value of the topicList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topicList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopicList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Topic }
     * 
     * 
     */
    public List<Topic> getTopicList() {
        if (topicList == null) {
            topicList = new ArrayList<Topic>();
        }
        return this.topicList;
    }

}
