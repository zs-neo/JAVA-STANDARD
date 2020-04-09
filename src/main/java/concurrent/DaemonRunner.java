package concurrent;

import java.util.Scanner;

public class DaemonRunner implements Runnable {
  
  @Override
  public void run() {
    while(true){
      for(int i=0;i<100;i++){
        System.out.println(i);
        try{
          Thread.sleep(1000);
        }catch (InterruptedException e){
          e.printStackTrace();
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Thread daemonThread = new Thread(new DaemonRunner());
    daemonThread.setDaemon(true);
    daemonThread.start();
    Scanner sc = new Scanner(System.in);
    sc.next();
    Runtime.getRuntime().addShutdownHook(new Thread(){
      @Override
      public void run(){
        System.out.println("JVM exit");
      }
    });
  }
}
