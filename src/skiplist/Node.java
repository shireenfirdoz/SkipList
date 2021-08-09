package skiplist;

public class Node {

  int key;
  Node prev;
  Node next;
  Node top;
  Node bottom;
  int level;

  public Node(int key, Node prev, Node next, Node top, Node bottom, int level) {
    this.key = key;
    this.prev = prev;
    this.next = next;
    this.top = top;
    this.bottom = bottom;
    this.level = level;
  }

}
