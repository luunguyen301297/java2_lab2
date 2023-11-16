package org.aptech.t2303e.bank;

import org.aptech.t2303e.bank.consts.CardType;
import org.aptech.t2303e.bank.consts.TransactionType;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.BankDao;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.impl.BankDaoImpl;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/16/2023, Thursday
 **/
public class Main {
  public static void main(String[] args) {
    BankDao bankDao = new BankDaoImpl();

    BankAccount account = BankAccount.builder()
      .cardType(CardType.VISA)
      .name("A")
      .cardNo("11231231231")
      .idCard("533535354")
      .msisdn("6464647373")
      .address("asdfggf")
      .dateOfBirth(LocalDate.parse("1997-11-11"))
      .build();
    account.deposit(100000);

    Transaction transaction = Transaction.builder()
      .transactionType(TransactionType.DEPOSIT.type)
      .transactionAmount(account.getBalance())
      .transactionTime(new Date())
      .build();

    bankDao.insertTransactionInformation(transaction);
    bankDao.getTransactionInformation(account.getIdCard());
  }
}
