package ch19.a.sub;
class Car {
  String model;
  
  public void run() {
    System.out.println("달린다!");
  }
  
  //익명클래스의 예제를 위한 클래스 ( Car를 상속받은 익명클래스 만들기
  public Car() {
    this.model = "미정";
  }
  
  public Car(String model) {
    this.model = model;
  }
  
  public static void main(String[] args) {
      class fraf extends Car{
      @Override
      public void run() {
        System.out.println("달린다traf");
      }
    }

      class wir implements Pen{
        @Override
        public void write() {
          System.out.println("쓴다리");
          
        }
        
      }
      
      
      
      
  }
}


interface Pen {
  void write();
}







