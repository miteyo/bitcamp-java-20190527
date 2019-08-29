// dynamic SQL 다루기 - <for each> 태그 사용법2
package ch26.f;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test09_2 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/f/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    ArrayList<String> list = new ArrayList<>();
    
    Scanner keyboard = new Scanner(System.in);
    
    while (true) {
      try {
        System.out.print("게시물 제목? ");
        String value = keyboard.nextLine();
        if (value.length() == 0) {
          break;
        }
        list.add(value);
      } catch (Exception e) {
        break;
      }
    }
    
    keyboard.close();
    
    HashMap<String,Object> params = new HashMap<>();
    params.put("list", list);
    
    List<Board> boards = sqlSession.selectList("board.select9_2", params);
    
    for (Board b : boards) {
      System.out.println(b);
    }
    System.out.println("-------------------------------");
    
  }

}






