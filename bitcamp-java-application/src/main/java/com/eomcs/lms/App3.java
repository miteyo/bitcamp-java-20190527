package com.eomcs.lms;

import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    
    java.io.InputStream keyboard = System.in;
    Scanner keyScan = new Scanner(keyboard);
    
    System.out.println("번호는? ");
    String no = keyScan.nextLine();
    
    System.out.println("내용은? ");
    String contents = keyScan.nextLine();

    System.out.println();
    
    System.out.println("번호: " + no);
    System.out.println("내용: " + contents);
    System.out.println("작성일: 2019-01-01");
    System.out.println("조회수: 0");
  }

}
