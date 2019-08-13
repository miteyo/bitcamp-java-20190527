package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonDetailCommand implements Command {

  private LessonDao lessonDao;

  public LessonDetailCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    try {

      int no = Input.getIntValue(in, out, "번호? ");
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("해당 번호의 데이터가 없습니다.");
        return;
      }

      out.printf("수업명: %s\n", lesson.getTitle());
      out.printf("수업내용: %s\n", lesson.getContents());
      out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
      out.printf("총수업시간: %d\n", lesson.getTotalHours());
      out.printf("수업명: %d\n", lesson.getDayHours());

    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}
