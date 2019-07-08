package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner keyScan;

  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    Member[] members = new Member[100]; //인스턴스 주소를 담을 배열 
    
    
    int i = 0;
    for (   ; i < members.length; i++) {
      
      Member member = new Member();
      member.no = getIntValue("번호? ");
      member.name = getStringValue("이름? ");
      member.email = getStringValue("이메일? ");
      member.password = getStringValue("암호? ");
      member.photo = getStringValue("사진? ");
      member.tel = getStringValue("전화번호? ");
      member.registeredDate = getDateValue("가입일? ");
      
      members[i] = member; //배열의 i번 방에 현재 만든 따끈따끈한 인스턴스 주소를 보관해야죠.
      
      System.out.println("계속하시겠습니까? (Y/n)");
      String response = keyScan.nextLine();
      if (response.equals("n"))
        break;
    }

    System.out.println();
    
    for (int i2 = 0; i2 <= i; i2++) {
     Member member = members[i2]; //멤버스i2에 들어있는 주소를 memeber에 넣어라.
      System.out.printf("%s, %s , %s, %s, %s\n", 
                      member.no, member.name, member.email, member.tel, member.registeredDate);

    }
  }

  private static int getIntValue(String massage) {
    while (true) {
      try {
        System.out.println(massage);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력해주세요.");
      }

    }
  }


  private static String getStringValue(String massage) {
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
