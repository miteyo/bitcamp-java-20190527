package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  static Scanner keyscan;

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyscan = new Scanner(keyboard);

    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    int[] password = new int[100];
    String[] picture = new String[100];
    int[] phoneNo = new int[100];


    int i = 0;
    for (; i < no.length; i++) {

      no[i] = getIntValue("번호는? ");
      name[i] = getStringValue("이름? ");
      email[i] = getStringValue("이메일은? ");
      password[i] = getIntValue("암호는? ");
      picture[i] = getStringValue("사진은? ");
      phoneNo[i] = getIntValue("전화번호는? ");

      System.out.println("계속하시겠습니까? (Y/n)");
      String response = keyscan.nextLine();
      if (response.equals("n"))
        break;
    }

    System.out.println();

    for (int i2 = 0; i2 <= i; i2++) {
      System.out.printf("%s, %s, %s, %s, %s\n", no[i2], name[i2], email[i2], password[i2],
          phoneNo[i2]);
      
    }
  }

  private static int getIntValue(String massage) {
    while (true) {
      try {
        System.out.println(massage);
        return Integer.parseInt(keyscan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력해주세요.");
      }

    }
  }


  private static String getStringValue(String massage) {
    while (true) {
      System.out.println(massage);
      return keyscan.nextLine();

    }
  }



}
