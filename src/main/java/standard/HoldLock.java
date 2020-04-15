package standard;

import java.util.HashMap;
import java.util.Map;

public class HoldLock {
  
  private final Map<String, String> map = new HashMap<>();
  
  /**
   * 其实需要同步的只有get方法，持有锁的时间过长了。
   *
   * @param name
   * @param regexp
   * @return
   */
  public synchronized boolean match(String name, String regexp) {
    String key = name = regexp;
    String location = map.get(key);
    if (location == null) {
      return false;
    } else {
      return true;
    }
  }
  
  public boolean matchProv(String name, String regexp) {
    String key = name = regexp;
    String location;
    synchronized (this) {
      location = map.get(key);
    }
    if (location == null) {
      return false;
    } else {
      return true;
    }
  }
  
}
