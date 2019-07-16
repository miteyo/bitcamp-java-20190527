package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class BoardHandler {
  private ArrayList<Board> boardList = new ArrayList<>();

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
    // Board[] boards = boardList.toArray(new Board[] {}); //0개의 배열을 줘서 toArray의 if문이 작동하도록

    Board[] boards = new Board[boardList.size()];
    boardList.toArray(boards); //
    for (Board board : boards) {

      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

}
