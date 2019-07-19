// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

import com.eomcs.util.Input;


public class App {
  static Scanner keyScan;
  public static void main(String[] args) throws Exception {

    keyScan = new Scanner(System.in);
    
    //명령어를 저장하는 컬랙션
    // => java.util.Stack에서는 Vector클래스의 Iterator를 리턴한다.
    //    Vector에서 제공하는 Iterator는 입력한 순서대로 값을 꺼낸다.
    //    즉 FIFO 방식으로 꺼내기 때문에 스택의 LIFO 방식과 맞지 않다.
    //    그래서 ArrayDeque를 사용하는 것이다.
    //      ArratDeque에서 제공하는 Iterator는 LIFO 방식으로 값을 꺼낸다.
     Deque<String> commandStack = new ArrayDeque<>(); //스택처럼 쓰고싶으면
     Queue<String> commandQueue = new LinkedList<>();
    
    Input input = new Input(keyScan);

    LessonHandler lessonHandler = new LessonHandler(input, new LinkedList<>());

    MemberHandler memberHandler = new MemberHandler(input, new ArrayList<>());

    BoardHandler boardHandler = new BoardHandler(input, new ArrayList<Board>());
    BoardHandler boardHandler2 = new BoardHandler(input, new LinkedList<>()); 

    while (true) {

      String command = prompt();
      
      //사용자가 아무것도 입력하지 않았으면 다시 입력 받는다.
      if(command.length() == 0) {
        continue;
      }
      
      commandStack.push(command); // 사용자가 입력한 명령을 스택에 보관한다.
      commandQueue.offer(command); 

      if (command.equals("quit")) {
        break;

      } else if (command.equals("history")) { // 
        printCommandHistory(commandStack); //
      } else if (command.equals("history2")) { 
        printCommandHistory(commandQueue); //새로복제된 큐의 객체 주소가 createI에 전달


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
  
  private static void printCommandHistory(Iterable<String> list) throws Exception { //파라미터에 스택이든 큐든 상관 없다. Iterable규칙에 따라 만든 규칙이 넘어온다.
    Iterator<String> iterator = list.iterator(); //Iterable따라 만든 규칙은 iterator();
    int count = 0;
    while (iterator.hasNext()) { // 비어있지 않다면
      System.out.println(iterator.next());
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
