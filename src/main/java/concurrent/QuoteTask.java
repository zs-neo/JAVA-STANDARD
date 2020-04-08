package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class QuoteTask implements Callable<String> {
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(100);
	
	public String call() throws Exception {
		return "price";
	}
	
	public List<String> getList(long time,TimeUnit unit){
		List<String> res = new ArrayList<String>();
//		List<Future<String>> futures = exec.invokeAll(res,time,unit);
		return res;
	}
}
