package concurrent;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class CachedFactorizer implements Servlet {
  
  private BigInteger lastNumber;
  private BigInteger[] lastFactors;
  private long hits;
  private long cacheHits;
  
  public synchronized long getHits(){
    return hits;
  }
  
  public synchronized double getCacheHitRatio(){
    return 0.0;
  }
  
  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    BigInteger i = new BigInteger("123");
    BigInteger[] factors = null;
    synchronized (this){
      ++hits;
      if(i.equals(lastNumber)){
        ++cacheHits;
        factors = lastFactors.clone();
      }
    }
    if(factors == null){
      //重新获取factors
      //factors = getFactors(...);
      synchronized (this){
        lastNumber = i;
        lastFactors = factors.clone();
      }
    }
    //do something
  }
  
  @Override
  public void init(ServletConfig servletConfig) throws ServletException {
  
  }
  
  @Override
  public ServletConfig getServletConfig() {
    return null;
  }
  
  @Override
  public String getServletInfo() {
    return null;
  }
  
  @Override
  public void destroy() {
  
  }
  
}
