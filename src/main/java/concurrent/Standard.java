package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Standard {
  
  private int value;
  private AtomicInteger i;
  /**
   * 使用volatile的条件
   *    - 写入变量的时候不依赖变量的当前值，或者能保证只有单一的线程修改变量的值。
   *    - 变量不需要和其他变量参与不变约束。
   *    - 访问变量的时候没有其他原因需要加锁。
   *
   */
  private volatile boolean aBoolean;
  
  public void unsafeSequence(){
    value++;
  }
  
  /**
   * 竞争条件
   *    - 多个线程或者进程在读写一个共享数据时结果依赖于它们执行的相对时间，这种情形叫做竞争。
   *    - 竞争条件发生在当多个进程或者线程在读写数据时，其最终的的结果依赖于多个进程的指令执行顺序。
   *
   * 活跃度失败
   *    - 死锁
   *    - 饥饿
   *    - 活锁
   *
   * 上下文切换
   *    - 保存并恢复线程执行的上下文，离开执行现场
   *
   * 状态
   *    - 包括任何会对它外部可见行为产生影响的数据
   *
   * 线程安全
   *    - 当多个线程访问一个类的时候，如果不用考虑这些线程在运行时环境下的调度和交替执行，且不需要额外的同步及在调用代码方不需做
   *    任何的协调，这个类和行为仍然是正确的，那么称这个类是线程安全的。
   *
   * Ad-hoc(非正式)线程限制
   *    - 指维护线程限制性的任务全部落在实现上的这种情况。没有用到同步关键字。
   *
   * final保证对象不需要同步就能自由地被访问和共享。
   *
   * 线程安全的容器
   *    - HashTable,synchronizedMap,ConcurrentMap
   *    - Vector,CopyOnWriteArrayList,SynchronizedList,SynchronizedSet
   *    - BlockingQueue,ConcurrentLinkedQueue
   *
   * 共享对象
   *    - 线程限制 线程独占，只能被占有它的线程修改
   *    - 共享只读
   *    - 共享线程安全 一个线程安全的对象在内部进行同步
   *    - 被守护的 一个被守护的对象只能通过特定的锁来访问
   */
  
}
