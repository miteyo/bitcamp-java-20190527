// 생성자 활용 - java.util.Date 클래스의 생성자
package ch10;

import java.util.Date;

public class Test15 {
  public static void main(String[] args) throws Exception {
    
    // Date() 기본 생성자
    Date d1 = new Date(); // 현재 시간을 저장한다. //오늘날짜의 년월일시분초가..
    System.out.println(d1);
    
    // Date(long) : 1970-01-01 00:00:00 부터 지금까지 경과된 밀리초 
    Date d2 = new Date(1000);
    System.out.println(d2);
    
    Date d3 = new Date(System.currentTimeMillis());
    System.out.println(d3);
    
    Date d4 = new Date(119, 0, 15); //119: 1900년대를 기준으로. 0:1월 
    System.out.println(d4);
    
    // java.sql.Date
    java.sql.Date d5 = new java.sql.Date(System.currentTimeMillis());
    System.out.println(d5);
    
    // 간접적으로 객체를 생성하기
    java.sql.Date d6 = java.sql.Date.valueOf("2019-1-16"); //Date 클래스의 Va~ 스태틱 메소드를 사용해서. 
    System.out.println(d6);
  }
}
















