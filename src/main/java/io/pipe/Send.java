package io.pipe;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;

public class Send implements Runnable {
  
  private PipedOutputStream pipedOutputStream;
  
  public Send() {
    this.pipedOutputStream = new PipedOutputStream();
  }
  
  public PipedOutputStream getPipedOutputStream() {
    return pipedOutputStream;
  }
  
  @Override
  public void run() {
    String msg = "hi";
    try{
      pipedOutputStream.write(msg.getBytes());
      pipedOutputStream.close();
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
