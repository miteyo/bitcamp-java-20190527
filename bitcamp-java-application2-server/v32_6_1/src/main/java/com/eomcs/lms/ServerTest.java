package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  static ObjectOutputStream out;
  static ObjectInputStream in;

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리 시스템 서버 애플리케이션 TEST]");

    try (Socket socket = new Socket("192.168.0.37", 8888); // 강사님 16 localhost
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); //
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음");

      // 다른 메소드가 입출력 객체를 사용할 수 있도록 스태틱 변수에 저장한다.
      ServerTest.in = in;
      ServerTest.out = out;

      Member member = new Member();
      member.setNo(1);
      member.setName("진다권");
      member.setEmail("jin@gmail.com");
      member.setPhoto("jin.gif");
      member.setTel("123-456");

      if (!add(member)) {
        error();
      }

      System.out.println("------------------------------------------");

      member = new Member();
      member.setNo(2);
      member.setName("용재리");
      member.setEmail("dragon@daum.net");
      member.setPhoto("dragon.gif");
      member.setTel("789-456");

      if (!add(member)) {
        error();
      }

      System.out.println("------------------------------------------");

      if (!list()) {
        error();
      }


      System.out.println("------------------------------------------");

      member = new Member();
      member.setNo(3);
      member.setName("영아영");
      member.setEmail("young@daum.net");
      member.setPhoto("young.gif");
      member.setTel("789-456");


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

      member = new Member();
      member.setNo(1);
      member.setName("영아영");
      member.setEmail("young@gmail.com");
      member.setPhoto("young.jpeg");
      member.setTel("456-789");

      if (!update(member)) {
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

    } catch (RequestException e) { // RequestException 발생시 블록 실행
      // 서버에서 요청처리에 실패했다면, 서버가 보낸 이유를 받는다.
      System.out.printf("오류: %s\n", in.readUTF()); // ok가 아닌 경우, 한번 더 읽는다. 지원하지 않는 명령입니다.

    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();

    }
    System.out.println("서버와 연결 끊음!");
  }



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
    out.writeUTF("/member/delete");
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
    out.writeUTF("/member/detail");
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


  private static boolean update(Member m) throws Exception {

    out.writeUTF("/member/update");
    out.writeObject(m); //위에서 변경된 member 객체를 보낸다.
    out.flush();

    System.out.print("update 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }


  private static boolean list() throws Exception {
    out.writeUTF("/member/list");
    out.flush();
    System.out.print("list 요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    @SuppressWarnings("unchecked")
    List<Member> list = (List<Member>) in.readObject();
    System.out.println("------------------------------------------");

    for (Member m : list) {
      System.out.println(m);
    }
    return true;
  }

  private static boolean add(Member m) throws IOException, RequestException {
    // 서버에 객체를 전송한다.
    out.writeUTF("/member/add");
    out.writeObject(m);
    out.flush();
    System.out.print("add요청함 ==> ");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }


}
