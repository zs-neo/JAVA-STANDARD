package netty.chap2.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOClientHandler implements CompletionHandler<Void, AIOClientHandler>, Runnable {
	
	private int port;
	private String host;
	private AsynchronousSocketChannel client;
	private CountDownLatch latch;
	
	public AIOClientHandler(int port, String host) {
		this.port = port;
		this.host = host;
		try {
			client = AsynchronousSocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		client.connect(new InetSocketAddress(host, port), this, this);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void completed(Void result, AIOClientHandler attachment) {
		byte[] req = "QUERY!".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (attachment.hasRemaining()) {
					client.write(attachment, attachment, this);
				} else {
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							attachment.flip();
							byte[] bytes = new byte[attachment.remaining()];
							attachment.get(bytes);
							String body;
							try {
								body = new String(bytes, "UTF-8");
								System.out.println("client receive " + body);
								latch.countDown();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
						
						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							try {
								client.close();
							} catch (IOException e) {
								//ignore
							}finally {
								latch.countDown();
							}
						}
					});
				}
			}
			
			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					client.close();
					latch.countDown();
				} catch (IOException e) {
					//ignore
				}
			}
		});
	}
	
	@Override
	public void failed(Throwable exc, AIOClientHandler attachment) {
		try {
			client.close();
		} catch (IOException e) {
			//ignore
		} finally {
			latch.countDown();
		}
	}
}
