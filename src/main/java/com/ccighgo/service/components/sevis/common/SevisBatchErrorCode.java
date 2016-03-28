package com.ccighgo.service.components.sevis.common;

public enum SevisBatchErrorCode {
   POST_PARAM_NA(999990), BATCH_CREATE_FAILED(999991);

   private int value;

   private SevisBatchErrorCode(int v) {
      this.value = v;
   }

   public int getValue() {
      return value;
   }

}
