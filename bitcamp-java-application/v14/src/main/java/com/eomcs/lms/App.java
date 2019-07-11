// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.lms.util.Input;

public class App {

  static Scanner keyScan;

  public static void main(String[] args) {

    keyScan = new Scanner(System.in);
    
    //Input 생성자를 통해 Input이 의존하는 객체인 Scanner를 주입한다.
    Input input = new Input(keyScan); //Input 클래스의 인스턴스를 만들려면 반드시 필수값(스캐너)줘라 (생성자) , 생성자의 파라미터는 그 인스턴스를 만드는데 필요한 필수값.
                                        //keyScan이  안꼽힌 인풋은 만들어질 가능성 없다.
    
    //각 핸들러의 생성자를 통해 의존 객체 "Input"을 주입한다.
    // => 이렇게 어떤 객체가 필요로 하는 의존객체를 주입하는 것을
    //      의존성 주입(Dependency Injection: DI)라고 한다.
    //      DI를 전문적으로 관리해주는 프레임워크는 Spring IoC컨테이너!
   
    LessonHandler lessonHandler = new LessonHandler(input); //필수의존객체를 꼽아야한다.(핸들러에 생성자를 만들었으니까)
    //핸들러는 input을 사용한다.
    MemberHandler memberHandler = new MemberHandler(input);

    BoardHandler boardHandler = new BoardHandler(input);
    BoardHandler boardHandler2 = new BoardHandler(input); // 힙에 생성됨. private Board[] boards = new


    while (true) {

      String command = prompt();

      if (command.equals("quit")) {
        break;

      } else if (command.equals("/lesson/add")) {
        lessonHandler.addLesson(); // addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.

      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();

      } else if (command.equals("/member/add")) {
        memberHandler.addMember();  //메소드() 실행할 떄, memberHandler 가 가리키는 곳에 데이터를 추가해라.

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
