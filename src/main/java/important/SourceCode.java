package important;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class SourceCode {
  
  private volatile int lock;
  private Lock lock1;
  private ReentrantLock reentrantLock;
  private ReadWriteLock readWriteLock;
  private AbstractQueuedSynchronizer abstractQueuedSynchronizer;
  
  private synchronized void lock() {
  }
  
  public static void main(String[] args) {
    
    /**
     * System.arraycopy方法大量使用，这是个native方法，复制数组。
     * Arrays.copyOf也是一个复制数组的java方法。
     * ensureCapacity确保容量。
     * int newCapacity = oldCapacity + (oldCapacity >> 1);
     */
    List list = new ArrayList();
    List list1 = new LinkedList();
    List list2 = new CopyOnWriteArrayList();
    
    Vector vector = new Vector();
    Stack stack = new Stack();
    
    Queue queue = new ArrayDeque();
    Deque deque = new ArrayDeque();
    
    Map map = new HashMap();
    Map map1 = new LinkedHashMap();
    Map map2 = new TreeMap();
    Map map3 = new WeakHashMap();
    
    Map map4 = new ConcurrentHashMap();
    Map map5 = new ConcurrentSkipListMap();
    
    Hashtable hashtable = new Hashtable();
    HashSet hashSet = new HashSet();
    HashSet hashSet1 = new LinkedHashSet();
  }
  
}
