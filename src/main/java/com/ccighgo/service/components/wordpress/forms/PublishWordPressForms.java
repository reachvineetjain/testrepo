package com.ccighgo.service.components.wordpress.forms;

import javax.xml.ws.Endpoint;

import org.springframework.stereotype.Component;

@Component
public class PublishWordPressForms {

   static {
      Endpoint.publish("http://localhost:9999/cci_gh_go/wordpressforms", new WordPressFormsImpl());
   }
}
