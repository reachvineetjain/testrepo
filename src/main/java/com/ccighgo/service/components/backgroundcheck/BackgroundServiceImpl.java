package com.ccighgo.service.components.backgroundcheck;

import org.apache.log4j.Logger;

import com.ccighgo.service.rest.backgroundcheck.BackgroundCheck;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;

public class BackgroundServiceImpl implements BackgroundServiceInterface {
   private static final Logger LOGGER = Logger.getLogger(BackgroundCheck.class);

   @Override
   public ScreenResponse requestScreen(ScreenRequest screenRequest) {
      return null;
      // try {
      // HttpClient client = new DefaultHttpClient();
      // HttpPost post = new HttpPost("http://www.baidu.com");
      // StringWriter sw = new StringWriter();
      // Marshaller jaxbMarshaller = JAXBContext.newInstance(Type.class).createMarshaller();
      // jaxbMarshaller.marshal(screenRequest, sw);
      // String xmlString = sw.toString();
      //
      // HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
      // post.setEntity(entity);
      // HttpResponse response = client.execute(post);
      // String result = EntityUtils.toString(response.getEntity());
      //
      // } catch (Exception e) {
      // ExceptionUtil.logException(e, LOGGER);
      // return null;
      // }
      // return null;
   }

}
