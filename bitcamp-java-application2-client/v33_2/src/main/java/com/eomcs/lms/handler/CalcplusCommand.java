package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalcplusCommand implements Command{

  Input input;
  
  public CalcplusCommand(Input input){
    this.input = input;
  }
  
  @Override
  public void execute() {
    int no1 = input.getIntValue("값1? " );
    int no2 = input.getIntValue("값2? " );
    
    int value = no1 + no2;
    System.out.println("합계? " + value);
  }

}
