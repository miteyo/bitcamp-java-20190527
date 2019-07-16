package ch14.f;

public class Sedan extends Car {
  //아~ Car를 만든 개발자가 나보고 구현 하라는 거구나.
  
  int capacity; // 승차인원
  
  @Override
  public void run() {
    System.out.println("씽씽~~ 달린다!");
  }

}
