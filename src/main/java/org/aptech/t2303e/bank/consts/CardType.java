package org.aptech.t2303e.bank.consts;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/14/2023, Tuesday
 **/
public enum CardType {
  JCB_BANK("JCB_BANK"),
  VISA("VISA"),
  HYBRID("HYBRID");
  public final String type;

  private CardType(String type) {
    this.type = type;
  }
}
