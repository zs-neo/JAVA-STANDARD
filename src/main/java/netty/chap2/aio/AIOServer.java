package netty.chap2.aio;

public class AIOServer {
	
	public static void main(String[] args) {
		int port = 8080;
		new Thread(new AIOServerHandler(port)).start();
	}
	
}
