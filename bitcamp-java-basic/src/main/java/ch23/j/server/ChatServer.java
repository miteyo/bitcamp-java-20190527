package ch23.j.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

  // 연결된 클라이언트들에의 출력 스트림을 보관하는 목록(언제? chatAgent가 준비되면
  ArrayList<PrintStream> outputStreams = new ArrayList<>();
  int port;

  public ChatServer(int port) {
    this.port = port;
  }

  public void service() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("채팅서버 시작!");

      while (true) {
        new Thread(new ChatAgent(serverSocket.accept())).start();
        System.out.println("채팅 클라이언트가 연결되었습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 모든 클라이언트의 출력 스트림에 메세지를 전송한다.
  synchronized private void send(String message) { //synchronized 한 스레드가 다 출력할 때까지 기다려라
    for (PrintStream out : outputStreams) {
      try {
        out.println(message);

      } catch (Exception e) {
        // 예외발생-> 출력할 필요 없음 -> 출력이 안되는 스트림은 다음에 사용하지 않기 위해 목록에서 제거한다
        outputStreams.remove(out);
      }
    }
  }


  class ChatAgent implements Runnable {
    Socket socket;

    public ChatAgent(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() { // 클라이언트가 연결되는 순간
      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintStream out = new PrintStream(socket.getOutputStream())) {

        // 출력스트림을 ChatServer에 보관한다.
        outputStreams.add(out);

        while (true) {
          String message = in.readLine();
          if (message.equals("quit"))
            break;

          // 채팅방에 참여한 모든사람들에게 메세지를 전달(공유)
          // => 메세지를 전문적을 보내는 일을 하는 객체에 맡긴다.
          new Thread(new MessageSender(message)).start();
        }
        // 채팅방에 참여한 모든 사람들에게 퇴장메세지를 전달한다.

      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("채팅 클라이언트가 종료되었습니다.");
    }
  }

  class MessageSender implements Runnable {
    String message;

    public MessageSender(String message) {
      this.message = message;
    }

    @Override
    public void run() {
      // 바깥 클래스의 메서드를 호출하여 메시지를 보낸다.
      send(message);
    }
  }

  public static void main(String[] args) {
    ChatServer chatServer = new ChatServer(8888);
    chatServer.service();
  }

}
