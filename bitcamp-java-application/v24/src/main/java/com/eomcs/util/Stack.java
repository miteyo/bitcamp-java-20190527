// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util;

public class Stack<E> extends LinkedList<E> implements Cloneable, Iterable{ // 약속한다. Cloneable(인터페이스) 복사할 수
                                                                   // 있다는 자격을 준다.

  @Override
  public Stack<E> clone() throws CloneNotSupportedException {

    // 현재 스택 객체의 노드를 그대로 유지하기 위해 "deep copy"를 실행한다.
    // => 새 스택을 만들고,
    Stack<E> temp = new Stack<>(); // 새 스택을 만들고
    for (int i = 0; i < size(); i++) {

      // 현재 스택에서 값을 꺼내 새 스택의 새 노드에 넣는다.
      // 즉 새 스택은 값을 넣을 떄 마다 새 노드를 생성하기 때문에 현재 스택의 노드에는 영향을 끼치지 않는다.
      temp.push(get(i)); // 기존 스택의값을 get(가져와서)한 값을 temp에 push 한다.
    }
    return temp; // 원래 노드 삭제해도 temp를 리턴한다.
    // return (Stack<E>) super.clone(); //swallow copy - 2개의 stack이 같은 주소를 가리키고 있다 ---> 링크가 다 끊어져
    // 있는데 뭘 복제해...
  }

  public void push(E value) {
    add(value);
  }

  public E pop() {
    return remove(size() - 1); // size - 1 은 배열의 맨 마지막 것.
  }

  public boolean empty() {
    return size() == 0; // 사이즈가 0이면 true ->비어있다.
  }

  @Override
  public Iterator<E> iterator() {

      return new Iterator<E> (){ // 중첩하면 생성자를 따로만들 필요 없다.
      @Override
      public boolean hasNext() {
        return size() > 0;
      }
      @Override
      public E next() {
        return pop();
      }
    };
  }


}
