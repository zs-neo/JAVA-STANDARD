package netty.chap2.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080;
		ServerSocket server = null;
		try {
//			server = new ServerSocket(port);
//			while (true) {
//				Socket socket = server.accept();
//				new Thread(new TimeServerHanler(socket)).start();
//			}
			server = new ServerSocket(port);
			TimeServerHanlerExecutePool pooledServer = new TimeServerHanlerExecutePool(50,10000);
			while (true) {
				Socket socket = server.accept();
				pooledServer.execute(new TimeServerHanler(socket));
			}
		} finally {
			if (server != null) {
				server.close();
			}
		}
	}
	
}
