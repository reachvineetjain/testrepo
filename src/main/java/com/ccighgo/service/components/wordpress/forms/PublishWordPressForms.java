package com.ccighgo.service.components.wordpress.forms;

import javax.xml.ws.Endpoint;

public class PublishWordPressForms {

   public static void main(String[] args) {
      Endpoint.publish("http://localhost:8085/cci_gh_go/wordpressforms", new WordPressFormsImpl());
   }
}
