package netty.chap2.aio;

public class AIOClient {
	
	public static void main(String[] args) {
		int port = 8080;
		new Thread(new AIOClientHandler(port,"127.0.0.1")).start();
	}
	
}
