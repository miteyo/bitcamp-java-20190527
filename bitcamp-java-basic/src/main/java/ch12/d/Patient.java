package ch12.d;

public class Patient {
  
  public static final int WOMAN = 1; // final: 변경못하도록, static: 인스턴스 만들때마다 만들 필요 없으니까, 클래스 로딩될 떄 딱 한번만, public 다른 곳에서도 쓸 수 있오록.
  public static final int MAN = 2;
  
  String name;
  int age;
  int height;
  int weight;
  int gender;
  
  
  public String toString() { //p의 주소를 this 변수에 저장, this는 생략가능
    
   return String.format("name= %s, age= %s, height = %d, weight= %d, gender= %s", 
                       this.name, this.age, this.height, this.weight, this.gender);
    
    //String s = "name= " + this.name + ", age= " + age + ", height= " + height + ", weight= " + weight + ", gender: " + gender;
   
  }
  
  
  

}
