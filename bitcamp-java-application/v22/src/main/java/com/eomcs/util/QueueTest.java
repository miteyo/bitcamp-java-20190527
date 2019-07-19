package com.eomcs.util;

public class QueueTest {
  public static void main(String[] args) {
    
    Queue<String> queue = new Queue<>();
    
    queue.offer("aaaaaaa");
    queue.offer("bffffbb");
    queue.offer("cddddcc");
    queue.offer("ddssssd");
    

    while(!queue.empty()) {// 만약에 비어있지 않으면 
      System.out.println(queue.poll());
    }
    
  }
}
