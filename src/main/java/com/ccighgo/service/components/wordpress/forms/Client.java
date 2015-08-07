package com.ccighgo.service.components.wordpress.forms;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.internationalPartners.InternationalPartners;

public class Client {

   public static void main(String[] args) throws Exception {

      URL url = new URL("http://localhost:8085/cci_gh_go/wordpressforms?wsdl");
      QName qname = new QName("http://forms.wordpress.components.service.ccighgo.com/", "WordPressFormsImplService");

      Service service = Service.create(url, qname);

      WordPressFormsInterface result = service.getPort(WordPressFormsInterface.class);

      System.out.println(result.inquiryPartner(new InternationalPartners()));

   }

}
