package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {

  private Board[] boards = new Board[100];
  private int boardsSize = 0;
  
  private Input input; // 외부로부터 인풋 객체를 받아서 그 객체 input. 로 값을 받아오게 하자.
  
  // BoardHandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야하는 객체를 "의존객체(dependency)"라고 부른다.
  // => 의존객체를 강제로 설정하게 만드든 방법?
  //   다음과 같이 의존객체를 넘겨 받는 생성자를 정의하는 것이다.
  public BoardHandler(Input input) {
    //생성자에서 넘겨받은 값을 static에 넣는 것은 부질없다. 왜? 하나에 계속 덮어 씌우니까, 
    //=>static 떼고 인스턴스멤버로 만든다 and 이 값이 외부에서 바꾸지 못하도록 private로 만든다.
    this.input = input;
  }
  
  

  public void addBoard() {
    Board board = new Board();
    board.no = input.getIntValue("번호는? ");
    board.contents = input.getStringValue("내용은? ");
    board.createdDate = new Date(System.currentTimeMillis());

    boards[boardsSize++] = board;
    System.out.println("저장하였습니다.");
  }

  
  public void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }

}
