package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardAddCommand implements Command {
  private BoardDao boardDao;
  private Input input;

  public BoardAddCommand(Input input, BoardDao boardDao) {

    this.input = input;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() { // Command 규칙을 따라야 하니까, 이름을 변경한다.
    Board board = new Board();
    board.setContents(input.getStringValue("내용은? "));

    try {
      boardDao.insert(board);
      System.out.println("저장하였습니다.");
      
    } catch (Exception e) {
      System.out.println("데이터 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }

  }

}
