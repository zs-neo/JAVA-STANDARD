package concurrent.excutor;

import concurrent.excutor.entity.Ad;
import concurrent.excutor.entity.ImageData;
import concurrent.excutor.entity.ImageInfo;
import concurrent.excutor.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Render {
  
  private final ExecutorService executorService;
  
  public Render(ExecutorService executorService) {
    this.executorService = executorService;
  }
  
  /**
   * Executor框架使用Runnable作为其任务的基本表达形式。但是它不能够返回一个值或者抛出受检查的异常。
   * Callable适用于有延迟的任务，在主进入点等待返回值，并为可能抛出的异常预先做好了准备。
   * 一个Executor执行的任务的生命周期有四个阶段：创建-提交-开始-完成。
   * Future描述了任务的生命周期，并提供了相关的方法来获得任务的结果，取消任务以及检验任务是否已经完成还是被取消。
   * 任务的状态决定了get方法的行为
   * 如果任务已经完成，get会立即抛出一个Exception，如果任务没有完成get会阻塞直到它完成。
   * 如果任务抛出了异常，get会封装为ExecutionException然后重新抛出。可以用getCause()方法获取到被封装的原始异常。
   * 如果任务被取消，get会抛出CancellationException。
   *
   * @param source
   */
  void renderPage(CharSequence source) {
    final List<ImageInfo> infos = new ArrayList<ImageInfo>();
    /**
     * CompletionService整合了Executor和阻塞队列，可以用take或者poll方法，在结果完整的时候获得这个结果。
     * ExecutorCompletionService是实现CompletionService接口的一个类，并将计算委托给一个Executor。
     *    在构造函数里创建一个阻塞队列保存完成时的结果，计算完成的时候调用FutureTask中的done方法。
     *    提交一个任务后，首先把这个任务包装为QueueingFuture（FutureTask的一个子类）然后覆写done方法。
     *    将结果置入阻塞队列中，它会在结果不可用时阻塞。
     */
    CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
    for (final ImageInfo imageInfo : infos) {
      completionService.submit(new Callable<ImageData>() {
        public ImageData call() throws Exception {
          return imageInfo.getImageData();
        }
      });
    }
    //do something
    try {
      for (int i = 0; i < infos.size(); i++) {
        Future<ImageData> f = completionService.take();
        ImageData imageData = f.get();
        //do something
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
  
  private static final long TIME_BUDGET = 10;
  private static final Ad DEFAULT_AD = new Ad();
  
  Page renderPageWithAd() throws InterruptedException {
    long endNanos = System.nanoTime() + TIME_BUDGET;
    Callable<Ad> task = new Callable<Ad>() {
      public Ad call() throws Exception {
        Ad result = new Ad();
        return result;
      }
    };
    Future<Ad> future = executorService.submit(task);
    Ad ad;
    try {
      long timeLeft = endNanos - System.nanoTime();
      ad = future.get(timeLeft, TimeUnit.NANOSECONDS);
    } catch (ExecutionException e) {
      ad = DEFAULT_AD;
    } catch (TimeoutException e) {
      ad = DEFAULT_AD;
      future.cancel(true);
    }
    Page page = new Page();
    page.setAd(ad);
    return page;
  }
}
