package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author : ad
 * @mailto : luunguyen301297@gmail.com
 * @created : 10/31/2023, Tuesday
 **/
public class FileUtils {
  public static final String HEADER = "ID|CARDTYPE|NAME|CARDNO|IDCARD|MSISDN|ADDRESS|DATEOFBIRTH";
  public static final List<String> CARD_TYPES = Arrays.asList("VISA", "VCB", "HYBRID");
  public static final String SEPARATOR = "|";

  static String randomCardType() {
    return CARD_TYPES.get(RandomUtils.randomNumber(0, 2));
  }

  static String randomCardNo() {
    return String.valueOf(System.currentTimeMillis());
  }

  static String randomIdCard() {
    return String.valueOf(System.currentTimeMillis());
  }

  public static void createBankAccount(String fileName, int num) {
    String path = "./etc/demo/textBank.txt";
    PrintWriter out = null;
    try {
      out = new PrintWriter(new BufferedWriter(
        new FileWriter(path, true)
      ));
      out.println(HEADER);
      for (int i = 0; i < num; i++) {
        StringBuilder builder = new StringBuilder();
        builder.append(i + 1)
          .append(SEPARATOR)
          .append(randomCardType())
          .append(SEPARATOR)
          .append(RandomUtils.randomAlphaNummeric(5))
          .append(SEPARATOR)
          .append(randomCardNo())
          .append(SEPARATOR)
          .append(randomIdCard())
          .append(SEPARATOR)
          .append(RandomUtils.randomMSISDN())
          .append(SEPARATOR)
          .append(RandomUtils.randomAlphaNummeric(10))
          .append(SEPARATOR)
          .append(DateTimeUtils.randomDateOfBirth());
        out.println(builder);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  public static void main(String[] args) {
    String path = "./etc/demo/textBank.txt";
    createBankAccount(path, 100);
  }
}
