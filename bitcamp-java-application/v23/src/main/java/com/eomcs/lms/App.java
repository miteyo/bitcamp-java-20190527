// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;
import com.eomcs.util.Queue;
import com.eomcs.util.Stack;

public class App {
  static Scanner keyScan;
  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();

  public static void main(String[] args) throws Exception {

    keyScan = new Scanner(System.in);

    Input input = new Input(keyScan);

    LessonHandler lessonHandler = new LessonHandler(input, new LinkedList<>());

    MemberHandler memberHandler = new MemberHandler(input, new ArrayList<>());

    BoardHandler boardHandler = new BoardHandler(input, new ArrayList<Board>());
    BoardHandler boardHandler2 = new BoardHandler(input, new LinkedList<>()); 

    while (true) {

      String command = prompt();

      commandStack.push(command); // 사용자가 입력한 명령을 스택에 보관한다.
      commandQueue.offer(command); 

      if (command.equals("quit")) {
        break;

      } else if (command.equals("history")) { // 입력한 명령을 stack형식(최신순)으로 호출하는 메소드
        printCommandHistory();

      } else if (command.equals("history2")) { 
        printCommandHistory2();


      } else if (command.equals("/lesson/add")) {
        lessonHandler.addLesson(); // addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.

      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();

      } else if (command.equals("/lesson/detail")) {
        lessonHandler.detailLesson();

      } else if (command.equals("/lesson/update")) {
        lessonHandler.updateLesson();

      } else if (command.equals("/lesson/delete")) {
        lessonHandler.deleteLesson();

      } else if (command.equals("/member/add")) {
        memberHandler.addMember(); // 메소드() 실행할 떄, memberHandler 가 가리키는 곳에 데이터를 추가해라.

      } else if (command.equals("/member/list")) {
        memberHandler.listMember();

      } else if (command.equals("/member/detail")) {
        memberHandler.detailMember();

      } else if (command.equals("/member/update")) {
        memberHandler.updateMember();

      } else if (command.equals("/member/delete")) {
        memberHandler.deleteMember();

      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();

      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();

      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();

      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();

      } else if (command.equals("/board/detail")) {
        boardHandler.detailBoard();

      } else if (command.equals("/board/update")) {
        boardHandler.updateBoard();

      } else if (command.equals("/board/delete")) {
        boardHandler.deleteBoard();

      } else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }

      System.out.println();
    }
  }
  private static void printCommandHistory2() throws Exception {
    Queue<String> queue = commandQueue.clone(); // 원본스택을 복제하는 것이다.
    int count = 0;
    while (!queue.empty()) { // 비어있지 않다면
      System.out.println(queue.poll());
      if(++count % 5 == 0) {    //출력하고 증가시키기.
        System.out.print(":");
        if(keyScan.nextLine().equalsIgnoreCase("q"))
        break;
      }
    }
  }
  
  private static void printCommandHistory() throws Exception {
    Stack<String> stack = commandStack.clone(); // 원본스택을 복제하는 것이다.
    int count = 0;
    while (!stack.empty()) { // 비어있지 않다면
      System.out.println(stack.pop());
      if(++count % 5 == 0) {    //출력하고 증가시키기.
        System.out.print(":");
        if(keyScan.nextLine().equalsIgnoreCase("q"))
        break;
      }
    }
  }

  private static String prompt() { // 이 클래스 안에서만 씁니다.
    System.out.print("명령> ");
    return keyScan.nextLine();
  }
}
