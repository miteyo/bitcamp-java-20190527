package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {

  private Board[] boards = new Board[100];
  private int boardsSize = 0;

  private Input input;

  public BoardHandler(Input input) {
    this.input = input;
  }

  public void addBoard() {
    Board board = new Board();
    board.setNo(input.getIntValue("번호는? "));
    board.setContents(input.getStringValue("내용은? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    boards[boardsSize++] = board;
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }

}
