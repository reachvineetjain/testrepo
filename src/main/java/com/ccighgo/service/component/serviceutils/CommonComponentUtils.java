/**
 * 
 */
package com.ccighgo.service.component.serviceutils;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.common.response.beans.Message;
import com.ccighgo.service.transport.common.response.beans.Status;

/**
 * @author ravi
 *
 */
@Component
public class CommonComponentUtils {
   
   public Status getStatus(String code, String message){
      Status status = new Status();
      status.setCode(code);
      Message msg = new Message();
      msg.setMessage(message);
      status.getMessages().add(msg);
      return status;
   }

}
