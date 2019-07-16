// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Input;

public class App {

  static Scanner keyScan;

  public static void main(String[] args) {

    keyScan = new Scanner(System.in);

    Input input = new Input(keyScan);

    LessonHandler lessonHandler = new LessonHandler(input);

    MemberHandler memberHandler = new MemberHandler(input);

    BoardHandler boardHandler = new BoardHandler(input);
    BoardHandler boardHandler2 = new BoardHandler(input); // 힙에 생성됨. private Board[] boards = new
    // Board[100];


    while (true) {

      String command = prompt();

      if (command.equals("quit")) {
        break;

      } else if (command.equals("/lesson/add")) {
        lessonHandler.addLesson(); // addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.

      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();

      } else if (command.equals("/member/add")) {
        memberHandler.addMember(); // 메소드() 실행할 떄, memberHandler 가 가리키는 곳에 데이터를 추가해라.

      } else if (command.equals("/member/list")) {
        memberHandler.listMember();

      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();

      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();

      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();

      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();

      } else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }

      System.out.println();
    }
  }


  static String prompt() {
    System.out.print("명령> ");
    return keyScan.nextLine();
  }
}
