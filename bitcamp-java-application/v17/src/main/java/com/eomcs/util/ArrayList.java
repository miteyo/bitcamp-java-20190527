package com.eomcs.util;
// 특정 객체가 아니라, 어떤 객체라도 저장할 수 있는 다용도 객체이다.

import java.util.Arrays;

public class ArrayList {
  private static final int DEFAULT_CAPACITY = 100;

  private Object[] list; // 상위클래스의 레퍼런스는 하위객체를 가르킬 수 있다.
  private int size = 0;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 50 || initialCapacity > 10000)
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[initialCapacity];
  }

  // obj 객체를 줄 수 있지만, 다형적 변수에 의해서 하위클래스의 객체도 줄 수 있다.
  // 배열의 크기를 늘리자.
  public void add(Object obj) {
    if (this.size == list.length) {
      int oldCapacity = list.length; // 배열의 현재용량
      int newCapacity = oldCapacity + (oldCapacity >> 1);  //->비트이동연산자: 기존크기의 /2

      list = Arrays.copyOf(this.list, newCapacity); // (복사할배열, 새로만들 배열의 크기)
    }
    this.list[this.size++] = obj;
  }

  // 원래배열에서 배열에 저장된 값만큼 리턴하자.
  public Object[] toArray() { // Object타입의 배열 -> 최상위 배열
    return Arrays.copyOf(this.list, this.size); // 배열이 100개다. 0~99개까지 리턴한다.
  }

}
