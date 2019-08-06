package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoProxy implements MemberDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberDaoProxy(ObjectInputStream in, ObjectOutputStream out) {

    this.in = in;
    this.out = out;
  }
  @Override
  public int insert(Member member) throws Exception {
    out.writeUTF("/member/add");
    out.writeObject(member);
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());

    System.out.println("처리 완료!");
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    out.writeUTF("/member/list");
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF()); // 예외를 던진다

    return (List<Member>) in.readObject();
  }


  @Override
  public Member findBy(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.writeInt(no);
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF()); //ok 아니면 이유를 읽고 예외를 던진다.

    return (Member) in.readObject();
  }

  @Override
  public int update(Member member) throws Exception {

    out.writeUTF("/member/update");
    out.writeObject(member); 
    out.flush();


    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());

    return 1;
  }
  
  @Override
  public int delete(int no) throws Exception {
    // delete -> fail보냄. 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/member/delete");
    out.writeInt(no);
    out.flush();

    if (!in.readUTF().equals("ok"))
      throw new Exception(in.readUTF());
    
    return 1;
  }
  
  
  
}
