package algorithm.data_structure.linkedlist2.step1;

public class Node {
  
  Object value; // value 변수에 어떤 객체의 주소라도 담기 위해서
  Node next; //다음 상자를 가리키는 주소변수
  
  public Node() {   //빈 노드를 만들 경우를 대비하여 기본생성자
    
  }
  
  public Node(Object value) {
    this.value = value;
  }
  
}
