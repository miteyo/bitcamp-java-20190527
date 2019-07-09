package com.eomcs.lms;

public class Car {
// static/class field 
//  =>class가 loading 될 떄, Method Area에 자동 생성된다.
//  =>class는 딱 한번만 loading 되기 때문에, 중복으로 로딩되지 않는다.
//    static field도 딱 한번만 생성된다.
  static int count; //메모리르 만드세요.(자동초기화 0)
  
  //instance field
  // => new 명령을 실행할 때, Heap에 생성된다.
  ///   new 명령을 실행하는 갯수만큼 instance field가 생성된다.
  int no;
  String model;
  
}
