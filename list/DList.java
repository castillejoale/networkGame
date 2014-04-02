/* DList.java */

package list;

public class DList extends List {

  /**
   *  (inherited)  size is the number of items in the list.
   *  head references the sentinel node.
   **/

  protected DListNode head;

  /**
   *  newNode() calls the DListNode constructor.  Use this method to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method need be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *
   *  @param item the item to store in the node.
   *  @param list the list that owns this node.  (null for sentinels.)
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   **/
  protected DListNode newNode(Object item, DList list,
                              DListNode prev, DListNode next) {
    return new DListNode(item, list, prev, next);
  }

  /**
   *  DList() constructs for an empty DList.
   **/
  public DList() {

    head = newNode(Integer.MIN_VALUE, null, null, null);
    head.next = head;
    head.prev = head;
    
    size = 0;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *
   *  @param item is the item to be inserted.
   *
   *  Performance:  runs in O(1) time.
   **/
  public void insertFront(Object item) {

    DListNode node = newNode(item, this, head, head.next);
    head.next.prev = node;
    head.next = node;
    node.myList = this;
    size++;


  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *
   *  @param item is the item to be inserted.
   *
   *  Performance:  runs in O(1) time.
   **/
  public void insertBack(Object item) {

    DListNode node = newNode(item, this, head.prev, head);
    head.prev.next = node;
    head.prev = node;
    node.myList = this;
    size++;


  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return an "invalid" node--a node with the property that any
   *  attempt to use it will cause an exception.  (The sentinel is "invalid".)
   *
   *  @return a ListNode at the front of this DList.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode front() {
    return head.next;
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return an "invalid" node--a node with the property that any
   *  attempt to use it will cause an exception.  (The sentinel is "invalid".)
   *
   *  @return a ListNode at the back of this DList.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode back() {
    return head.prev;
  }


  /**
   *  removeBack() removes the last node of a DList if valid
   */
  public void removeBack() throws InvalidNodeException {

    if (!(head.prev.isValidNode())) {
      throw new InvalidNodeException();
    }

    head.prev.prev.next = head;
    head.prev = head.prev.prev;
    size--;
    

  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  @return a String representation of this DList.
   *
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }


}