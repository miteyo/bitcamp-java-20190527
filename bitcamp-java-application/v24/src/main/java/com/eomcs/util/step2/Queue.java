// 상속 문법을 이용하여 큐 만들기
package com.eomcs.util.step2;

import com.eomcs.util.Iterator;
import com.eomcs.util.LinkedList;

public class Queue<E> extends LinkedList<E> implements Cloneable { // 약속한다. Cloneable(인터페이스) 복사할 수
                                                                   // 있다는 자격을 준다.

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
  public Iterator<E> createIterator() { // 큐 객체에서 데이터를 대신 꺼내주는 역할 -> clone()된 주소가 넘어와서

    // Queue<E> clonedqueue = this; //복제된 commandQueue의 주소가 들어있다.
    // QueueIterator<E> iterator = new QueueIterator<>(this); //큐 객체에서 데이터를 대신 꺼내주는 역할
    // return iterator;
    // return new QueueIterator<E>(this); //this? createIterator()호출한 그 주소, 그 값을 가진 생성자로 찾아갔더니
    return new QueueIterator(); // this? createIterator()호출한 그 주소, 그 값을 가진 생성자로 찾아갔더니
  }

  // [중첩클래스]

  // => 이 클래스에서 사용할 클래스는 이 클래스 안에 선언하는 것이 소스 코드 관리에 좋다.
  // 이렇게 클래스에 안에 선언된 클래스를 nested class(중첩클래스)라 부른다.
  // 중첩클래스 중에서 static이 안붙은 클래스를 "non-static nested class"라 부른다.
  // non-static nested class를 "inner class"라 부른다.
  // => 중첩 클래스의 가장 큰 효용성은 다른 멤버(메서드)들처럼 다른 멤버를 그냥 사용할 수 있따는 것이다.
  //
  private class QueueIterator implements Iterator<E> {

    // 중첩클래스 안에서는 자신을 생성한 바깥 클래스의 인스턴스 주소를
    // "바깥 클래스명.this" 라는 변수에 저장하고있다.
    // 그래서 생성자에서 따로 바깥 클래스의 인스턴스 주소를 받을 필요가 없이
    // 바로 바깥 클래스의 인스턴스를 바로 사용할 수 있다.
    // return Queue.this.size() > 0;

    // 만약 사용하려는 필드나 메서드가 중첩클래스에 있는 필드나 메소드가 아니라면
    // 바깥클래스의 인스턴스 주소를 생략할 수 있다.

    @Override
    public boolean hasNext() {
      return size() > 0; // 창고의 크기가 0보다 크다? 데이터가있다
    }

    @Override
    public E next() {
      // return (E) Queue.this.poll(); // 창고에서 하나 꺼내라.
      return poll(); // 바깥클래스 (Queue) 의 인스턴스 주소를 생략할 수 있따.

    }
  }
}
