package org.aptech.t2303e.bank.consts;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/15/2023, Wednesday
 **/
public enum TransactionType {
  DEPOSIT("DEPOSIT"),
  WITHDRAW("WITHDRAW");
  public final String type;

  private TransactionType(String type) {
    this.type = type;
  }
}
