package org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.aptech.t2303e.bank.BankAccount;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.service.UserService;
import org.aptech.t2303e.bank.consts.CardType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/15/2023, Wednesday
 **/
public class UserServiceImpl implements UserService {
  @Override
  public void insertFile(List<BankAccount> bankAccountList, String fileName) {
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(
        new FileWriter(fileName, true)));

      for (BankAccount b : bankAccountList) {
        StringBuilder sb = new StringBuilder();
        sb.append(b.getName()).append("|");
        out.println(sb.toString());
      }
      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void writeToFile() {
    Scanner scanner = new Scanner(System.in);
    String path = "./etc/bank_account.txt";
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(path, true);
      while (true) {
        System.out.println("Insert line :");
        String line = scanner.nextLine() + "\n";
        try {
          fileWriter.write(line);
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
        System.out.println("Continue ? (Y/N) : ");
        if (scanner.nextLine().equalsIgnoreCase("n")) {
          break;
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        assert fileWriter != null;
        fileWriter.close();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @Override
  public void readFile() {
    List<BankAccount> accountList = new ArrayList<>();
    String path = "./etc/bank_account.txt";
    String header = "ID|CARDTYPE|NAME|CARDNO|IDCARD|MSISDN|ADDRESS|DATEOFBIRTH|BALANCE";
    FileInputStream inputStream = null;
    Scanner scanner;

    try {
      inputStream = new FileInputStream(path);
      scanner = new Scanner(inputStream);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        BankAccount account = convert(header, line);
        if (!Objects.isNull(account)) {
          accountList.add(account);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found!" + e.getMessage());
    } finally {
      try {
        assert inputStream != null;
        inputStream.close();
      } catch (IOException e) {
        System.err.println("IOException " + e.getMessage());
      }
    }
    accountList.forEach(bankAccount -> System.out.println(accountList));
  }

  public static BankAccount convert(String header, String line) {
    if (StringUtils.isEmpty(line))
      return null;
    if (line.trim().equalsIgnoreCase(header))
      return null;
    String[] chars = line.split("\\|");

    return BankAccount.builder()
      .cardType(CardType.valueOf((chars[1])))
      .name(String.valueOf(chars[2]))
      .cardNo(String.valueOf(chars[3]))
      .idCard(String.valueOf(chars[4]))
      .msisdn(String.valueOf(chars[5]))
      .address(String.valueOf(chars[6]))
      .dateOfBirth(LocalDate.parse(chars[7]))
      .balance(Integer.parseInt(chars[8]))
      .build();
  }
}
