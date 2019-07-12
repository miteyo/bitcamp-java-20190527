package ch12.d;

public class Patient2 {
  
  public static final int WOMAN = 1; // final: 변경못하도록, static: 인스턴스 만들때마다 만들 필요 없으니까, 클래스 로딩될 떄 딱 한번만, public 다른 곳에서도 쓸 수 있오록.
  public static final int MAN = 2;
  
  private String name;
  private int age;
  private int height;
  private int weight;
  private int gender;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getAge() {
    
    return age;
  }

  public void setAge(int age) {
    if(age > 0 && age <150)
    this.age = age;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    if(height > 1 && height <500)
    this.height = height;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    if(weight >0 && weight <500)
    this.weight = weight;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    if(gender > 0 && gender <3)
    this.gender = gender;
  }

  public String toString() { //p의 주소를 this 변수에 저장, this는 생략가능
    
   return String.format("name= %s, age= %s, height = %d, weight= %d, gender= %s", 
                       this.name, this.age, this.height, this.weight, this.gender);
    
    //String s = "name= " + this.name + ", age= " + age + ", height= " + height + ", weight= " + weight + ", gender: " + gender;
   
  }
  
  
  

}
