// 상속 문법을 이용하여 큐 만들기
package com.eomcs.util.step3;

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

    // 특정 메서드 안에서만 사용되는 클래스라면 그 메서드 안에 선언하라!
    // 이렇게 메서드 안에 선언된 중첩 클래스를 "local class"라 부른다.

//[중첩클래스] 바로 위의 createIterator() 사용 할 때, 딱 한번 쓰인다. -> 메소드 안으로 끌어올려~
    class QueueIterator implements Iterator<E> {
      @Override
      public boolean hasNext() {
        return size() > 0; // 창고의 크기가 0보다 크다? 데이터가있다
      }

      @Override
      public E next() {
        return poll(); // 바깥클래스 (Queue) 의 인스턴스 주소를 생략할 수 있따.
      }
    }
    
    return new QueueIterator(); // this? createIterator()호출한 그 주소, 그 값을 가진 생성자로 찾아갔더니
  }


}
