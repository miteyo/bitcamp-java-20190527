package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDeleteCommand implements Command{
  private List<Board> list; 
  private Input input;

  public BoardDeleteCommand(Input input, List<Board> list) { 
    
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < list.size(); i++) {
      Board temp = list.get(i);
      if (temp.getNo() == no) {
        list.remove(i);
        System.out.println("데이터를 삭제했습니다.");
        return;
      }
    }
    System.out.println("해당 게시글을 찾을 수 없습니다.");
    
  }

}
