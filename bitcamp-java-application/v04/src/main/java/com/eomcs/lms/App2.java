package com.eomcs.lms;

import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    Scanner keyScan = new Scanner(keyboard);

    System.out.println("번호는? ");
    String no = keyScan.nextLine();

    System.out.println("이름은? ");
    String name = keyScan.nextLine();

    System.out.println("이메일은? ");
    String email = keyScan.nextLine();

    System.out.println("암호는? ");
    String password = keyScan.nextLine();

    System.out.println("사진은? ");
    String picture = keyScan.nextLine();

    System.out.println("전화번호는? ");
    String phoneNo = keyScan.nextLine();

    System.out.println();


    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNo);
    System.out.println("가입일: 2019-01-01" );

  }

}
