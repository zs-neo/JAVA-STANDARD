package io.pipe;

import java.io.IOException;

public class Connect {
  
  public static void main(String[] args) {
    Send send = new Send();
    Recieve recieve = new Recieve();
    try{
      send.getPipedOutputStream().connect(recieve.getInput());
    }catch (IOException e){
      e.printStackTrace();
    }
    new Thread(send).start();
    new Thread(recieve).start();
  }
  
}
