package concurrent.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer {
  
  /**
   * 锁对象
   */
  final Lock lock = new ReentrantLock();
  /**
   * 写线程锁
   */
  final Condition notFull = lock.newCondition();
  /**
   * 读线程锁
   */
  final Condition notEmpty = lock.newCondition();
  /**
   * 缓存队列
   */
  final Object[] items = new Object[100];
  /**
   * 写索引
   */
  int putptr;
  /**
   * 读索引
   */
  int takeptr;
  /**
   * 队列中数据数目
   */
  int count;
  
  //写
  public void put(Object x) throws InterruptedException {
    lock.lock(); //锁定
    try {
      // 如果队列满，则阻塞<写线程>
      while (count == items.length) {
        notFull.await();
      }
      // 写入队列，并更新写索引
      items[putptr] = x;
      if (++putptr == items.length) putptr = 0;
      ++count;
      
      // 唤醒<读线程>
      notEmpty.signal();
    } finally {
      lock.unlock();//解除锁定
    }
  }
  
  //读
  public Object take() throws InterruptedException {
    lock.lock(); //锁定
    try {
      // 如果队列空，则阻塞<读线程>
      while (count == 0) {
        notEmpty.await();
      }
      
      //读取队列，并更新读索引
      Object x = items[takeptr];
      if (++takeptr == items.length) takeptr = 0;
      --count;
      
      // 唤醒<写线程>
      notFull.signal();
      return x;
    } finally {
      lock.unlock();//解除锁定
    }
  }
  
  public static void main(String[] args) {
    final String str = "abc";
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("1" + str);
      }
    });
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("2" + str);
      }
    });
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("2" + str);
      }
    });
  }
}
