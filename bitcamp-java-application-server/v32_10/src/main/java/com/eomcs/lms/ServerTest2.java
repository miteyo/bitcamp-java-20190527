package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class ServerTest2 {
  static ObjectOutputStream out;
  static ObjectInputStream in;

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리 시스템 서버 애플리케이션 TEST]");

    try (Socket socket = new Socket("192.168.0.37", 8888); // 강사님 16 localhost
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); //
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음");

      // 다른 메소드가 입출력 객체를 사용할 수 있도록 스태틱 변수에 저장한다.
      ServerTest2.in = in;
      ServerTest2.out = out;

      Lesson lesson = new Lesson();
      lesson.setNo(1);
      lesson.setTitle("자바 프로그래밍");
      lesson.setContents("okok");
      lesson.setStartDate(Date.valueOf("2019-1-1"));
      lesson.setEndDate(Date.valueOf("2019-1-1"));
      lesson.setTotalHours(400);
      lesson.setDayHours(4);

      if (!add(lesson)) {
        error();
      }

      System.out.println("------------------------------------------");

      lesson = new Lesson();
      lesson.setNo(2);
      lesson.setTitle("자바 프로그래밍22");
      lesson.setContents("okok2");
      lesson.setStartDate(Date.valueOf("2019-3-3"));
      lesson.setEndDate(Date.valueOf("2019-4-4"));
      lesson.setTotalHours(100);
      lesson.setDayHours(5);

      if (!add(lesson)) {
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

      lesson = new Lesson();
      lesson.setNo(1);
      lesson.setTitle("자바 웹프로그래밍");
      lesson.setContents("개발자 양성과정");
      lesson.setStartDate(Date.valueOf("2019-7-3"));
      lesson.setEndDate(Date.valueOf("2019-11-4"));
      lesson.setTotalHours(100);
      lesson.setDayHours(5);

      if (!update(lesson)) {
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

    } catch (Exception e) { // RequestException 발생시 블록 실행
      // 서버에서 요청처리에 실패했다면, 서버가 보낸 이유를 받는다.
      System.out.printf("오류: %s\n", in.readUTF()); // ok가 아닌 경우, 한번 더 읽는다. 지원하지 않는 명령입니다.

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
    out.writeUTF("/lesson/delete");
    out.writeInt(2);
    out.flush();
    System.out.print("delete 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean detail() throws Exception {

    // delete -> fail보냄. 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/lesson/detail");
    out.writeInt(0);
    out.flush();
    System.out.print("detail 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    // 서버가 보낸 객체를 읽고 출력한다.
    System.out.println(in.readObject());

    return true;
  }


  private static boolean update(Lesson obj) throws Exception {

    out.writeUTF("/lesson/update");
    out.writeObject(obj); // 위에서 변경된 Lesson 객체를 보낸다.
    out.flush();

    System.out.print("update 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean list() throws Exception {
    out.writeUTF("/lesson/list");
    out.flush();
    System.out.print("list 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    @SuppressWarnings("unchecked")
    List<Lesson> list = (List<Lesson>) in.readObject();
    System.out.println("------------------------------------------");

    for (Lesson obj : list) {
      System.out.println(obj);
    }
    return true;
  }

  private static boolean add(Lesson obj) throws IOException {
    // 서버에 객체를 전송한다.
    out.writeUTF("/lesson/add");
    out.writeObject(obj);
    out.flush();
    System.out.print("add요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }


}
