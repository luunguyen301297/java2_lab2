package org.aptech.t2303e.bank;

import org.aptech.t2303e.bank.consts.CardType;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.BankDao;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.impl.BankDaoImpl;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.service.UserService;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/16/2023, Thursday
 **/
public class Main {
  public static void main(String[] args) {
    BankDao bankDao = new BankDaoImpl();
    UserService userService = new UserServiceImpl();
    Transaction transaction = new Transaction();

    Scanner scanner = new Scanner(System.in);
    int choose;
    do {
      showMenu();
      choose = Integer.parseInt(scanner.nextLine());

      switch (choose) {
        case 1:
          insertUser();
          break;
        case 2:
          conductTransaction();
          break;
        case 3:
          break;
        case 4:
          userService.readFile();
          break;
        case 5:
          System.out.println("Exit the program !");
          break;
        default:
          System.err.println("Wrong key, try again !");
      }
    } while (choose != 4);
  }

  static void showMenu() {
    System.out.println("1. Insert new user to database/txt file");
    System.out.println("2. Conduct transaction and save to database/txt file");
    System.out.println("3. Get transaction by ID CARD");
    System.out.println("4. Read transaction file");
    System.out.println("5. Exit");
  }

  static void insertUser() {
    BankDao bankDao = new BankDaoImpl();
    Scanner scanner = new Scanner(System.in);
    BankAccount account;
    System.out.println("Enter name :");
    String name = scanner.nextLine();
    System.out.println("Enter card type :");
    CardType cardType = CardType.valueOf(scanner.nextLine());
    System.out.println("Enter cardNo :");
    String cardNo = scanner.nextLine();
    System.out.println("Enter id card :");
    String idCard = scanner.nextLine();
    System.out.println("Enter phone number :");
    String phoneNumber = scanner.nextLine();
    System.out.println("Enter address :");
    String address = scanner.nextLine();
    System.out.println("Enter date of birth :");
    Date dateOfBirth;
    try {
      dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    account= BankAccount.builder()
      .name(name)
      .cardType(cardType)
      .cardNo(cardNo)
      .idCard(idCard)
      .msisdn(phoneNumber)
      .address(address)
      .dateOfBirth(dateOfBirth)
      .build();
    bankDao.insertUser(account);
  }

  static void conductTransaction() {
    System.out.println("Choose action :");
    System.out.println("1. Deposit");
    System.out.println("2. WithDraw");
    System.out.println("3. Show transaction history");

    BankAccount account = new BankAccount();
    BankDao bankDao = new BankDaoImpl();
    int choose;
    Scanner scanner = new Scanner(System.in);
    choose = Integer.parseInt(scanner.nextLine());

    switch (choose) {
      case 1:
        System.out.println("Enter deposit amount :");
        int deposit = Integer.parseInt(scanner.nextLine());
        account.deposit(deposit);
        break;
      case 2:
        System.out.println("Enter the amount to withdraw :");
        int withDraw = Integer.parseInt(scanner.nextLine());
        account.withDraw(withDraw);
      case 3:
        Transaction transaction = Transaction.builder().build();
        bankDao.getTransactionInformation(transaction, account.getIdCard());
    }
  }
}
