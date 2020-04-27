package internet;

import java.io.*;
import java.net.Socket;

public class Client {
  
  
  public static void main(String[] args) throws IOException {
    int port = 7000;
    String host = "127.0.0.1";
    Socket socket = new Socket(host, port);
    
    OutputStream out = socket.getOutputStream();
    byte[] bytes = new byte[1024];
    int len = 0;
    while (true) {
      out.write("hello".getBytes());
      out.flush();
    }
  }
  
}
