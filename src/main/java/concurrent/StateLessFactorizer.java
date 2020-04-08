package concurrent;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class StateLessFactorizer implements Servlet {
	
	private long count = 0;
	
	private final AtomicInteger c = new AtomicInteger(0);
	
	private static final Executor exec = Executors.newFixedThreadPool(100);
	
	public static void main(String[] args) throws IOException{
		ServerSocket socket = new ServerSocket(80);
		while(true){
			final Socket con = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					//...
				}
			};
			exec.execute(task);
		}
	}
	
	public void init(ServletConfig servletConfig) throws ServletException {
		c.decrementAndGet();
		c.incrementAndGet();
	}
	
	public ServletConfig getServletConfig() {
		return null;
	}
	
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
	
	}
	
	public String getServletInfo() {
		return null;
	}
	
	public void destroy() {
	
	}
	
	private final ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	void renderPage(CharSequence source){
		Callable<List<String>> task = new Callable<List<String>>() {
			public List<String> call() throws Exception {
				List<String> res = new ArrayList<String>();
				for(String a : res){
					res.add(a);
				}
				return res;
			}
		};
		Future<List<String>> future = executorService.submit(task);
		//...
		try{
			List<String> a = future.get();
		}catch (InterruptedException e){
			Thread.currentThread().interrupt();
			future.cancel(true);
			e.printStackTrace();
		}catch (ExecutionException e){
			e.printStackTrace();
		}
	}
	
	void timeout(){
//		Future<String> f = executorService.submit(new Callable<String>() {
//			public String call() throws Exception {
//				return null;
//			}
//		});
//		try{
//			long timeleft = 1000;
//			ad = f.get(timeleft,TimeUnit.NANOSECONDS);
//		}catch (TimeoutException e){
//			e.printStackTrace();
//			ad = defaultAd;
//			f.cancel(true);
//		}
	}
	
	
}
