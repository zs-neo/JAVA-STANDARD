package internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
  
  
  private static ExecutorService exec = Executors.newFixedThreadPool(10);
  
  private final static AtomicInteger i = new AtomicInteger(0);
  
  public static void main(String[] args) throws IOException {
    int port = 7000;
    ServerSocket socket = new ServerSocket(port);
    StringBuilder stringBuilder = new StringBuilder();
    byte[] bytes = new byte[1024];
    int len = 0;
    while (true) {
      Socket con = socket.accept();
      InputStream in = con.getInputStream();
      exec.submit(new Runnable() {
        @Override
        public void run() {
          try {
            int len = in.read(bytes);
            stringBuilder.append(new String(bytes, 0, len, "UTF-8"));
            System.out.println(stringBuilder);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
    }
    //回收
  }
  
}
