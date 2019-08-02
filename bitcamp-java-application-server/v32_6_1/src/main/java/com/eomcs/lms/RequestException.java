package com.eomcs.lms;

//서버에서 요청 처리를 실패했을 때 발생시키는 예외이다.
public class RequestException extends RuntimeException {

  //수퍼클래스가 serialize 되어있는 형태이기 때문에, serialVersionUID 지정해라.
  private static final long serialVersionUID = 1L;

  public RequestException() {
    super();
  }

  public RequestException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public RequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestException(String message) {
    super(message);
  }

  public RequestException(Throwable cause) {
    super(cause);
  }
  

}
