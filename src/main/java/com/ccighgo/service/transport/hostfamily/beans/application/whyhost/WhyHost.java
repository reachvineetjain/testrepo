//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.07 at 12:04:22 PM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.whyhost;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for WhyHost complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WhyHost">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostfamilySeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hostFamilyHomeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fieldsFilled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="percentUpdate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="whyFamilyInterestedInHosting" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hopingToLearn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aspectsOfAmericanCultureYouWillShare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="willYouBeWorkingasLCForAnotherOrg" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="forWhomYouWillBeWorkingasLCForAnotherOrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="haveYouHostedForAnotherOrg" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ifYesForWhomAndHowManyYears" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="familyExpectationOnStudentResponsibility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WhyHost", propOrder = {
    "loginId",
    "goId",
    "hostfamilySeasonId",
    "hostFamilyHomeId",
    "fieldsFilled",
    "percentUpdate",
    "whyFamilyInterestedInHosting",
    "hopingToLearn",
    "aspectsOfAmericanCultureYouWillShare",
    "willYouBeWorkingasLCForAnotherOrg",
    "forWhomYouWillBeWorkingasLCForAnotherOrg",
    "haveYouHostedForAnotherOrg",
    "ifYesForWhomAndHowManyYears",
    "familyExpectationOnStudentResponsibility"
})
public class WhyHost
    extends Response
{

    protected int loginId;
    protected int goId;
    protected int hostfamilySeasonId;
    protected int hostFamilyHomeId;
    protected int fieldsFilled;
    protected double percentUpdate;
    @XmlElement(required = true)
    protected String whyFamilyInterestedInHosting;
    @XmlElement(required = true)
    protected String hopingToLearn;
    @XmlElement(required = true)
    protected String aspectsOfAmericanCultureYouWillShare;
    protected boolean willYouBeWorkingasLCForAnotherOrg;
    @XmlElement(required = true)
    protected String forWhomYouWillBeWorkingasLCForAnotherOrg;
    protected boolean haveYouHostedForAnotherOrg;
    @XmlElement(required = true)
    protected String ifYesForWhomAndHowManyYears;
    @XmlElement(required = true)
    protected String familyExpectationOnStudentResponsibility;

    /**
     * Gets the value of the loginId property.
     * 
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     */
    public void setLoginId(int value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the goId property.
     * 
     */
    public int getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     */
    public void setGoId(int value) {
        this.goId = value;
    }

    /**
     * Gets the value of the hostfamilySeasonId property.
     * 
     */
    public int getHostfamilySeasonId() {
        return hostfamilySeasonId;
    }

    /**
     * Sets the value of the hostfamilySeasonId property.
     * 
     */
    public void setHostfamilySeasonId(int value) {
        this.hostfamilySeasonId = value;
    }

    /**
     * Gets the value of the hostFamilyHomeId property.
     * 
     */
    public int getHostFamilyHomeId() {
        return hostFamilyHomeId;
    }

    /**
     * Sets the value of the hostFamilyHomeId property.
     * 
     */
    public void setHostFamilyHomeId(int value) {
        this.hostFamilyHomeId = value;
    }

    /**
     * Gets the value of the fieldsFilled property.
     * 
     */
    public int getFieldsFilled() {
        return fieldsFilled;
    }

    /**
     * Sets the value of the fieldsFilled property.
     * 
     */
    public void setFieldsFilled(int value) {
        this.fieldsFilled = value;
    }

    /**
     * Gets the value of the percentUpdate property.
     * 
     */
    public double getPercentUpdate() {
        return percentUpdate;
    }

    /**
     * Sets the value of the percentUpdate property.
     * 
     */
    public void setPercentUpdate(double value) {
        this.percentUpdate = value;
    }

    /**
     * Gets the value of the whyFamilyInterestedInHosting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhyFamilyInterestedInHosting() {
        return whyFamilyInterestedInHosting;
    }

    /**
     * Sets the value of the whyFamilyInterestedInHosting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhyFamilyInterestedInHosting(String value) {
        this.whyFamilyInterestedInHosting = value;
    }

    /**
     * Gets the value of the hopingToLearn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHopingToLearn() {
        return hopingToLearn;
    }

    /**
     * Sets the value of the hopingToLearn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHopingToLearn(String value) {
        this.hopingToLearn = value;
    }

    /**
     * Gets the value of the aspectsOfAmericanCultureYouWillShare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAspectsOfAmericanCultureYouWillShare() {
        return aspectsOfAmericanCultureYouWillShare;
    }

    /**
     * Sets the value of the aspectsOfAmericanCultureYouWillShare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAspectsOfAmericanCultureYouWillShare(String value) {
        this.aspectsOfAmericanCultureYouWillShare = value;
    }

    /**
     * Gets the value of the willYouBeWorkingasLCForAnotherOrg property.
     * 
     */
    public boolean isWillYouBeWorkingasLCForAnotherOrg() {
        return willYouBeWorkingasLCForAnotherOrg;
    }

    /**
     * Sets the value of the willYouBeWorkingasLCForAnotherOrg property.
     * 
     */
    public void setWillYouBeWorkingasLCForAnotherOrg(boolean value) {
        this.willYouBeWorkingasLCForAnotherOrg = value;
    }

    /**
     * Gets the value of the forWhomYouWillBeWorkingasLCForAnotherOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForWhomYouWillBeWorkingasLCForAnotherOrg() {
        return forWhomYouWillBeWorkingasLCForAnotherOrg;
    }

    /**
     * Sets the value of the forWhomYouWillBeWorkingasLCForAnotherOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForWhomYouWillBeWorkingasLCForAnotherOrg(String value) {
        this.forWhomYouWillBeWorkingasLCForAnotherOrg = value;
    }

    /**
     * Gets the value of the haveYouHostedForAnotherOrg property.
     * 
     */
    public boolean isHaveYouHostedForAnotherOrg() {
        return haveYouHostedForAnotherOrg;
    }

    /**
     * Sets the value of the haveYouHostedForAnotherOrg property.
     * 
     */
    public void setHaveYouHostedForAnotherOrg(boolean value) {
        this.haveYouHostedForAnotherOrg = value;
    }

    /**
     * Gets the value of the ifYesForWhomAndHowManyYears property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfYesForWhomAndHowManyYears() {
        return ifYesForWhomAndHowManyYears;
    }

    /**
     * Sets the value of the ifYesForWhomAndHowManyYears property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfYesForWhomAndHowManyYears(String value) {
        this.ifYesForWhomAndHowManyYears = value;
    }

    /**
     * Gets the value of the familyExpectationOnStudentResponsibility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyExpectationOnStudentResponsibility() {
        return familyExpectationOnStudentResponsibility;
    }

    /**
     * Sets the value of the familyExpectationOnStudentResponsibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyExpectationOnStudentResponsibility(String value) {
        this.familyExpectationOnStudentResponsibility = value;
    }

}
