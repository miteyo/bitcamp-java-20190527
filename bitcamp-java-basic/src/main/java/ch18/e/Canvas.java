package ch18.e;

public class Canvas {
  
  // Canvas가 사용할 도구를 구체적으로 지정(클래스 이름을 사용)하는 대신에
  // 좀 더 추상적인 방식으로 클래스 이름 대신 인터페이스를 사용하여 
  // 특정 규칙에 따라 만든 도구를 사용하겠다고 지정한다.
  // => 이 방식의 장점은 규칙에 따라 만든 도구가 어떤 클래스를 상속 받는 상관하지 않는다.
  // => 만약 Pen이 인터페이스가 아니라 클래스라면 반드시 Pen의 서브 클래스만 사용해야 한다.
  //    따라서 클래스를 사용하는 것 보다 인터페이스를 사용하는 것이 훨신 유연한 코딩을 할 수 있다.
  Pen tool;
  
  // 만약 캔버스의 그리기 도구로서 다음과 같이 클래스를 지정한다면,
  // => 오직 Monami 도구만, Monami의 서브 클래스만 사용할 수 있다.
  //    Canvas가 사용할 도구를 만들 때도 오직 Monami의 서브 클래스만 만들어야 한다.
  //    도구를 사용하는 쪽이나 도구를 만드는 쪽 모두 Monami로 제한되어 유연하지 못하다.
  // => 그러나 위의 필드처럼 도구를 지정할 때 인터페이스를 사용하면,
  //    어느 클래스를 상속 받는지 상관없이 다양한 도구를 사용할 수 있어 
  //    Canvas를 만든 개발자 입장에서는 훨씬 편리하다.
  //    또한 도구를 만드는 입장에서도 다른 유용한 클래스를 마음껏 상속 받아 도구를 만들 수 있어 
  //    매우 편리하다. 어떤 클래스를 상속 받든 단지 Pen 이라는 규칙만 따르면 되기 때문이다.
  //Monami tool;
  
  
  public Canvas(Pen tool) { //생성자에 Pen 인터페이스 규칙에 따라 만든 객체를 넣는다)
    this.tool = tool;
  }
  
  public void setPen(Pen tool) {
    this.tool = tool;
  }
  
  public void render(String contents) {
    
    // 어떤 Pen인지 상관없이, Pen 사용 규칙에 따라 메소드를 호출한다.
    // => 클래스에 상관없이 일관된 방법으로 펜을 사용할 수 있어 유지보수에 좋다.
    // => 이것이 인터페이스를 사용하는 이유이다.
    tool.write(contents);
  }
}
