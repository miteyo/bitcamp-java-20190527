package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardAddCommand implements Command {
  private List<Board> list;
  private Input input;

  public BoardAddCommand(Input input, List<Board> list) { 

    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() { //Command 규칙을 따라야 하니까, 이름을 변경한다.
    Board board = new Board();

    board.setNo(input.getIntValue("번호는? "));
    board.setContents(input.getStringValue("내용은? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    list.add(board); // 어레이리스트 호출할떄 board 객체 줘도 된다(다형적 변수)
    System.out.println("저장하였습니다.");
  }

}
