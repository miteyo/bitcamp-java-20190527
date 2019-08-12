package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class ServerTest3 {
  static ObjectOutputStream out;
  static ObjectInputStream in;

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리 시스템 서버 애플리케이션 TEST]");

    try (Socket socket = new Socket("localhost", 8888); // 강사님 16 localhost
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); //
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음");

      // 다른 메소드가 입출력 객체를 사용할 수 있도록 스태틱 변수에 저장한다.
      ServerTest3.in = in;
      ServerTest3.out = out;

      Board board = new Board();
      board.setNo(1);
      board.setContents("제목1");

      if (!add(board)) {
        error();
      }

      System.out.println("------------------------------------------");

      board = new Board();
      board.setNo(2);
      board.setContents("제목2");

      if (!add(board)) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!list()) {
        error();
      }

      System.out.println("------------------------------------------");
      if (!delete()) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!list()) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!detail()) {
        error();
      }

      System.out.println("------------------------------------------");

      board = new Board();
      board.setNo(1);
      board.setContents("오호라~");

      if (!update(board)) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!list()) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!quit()) {
        error();
      }

    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();

    }
    System.out.println("서버와 연결 끊음!");
  }// main끝


  private static void error() throws Exception {
    System.out.printf("오류: %s\n", in.readUTF());

  }

  private static boolean quit() throws Exception {
    // quit 실행하기
    out.writeUTF("quit");
    out.flush();
    System.out.print("quit 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean delete() throws Exception {

    // delete -> fail보냄. 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/board/delete");
    out.writeInt(1);
    out.flush();
    System.out.print("delete 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean detail() throws Exception {

    // delete -> fail보냄. 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/board/detail");
    out.writeInt(1);
    out.flush();
    System.out.print("detail 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    // 서버가 보낸 객체를 읽고 출력한다.
    System.out.println(in.readObject());

    return true;
  }

  private static boolean update(Board obj) throws Exception {

    out.writeUTF("/board/update");
    out.writeObject(obj); // 위에서 변경된 Board 객체를 보낸다.
    out.flush();

    System.out.print("update 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean list() throws Exception {
    out.writeUTF("/board/list");
    out.flush();
    System.out.print("list 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    @SuppressWarnings("unchecked")
    List<Board> list = (List<Board>) in.readObject();
    System.out.println("------------------------------------------");

    for (Board obj : list) {
      System.out.println(obj);
    }
    return true;
  }

  private static boolean add(Board obj) throws IOException {
    // 서버에 객체를 전송한다.
    out.writeUTF("/board/add");
    out.writeObject(obj);
    out.flush();
    System.out.print("add요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }


}
