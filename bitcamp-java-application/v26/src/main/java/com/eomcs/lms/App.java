// 애플리케이션 Main 클리스
// =>애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.CalcplusCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.HiCommand;
import com.eomcs.lms.handler.LessonAddcommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
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
    //    ArratDeque에서 제공하는 Iterator는 LIFO 방식으로 값을 꺼낸다.
     Deque<String> commandStack = new ArrayDeque<>(); //스택처럼 쓰고싶으면
     Queue<String> commandQueue = new LinkedList<>();
    
    Input input = new Input(keyScan);
    
    //Command 객체를 보관할 Map 준비
    HashMap<String, Command> commandMap = new HashMap<>();
    
    ArrayList<Member> memberList = new ArrayList<>();
    commandMap.put("/member/add", new MemberAddCommand(input, memberList));
    commandMap.put("/member/list", new MemberListCommand(input, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(input, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(input, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(input, memberList));
    
    ArrayList<Lesson> lessonList = new ArrayList<>();
    commandMap.put("/lesson/add", new LessonAddcommand(input, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(input, lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(input, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(input, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(input, lessonList));
    
    ArrayList<Board> boardList = new ArrayList<>();
    commandMap.put("/board/add", new BoardAddCommand(input, boardList));
    commandMap.put("/board/list", new BoardListCommand(input, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(input, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(input, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(input, boardList));

    commandMap.put("/hi" , new HiCommand(input));
    commandMap.put("/calc/plus" , new CalcplusCommand(input));
    
    while (true) {
      String command = prompt();
      
      //사용자가 아무것도 입력하지 않았으면 다시 입력 받는다.
      if(command.length() == 0) {
        continue;
      }
      
      commandStack.push(command); 
      commandQueue.offer(command); 

      //사용자가 입력한 명령어를 처리할 Command 객체를 Map에서 꺼내기
      Command executor = commandMap.get(command); //커맨드를 맵에서 command 명령어를 처리 할 executor  
      //Command executor 에 넘어오는 객체가 무엇이든 상관 없다. Command 인터페이스로 구현한 객체이기 때문에
      
      if (command.equals("quit")) {
        break;

      } else if (command.equals("history")) { // 
        printCommandHistory(commandStack); //
      } else if (command.equals("history2")) { 
        printCommandHistory(commandQueue); //새로복제된 큐의 객체 주소가 createI에 전달

      }else if (executor != null) {
        executor.execute();

      } else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }

      System.out.println();
    }
  }
  
  private static void printCommandHistory(Iterable<String> list) throws Exception { 
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
