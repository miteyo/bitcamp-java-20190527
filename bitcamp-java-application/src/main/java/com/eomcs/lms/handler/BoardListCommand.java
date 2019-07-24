package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command {
  private List<Board> list;
  private Input input;

  public BoardListCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
    // Board[] boards = boardList.toArray(new Board[] {}); //0개의 배열을 줘서 toArray의 if문이 작동하도록

    Board[] boards = new Board[list.size()];
    list.toArray(boards); //
    for (Board board : boards) {

      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
          board.getCreatedDate(), board.getViewCount());
    }
  }


}
