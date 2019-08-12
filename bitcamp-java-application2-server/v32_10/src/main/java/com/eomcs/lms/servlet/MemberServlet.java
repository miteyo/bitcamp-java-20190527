package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {
  
  static ArrayList<Member> memberList = new ArrayList<>();
  
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException { //클래스가 없어서 serialize를 못하는 예외는 던져버린다.
    this.in = in;
    this.out = out;

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
    }

  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException { 

    // File의 정보 준비하기.
    File file = new File("./member.ser");
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
      memberList = (ArrayList<Member>) in.readObject();
      
     System.out.println("회원 데이터 로딩 완료!");
    }
  }

  public void saveData() {

    File file = new File("./member.ser");

    try (
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(file))){

      out.writeObject(memberList);
      System.out.println("회원 데이터 저장 완료!");

    } catch (FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다.");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } 
  }

  
  @Override
  public void service(String command) throws Exception {
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

      default:
        out.writeUTF("Fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

  private void updateMember0() throws Exception {
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


  private void updateMember() throws Exception {

    Member member = (Member) in.readObject();

    int index = indexOfMember(member.getNo());

    if (index == -1) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }

    memberList.set(index, member);
    out.writeUTF("ok");

  }

  private void detailMember() throws Exception {
    int no = in.readInt();

    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(memberList.get(index));


  }

  private void deleteMember() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    int index = indexOfMember(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    memberList.remove(index);
    out.writeUTF("ok");

  }

  private void addMember() throws Exception {
    // 클라이언트가 보낸 객체를 읽는다
    Member member = (Member) in.readObject();
    memberList.add(member); // 읽은 객체를 리스트에 담는다.

    out.writeUTF("ok");

  }

  private void listMember() throws Exception {

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

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

  
}
