package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.Input;

public class LessonHandler {

  private Lesson[] lessons = new Lesson[100]; // ,레퍼런스배열 100개를 만들어놈.
  private int lessonsSize = 0;

  public static Scanner keyScan;

  public void addLesson() {
    Lesson lesson = new Lesson();

    lesson.no = Input.getIntValue("번호? ");
    lesson.title = Input.getStringValue("수업명? ");
    lesson.contents = Input.getStringValue("설명? ");
    lesson.startDate = Input.getDateValue("시작일? ");
    lesson.endDate = Input.getDateValue("종료일? ");
    lesson.totalHours = Input.getIntValue("총 수업시간? ");
    lesson.dayHours = Input.getIntValue("일 수업시간?");

    lessons[lessonsSize++] = lesson;// 현재 size 0, 일단 0이 놓이고. lesson에서는 0이 우선 저장이되고(size는 1이 되었어도)
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    for (int i = 0; i < lessonsSize; i++) { // 현재 lesson 인스턴스 주소가 들어있는 배열 번호까지
      Lesson lesson = lessons[i]; // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다
      System.out.printf("%s, %s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.contents,
          lesson.startDate, lesson.endDate, lesson.totalHours);
    }
  }

}
