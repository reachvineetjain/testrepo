package com.ccighgo.service.components.wordpress.forms;

import javax.xml.ws.Endpoint;

public class PublishWordPressForms {

   static {
      Endpoint.publish("http://localhost:9999/cci_gh_go_test/wordpressforms", new WordPressFormsImpl());
   }
}
