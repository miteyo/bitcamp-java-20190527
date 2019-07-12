package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private BoardList boardList = new BoardList(); // ()값을 안주면 기본 100개 배열, (1000) 이면 initialSize 생성자가
                                                 // 호출되서 1000개 만들도록

  private Input input;

  public BoardHandler(Input input) {
    this.input = input;
  }

  public void addBoard() {
    Board board = new Board();
    board.setNo(input.getIntValue("번호는? "));
    board.setContents(input.getStringValue("내용은? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    boardList.add(board); // lesson 수업 주소를 주면서 LessonList클래스에서 파라미터로 받은 lesson로 배열에 추가한다.
    System.out.println("저장하였습니다.");

  }

  public void listBoard() {
    Board[] boards = boardList.toArray(); // lessonList야~ toArray에 들어있는 데이터 배열에 있는 값 리턴해줘
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

}
