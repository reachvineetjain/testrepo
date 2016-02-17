//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 11:14:24 AM CST 
//


package gov.ice.xmlschema.sevisbatch.exchangevisitor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Container for all TIPP Phase start and end dates
 * 
 * <p>Java class for TippPhaseDates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TippPhaseDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TippPhase" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="StartDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
 *                   &lt;element name="EndDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TippPhaseDates", propOrder = {
    "tippPhase"
})
public class TippPhaseDates {

    @XmlElement(name = "TippPhase", required = true)
    protected List<TippPhaseDates.TippPhase> tippPhase;

    /**
     * Gets the value of the tippPhase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tippPhase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTippPhase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TippPhaseDates.TippPhase }
     * 
     * 
     */
    public List<TippPhaseDates.TippPhase> getTippPhase() {
        if (tippPhase == null) {
            tippPhase = new ArrayList<TippPhaseDates.TippPhase>();
        }
        return this.tippPhase;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="StartDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
     *         &lt;element name="EndDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "phaseId",
        "startDate",
        "endDate"
    })
    public static class TippPhase {

        @XmlElement(name = "PhaseId")
        protected int phaseId;
        @XmlElement(name = "StartDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar startDate;
        @XmlElement(name = "EndDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar endDate;

        /**
         * Gets the value of the phaseId property.
         * 
         */
        public int getPhaseId() {
            return phaseId;
        }

        /**
         * Sets the value of the phaseId property.
         * 
         */
        public void setPhaseId(int value) {
            this.phaseId = value;
        }

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getStartDate() {
            return startDate;
        }

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setStartDate(XMLGregorianCalendar value) {
            this.startDate = value;
        }

        /**
         * Gets the value of the endDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEndDate() {
            return endDate;
        }

        /**
         * Sets the value of the endDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEndDate(XMLGregorianCalendar value) {
            this.endDate = value;
        }

    }

}
