package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;

// 데이터를 저장하고 보관하는것.
public class BoardList {
  private static final int DEFAULT_CAPACITY = 100; // 이곳에서만 쓸 거기때문에 private
  private Board[] list; // 데이터 보관하는 일 -> 핸들러에 있던 배열을 변수 이름만 바꿔서 이동함.
  private int size = 0;

  public BoardList() { // 이 생성자를 호출하면 initialSize()생성자가 호출되도록 부르기.
    this(DEFAULT_CAPACITY); // this라는 레퍼런스가 아니라 특수문법임!!! int 100이라는 파라미터를 보고 int a라는 생성자를 만든다.
    // 생성자에서 다른 생성자를 호출할 수 있다.
  }

  public BoardList(int initialCapacity) { // 배열을 1000개를 만들고 싶으면
    if (initialCapacity < 50 || initialCapacity > 10000)
      list = new Board[DEFAULT_CAPACITY];
    else
    list = new Board[initialCapacity];
  }

  public void add(Board board) {// this 저장된 lessons 배열에 찾아가서, 그 사이즈 위치에 lesson을 집어 넣어라.
    if (this.size == list.length)
      throw new RuntimeException("배열이 꽉 찼습니다");

    // this(100); 컴파일 오류! 일반 메서드는 생성자를 호출할 수 없다.
    this.list[this.size++] = board; // add가 호출될때마다 +1한다.
  }



  public Board[] toArray() { // 3개의 값을 입력받으면 100개 배열 다 리턴하는 것이 아니라 add 시킨 3개만 리턴한다.
    Board[] arr = new Board[this.size]; // w저장 된것만 꺼내기 위해서 arr를 만든다. size~> add된 갯수
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

}
