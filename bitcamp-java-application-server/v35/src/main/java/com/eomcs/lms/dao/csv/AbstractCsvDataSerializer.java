package com.eomcs.lms.dao.csv;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class AbstractCsvDataSerializer<T, K> { // T: 데이터 타입, K: 데이터를 꺼낼 때 사용하는 타입

  // 서브클래스에서 저장된 데이터를 조회할 수 있도록 하기 위해 접근 범위를 protected로 한다.
  protected ArrayList<T> list = new ArrayList<>();

  // 내부에서만 사용할 필드이기 때문에, 외부에서는 사용해서는 안되는 필드이기 때문에 private으로 한다.
  private File file;

  public AbstractCsvDataSerializer(String file) {
    this.file = new File(file);

  }

  protected void loadData() throws IOException, ClassNotFoundException {

    try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
      // Buffered (char단위) FileInputStream(byte단위) => 어댑터인 inputStreamReadr를 추가한다.

      String line = null;
      
      while ((line = in.readLine()) != null) { // 읽은 값의 리턴값이 null이 아니면
        
        String[] values = line.split(","); // ,로 구분하여 쪼개어 values에 넣는다.
        T obj = createObject(values);
        // 객체를 만들어서 obj에 저장한다.( 그 행위는 구체적으로 정의하지 않았다.-> 이 클래스를 구현 받는 서브클래스에서 구현한다.)
        list.add(obj);
      }
    }
  }

  protected void saveData() throws IOException {
    try (PrintStream out = new PrintStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      
      //리스트에서 하나씩 꺼내기
      for(T obj : list) {
        out.println(createCSV(obj)); //csv value를 리턴해주는 메소드를 한줄씩 출력하기
      }

    }
  }



  // 서브 클래스에게 문자열 배열에 대해 객체를 생성하는 책임을 넘긴다.
  // 서브 클래스에게 구현을 맞긴다.
  // => 위의 loadData() 메소드에서 기본 기능을 정의하고,
  // => 특정 기능에 대해서는 서브 클래스에서 완성하도록 유도하는 설계기법을
  // "template method" 패턴이라고 한다.
  protected abstract T createObject(String[] values);
  protected abstract String createCSV(T obj);

  // 서브 클래스에서 특정 값을 가지고 데이터를 찾는 기능을 반드시 구현하도록 강제하자!
  public abstract int indexOf(K key);

}
