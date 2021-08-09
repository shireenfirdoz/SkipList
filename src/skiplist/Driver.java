package skiplist;

public class Driver {

  public static void main(String[] args) {
    SkipList skiplist = new SkipList();

    // insert 20, insert 40, insert 10, insert 20, insert 5,
    // insert 80, delete 20, insert 100, insert
    // 20, insert 30, delete 5, insert 50, lookup 80, etc
    
  /**  for(int i = 0; i < 1000; i++) {
      skiplist.insert(i);
    }
    skiplist.printNode();**/

    skiplist.insert(20);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(40);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(10);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(20);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(5);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(80);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.delete(20);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(100);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(20);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(30);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.delete(5);
    skiplist.printNode();
    System.out.println("\n");

    skiplist.insert(50);
    skiplist.printNode();
    System.out.println("\n");
    

    Node node = skiplist.findKey(80);
    if (node != null) {
      System.out.println("\n Node found node key " + node.key);
    } else {
      System.out.println("\n Node not found ");
    }

    
    
  }

}
