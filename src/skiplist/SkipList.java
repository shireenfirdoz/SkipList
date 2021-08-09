package skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList {

  Node head;
  Node tail;
  int maxLevel = 0;

  /**
   * Method for coin flip to get head probablity.
   * 
   * @return
   */
  private boolean flipHead() {
    Random random = new Random();
    int probablity = random.nextInt(2);
    if (probablity == 1) {
      return false;
    } else {
      return true;
    }
  }

  public SkipList() {

    head = new Node(Integer.MIN_VALUE, null, null, null, null, 1);
    tail = new Node(Integer.MAX_VALUE, null, null, null, null, 1);
  }

  /**
   * Method for copying node to generate the levels based on coin flip and gets
   * head.
   * 
   * @param node
   * @return
   */
  private Node copyNode(Node node) {
    Node copyNode = new Node(node.key, null, null, null, node, node.level + 1);
    node.top = copyNode;
    return copyNode;
  }

  /**
   * Method inserts the new node called in loop based on coin flip.
   * 
   * @param newNode
   * @param prevNewNode
   * @return
   */
  private Node insertNewNode(Node newNode, Node prevNewNode) {

    // 1) check for all the prev top node till you reach head
    // 2) if any prev top node is not null then add the new copy node prev pointer
    // to this prev top node
    // 3) if you reach to head in the same level and head top is not null then add
    // new copy node prev pointer to this head top
    // 4) if you reach to head in the same level and head top is null then add new
    // level...and new copy node prev pointer to the head of the new level
    Node newCopyNode = copyNode(newNode);
    if (prevNewNode.top != null) {
      // 2
      newCopyNode.next = prevNewNode.top.next;
      newCopyNode.prev = prevNewNode.top;
      prevNewNode.top.next.prev = newCopyNode;
      prevNewNode.top.next = newCopyNode;

      System.out.println("Added new node");
    }

    else if (prevNewNode.top == null && prevNewNode.prev == null) {
      // reached header for the same level
      // this header is the header top so insert new level

      Node headtemp = new Node(Integer.MIN_VALUE, null, null, null, head, head.level + 1);
      Node tailtemp = new Node(Integer.MAX_VALUE, null, null, null, tail, tail.level + 1);

      this.head.top = headtemp;
      this.tail.top = tailtemp;

      newCopyNode.prev = headtemp;
      newCopyNode.next = tailtemp;
      headtemp.next = newCopyNode;
      tailtemp.prev = newCopyNode;

      this.head = headtemp;
      this.tail = tailtemp;

      System.out.println("Added new level");

    } else if (prevNewNode.top == null && prevNewNode.prev != null) {
      insertNewNode(newNode, prevNewNode.prev);
    }

    return newNode.top;
  }

  /**
   * Method for deleting the node.
   * 
   * @param newKey
   */
  public void delete(int newKey) {
    Node node = findKey(newKey);

    while (node != null) {
      Node temp = node.next;
      node.next.prev = node.prev;
      node.prev.next = temp;
      node = node.bottom;
    }

  }

  /**
   * Method for inserting the key and generates the level.
   * 
   * @param newKey
   */
  public void insert(int newKey) {
    System.out.println("Inserting new key" + newKey);
    Node newNode = null;
    boolean nodeExist = false;
    if (head.next == null) {
      newNode = new Node(newKey, head, tail, null, null, 1);
      head.next = newNode;
      tail.prev = newNode;
    } else {
      // find k place
      // insert at 0th level
      Node searchNode = findKey(newKey);
      if (searchNode == null) {
        Node nodePrev = find(newKey);
        newNode = new Node(newKey, nodePrev, nodePrev.next, null, null, 1);
        nodePrev.next.prev = newNode;
        nodePrev.next = newNode;
        nodeExist = false;
      } else {
        nodeExist = true;
      }

    }
    if (!nodeExist) {
      int i = 1;

      while (flipHead()) {

        // creating copy for newKey

        newNode = insertNewNode(newNode, newNode.prev);

        i++;
      }
      maxLevel = i;
    }
  }

  public int flipHead12(int key) {
    if (key == 5) {
      return 3;
    } else if (key == 10) {
      return 3;
    } else if (key == 20) {
      return 5;
    } else if (key == 40) {
      return 1;
    } else if (key == 80) {
      return 2;
    }
    return 0;
  }

  /**
   * Method to find the previous to insert the key.
   * 
   * @param key
   * @return
   */
  public Node find(int key) {
    List<Node> nodeList = find1(key, head);
    Node node = nodeList.get(nodeList.size() - 1);
    return node;
  }

  /**
   * Method to search the key.
   * 
   * @param key
   * @return
   */
  public Node findKey(int k) {
    Node temp = head;

    while (temp != null && compare(temp.key, k) != 0) {

      if (compare(temp.next.key, k) < 0) {
        temp = temp.next;
      } else if (compare(temp.next.key, k) == 0) {
        return temp.next;
      } else {
        temp = temp.bottom;
      }

    }
    return temp;
  }

  private int compare(int a, int b) {
    if (a == b) {
      return 0;
    } else if (a > b) {
      return 1;
    } else if (a < b) {
      return -1;
    }
    return -1;
  }

  private List<Node> find1(int k, Node node) {
    List<Node> previousNode = new ArrayList<>();
    /**
     * If k = key, done! If k < next key, go down a level If k > next key, go right
     **/

    if (node == null) {
      return null;
    }
    Node temp = node;

    while (temp != null) {

      if (compare(temp.next.key, k) < 0) {
        temp = temp.next;
      } else {
        previousNode.add(temp);
        temp = temp.bottom;
      }

    }
    while (previousNode.get(previousNode.size() - 1).bottom != null) {
      previousNode.add(previousNode.get(previousNode.size() - 1).bottom);
    }
    return previousNode;

  }

  /**
   * Method to print the skiplist.
   */
  public void printNode() {
    printNew(head);
  }

  private static void printNew(Node root) {
    if (root == null)
      return;

    Node temp = root;

    while (temp.bottom != null) {
      temp = temp.bottom;
    }

    while (temp.next != null) {
      Node temp1 = temp;
      System.out.println();
      while (temp1.top != null) {
        System.out.print(temp1.key + "\t");
        temp1 = temp1.top;
      }
      System.out.print(temp1.key + "\t");
      temp = temp.next;
    }

    if (temp.next == null) {
      Node temp1 = temp;
      System.out.println();
      while (temp1.top != null) {
        System.out.print(temp1.key + "\t");
        temp1 = temp1.top;
      }
      System.out.print(temp1.key + "\t");
    }

  }

}
