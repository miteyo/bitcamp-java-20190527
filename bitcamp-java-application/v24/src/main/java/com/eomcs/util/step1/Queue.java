//상속 문법을 이용하여 큐 만들기
package com.eomcs.util.step1;

import com.eomcs.util.Iterator;
import com.eomcs.util.LinkedList;

public class Queue<E> extends LinkedList<E> implements Cloneable { // 약속한다. Cloneable(인터페이스) 복사할 수 있다는 자격을 준다.

  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    
    // 현재 큐 객체의 노드를 그대로 유지하기 위해 "deep copy"를 실행한다.
    //  => 새 큐를 만들고, 
    Queue<E> temp = new Queue<>(); //새 스택을 만들고
    for (int i = 0; i< size(); i++) {
      // =>현재 큐에서 값을 꺼내 새 큐의 새 노드에 넣는다.
      //    즉 새 큐는 값을 넣을 떄 마다 새 노드를 생성하기 때문에 현재 큐의 노드에는 영향을 끼치지 않는다.
      temp.offer(get(i)); // 기존 큐의 값을  get(가져와서)한 값을 temp에 push 한다.
    }
    return temp; //원래 노드 삭제해도 temp를 리턴한다.
    //return (Stack<E>) super.clone(); //swallow copy - 2개의 stack이 같은 주소를 가리키고 있다 ---> 링크가 다 끊어져 있는데 뭘 복제해...
  }
  
  public void offer(E value) {
    add(value);
  }
  
  public E poll() {
    return remove(0); 
  }

  public boolean empty() {
    return size() == 0; //사이즈가 0이면 true ->비어있다.
    
  }
  
  //큐의 데이터를 꺼내줄 Iterator를 제공한다.
  public Iterator<E> createIterator(){ //clone()된 주소가 넘어와서
    return new QueueIterator<E>(this); //그 값을 가진 생성자로 찾아갔더니
    
  }

  
  
  
}
