package com.eomcs.util;
// 특정 객체가 아니라, 어떤 객체라도 저장할 수 있는 다용도 객체이다.


import java.util.Arrays;

public class ArrayList<E> { // ArrayList에서 다룰 타입<dataTypee>, <T>:타입정보를 받는 변수->타입파라미터
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
  public void add(E obj) {
    if (this.size == list.length) {
      int oldCapacity = list.length; // 배열의 현재용량
      int newCapacity = oldCapacity + (oldCapacity >> 1); // ->비트이동연산자: 기존크기의 /2

      list = Arrays.copyOf(this.list, newCapacity); // (복사할배열, 새로만들 배열의 크기)
    }
    this.list[this.size++] = obj;
  }

  // 원래배열에서 배열에 저장된 값만큼 리턴하자.
  public Object[] toArray() { // 파라미터 안주면, 기존 배열 100개중 실제 저장된 17개의 Object타입의 배열 만들어서 리턴
    return Arrays.copyOf(this.list, this.size); // new Object[this.size]를 만든다. (원본배열,복사할갯수(size 크기만큼))
  }

  public E[] toArray(E[] a) { // Board배열을 원하면 Board배열을 빈배열 a에 담겠다.
    if (a.length < size) {
      // 파라미터로 넘겨 받은 배열의 크기가 저당된 데이터의 개수보다 작다면
      // 이 메서드에서 !새 배열!을 만든다. - > 0개일 배열이 아닌 새 배열 주소를 리턴한다. (copyOf :  배열타입을 지정하게 되면 그 타입의 빈배열을 만든다. 
      return (E[]) Arrays.copyOf(list, size, a.getClass()); // a(입력한 갯수)와 list(배열 100개)를 가져와서 복사한다.
                                                            // Object 배열을 만들고 그 배열에서 복사할 크기를 지정한다. new Board[this.size]를 만들고 싶으면  a.getClass()
    }

    System.arraycopy(list, 0, a, 0, size); // 기존배열에 복사. 원본 배열주소"aaa,bbb,ccc", 몇번째부터 복사할거냐,

    if (a.length > size)
      a[size] = null; //a[10] 에 null을 채워라. 인덱스는 9까지 채워져있다. (출력시, 배열==null이면 break 가능성.)
    return a;
  }

  public int size() { // 몇개의 배열을 가지고 있냐고 묻는 메소드
    return this.size;
  }

}
