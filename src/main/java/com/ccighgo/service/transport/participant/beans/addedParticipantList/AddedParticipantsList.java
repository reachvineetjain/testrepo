//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.16 at 12:04:14 PM IST 
//


package com.ccighgo.service.transport.participant.beans.addedParticipantList;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for AddedParticipantsList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddedParticipantsList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="participantCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participants" type="{http://www.ccighgo.com/addedParticipantList}AddedParticipantsDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddedParticipantsList", propOrder = {
    "participantCount",
    "participants"
})
public class AddedParticipantsList
    extends Response
{

    protected int participantCount;
    protected List<AddedParticipantsDetails> participants;

    /**
     * Gets the value of the participantCount property.
     * 
     */
    public int getParticipantCount() {
        return participantCount;
    }

    /**
     * Sets the value of the participantCount property.
     * 
     */
    public void setParticipantCount(int value) {
        this.participantCount = value;
    }

    /**
     * Gets the value of the participants property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the participants property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParticipants().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddedParticipantsDetails }
     * 
     * 
     */
    public List<AddedParticipantsDetails> getParticipants() {
        if (participants == null) {
            participants = new ArrayList<AddedParticipantsDetails>();
        }
        return this.participants;
    }

}
