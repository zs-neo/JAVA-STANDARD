package concurrent.excutor;

import java.util.concurrent.Executor;

public class WithinThreadExecutor implements Executor {
  
  public void execute(Runnable command) {
    command.run();
  }
  
}
