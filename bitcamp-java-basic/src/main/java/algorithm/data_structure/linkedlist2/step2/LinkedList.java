//LinkedList : Node 클래스를 중첩클래스(static nested class)로 만들기
package algorithm.data_structure.linkedlist2.step2;

public class LinkedList {
  Node head;
  Node tail;
  int size = 0; // 인스턴스 필드는 초기화시키지 않아도 0

  public LinkedList() {

  }

  public boolean add(Object value) {
    Node temp = new Node(value);
    if (head == null)
      head = temp;

    if (tail != null) // null이 아닐때만 tail에 새 노드를 지정하겠다.
      tail.next = temp;
    tail = temp;
    //
    // tail.value = value;
    // tail.next = new Node(); // 새로운 빈 노드를 만들고 next 변수에 담는가 가르키도록 한다..
    // tail = tail.next; // 테일의 넥스트 변수에 들어있는 새주소가 tail이 되야한다. tail에 저장된 주소변수에 찾아가의 next의 변수에 들어있는 값을
    // // tail 변수에 저장하라
    size++;
    return true;
  }

  public Object get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node node = head; // 첫번째 head가 가르키는 것부터 시작.
    for (int i = 0; i < index; i++) {
      node = node.next; // 현재 노드가 100번지의 next 주소를 가져와라.. 반복.
    }
    return node.value;
  }

  // 특정위치의 값을 바꾼다.
  public Object set(int index, Object value) {

    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    Object oldvalue = node.value; // 기존 노드에 저장된 기존 값 백업
    node.value = value; // 해당 노드의 값을 파라미어테서 받은 값으로 변경
    return oldvalue;
  }

  // 특정위치 값을 삭제한다.
  public Object remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node deleteNode = null;
    if (index == 0) {
      deleteNode = head;
      head = deleteNode.next;
    } else {

      Node node = head;
      for (int i = 0; i < index - 1; i++) { // 인덱스 2를 삭제? 1번만

        node = node.next; // 삭제하려는 node의 이전노드까지 간다. node는 우리가 찾는 이전노드.
      }

      deleteNode = node.next; // 삭제될 노드를 임시보관한다. // 삭제될 노드가 다음 값을 계속 가르키고 있으니까.
      node.next = deleteNode.next; // 삭제될 노드의 다음 노드를 가르킨다.

      if (deleteNode == tail) { // 삭제할 노드가 마지막 노드라면 tail값을 바꿔야 한다.
        tail = node; // tail노드를 변경한다.
      }

    }

    Object oldVal = deleteNode.value; // 삭제될 노드의 값을 임시 보고나한다.
    deleteNode.value = null; // 삭제될 노드가 다른 객체를 참조하지 않도록 null 처리한다.
    deleteNode.next = null; // 이런식으로 개발자가 메모리관리에 기여할 수 있다.

    size--;

    return oldVal;
  }

  public int size() {
    return size;
  }

  public void clear() {
    if (size == 0)
      return;
    // 노드를 따라 가면서 삭제하기

    while (head != null) {
      Node deletedNode = head; // 와일분 나가면서 de변수는 로컬변수로 사라진다.
      head = head.next; // 헤드를 다음노드로 이동시킨다.
      deletedNode.value = null;
      deletedNode.next = null;
    }

    tail = null;
    size = 0;
  }

  public Object[] toArray() {
    // LinkedList에 있는 데이터를 저장할 배열을 준비한다.
    Object[] arr = new Object[size];

    // LinkedList의 head에서 tail까지 반복하면서 배열에 value를 복사한다.
// 방법1]
    // Node node = head;
    // for (int i = 0; i < size; i++) {
    // arr[i] = node.value;
    // node = node.next;
    // }

    // 배열을 리턴한다.
    // return arr;

// 방법2]

    Node node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    return arr;
  }

  // LinkedList에서 사용하는 클래스라면 굳이 패키지 멤버 클래스로 만들 필요가 없다.
  // LinkedList 안에 선언하여 중첩클래스로 정의하는 것이 소스코드의 유지보수에 좋다.
  // 외부에 직접 노출되지 않기 때문에 쓸데없는 클래스를 감추는 효과도 있다.
  static public class Node {

    Object value; // value 변수에 어떤 객체의 주소라도 담기 위해서
    Node next; // 다음 상자를 가리키는 주소변수

    public Node() { // 빈 노드를 만들 경우를 대비하여 기본생성자

    }

    public Node(Object value) {
      this.value = value;
    }

  }



}
