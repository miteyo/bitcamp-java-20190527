package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberAddCommand implements Command {

  private List<Member> list;
  private Input input;

  public MemberAddCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }
  
  @Override
  public void execute() {
    Member member = new Member();

    member.setNo(input.getIntValue("번호? "));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPassword(input.getStringValue("암호? "));
    member.setPhoto(input.getStringValue("사진? "));
    member.setTel(input.getStringValue("전화번호? "));
    member.setRegisteredDate(input.getDateValue("가입일? "));

    list.add(member); // lesson 수업 주소를 주면서 LessonList클래스에서 파라미터로 받은 lesson로 배열에 추가한다.
    System.out.println("저장하였습니다.");
  }

}
