package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
  
  /**
   *  - new CachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
   *  - new FixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
   *  - new ScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
   *  - new SingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
   *
   * int corePoolSize, 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收
   * int maximumPoolSize, 线程数的上限
   * long keepAliveTime, 超过corePoolSize的线程的idle时长，超过这个时间，多余的线程会被回收(闲置线程存活时间)
   * TimeUnit unit, 时间单元
   * BlockingQueue<Runnable> workQueue 任务队列策略
   *    - ArrayBlockingQueue 基于数组的有界阻塞队列，按FIFO排序。
   *        新任务进来后，会放到该队列的队尾，有界的数组可以防止资源耗尽问题。
   *        当线程池中线程数量达到corePoolSize后，再有新任务进来，则会将任务放入该队列的队尾，等待被调度。
   *        如果队列已经是满的，则创建一个新线程，如果线程数量已经达到maxPoolSize，则会执行拒绝策略。
   *    - LinkedBlockingQuene 基于链表的无界阻塞队列（其实最大容量为Interger.MAX）
   *        按照FIFO排序。
   *        由于该队列的近似无界性，当线程池中线程数量达到corePoolSize后，再有新任务进来，会一直存入该队列，而不会去创建新线程直到maxPoolSize，因此使用该工作队列时，参数maxPoolSize其实是不起作用的。
   *    - SynchronousQuene
   *        一个不缓存任务的阻塞队列，生产者放入一个任务必须等到消费者取出这个任务。
   *        也就是说新任务进来时，不会缓存，而是直接被调度执行该任务，如果没有可用线程，则创建新线程，如果线程数量达到maxPoolSize，则执行拒绝策略。
   *    - PriorityBlockingQueue 具有优先级的无界阻塞队列，优先级通过参数Comparator实现。
   * ThreadFactory threadFactory 新线程的产生方式
   * RejectedExecutionHandler handler 拒绝策略
   *    - AbortPolicy 直接丢弃任务，并抛出RejectedExecutionException异常。
   *    - CallerRunsPolicy 在调用者线程中直接执行被拒绝任务的run方法
   *    - DiscardPolicy 直接丢掉，不抛异常。
   *    - DiscardOldestPolicy 抛弃进入队列最早的那个任务，然后尝试把这次拒绝的任务放入队列
   */
  public ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 0, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
  
}
