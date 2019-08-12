// v32_15 : 서블릿에서 DAO를 쉽게 교체할 수 있도록 외부에서 주입하라.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.dao.serial.BoardSerialDao;
import com.eomcs.lms.dao.serial.LessonSerialDao;
import com.eomcs.lms.dao.serial.MemberSerialDao;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

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

//     BoardCsvDao boardDao = new BoardCsvDao("./board.csv");
//     MemberCsvDao memberDao = new MemberCsvDao("./member.csv");
//     LessonCsvDao lessonDao = new LessonCsvDao("./lesson.csv");

        BoardSerialDao boardDao = new BoardSerialDao("./board.ser");
        MemberSerialDao memberDao = new MemberSerialDao("./member.ser");
        LessonSerialDao lessonDao = new LessonSerialDao("./lesson.ser");

        BoardServlet boardServlet = new BoardServlet(boardDao, in, out);
        MemberServlet memberServlet = new MemberServlet(memberDao, in, out);
        LessonServlet lessonServlet = new LessonServlet(lessonDao, in, out);

        // 클라이언트로부터 명령어를 읽는다.

        while (true) {
          // 클라이언트가 보낸 명령어를 읽는다.
          String command = in.readUTF();
          System.out.println(command + "요청 처리중...");

          if (command.startsWith("/board/")) {
            boardServlet.service(command);
            out.flush();
            continue;
          }

          else if (command.startsWith("/member/")) {
            memberServlet.service(command);
            out.flush();
            continue;
          }

          else if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);
            out.flush();
            continue;
          }

          else if (command.equals("quit")) {
            out.writeUTF("ok");
            out.flush();
            break;

          } else {
            out.writeUTF("Fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료!");
        }

        //클라이언트와 연결을 끊기 전에 작업내용을 파일에 저장한다.
        boardDao.saveData();
        memberDao.saveData();
        lessonDao.saveData();

      }

      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }

}
