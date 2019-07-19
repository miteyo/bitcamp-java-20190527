package com.eomcs.util;

public class LinkedListTest {

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();

    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    

    list.remove(3); //ddd
    list.remove(0); //aaa
    list.remove(4); //ggg

    //list.clear();
    
    Object[] arr = list.toArray(new String[] {}); //빈배열을 넘기면 알아서 새배열을 리턴해준다.
    for (Object obj : arr) {
      System.out.println(obj);
    }
  }
}
