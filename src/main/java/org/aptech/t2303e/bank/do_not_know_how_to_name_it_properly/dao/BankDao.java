package org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao;

import org.aptech.t2303e.bank.BankAccount;
import org.aptech.t2303e.bank.Transaction;

import java.util.List;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/15/2023, Wednesday
 **/
public interface BankDao {
  boolean insertUser(BankAccount bankAccount);
  boolean insertTransactionInformation(Transaction transaction, String idCard);
  List<Transaction> getTransactionInformation(Transaction transaction, String idCard);
}
