package standard;

public class Standard {
	
	private String data;
	protected String protectedData;
	protected static String staticData;
	
	public Standard(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public static void inc(int i){
	  i++;
  }
  
  public static void main(String[] args) {
    int i = 0;
    inc(i);inc(i);
    System.out.println(i);
  }
	
}
/**
 * overload（重载）
 * 参数类型、个数、顺序至少有一个不相同。
 * 不能重载只有返回值不同的方法名。
 * 存在于父类和子类、同类中。
 *
 * override（重写）
 * 方法名、参数、返回值相同。
 * 子类方法不能缩小父类方法的访问权限。
 * 子类方法不能抛出比父类方法更多的异常(但子类方法可以不抛出异常)。
 * 存在于父类和子类之间。
 * 方法被定义为final不能被重写。
 *
 * 线程可能因为一些原因被阻塞或暂停：
 *    - 等待IO
 *    - 等待获取一个锁
 *    - 等待从sleep中唤醒
 *    - 等待另一个线程的结果
 *
 * 当线程被阻塞的时候经常被设置成线程阻塞的某个状态
 *    - BLOCKED
 *    - WAITING
 *    - TIMED_WAITING
 *
 * 中断处理方案
 *    - 传递InterruptedException
 *    - 恢复中断
 *    class taskRunnable implemmts Runnable{
 *        BlockingQueue<Task> queue;
 *        public void run(){
 *            try{
 *                processTask(queue.take());
 *            }catch(InterruptedException e){
 *                //恢复中断状态
 *                Thread.currentThread().interrupt();
 *            }
 *        }
 *    }
 *
 * Synchronized依据本身的状态调节线程的控制流。
 *    - 封装状态，这些状态决定着线程执行到某一点时是通过还是等待
 *    - 提供操作的方法以及高效地等待Synchronized进入到期望状态的方法
 *
 * Synchronizer
 *    - latch闭锁是一种Synchronizer，它可以延迟线程的进度到达终止
 *    - CountDownLatch是一个灵活的闭锁实现，计数已经完成的任务
 *    - FutureTask描述了一个抽象的可携带结果的计算
 *    - Counting semaphore 线程从许可集中获取可用许可，如果没有了就不给许可，等待
 *    - barrier 所有线程必须同时到达关卡才能被解除
 *
 * SpeedUp <= 1/(F+(1-F)/N)),其中F是必须串行化执行的比重，在一个N处理器的机器中。
 *
 * 单个任务的处理时间不仅包括任务的处理时间，也包括从队列中取出任务的时间。
 *
 * little定律的结论是"稳定的系统中顾客的平均数量等于他们的平均到达率乘以他们在系统中平均的停留时间"
 *
 * readWriteLock
 *
 *
 */
