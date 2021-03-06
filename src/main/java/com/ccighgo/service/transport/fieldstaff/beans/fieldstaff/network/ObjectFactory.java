

package com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FieldStaffNetwork_QNAME = new QName("http://www.ccighgo.com/fslnet", "FieldStaffNetwork");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffNetwork }
     * 
     */
    public FieldStaffNetwork createFieldStaffNetwork() {
        return new FieldStaffNetwork();
    }

    /**
     * Create an instance of {@link FSLNetwork }
     * 
     */
    public FSLNetwork createFSLNetwork() {
        return new FSLNetwork();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffNetwork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fslnet", name = "FieldStaffNetwork")
    public JAXBElement<FieldStaffNetwork> createFieldStaffNetwork(FieldStaffNetwork value) {
        return new JAXBElement<FieldStaffNetwork>(_FieldStaffNetwork_QNAME, FieldStaffNetwork.class, null, value);
    }

}
