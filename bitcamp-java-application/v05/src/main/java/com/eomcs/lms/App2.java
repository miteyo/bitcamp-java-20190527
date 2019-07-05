package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  static Scanner keyscan;

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyscan = new Scanner(keyboard);

    int no = getIntValue("번호는? ");
    String name = getStringValue("이름? "); 
    String email = getStringValue("이메일은? ");
    int password = getIntValue("암호는? ");
    String picture = getStringValue("사진은? ");
    int phoneNo = getIntValue("전화번호는? ");
    
    System.out.println();

    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNo);
    System.out.println("가입일: 2019-01-01");

  }


  private static int getIntValue(String massage) {
    while (true) {
      try {
      System.out.println(massage);
      return Integer.parseInt(keyscan.nextLine());
      }catch(NumberFormatException e) {
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
