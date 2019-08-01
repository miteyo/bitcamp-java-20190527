// v32_3 : 클라이언트와 서버간에 데이터 주고 받기 - 클라이언트가 보낸 데이터를 되돌려 주기.
package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[수업관리 시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();

          // 1) 클라이언트 연결 객체(소켓)에서 I/O 스트림 객체를 얻는다.
          //    사용하기 편하게 데코레이터를 붙인다.
          BufferedReader in =
              new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          // 생성자가 reader인데 InputStream은 바이트타입이니까,바이트 -> 캐릭터타입으로 읽을 수 있도록 하는 어댑터
          // readLine을 사용하기 위해 BufferedReader를 사용한다.

          PrintWriter out = new PrintWriter(
              new BufferedOutputStream(clientSocket.getOutputStream()))) {
        System.out.println("클라이언트와 연결 되었음.");

        // 2) 클라이언트가 보내 데이터를 읽는다.
        // => 보낸 규칙에 맞춰서 읽어야 한다.
        String message = in.readLine(); // 클라언트가 보낸 문자열 읽기
        System.out.println("클라이언트가 데이터를 읽었음.");
        // 3) 클라이언트가 보낸 문자열 궁금하다. 서버 쪽 콘솔창에 출력해보자
        System.out.println("--->> " + message);

        // 4) 클라이언트가 보낸 문자열을 바로 리턴한다.
        out.println("['영아']"+message);
        out.flush(); // 버퍼에 PrintWriter에 출력한 데이터는 아직 버퍼에 있다. 버퍼에 있는 데이터를 강제로 출력하라!
        System.out.println("클라이언트로 데이터를 보냈음.");

      }
      System.out.println("클라이언트와 연결을 끊었음.");

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }
}
