// 상속 문법을 이용하여 큐 만들기
package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable, Iterable<E> { 

  @Override
  public Queue<E> clone() throws CloneNotSupportedException {

    // 현재 큐 객체의 노드를 그대로 유지하기 위해 "deep copy"를 실행한다.
    // => 새 큐를 만들고,
    Queue<E> temp = new Queue<>(); // 새 스택을 만들고
    for (int i = 0; i < size(); i++) {
      // =>현재 큐에서 값을 꺼내 새 큐의 새 노드에 넣는다.
      // 즉 새 큐는 값을 넣을 떄 마다 새 노드를 생성하기 때문에 현재 큐의 노드에는 영향을 끼치지 않는다.
      temp.offer(get(i)); // 기존 큐의 값을 get(가져와서)한 값을 temp에 push 한다.
    }
    return temp; // 원래 노드 삭제해도 temp를 리턴한다.
    // return (Stack<E>) super.clone(); //swallow copy - 2개의 stack이 같은 주소를 가리키고 있다 ---> 링크가 다 끊어져
    // 있는데 뭘 복제해...
  }

  public void offer(E value) {
    add(value);
  }

  public E poll() {
    return remove(0);
  }

  public boolean empty() {
    return size() == 0; // 사이즈가 0이면 true ->비어있다.

  }

  // 큐의 데이터를 꺼내줄 Iterator를 제공한다.
  @Override
  public Iterator<E> iterator() { // 큐 객체에서 데이터를 대신 꺼내주는 역할 -> clone()된 주소가 넘어와서
    //중첩클래스를 정의한 후 인스턴스를 딱 한개 생성하는 용도로 사용한다면
    // 굳이 클래스 이름을 가질 필요가 없다.
    // 클래스를 정의 하자마자 바로 인스턴스를 만들어 사용하면 편하다.
    // 이렇게 정의하는 중첩 클래스를 "anonymous class"라 부른다.
    
    
    return new Iterator<E>() { //수퍼클래스의 기본 생성자를 만든다.
      @Override
      public boolean hasNext() {
        return size() > 0; // 창고의 크기가 0보다 크다? 데이터가있다
      }
      @Override
      public E next() {
        return poll(); // 바깥클래스 (Queue) 의 인스턴스 주소를 생략할 수 있따.
      }
    };
  }
}
    
//    Iterator<E> iterator = new Iterator<E> () { //수퍼클래스의 기본 생성자를 만든다.
//      @Override
//      public boolean hasNext() {
//        return size() > 0; // 창고의 크기가 0보다 크다? 데이터가있다
//      }
//      @Override
//      public E next() {
//        return poll(); // 바깥클래스 (Queue) 의 인스턴스 주소를 생략할 수 있따.
//      }
//    }
//    return iterator; 
//  }
//}


