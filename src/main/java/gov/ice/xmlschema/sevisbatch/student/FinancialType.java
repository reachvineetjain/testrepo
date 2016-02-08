//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:23:10 AM CST 
//


package gov.ice.xmlschema.sevisbatch.student;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FinancialType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinancialType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcademicTerm">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="01"/>
 *               &lt;enumeration value="02"/>
 *               &lt;enumeration value="03"/>
 *               &lt;enumeration value="04"/>
 *               &lt;enumeration value="05"/>
 *               &lt;enumeration value="06"/>
 *               &lt;enumeration value="07"/>
 *               &lt;enumeration value="08"/>
 *               &lt;enumeration value="09"/>
 *               &lt;enumeration value="10"/>
 *               &lt;enumeration value="11"/>
 *               &lt;enumeration value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Expense">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tuition" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
 *                   &lt;element name="LivingExpense" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
 *                   &lt;element name="DependentExp" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
 *                   &lt;element name="Other" type="{}FinancialAmountType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Funding">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Personal" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
 *                   &lt;element name="School" type="{}FinancialAmountType" minOccurs="0"/>
 *                   &lt;element name="Other" type="{}FinancialAmountType" minOccurs="0"/>
 *                   &lt;element name="Employment" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
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
@XmlType(name = "FinancialType", propOrder = {
    "academicTerm",
    "expense",
    "funding"
})
public class FinancialType {

    @XmlElement(name = "AcademicTerm", required = true)
    protected String academicTerm;
    @XmlElement(name = "Expense", required = true)
    protected FinancialType.Expense expense;
    @XmlElement(name = "Funding", required = true)
    protected FinancialType.Funding funding;

    /**
     * Gets the value of the academicTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicTerm() {
        return academicTerm;
    }

    /**
     * Sets the value of the academicTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicTerm(String value) {
        this.academicTerm = value;
    }

    /**
     * Gets the value of the expense property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialType.Expense }
     *     
     */
    public FinancialType.Expense getExpense() {
        return expense;
    }

    /**
     * Sets the value of the expense property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialType.Expense }
     *     
     */
    public void setExpense(FinancialType.Expense value) {
        this.expense = value;
    }

    /**
     * Gets the value of the funding property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialType.Funding }
     *     
     */
    public FinancialType.Funding getFunding() {
        return funding;
    }

    /**
     * Sets the value of the funding property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialType.Funding }
     *     
     */
    public void setFunding(FinancialType.Funding value) {
        this.funding = value;
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
     *         &lt;element name="Tuition" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
     *         &lt;element name="LivingExpense" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
     *         &lt;element name="DependentExp" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
     *         &lt;element name="Other" type="{}FinancialAmountType" minOccurs="0"/>
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
        "tuition",
        "livingExpense",
        "dependentExp",
        "other"
    })
    public static class Expense {

        @XmlElement(name = "Tuition")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected int tuition;
        @XmlElement(name = "LivingExpense")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected int livingExpense;
        @XmlElement(name = "DependentExp")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer dependentExp;
        @XmlElement(name = "Other")
        protected FinancialAmountType other;

        /**
         * Gets the value of the tuition property.
         * 
         */
        public int getTuition() {
            return tuition;
        }

        /**
         * Sets the value of the tuition property.
         * 
         */
        public void setTuition(int value) {
            this.tuition = value;
        }

        /**
         * Gets the value of the livingExpense property.
         * 
         */
        public int getLivingExpense() {
            return livingExpense;
        }

        /**
         * Sets the value of the livingExpense property.
         * 
         */
        public void setLivingExpense(int value) {
            this.livingExpense = value;
        }

        /**
         * Gets the value of the dependentExp property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getDependentExp() {
            return dependentExp;
        }

        /**
         * Sets the value of the dependentExp property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setDependentExp(Integer value) {
            this.dependentExp = value;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountType }
         *     
         */
        public FinancialAmountType getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountType }
         *     
         */
        public void setOther(FinancialAmountType value) {
            this.other = value;
        }

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
     *         &lt;element name="Personal" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType"/>
     *         &lt;element name="School" type="{}FinancialAmountType" minOccurs="0"/>
     *         &lt;element name="Other" type="{}FinancialAmountType" minOccurs="0"/>
     *         &lt;element name="Employment" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
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
        "personal",
        "school",
        "other",
        "employment"
    })
    public static class Funding {

        @XmlElement(name = "Personal")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected int personal;
        @XmlElement(name = "School")
        protected FinancialAmountType school;
        @XmlElement(name = "Other")
        protected FinancialAmountType other;
        @XmlElement(name = "Employment")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer employment;

        /**
         * Gets the value of the personal property.
         * 
         */
        public int getPersonal() {
            return personal;
        }

        /**
         * Sets the value of the personal property.
         * 
         */
        public void setPersonal(int value) {
            this.personal = value;
        }

        /**
         * Gets the value of the school property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountType }
         *     
         */
        public FinancialAmountType getSchool() {
            return school;
        }

        /**
         * Sets the value of the school property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountType }
         *     
         */
        public void setSchool(FinancialAmountType value) {
            this.school = value;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountType }
         *     
         */
        public FinancialAmountType getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountType }
         *     
         */
        public void setOther(FinancialAmountType value) {
            this.other = value;
        }

        /**
         * Gets the value of the employment property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getEmployment() {
            return employment;
        }

        /**
         * Sets the value of the employment property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setEmployment(Integer value) {
            this.employment = value;
        }

    }

}
