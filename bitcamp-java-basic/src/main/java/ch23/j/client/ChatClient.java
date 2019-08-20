package ch23.j.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.security.spec.ECFieldF2m;

public class ChatClient extends Frame implements ActionListener {
  private static final long serialVersionUID = 1L;

  TextField addressTF = new TextField(20);
  TextField portTF = new TextField(4);
  Button connectBtn = new Button("connect");
  TextArea chattingPane = new TextArea(); // 글자쓰기
  TextField messageTF = new TextField();
  Button sendBtn = new Button("send");
  Socket socket;
  BufferedReader in;
  PrintStream out;

  public ChatClient(String title) {
    super(title);

    this.setSize(600, 480);

    // 윈도우 관련 이벤트를 처리할 담당자를 설정한다.
    // =>옵져버 패턴
    // => window 이벤트가 발생했을 때 보고를 받을 객체를 등록한다.
    this.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        try {
          out.close();
        } catch (Exception ex) {
        }
        try {
          in.close();
        } catch (Exception ex) {
        }
        try {
          socket.close();
        } catch (Exception ex) {
        }

        System.exit(0);
      }
    });

    Panel topPane = new Panel();
    topPane.add(addressTF);
    topPane.add(portTF);
    topPane.add(connectBtn);
    this.add(topPane, BorderLayout.NORTH);

    this.add(chattingPane, BorderLayout.CENTER);

    Panel bottomPane = new Panel();
    bottomPane.setLayout(new BorderLayout());
    bottomPane.add(messageTF, BorderLayout.CENTER);
    bottomPane.add(sendBtn, BorderLayout.EAST);

    this.add(bottomPane, BorderLayout.SOUTH);

    connectBtn.addActionListener(this); // 연결버튼에 대해 옵저버(리스너) 등록하기
    sendBtn.addActionListener(this); // 보내기 버튼에 대해 옵저버 (리스너) 등록하기.
  }
  

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sendBtn) {
      //서버에 전송
      out.println(messageTF.getText());
      
      // 기존에 입력한 메세지는 지운다.
      messageTF.setText("");
      
    } else if (e.getSource() == connectBtn) {
      connectChatServer();
    }
  }

  private void connectChatServer() {
    try {
      socket = new Socket(addressTF.getText(), Integer.parseInt(portTF.getText()));
      out = new PrintStream(socket.getOutputStream());
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      chattingPane.append("서버와 연결됨\n");

      //서버와 연결되면 메세지 수신을 별도의 담당자에게 맡긴다.
      new Thread(new MessageReceiver()).start();

      //메세지 전송은 버튼을 누를 때 마다 main 스레드가 처리할 것이다.
    } catch (Exception e) {
      chattingPane.append("서버연결 오류!\n");
    }
  }

  class MessageReceiver implements Runnable {
    @Override
    public void run() {
      try {
        while (true) {
          String message = in.readLine();
          chattingPane.append(message + "\n");
        }

      } catch (Exception e) {
        chattingPane.append("메세지 수신 중 오류 발생!\n");
      }
    }
  }

  public static void main(String[] args) {
    ChatClient app = new ChatClient("비트채팅!");
    app.setVisible(true);
  }
}

// Java 윈도우 프로그래밍
// 1) AWT
// 2) Swing
// 3) Eclipse SWT
// 4) Java FX
