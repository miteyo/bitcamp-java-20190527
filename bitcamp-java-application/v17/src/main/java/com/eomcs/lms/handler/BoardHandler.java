package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class BoardHandler {
  private ArrayList boardList = new ArrayList();

  private Input input;

  public BoardHandler(Input input) {
    this.input = input;
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(input.getIntValue("번호는? "));
    board.setContents(input.getStringValue("내용은? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    boardList.add(board); // 어레이리스트 호출할떄 board 객체 줘도 된다(다형적 변수)
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Object[] list = boardList.toArray(); //
    for (Object obj : list) {
      Board board = (Board) obj; // obj가 일반용도로 쓸 수 있도록 타입캐스팅 한다.

      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

}
