package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class LessonHandler { // 분리: 핸들러는 입출력만, 데이터 보관은 LessonList클래스에서

  private ArrayList lessonList = new ArrayList();   // 핸들러는 저장하는 일을 List클래스로 넘겼으니까, List를 쓰기위해서 여기서
                                                    // 객체를 생성한다. lessonlist 는 배열을(LessonList클래스의) 갖고있다.  안주면 100개배열, 
  private Input input;

  public LessonHandler(Input input) {
    this.input = input;
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    lesson.setNo(input.getIntValue("번호? "));
    lesson.setTitle(input.getStringValue("수업명? "));
    lesson.setContents(input.getStringValue("설명? "));
    lesson.setStartDate(input.getDateValue("시작일? "));
    lesson.setEndDate(input.getDateValue("종료일? "));
    lesson.setTotalHours(input.getIntValue("총 수업시간? "));
    lesson.setDayHours(input.getIntValue("일 수업시간?"));

    // LessonHandler에서 직접 데이터를 보관하지 않고
    // LessonList에게 전달한다 ->private LessonList lessonList = new LessonList(); 만든다.

    lessonList.add(lesson); 
    System.out.println("저장하였습니다.");
  }

  public void listLesson() {
    Object[] list = lessonList.toArray(); // lessonList야~ toArray에 들어있는 데이터 배열에 있는 값 리턴해줘
    for (Object obj : list) {
      Lesson lesson = (Lesson)obj;
      
      System.out.printf("%s, %s, %s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(),
          lesson.getContents(), lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }


}
