package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable { /**
   * 
   */
  private static final long serialVersionUID = 1L;
// 회원정보를 담을 새 메모리 구조를 설계하는 설계도.
                      // 아직 안만들어짐. jvm야.. 저 순서대로 하나의 일련의 메모리를 준비해라, 만들어라. new라는 명령어를 통해 변수가 만들어진다.
  private int no;
  private String name;
  private String email;
  private String password;
  private String photo;
  private String tel;
  private Date registeredDate;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }



}
