package netty.chap2.nio;

public class NIOServer {
	
	public static void main(String[] args) {
		int port = 8080;
		new Thread(new NIOMultiplexerServer(port), "nio-1").start();
	}
	
}
