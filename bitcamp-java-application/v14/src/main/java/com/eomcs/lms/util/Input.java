package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input {
  
  //Input에 연결된 Scanner객체를 각 Input 마다 개별적으로 관리하기 위해 인스턴스필드로 선언한다.
  
  private Scanner keyScan; //new 할떄마다 인스턴스 생성.  키보드로, 파일로 읽는 인스턴스를 쓸 수 있도록.
    //이 값이 인스턴스마다 달라 질 수 있다.

  public Input(Scanner keyScan) { // 생성자 선언. 필수값을 받아들이는 생성자.
    //Input 클래스에 있는 매서드를 사용하는데 필요한 keyScan 변수를 준비시킨다.
    //단, 인슨턴스를 생성할 때, 반드시 Scanner 객체를 넘기도록 강제하기 위해 생성자의 파라미터변수를 선언한다.
    this.keyScan = keyScan;
    //인스턴스 필드를 초기화하고싶다.
    //반드시 keyScan 값을 받겠다. 그래서 파라미터에 Scanner keyScan 썼다.
  }
  
  
  //기존의 스태틱 메소드를 인스턴스 메서드로 전환한 이유?
  //=> 각 Input 객체마다 Scanner를 구분해서 다루기 위함이다.
   public int getIntValue(String message) { //다른 클래스에서도 쓸 수 있도록 private 없애줘야한다.
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를  입력하세요.");
      }
    }
  }

   public Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로  입력하세요.");
      }
    }
  }

   public String getStringValue(String message) {
    while (true) {
      System.out.print(message);
      return keyScan.nextLine();
    }
  }
}
