package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class HelloCommand implements Command {
  

  @Override
  public void execute(BufferedReader in , PrintStream out) {
    try {
      out.println("선생님 방가방가");
      
    } catch (Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!"); //클라이언트에게 답변하기
      System.out.println(e.getMessage());
    }
  }

}
