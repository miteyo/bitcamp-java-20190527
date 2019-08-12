// v32_5 : 명령어에 따라 클라이언트가 보낸 데이터 처리하기 
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    ArrayList<Member> memberlist = new ArrayList<>();

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

        System.out.println("클라이언트와 연결 되었음.");

        //클라이언트로부터 명령어를 읽는다.
        loop: //loop 옆의 딱 한문장
          while(true) {
            //클라이언트가 보낸 명령어를 읽는다.
            String command = in.readUTF(); 
            System.out.println(command + "요청 처리중...");

            //명령어에 따라 처리한다.
            switch (command) {
              case "add" :
                //클라이언트가 보낸 객체를 읽는다
                Member member = (Member)in.readObject();
                memberlist.add(member); //읽은 객체를 리스트에 담는다.
                out.writeUTF("add OK~");
                break;

              case "list" :
                out.writeUTF("list OK!");
                out.writeObject(memberlist);
                break;

              case "quit" :
                out.writeUTF("quit 네!");
                break loop; //loop (while문 나가라)

              default : 
                out.writeUTF("~Fail~");
                out.writeUTF("지원하지 않는 명령입니다.");
            }
            out.flush(); // 버퍼를 안쓰더라도 flush() 해주자.
            System.out.println("클라이언트에게 응답 완료!");
          } //loop 끝
        out.flush(); // 버퍼를 안쓰더라도 flush() 해주자.
      }
      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }
}
