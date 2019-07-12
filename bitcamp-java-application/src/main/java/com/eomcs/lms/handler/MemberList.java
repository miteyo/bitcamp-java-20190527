package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

// 데이터를 저장하고 보관하는것.
public class MemberList {

  private static final int DEFAULT_CAPACITY = 100;
  private Member[] list = new Member[100]; // 데이터 보관하는 일 -> 핸들러에 있던 배열을 변수 이름만 바꿔서 이동함.
  private int size = 0;

  public MemberList() {
    this(DEFAULT_CAPACITY);
  }

  public MemberList(int initialcapacity) {
    if (initialcapacity < 50 || initialcapacity > 10000)
      list = new Member[DEFAULT_CAPACITY];
    else
      list = new Member[initialcapacity];
  }

  public void add(Member member) {
    this.list[this.size++] = member;
  }

  public Member[] toArray() { // 3개의 값을 입력받으면 100개 배열 다 리턴하는 것이 아니라 add 시킨 3개만 리턴한다.
    Member[] arr = new Member[this.size]; // w저장 된것만 꺼내기 위해서 arr를 만든다. size~> add된 갯수
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

}
