package com.eomcs.lms;

import java.sql.Date;

public class Member { //회원정보를 담을 새 메모리 구조를 설계하는 설계도. 
                      //아직 안만들어짐. jvm야.. 저 순서대로 하나의 일련의 메모리를 준비해라, 만들어라. new라는 명령어를 통해 변수가 만들어진다. 
    int no;
    String name;
    String email;
    String password;
    String photo;
    String tel;
    Date registeredDate;
}
