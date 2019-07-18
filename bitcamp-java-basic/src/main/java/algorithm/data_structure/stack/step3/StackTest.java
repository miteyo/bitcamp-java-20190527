package algorithm.data_structure.stack.step3;

public class StackTest {
  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    

    while(!stack.empty()) {// 만약에 비어있지 않으면 
      System.out.println(stack.pop());
    }
    
  }
}
