package concurrent.excutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
  
  private static final int NTHREADS = 100;
  private static final int PORT = 80;
  private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
//  private static final Executor exec = Executors.newCachedThreadPool();
//  private static final Executor exec = Executors.newScheduledThreadPool(NTHREADS);
//  private static final Executor exec = Executors.newSingleThreadExecutor();
//  private static final Executor exec = Executors.unconfigurableExecutorService(new ExecutorService());
  
  protected static void handleRequest(Socket con) {
  }
  
  public static void main(String[] args) throws IOException {
    ServerSocket socket = new ServerSocket(PORT);
    while(true){
      final Socket con = socket.accept();
      Runnable task = new Runnable() {
        public void run() {
          handleRequest(con);
        }
      };
      exec.execute(task);
    }
  }
  
}
