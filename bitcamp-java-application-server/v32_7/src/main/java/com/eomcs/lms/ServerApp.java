// v32_7 : 수업과 게시물 데이터를 다루는 CRUD 명령을 처리한다.ㅣ
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {

  static ObjectInputStream in;
  static ObjectOutputStream out;
  static ArrayList<Member> memberList = new ArrayList<>();
  static ArrayList<Lesson> lessonList = new ArrayList<>();
  static ArrayList<Board> boardList = new ArrayList<>();



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

        // 클라이언트로부터 명령어를 읽는다.
        loop: // loop 옆의 딱 한문장
          while (true) {
            // 클라이언트가 보낸 명령어를 읽는다.
            String command = in.readUTF();
            System.out.println(command + "요청 처리중...");

            // 명령어에 따라 처리한다.
            switch (command) {
              case "/member/add":
                addMember();
                break;

              case "/member/list":
                listMember();
                break;

              case "/member/delete":
                deleteMember();
                break;

              case "/member/detail":
                detailMember();
                break;

              case "/member/update":
                updateMember();
                break;

              case "/lesson/add":
                addLesson();
                break;

              case "/lesson/list":
                listLesson();
                break;

              case "/lesson/delete":
                deleteLesson();
                break;

              case "/lesson/detail":
                detailLesson();
                break;

              case "/lesson/update":
                updateLesson();
                break;

              case "/board/add":
                addBoard();
                break;

              case "/board/list":
                listBoard();
                break;

              case "/board/delete":
                deleteBoard();
                break;

              case "/board/detail":
                detailBoard();
                break;

              case "/board/update":
                updateBoard();
                break;

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


  private static void updateMember0() throws Exception {
    // 멤버 객체를 받는다.
    Member member = (Member) in.readObject();
    for (Member m : memberList) {
      if (m.getNo() == member.getNo()) {

        // 클라이언트가 보낸 값으로 기존 객체의 값을 바꾼다(기존의 것을 수정)
        m.setName(member.getName());
        m.setEmail(member.getEmail());
        m.setPassword(member.getPassword());
        m.setPhoto(member.getPhoto());
        m.setTel(member.getTel());
        m.setRegisteredDate(member.getRegisteredDate());

        out.writeUTF("ok");
        return;
      }
    }
    fail("해당 번호의 회원이 없습니다.");
  }


  private static void updateMember() throws Exception {

    Member member = (Member) in.readObject();

    int index = indexOfMember(member.getNo());

    if (index == -1) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }

    memberList.set(index, member);
    out.writeUTF("ok");

    // for (int i = 0; i < memberList.size(); i++) {
    // Member m = memberList.get(i);
    // if (m.getNo() == member.getNo()) {
    //
    // memberList.set(i, member);
    // out.writeUTF("ok");
    // return;
    // }
    // }
    // out.writeUTF("fail");
    // out.writeUTF("해당번호의 회원이 없습니다.");
  }

  private static void detailMember() throws Exception {
    int no = in.readInt();

    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(memberList.get(index));

    // for (Member m : memberList) {
    // if (m.getNo() == no) {
    // out.writeUTF("ok");
    // out.writeObject(m);
    // return;
    // }
    // }
    // out.writeUTF("fail");
    // out.writeUTF("해당번호의 회원이 없습니다.");
  }

  private static void deleteMember() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    memberList.remove(index);
    out.writeUTF("ok");

    //
    // for (Member m : memberList) {
    // if (m.getNo() == no) {
    // memberList.remove(m);
    // out.writeUTF("ok");
    // return;
    // }
    // }
    //
    // out.writeUTF("fail");
    // out.writeUTF("해당번호의 회원이 없습니다.");
  }

  private static void addMember() throws Exception {
    // 클라이언트가 보낸 객체를 읽는다
    Member member = (Member) in.readObject();
    memberList.add(member); // 읽은 객체를 리스트에 담는다.

    out.writeUTF("ok");

  }

  private static void listMember() throws Exception {

    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberList);
  }

  private static int indexOfMember(int no) {
    int i = 0;
    for (Member m : memberList) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  // Lesson

  private static void updateLesson() throws Exception {

    Lesson lesson = (Lesson) in.readObject();

    int index = indexOfLesson(lesson.getNo());

    if (index == -1) {
      fail("해당번호의 수업이 없습니다.");
      return;
    }

    lessonList.set(index, lesson);
    out.writeUTF("ok");

  }

  private static void detailLesson() throws Exception {
    int no = in.readInt();

    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(memberList.get(index));

  }

  private static void deleteLesson() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }

    lessonList.remove(index);
    out.writeUTF("ok");

  }

  private static void addLesson() throws Exception {
    // 클라이언트가 보낸 객체를 읽는다
    Lesson lesson = (Lesson) in.readObject();
    lessonList.add(lesson); // 읽은 객체를 리스트에 담는다.

    out.writeUTF("ok");

  }

  private static void listLesson() throws Exception {

    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonList);
  }

  private static int indexOfLesson(int no) {
    int i = 0;
    for (Lesson m : lessonList) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  // Board

  private static void updateBoard() throws Exception {

    Board board = (Board) in.readObject();

    int index = indexOfMember(board.getNo());

    if (index == -1) {
      fail("해당번호의 게시물이 없습니다.");
      return;
    }

    boardList.set(index, board);
    out.writeUTF("ok");

  }

  private static void detailBoard() throws Exception {
    int no = in.readInt();

    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(boardList.get(index));

  }

  private static void deleteBoard() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }

    boardList.remove(index);
    out.writeUTF("ok");

  }

  private static void addBoard() throws Exception {
    // 클라이언트가 보낸 객체를 읽는다
    Board board = (Board) in.readObject();
    boardList.add(board); // 읽은 객체를 리스트에 담는다.

    out.writeUTF("ok");

  }

  private static void listBoard() throws Exception {

    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(boardList);
  }



  private static int indexOfBoard(int no) {
    int i = 0;
    for (Board m : boardList) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }



  private static void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
