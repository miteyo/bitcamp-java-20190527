package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input {
  
  public static Scanner keyScan;

   public static int getIntValue(String message) { //다른 클래스에서도 쓸 수 있도록 private 없애줘야한다.
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를  입력하세요.");
      }
    }
  }

   public static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로  입력하세요.");
      }
    }
  }

   public static String getStringValue(String message) {
    while (true) {
      System.out.print(message);
      return keyScan.nextLine();
    }
  }
}
