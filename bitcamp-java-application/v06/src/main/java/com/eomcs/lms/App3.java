package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  static Scanner keyScan;

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    int[] no = new int[100];
    String[] contents = new String[100];
    int[] view = new int[100];
    
    int i = 0;
    for (; i < no.length; i++) {
      no[i] = getIntValue("번호는? ");
      contents[i] = getStringValue("내용은? ");
      view[i] = 0;
      
      System.out.print("계속 입력하시겠습니까? (Y/n)");
      String response = keyScan.nextLine();

      if (response.equals("n"))
        break;
    }
    System.out.println();

    for (int i2=0; i2<=i;  i2++) {
      System.out.printf("%s, %s, %s\n", no[i2], contents[i2], view[i2]);
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



}
