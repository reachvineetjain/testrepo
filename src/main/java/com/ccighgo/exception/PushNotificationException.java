package com.ccighgo.exception;

import java.io.IOException;

public class PushNotificationException extends IOException {

   private static final long serialVersionUID = -555361284796213682L;

   public PushNotificationException(String errorText) {
      super(errorText);
   }

   public PushNotificationException(String errorText, Throwable cause) {
      super(errorText, cause);
   }

}
