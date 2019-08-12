package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리 시스템 서버 애플리케이션 TEST]");

    try (Socket socket = new Socket("192.168.0.68", 8888); //강사님 16
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); //

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음");
                                              //서버는 문자열을 읽고, 그 문자열에 따라 객체를 읽던가, 바로 응답을 하던가 한다.
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@gmail.com");
      member.setPhoto("hong.gif");
      member.setTel("123-456");

      // 서버에 객체를 전송한다.
      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add요청함.");

      // 서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
      System.out.println("--->> " + response);

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("daum.net");
      member.setPhoto("lim.gif");
      member.setTel("789-456");

      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함.");
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--->> " + response);

      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함.");

      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--->> " + response);

      @SuppressWarnings("unchecked")
      List<Member> list = (List<Member>) in.readObject();
      System.out.println("--------------------------------");
      for(Member m : list) {
        System.out.println(m);
      }
      System.out.println("--------------------------------");

 //delete 서버가 처리할 수 없는 명령어 보내기 -> fail보냄.
      out.writeUTF("delete");
      out.flush();
      System.out.println("delete 요청함.");
      
      response = in.readUTF();
      System.out.println("--->> " + response);

 //delete "지원하지 않는 명령입니다." 이유 읽음
      response = in.readUTF();
      System.out.println("--->> " + response);

//quit 실행하기
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함.");
      
      response = in.readUTF();
      System.out.println("--->> " + response);

    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음!");
  }
}
