// v4: 수업관리 시스템의 데이터를 로딩하고 저장하는 코드를 옵저버로 분리한다.
package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner; // 컴파일러에게 알려주는 것.
import com.eomcs.lms.context.ApplicationContextListener;
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
import com.eomcs.lms.listener.DataLoaderListener;
import com.eomcs.lms.listener.HelloApplicationContextListener;
import com.eomcs.util.Input;

public class App {

  //옵저버를 보관할 컬렉션 객체 준비
  ArrayList<ApplicationContextListener> appCtxListeners = new ArrayList<>();

  //App 객체가 사용할 값을 모아두는 바구니 준비
  Map<String, Object> beanContainer = new HashMap<>();
  
  Scanner keyScan;
  
  @SuppressWarnings("unchecked")
  private void service() {

    //애플리케이션의 서비스를 시작할 때 등록된 옵저버에게 알린다.
    for(ApplicationContextListener listener : appCtxListeners) {
      listener.contextInitialized(beanContainer); //옵저버를 실행 할 떄, 옵저버를 담는 바구니를 파라미터로 넘겨준다.
    }
    
    //옵저버를 
    List<Lesson> lessonList = (List<Lesson>)beanContainer.get("lessonList");
    List<Member> memberList = (List<Member>)beanContainer.get("memberList");
    List<Board> boardList = (List<Board>)beanContainer.get("boardList");

    keyScan = new Scanner(System.in);

    Deque<String> commandStack = new ArrayDeque<>(); // 스택처럼 쓰고싶으면
    Queue<String> commandQueue = new LinkedList<>();

    Input input = new Input(keyScan);

    // Command 객체를 보관할 Map 준비
    HashMap<String, Command> commandMap = new HashMap<>();
    
    commandMap.put("/lesson/add", new LessonAddcommand(input, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(input, lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(input, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(input, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(input, lessonList));

    commandMap.put("/member/add", new MemberAddCommand(input, memberList));
    commandMap.put("/member/list", new MemberListCommand(input, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(input, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(input, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(input, memberList));

    commandMap.put("/board/add", new BoardAddCommand(input, boardList));
    commandMap.put("/board/list", new BoardListCommand(input, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(input, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(input, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(input, boardList));

    commandMap.put("/hi", new HiCommand(input));
    commandMap.put("/calc/plus", new CalcplusCommand(input));

    while (true) {
      String command = prompt();

      // 사용자가 아무것도 입력하지 않았으면 다시 입력 받는다.
      if (command.length() == 0) {
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command executor = commandMap.get(command); // 커맨드를 맵에서 command 명령어를 처리 할 executor

      if (command.equals("quit")) {
        break;

      } else if (command.equals("history")) {
        printCommandHistory(commandStack); //
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue); // 새로복제된 큐의 객체 주소가 createI에 전달

      } else if (executor != null) {
        executor.execute();

      } else {
        System.out.println("해당 명령을 지원하지 않습니다.");
      }

      System.out.println();
    } // while

    //애플리케이션의 서비스를 종료할 때 등록된 옵저버에게 알린다.
    for(ApplicationContextListener listener : appCtxListeners) {
      listener.contextDestroyed(beanContainer);
    }

  }

  private void printCommandHistory(Iterable<String> list) {
    Iterator<String> iterator = list.iterator(); // Iterable따라 만든 규칙은 iterator();
    int count = 0;
    while (iterator.hasNext()) { // 비어있지 않다면
      System.out.println(iterator.next());
      if (++count % 5 == 0) { // 출력하고 증가시키기.
        System.out.print(":");
        if (keyScan.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private String prompt() { // 이 클래스 안에서만 씁니다.
    System.out.print("명령> ");
    return keyScan.nextLine();
  }
 
  //ApplicationContextListener 옵저버를 등록하는 메소드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.appCtxListeners.add(listener);
  }

  public static void main(String[] args) {
    App app = new App();
    
    //애플리케이션을 시작하거나 종료할 떄 보고를 받고자 하는 객체를 등록한다.
    app.addApplicationContextListener(new HelloApplicationContextListener());
    app.addApplicationContextListener(new DataLoaderListener());
    
    app.service();
  }

}
