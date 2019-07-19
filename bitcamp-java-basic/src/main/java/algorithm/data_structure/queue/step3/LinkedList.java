// LinkedList : 목록으로 다루는 값을 특정 타입으로 제한하기 위해 제네릭 적용하기
package algorithm.data_structure.queue.step3;

import java.lang.reflect.Array;

public class LinkedList<T> {
  Node<T> head;
  Node<T> tail;
  int size = 0; // 인스턴스 필드는 초기화시키지 않아도 0

  public LinkedList() {

  }

  public boolean add(T value) {
    Node<T> temp = new Node<>(value);
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

  public T get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node<T> node = head; // 첫번째 head가 가르키는 것부터 시작.
    for (int i = 0; i < index; i++) {
      node = node.next; // 현재 노드가 100번지의 next 주소를 가져와라.. 반복.
    }
    return node.value;
  }

  // 특정위치의 값을 바꾼다.
  public T set(int index, T value) {

    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    T oldvalue = node.value; // 기존 노드에 저장된 기존 값 백업
    node.value = value; // 해당 노드의 값을 파라미어테서 받은 값으로 변경
    return oldvalue;
  }

  // 특정위치 값을 삭제한다.
  public T remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");

    Node<T> deleteNode = null;
    if (index == 0) {
      deleteNode = head;
      head = deleteNode.next;
    } else {

      Node<T> node = head;
      for (int i = 0; i < index - 1; i++) { // 인덱스 2를 삭제? 1번만

        node = node.next; // 삭제하려는 node의 이전노드까지 간다. node는 우리가 찾는 이전노드.
      }

      deleteNode = node.next; // 삭제될 노드를 임시보관한다. // 삭제될 노드가 다음 값을 계속 가르키고 있으니까.
      node.next = deleteNode.next; // 삭제될 노드의 다음 노드를 가르킨다.

      if (deleteNode == tail) { // 삭제할 노드가 마지막 노드라면 tail값을 바꿔야 한다.
        tail = node; // tail노드를 변경한다.
      }

    }

    T oldVal = deleteNode.value; // 삭제될 노드의 값을 임시 보고나한다.
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
      Node<T> deletedNode = head; // 와일분 나가면서 de변수는 로컬변수로 사라진다.
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

    Node<T> node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    return arr;
  }


  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if (a.length < size) {
      //넘겨받은 배열크기가 저장된 데이터 개수보다 작으면 새 배열을 만들어서 주소를 리턴한다(T[]타입캐스팅해서 리턴한다)
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size); // (배열타입, 배열안의 각각의 항목타입, 크기)
    }
    
    //사이즈가 충분하다면 배열에 담아라.
    Node<T> node = head;
    for (int i = 0; i < size; i++) {
      a[i] = node.value;
      node = node.next;
    }

    if (a.length > size)
      a[size] = null; // a[10] 에 null을 채워라. 인덱스는 9까지 채워져있다. (출력시, 배열==null이면 break 가능성.)
    return a;

  }

  // Node 객체에 보관하는 데이터의 클래스이름을 "타입 파라미터 T" 에 받는다.
  static public class Node<T> {

    T value; // value 변수에 어떤 객체의 주소라도 담기 위해서
    Node<T> next; // 다음 상자를 가리키는 주소변수

    public Node() { // 빈 노드를 만들 경우를 대비하여 기본생성자
    }

    public Node(T value) {
      this.value = value;
    }

  }



}
