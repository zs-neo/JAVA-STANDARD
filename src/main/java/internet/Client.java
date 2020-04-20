package internet;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
  
  private ExecutorService exec = Executors.newFixedThreadPool(10);
  
  public static void main(String[] args) throws IOException {
    int port = 7000;
    String host = "127.0.0.1";
    Socket socket = new Socket(host, port);
    
    InputStream in = socket.getInputStream();
    StringBuilder stringBuilder = new StringBuilder();
    byte[] bytes = new byte[1024];
    int len = 0;
    while ((len = in.read(bytes)) != -1) {
      stringBuilder.append(new String(bytes,0,len,"UTF-8"));
    }
    System.out.println("get "+stringBuilder);
    socket.close();
  }
  
}
