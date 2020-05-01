package netty.chap2.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOServerHandler implements Runnable {
	
	private int port;
	public CountDownLatch latch;
	public AsynchronousServerSocketChannel asyhChannel;
	
	public AIOServerHandler(int port) {
		this.port = port;
		try {
			asyhChannel = AsynchronousServerSocketChannel.open();
			asyhChannel.bind(new InetSocketAddress(port));
			System.out.println("server init " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try{
			latch.await();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void doAccept() {
		asyhChannel.accept(this, new AIOAcceptCompletionHandler());
	}
}
