package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class MemberHandler {

  private ArrayList memberList = new ArrayList();
  private Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void addMember() {
    Member member = new Member();

    member.setNo(input.getIntValue("번호? "));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPassword(input.getStringValue("암호? "));
    member.setPhoto(input.getStringValue("사진? "));
    member.setTel(input.getStringValue("전화번호? "));
    member.setRegisteredDate(input.getDateValue("가입일? "));

    memberList.add(member); // lesson 수업 주소를 주면서 LessonList클래스에서 파라미터로 받은 lesson로 배열에 추가한다.
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Object[] list = memberList.toArray();
    for (Object obj : list) {
      Member member = (Member)obj;

      System.out.printf("%s, %s , %s, %s, %s\n", member.getNo(), member.getName(),
          member.getEmail(), member.getTel(), member.getRegisteredDate());
    }
  }



}
