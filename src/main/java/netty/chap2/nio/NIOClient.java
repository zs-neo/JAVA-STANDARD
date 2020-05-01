package netty.chap2.nio;

public class NIOClient {
	
	public static void main(String[] args) {
		int port = 8080;
		new Thread(new NIOClientHandle("127.0.0.1", port), "client").start();
	}
	
}
