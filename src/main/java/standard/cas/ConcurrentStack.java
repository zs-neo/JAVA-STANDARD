package standard.cas;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {
  
  static class Node<E> {
    public final E item;
    public Node<E> next;
    
    public Node(E item, Node<E> next) {
      this.item = item;
      this.next = next;
    }
  }
  
  AtomicReference<Node<E>> top = new AtomicReference<>();
  
  public void push(E item) {
    Node<E> newHead = new Node<E>(item, null);
    Node<E> oldHead;
    do {
      oldHead = top.get();
      newHead.next = oldHead;
    } while (!top.compareAndSet(oldHead, newHead));
  }
  
  public E pop() {
    Node<E> oldHead;
    Node<E> newHead;
    do {
      oldHead = top.get();
      if (oldHead == null) {
        return null;
      }
      newHead = oldHead.next;
    } while (!top.compareAndSet(oldHead, newHead));
    return oldHead.item;
  }
  
}
