package concurrent.excutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LifeCycleWebServer {
  
  private final ExecutorService exec = Executors.newSingleThreadExecutor();
  private static final int PORT = 80;
  
  protected static void handleRequest(Socket con) {
  }
  
  public void start() throws IOException {
    ServerSocket socket = new ServerSocket(PORT);
    while(!exec.isShutdown()){
      try{
        final Socket con = socket.accept();
        exec.execute(new Runnable() {
          public void run() {
            handleRequest(con);
          }
        });
      }catch (RejectedExecutionException e){
        e.printStackTrace();
        if(!exec.isShutdown()){
          //...
        }
      }
    }
  }
  
  public void stop(){
    exec.shutdown();
  }
  
  static class ThrowTask extends TimerTask {
    @Override
    public void run() {
      throw new RuntimeException();
    }
  }
  
  public static void main(String[] args) throws Exception{
    //使用ScheduledExecutorService代替Timer吧
    Timer timer = new Timer();
    timer.schedule(new ThrowTask(),1);
    SECONDS.sleep(1);
    timer.schedule(new ThrowTask(),1);
    SECONDS.sleep(5);
  }
  
}
