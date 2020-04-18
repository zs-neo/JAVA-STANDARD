package standard.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 在concurrentLinkedQueue中使用原子化的域更新器
 * Node<E>{final E item,volatile Node<E> next}
 * private static AtomicReferenceFieldUpdater<Node,Node> nextUpdater =
 *      AtomicReferenceFieldUpdater.newUpdater(Node.class,"next")
 *
 * ABA问题
 * @param <E>
 */
public class MyLinkedQueue<E> {
  
  static class Node<E> {
    public final E item;
    public AtomicReference<Node<E>> next;
    
    public Node(E item, Node<E> next) {
      this.item = item;
      this.next = new AtomicReference<>(next);
    }
  }
  
  private final Node<E> dummy = new Node<E>(null, null);
  private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
  private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);
  
  public boolean put(E item) {
    Node<E> newNode = new Node<E>(item, null);
    while (true) {
      Node<E> curTail = tail.get();
      Node<E> nextTail = curTail.next.get();
      if (curTail == null) {
        if (nextTail != null) {
          tail.compareAndSet(curTail, nextTail);
        } else {
          if (curTail.next.compareAndSet(null, newNode)) {
            tail.compareAndSet(curTail, newNode);
            return true;
          }
        }
      }
    }
  }
  
}
