package org.aptech.t2303e.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.aptech.t2303e.bank.consts.CardType;
import org.aptech.t2303e.bank.consts.TransactionType;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.BankDao;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.impl.BankDaoImpl;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 10/29/2023, Sunday
 **/
@Data
@Builder
@AllArgsConstructor
public class BankAccount {
  private CardType cardType;
  private String name, cardNo, idCard, msisdn, address;
  private Date dateOfBirth;
  private int balance;
  public static final int MIN_BALANCE = 50000;

  public void withDraw(int num){
    Transaction transaction = Transaction.builder()
      .transactionType(TransactionType.WITHDRAW.type)
      .transactionAmount(num)
      .transactionTime(new Date())
      .build();

    System.out.println(transaction.getTransactionType() + " | ID Card : " + getIdCard());
    if (num > getBalance() - MIN_BALANCE) {
      System.out.println("Balance : " + getBalance());
    } else {
      System.out.println("hello con dy ngheo kho");
    }
    setBalance(getBalance() - num);
  }

  public void deposit(int num) {
    Transaction transaction = Transaction.builder()
      .transactionType(TransactionType.DEPOSIT.type)
      .transactionAmount(num)
      .transactionTime(new Date())
      .build();

    System.out.println(transaction.getTransactionType() + " | ID Card : " + getIdCard());
    setBalance(getBalance() + num);
    System.out.println("Balance : " + getBalance());

    BankDao bankDao = new BankDaoImpl();
    bankDao.insertTransactionInformation(transaction, getIdCard());
  }

  public BankAccount() {

  }

  public void setName(String name) {
    if (!name.isEmpty() && name.matches("^[a-zA-Z]*$")) {
      this.name = name;
    } else {
      System.err.println("Incorrect name format");
    }
  }

  public void setIdCard(String idCard) {
    if (idCard.length() == 12 && idCard.matches("[0-9]+")) {
      this.idCard = idCard;
    } else {
      System.err.println("Incorrect cardNo format");
    }
  }

  public void setCardNo(String cardNo) {
    if (cardNo.length() == 12 && cardNo.matches("[0-9]+")) {
      this.cardNo = cardNo;
    } else {
      System.err.println("Incorrect cardNo format");
    }
  }

  public void setTell(String msisdn) {
    if (msisdn.length() == 7 && msisdn.matches("[0-9]+")) {
      this.msisdn = msisdn;
    } else {
      System.err.println("The phone number must have 7 digits !");
    }
  }

  public void setAddress(String address) {
    if (!address.isEmpty() && address.matches("^[0-9a-zA-Z]*$")) {
      this.address = address;
    } else {
      System.err.println("Incorrect address format");
    }
  }

  public void setDateOfBirth(Date dateOfBirth) {
    Date dayNow = new Date();
    try {
      if (dateOfBirth.toInstant().isBefore(dayNow.toInstant()) && dateOfBirth.getYear() > 1800) {
        this.dateOfBirth = dateOfBirth;
      } else {
        System.err.println("Are you using a time machine ?");
      }
    } catch (Exception e) {
      System.err.println(e.getMessage() + " / Incorrect date format !");
    }
  }
}
