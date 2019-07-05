package com.eomcs.lms;

import java.sql.Date;

public class LessonTest {
  public static void main(String[] args) {
    
    //수업데이터를 저장하기 위해 낱개의 메모리를 일일이 준비해서 사요하는 경우:
    int no = 1;
    String title = "자바";
    String contents= "자바개발";
    Date startDate = Date.valueOf("2019-01-01");
    Date endDate = Date.valueOf("2019-02-02");
    int totalHOurs = 100;
    int dayHours = 4;
    
    //수업데이터를 저장하기 위해 미리 준비한 메모리 설계도를 이용하는 경우:
    //=> Lesson 메모리 설계도(클래스)에 따라 메모리를 준비하기.
    //
    Lesson lesson =new Lesson();
    
    //레퍼런스에 저장된 주소로 찾아가서 메모리에 값 넣기.
    lesson.no=2;
    lesson.title = "웹 프로그래밍";
    lesson.contents = "웹 개발자";
    lesson.startDate = Date.valueOf("2019-2-2");
    lesson.endDate = Date.valueOf("2019-5-5");
    lesson.totalHours = 200;
    lesson.dayHours = 4;
    
    System.out.printf("%s -- %s\n", no, lesson.no);
    System.out.printf("%s -- %s\n", title, lesson.title);
    
    
//    Lesson lesson2 = null;
//    lesson2.no = 200; //주소가 없는데 no를 어떻게 찾지 
    
    Lesson lesson2 = lesson;
    lesson.title = "오호라!";
    System.out.println(lesson2.title); //오호라!
    
    
    
  
    
    
    
    
    
    
    
  }
}
