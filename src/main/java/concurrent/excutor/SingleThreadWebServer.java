package concurrent.excutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebServer {
  
  protected static void handleRequest(Socket con) {
  
  }

//  public static void main(String[] args) throws IOException {
//    ServerSocket socket = new ServerSocket(80);
//    while (true) {
//      Socket con = socket.accept();
//      handleRequest(con);
//    }
//  }
  
  public static void main(String[] args) throws IOException {
    ServerSocket socket = new ServerSocket(80);
    while (true) {
      final Socket con = socket.accept();
      Runnable task = new Runnable() {
        public void run() {
          handleRequest(con);
        }
      };
      new Thread(task).start();
    }
  }
  
}
