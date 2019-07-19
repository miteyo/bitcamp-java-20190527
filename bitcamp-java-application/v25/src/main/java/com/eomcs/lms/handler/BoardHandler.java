package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardHandler {
  private List<Board> boardList; //콕찝어서 LinkedList 쓰겠다고 하면 안된다.
  private Input input;

  public BoardHandler(Input input, List<Board> list) { //데이터 저장하기위해 인터페이스(어떤 클래스든지 상관없다. list 규칙에 따른 거라면 괜찮다)
    
    this.input = input;
    this.boardList = list;
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

  public void detailBoard() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
      }
    }

    if (board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
    }

    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreatedDate());
  }

  public void updateBoard() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    String str = input.getStringValue("내용? ");
    if (str.length() > 0) {
      board.setContents(str);
      System.out.println("게시글을 변경했습니다.");
    }
  }

  public void deleteBoard() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        boardList.remove(i);
        return;
      }
    }
    System.out.println("해당 게시글을 찾을 수 없습니다.");
    
  }

}
