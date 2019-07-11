package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {

  private Member[] members = new Member[100];
  private int membersSize = 0;

  public Input intput;

  public MemberHandler(Input input) {
    this.intput = input;
  }

  public void addMember() {
    Member member = new Member();
    member.no = intput.getIntValue("번호? ");
    member.name = intput.getStringValue("이름? ");
    member.email = intput.getStringValue("이메일? ");
    member.password = intput.getStringValue("암호? ");
    member.photo = intput.getStringValue("사진? ");
    member.tel = intput.getStringValue("전화번호? ");
    member.registeredDate = intput.getDateValue("가입일? ");

    members[membersSize++] = member;
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s , %s, %s, %s\n", member.no, member.name, member.email, member.tel,
          member.registeredDate);
    }
  }



}
