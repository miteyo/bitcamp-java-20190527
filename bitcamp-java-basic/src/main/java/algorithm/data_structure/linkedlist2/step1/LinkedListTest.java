package algorithm.data_structure.linkedlist2.step1;

public class LinkedListTest {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    list.set(2, "xxx"); //ccc -> xxx
    list.remove(3); //ddd
    list.remove(0); //aaa
    list.remove(4); //ggg

    
    Object[] arr = list.toArray();
    for (Object obj : arr) {
      System.out.println(obj);
    }
  }
}
