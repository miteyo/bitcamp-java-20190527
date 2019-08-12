package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoProxy implements LessonDao {

  String host;
  int port;

  public LessonDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      System.out.println("처리 완료!");
      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      out.writeUTF("/lesson/list");
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF()); // 예외를 던진다

      return (List<Lesson>) in.readObject();
    }
  }

  @Override
  public Lesson findBy(int no) throws Exception {


    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF()); // ok 아니면 이유를 읽고 예외를 던진다.

      return (Lesson) in.readObject();
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      out.writeUTF("/lesson/update");
      out.writeObject(lesson);
      out.flush();


      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @Override
  public int delete(int no) throws Exception {


    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      // delete -> fail보냄. 서버가 처리할 수 없는 명령어 보내기
      out.writeUTF("/lesson/delete");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }
  
  
  public static void main(String[] args) throws Exception {
    LessonDaoProxy daoProxy = new LessonDaoProxy("localhost", 8888);
//입력테스트    
//    Lesson lesson = new Lesson();
//    lesson.setNo(100);
//    lesson.setContents("어피치~");
//    daoProxy.insert(lesson);
//    System.out.println("입력성공");
    
//조회테스트
    Lesson lesson = daoProxy.findBy(100);
    System.out.println(lesson);
    
    
  }
  
  
}
