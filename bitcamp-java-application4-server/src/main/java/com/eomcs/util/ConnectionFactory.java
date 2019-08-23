package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

//DAO가 사용할 커넥션 객체를 생성해주는 역할
public class ConnectionFactory {
  String jdbcDriver;
  String jdbcUrl;
  String username;
  String password;

  public ConnectionFactory(String jdbcDriver, String jdbcUrl, String username, String password) {

    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;

  }

  //커넥션을 만들어주는 일.
  public Connection getConnection() throws Exception {
    //강제적으로 jdbcDriver 로딩하기
    Class.forName(jdbcDriver);
    return DriverManager.getConnection(jdbcUrl, username, password);

  }

}
