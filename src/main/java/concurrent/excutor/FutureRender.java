package concurrent.excutor;

import concurrent.excutor.entity.ImageData;
import concurrent.excutor.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureRender {
  
  private final ExecutorService exector = Executors.newSingleThreadExecutor();
  
  void renderPage(CharSequence source) {
    final List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
    Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
      public List<ImageData> call() throws Exception {
        List<ImageData> result = new ArrayList<ImageData>();
        //此处语法糖注意,应尽量避免使用,仅展示逻辑
        for (ImageData imageInfo : result) {
          result.add(imageInfo);
        }
        return result;
      }
    };
    Future<List<ImageData>> future = exector.submit(task);
    //do somthing
    try {
      List<ImageData> imageDatas = future.get();
      for (ImageData imageData : imageDatas) {
        //do something
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      future.cancel(true);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
  
}
