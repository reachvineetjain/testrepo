package com.ccighgo.service.components.wordpress.forms;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

   public static void main(String[] args) throws Exception {
      URL url = new URL("http://52.2.191.63:9999/cci_gh_go/wordpressforms?wsdl");
      QName qname = new QName("http://forms.wordpress.components.service.ccighgo.com/", "WordPressFormsImplService");

      Service service = Service.create(url, qname);

      IWordPressForms result = service.getPort(IWordPressForms.class);

      // System.out.println(result.inquiryPartner(new InternationalPartners()));

   }

}
