package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static Scanner keyScan;

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    Board[] boards = new Board[100];

    int i = 0;
    for (; i < boards.length; i++) {
      Board board = new Board();

      board.no = getIntValue("번호는? ");
      board.contents = getStringValue("내용은? ");
      board.viewCount = 0;
      board.createdDate = getDateValue("작성일은? ");

          boards[i] = board;

      System.out.print("계속 입력하시겠습니까? (Y/n)");
      String response = keyScan.nextLine();

      if (response.equals("n"))
        break;
    }
    System.out.println();

    for (int i2 = 0; i2 <= i; i2++) {
      Board board = boards[i2];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate, board.viewCount);
    }
  }


  public static int getIntValue(String massage) {
    while (true) {
      try {
        System.out.println(massage);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("다시 입력해주세요");
      }
    }
  }

  public static String getStringValue(String massage) {
    while (true) {
      System.out.println(massage);
      return keyScan.nextLine();
    }
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로  입력하세요.");
      }
    }
  }

}
