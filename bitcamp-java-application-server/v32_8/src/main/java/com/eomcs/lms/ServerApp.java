// v32_8: 회원/ 수업 / 게시물 요청을 처리하는 코드를 별도의 클래스로 분리한다
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  static ObjectInputStream in;
  static ObjectOutputStream out;

  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

        System.out.println("클라이언트와 연결 되었음.");

        // 다른 메소드가 사용할수 있도록 입출력 스트림을 스태틱 변수에 저장한다.
        ServerApp.in = in;
        ServerApp.out = out;

        BoardServlet boardServlet = new BoardServlet(in,out);
        MemberServlet memberServlet = new MemberServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);

        // 클라이언트로부터 명령어를 읽는다.
        loop: 

          while (true) {
            // 클라이언트가 보낸 명령어를 읽는다.
            String command = in.readUTF();
            System.out.println(command + "요청 처리중...");

            if(command.startsWith("/board/")) {
              boardServlet.service(command);
              out.flush();
              continue; 
            }

            if(command.startsWith("/member/")) {
              memberServlet.service(command);
              out.flush();
              continue; 
            }

            if(command.startsWith("/lesson/")) {
              lessonServlet.service(command);
              out.flush();
              continue; 
            }

            switch (command) {

              case "quit":
                out.writeUTF("ok");
                break loop; // loop (while문 나가라)

              default:
                out.writeUTF("Fail");
                out.writeUTF("지원하지 않는 명령입니다.");
            }

            out.flush(); // switch 끝날 때 마다 매번 flush() 해주자.
            System.out.println("클라이언트에게 응답 완료!");

          } // loop 끝
        out.flush(); // 버퍼를 안쓰더라도 flush() 해주자.
      }

      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }



}
