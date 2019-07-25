package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonAddcommand implements Command {

  private List<Lesson> list;
  private Input input;

  public LessonAddcommand(Input input, List<Lesson> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
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

    list.add(lesson);
    System.out.println("저장하였습니다.");
  }
}
