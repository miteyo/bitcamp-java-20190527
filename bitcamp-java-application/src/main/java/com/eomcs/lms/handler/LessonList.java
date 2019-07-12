package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

// 데이터를 저장하고 보관하는것.
public class LessonList {
  private Lesson[] list = new Lesson[100]; // 데이터 보관하는 일 -> 핸들러에 있던 배열을 변수 이름만 바꿔서 이동함.
  private int size = 0;
  private static final int DEFAULT_CAPACITY = 100;


  public LessonList() {
    this(DEFAULT_CAPACITY);
  }

  public LessonList(int initialcapacity) {
    if (initialcapacity < 50 || initialcapacity > 10000)
      list = new Lesson[DEFAULT_CAPACITY];
    else
      list = new Lesson[initialcapacity];
  }


  public void add(Lesson lesson) {// this 저장된 lessons 배열에 찾아가서, 그 사이즈 위치에 lesson을 집언허어라.
    this.list[this.size++] = lesson; // add가 호출될때마다 +1한다.
  }

  public Lesson[] toArray() { // 3개의 값을 입력받으면 100개 배열 다 리턴하는 것이 아니라 add 시킨 3개만 리턴한다.
    Lesson[] arr = new Lesson[this.size]; // w저장 된것만 꺼내기 위해서 arr를 만든다. size~> add된 갯수
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

}
