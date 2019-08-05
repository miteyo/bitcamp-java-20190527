package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.serial.MemberSerialDao;
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {

  MemberDao memberDao;

  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(MemberDao memberDao, ObjectInputStream in, ObjectOutputStream out) throws Exception {

    this.in = in;
    this.out = out;
    // 서블릿이 사용할 DAO를 직접 만들지 않고 외부에서 주입 받아 사용한다.
    // 이렇게 의존하는 객체를 외부에서 주입받아 사용하는 방법을
    // "의존성 주입(Dependency Injection; DI)"라 부른다.
    // => 그래야만 의존 객체를 교체하기 쉽다
    this.memberDao = memberDao;
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

  private void updateMember() throws Exception {

    Member member = (Member) in.readObject();

    // 변경일은 서버쪽에서 설정해야 한다.
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.update(member) == 0) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailMember() throws Exception {
    int no = in.readInt();

    Member member = memberDao.findBy(no);
    if (member == null) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);
  }

  private void deleteMember() throws Exception {
    int no = in.readInt(); // (2)를 읽는다.

    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void addMember() throws Exception {
    Member member = (Member) in.readObject();

    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.insert(member) == 0) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {

    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberDao.findAll());
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
