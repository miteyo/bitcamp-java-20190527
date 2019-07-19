package com.eomcs.util.step1;

import com.eomcs.util.Iterator;

//스택에 있는 데이터를 꺼내주는 역할을 한다.
//Iterator 규칙에 따라 작성하여 이 객체를 사용하는 개발자가 일관된 방식으로 호출할 수 있게한다.

//Stack: 창고
//tackIterator : 꺼내주는 역할~ 창고주소를 알려줘야지
public class StackIterator<E> implements Iterator<E> {

  Stack<E> stack;
  
  public StackIterator(Stack<E> stack) { //창고주소를 알려줘야지
    this.stack = stack;
  }
  
  @Override
  public boolean hasNext() {
    
    return stack.size() > 0; //창고의 크기가 0보다 크다? 데이터가있다
  }

  @Override
  public E next() {
    return stack.pop(); // 창고에서 하나 꺼내라.
  }

}
