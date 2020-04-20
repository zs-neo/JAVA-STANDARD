package internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
  public static void main(String[] args) throws IOException {
    int port = 7000;
    ServerSocket socket = new ServerSocket(port);
    Socket con = socket.accept();
    
    while(true){
      String msg = "hello!";
      OutputStream out = con.getOutputStream();
      out.write(msg.getBytes());
    }
    //回收
  }
  
}
