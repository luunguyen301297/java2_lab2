package org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.impl;

import org.aptech.t2303e.bank.BankAccount;
import org.aptech.t2303e.bank.Transaction;
import org.aptech.t2303e.bank.do_not_know_how_to_name_it_properly.dao.BankDao;
import org.aptech.t2303e.config.properties.Datasource;

import java.sql.*;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 11/15/2023, Wednesday
 **/
public class BankDaoImpl implements BankDao {
  @Override
  public boolean insertUser(BankAccount bankAccount) {
    PreparedStatement preSt;
    Connection conn = Datasource.getConn();
    String sql = "insert into bank_account (idCard, cardType, name, cardNo, msisdn, address, date_of_birth, balance)" +
      " values(?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      preSt = conn.prepareStatement(sql);
      preSt.setString(1, bankAccount.getIdCard());
      preSt.setString(2, String.valueOf(bankAccount.getCardType()));
      preSt.setString(3, bankAccount.getName());
      preSt.setString(4, bankAccount.getCardNo());
      preSt.setString(5, bankAccount.getMsisdn());
      preSt.setString(6, bankAccount.getAddress());
      preSt.setTimestamp(7, new Timestamp(bankAccount.getDateOfBirth().getTime()));
      preSt.setInt(8, bankAccount.getBalance());

      System.out.println(sql);
      preSt.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public boolean insertTransactionInformation(Transaction transaction, String idCard) {
    PreparedStatement preSt;
    Connection conn = Datasource.getConn();
    String sql = "insert into transaction_info (idCard, transaction_type, transaction_amount, transaction_time)"
      + " value (?, ?, ?, ?)";

    try {
      preSt = conn.prepareStatement(sql);

      preSt.setString(1, idCard);
      preSt.setString(2, String.valueOf(transaction.getTransactionType()));
      preSt.setInt(3, transaction.getTransactionAmount());
      preSt.setTimestamp(4, new Timestamp(transaction.getTransactionTime().getTime()));

      System.out.println(sql);
      preSt.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public void getTransactionInformation(Transaction transaction, String idCard) {
    PreparedStatement preSt;
    Connection conn = Datasource.getConn();
    String sql = "Select * from transaction_info where idCard = ?";

    try {
      preSt = conn.prepareStatement(sql);
      preSt.setString(1, idCard);
      ResultSet resultSet = preSt.executeQuery();

      while (resultSet.next()) {
        transaction = transactionRowMapper(resultSet);
      }
      System.out.println(transaction);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static Transaction transactionRowMapper(ResultSet resultSet) {
    Transaction transaction;
    try {
      int id = resultSet.getInt("id");
      String idCard = resultSet.getString("idCard");
      String transactionType = resultSet.getString("transaction_type");
      Time transactionTime = resultSet.getTime("transaction_time");
      int transactionAmount = resultSet.getInt("transaction_amount");

      transaction = Transaction.builder()
        .id(id)
        .idCard(idCard)
        .transactionType(String.valueOf(transactionType))
        .transactionTime(transactionTime)
        .transactionAmount(transactionAmount)
        .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return transaction;
  }
}
