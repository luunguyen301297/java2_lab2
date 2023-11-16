package org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.service;

import org.aptech.t2303e.bank.BankAccount;

import java.util.List;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/15/2023, Wednesday
 **/
public interface UserService {
  void insertFile(List<BankAccount> userList, String fileName);

  void readFile();
}
