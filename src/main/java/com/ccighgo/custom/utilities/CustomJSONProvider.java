/**
 * 
 */
package com.ccighgo.custom.utilities;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ravi
 *
 */
@Component
public class CustomJSONProvider extends JacksonJaxbJsonProvider {

   /*
    * Jettison provided by cxf as default had issues in creating JSON when list
    * has only one element. Changed provider to Jackson but Jackson has its own
    * issues in missing root element while creating JSON, so had to override
    * default settings.
    */
   public CustomJSONProvider() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
      mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
      _mapperConfig.setMapper(mapper);
      _mapperConfig.getConfiguredMapper().setAnnotationIntrospector(new JaxbAnnotationIntrospector());
   }

}
