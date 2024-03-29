// server-v37_6 : 스레드풀 적용하기 (48-line)
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.HelloCommand;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberSearchCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class App {

  private static final int CONTINUE = 1;
  private static final int STOP = 0;

  Connection con;
  HashMap<String, Command> commandMap = new HashMap<>();
  int state;
  
//스레드 풀
  ExecutorService executorService = Executors.newCachedThreadPool();
   
  public App() throws Exception {
    // 처음에는 클라이언트 요청을 처리해야하는 상태로 설정한다.
    state = CONTINUE;

    try {
      // DAO가 사용할 Connection 객체 준비하기
      con = DriverManager
          .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");

      // Command 객체가 사용할 데이터 처리 객체를 준비한다.
      BoardDao boardDao = new BoardDaoImpl(con);
      MemberDao memberDao = new MemberDaoImpl(con);
      LessonDao lessonDao = new LessonDaoImpl(con);

      // 클라이언트 명령을 처리할 Command 객체를 준비한다.
      commandMap.put("/lesson/add", new LessonAddCommand(lessonDao));
      commandMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
      commandMap.put("/lesson/detail", new LessonDetailCommand(lessonDao));
      commandMap.put("/lesson/list", new LessonListCommand(lessonDao));
      commandMap.put("/lesson/update", new LessonUpdateCommand(lessonDao));

      commandMap.put("/member/add", new MemberAddCommand(memberDao));
      commandMap.put("/member/delete", new MemberDeleteCommand(memberDao));
      commandMap.put("/member/detail", new MemberDetailCommand(memberDao));
      commandMap.put("/member/list", new MemberListCommand(memberDao));
      commandMap.put("/member/update", new MemberUpdateCommand(memberDao));
      commandMap.put("/member/search", new MemberSearchCommand(memberDao));

      commandMap.put("/board/add", new BoardAddCommand(boardDao));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao));
      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/update", new BoardUpdateCommand(boardDao));
      // commandMap.put("/hello", new HelloCommand());

    } catch (Exception e) {
      System.out.println("DBMS에 연결할 수 없습니다.");
      throw e;
    }
  }

  @SuppressWarnings("static-access")
  private void service() {

    try (ServerSocket serverSocket = new ServerSocket(8888);) {
      System.out.println("애플리케이션 서버가 시작 되었음!");

      while (true) {
        
//클라이언트가 접속하면 작업을 수행할 Runnable 객체를 만들어 스레드풀에 맡긴다.
        executorService.submit(new CommandProcessor(serverSocket.accept()));

        //한 클라이언트가 serverstop 명령을 보내면 종료상태로 설정하고 다음 요청을 처리할 때 즉시 실행을 멈춘다.
        if (state == STOP)
          break;
      }
      
      //스레드풀에게 실행 종료를 요청한다.
      // =>스레드풀은 자신이 관리하는 스레드들이 실행이 종료되었는지 감시한다.
      executorService.shutdown();

      //스레드풀이 관리하는 모든 스레드가 종료되었는지 매 0.5초마다 검사한다.
      // =>스레드풀의 모든 스레드가 실행을 종료 했으면 즉시 main 스레드를 종료한다.
      while(!executorService.isTerminated()) {
        Thread.currentThread().sleep(500);
      }
      
      System.out.println("~~애플리케이션 서버 종료~~");

    } catch (Exception e) {
      System.out.println("소켓 통신 오류!");
      e.printStackTrace();
    }

    // DBMS와의 연결을 끊는다. while 문이 serverstop으로 break 되면 연결 끊기
    try {
      con.close();
    } catch (Exception e) {
      // 연결 끊을 때 발생되는 예외는 무시한다.
    }
  }

  class CommandProcessor implements Runnable {
    Socket socket;

    public CommandProcessor(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {

      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintStream out = new PrintStream(socket.getOutputStream());) {

        System.out.println("클라이언트와 연결됨");

        String request = in.readLine();
        if (request.equals("quit")) {
          out.println("굿바이!");

        } else if (request.equals("serverstop")) {
          state = STOP;
          out.println("굿바이!!");
          //dummyRequest();

        } else {
          // non-static 중첩 클래스는 바깥 클래스의
          Command command = commandMap.get(request); // quit가 아니면 요청을 처리할 commandMap를 찾는다.

          if (command == null) {
            out.println("해당 명령을 처리할 수 없습니다.");

          } else {
            command.execute(in, out); // 인터페이스에서 default
          }
        }
        out.println("!end!");
        out.flush();

        System.out.println("클라이언트와 연결 끊음!");

      } catch (Exception e) {
        System.out.println("클라이언트와 통신 오류!");
      }
    }
  }

//  // 클라이언트에서 serverstop 명령을 보내면,  서버의 sate 값이 STOP값으로 바뀐다.
//  // 이 state 상태를  즉시 인식할 수 있도록 가상의 클라이언트가 되어 요청을 보낸다.
//  private void dummyRequest() {
//    try (Socket socket = new Socket("localhost", 8888);
//        PrintStream out = new PrintStream(socket.getOutputStream()); 
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
//
//      out.println("quit");
//      out.flush();
//
//      while(true) {
//        if(in.readLine().equals("!end!"))
//          break;
//      }
//    } catch (Exception e) {
//      //예외는 무시한다.
//    }
//  }

  public static void main(String[] args) {
    try {
      App app = new App();
      app.service();

    } catch (Exception e) {
      System.out.println("시스템 실행 중 오류발생!");
      e.printStackTrace();
    }
  }
}


