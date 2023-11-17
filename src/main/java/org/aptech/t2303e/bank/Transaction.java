package org.aptech.t2303e.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/16/2023, Thursday
 **/
@Data
@Builder
@AllArgsConstructor
public class Transaction{
  private int autoIncrement = 0;
  private int id;
  private String idCard;
  private String transactionType;
  private int transactionAmount;
  private Date transactionTime;

  public Transaction() {
    this.id = ++autoIncrement;
  }
}
