package standard;

public class DeadLock {
  
  /**
   * 一个线程永远占有一个锁，而其他线程尝试去获取这个锁那么它们将永远被阻塞。
   *
   * 数据库通过正在等待有向图上搜索环，会选择一个牺牲者使他退出事务，从而解除死锁。应用程序可以重新执行这个牺牲者。
   *
   * public void leftRight(){
   *     synchronized(left){
   *         synchronized(right){
   *             dosomething();
   *         }
   *     }
   * }
   * public void leftRight(){
   *      synchronized(left){
   *          synchronized(right){
   *              dosomething();
   *          }
   *      }
   * }
   *
   * 线程饥饿死锁：有界池和相互依赖的任务不能放在一起
   *
   * tryLock
   *
   * 线程转储
   *    - 包括每个运行中的线程的栈追踪信息
   *    - 在is-waiting-for关系的有向图中搜索循环来寻找死锁
   *
   * 饥饿
   *    - 当线程访问它所需要的资源时被永久拒绝
   *    - CPU周期
   *    - 线程优先级不当
   *    - 在锁中执行无终止的构建(无限循环，无限等待资源)
   *
   * 活锁
   *    - 尽管没有被阻塞线程却仍然不能继续，因为它不断重试却总是失败。
   *    - 或者发生在多个相互协作的线程间，当它们为了彼此响应而修改了状态。
   *    - 就好比两个有礼貌的人相遇，它们都避开对方的路，却又在别的路上相遇，就这样一直避让下去。
   *    - 通常发生在消息处理应用中
   *        - 消息处理失败传递消息的底层架构会回退整个事务，并把它置回队首
   *        - 如果程序对某个消息总是处理错误，那么，就会这个消息就会被从队列中取出传递到存在问题的处理器
   *        - 事务回退
   *        - 消息回到队首
   *        - 重复
   *
   */
  
}
