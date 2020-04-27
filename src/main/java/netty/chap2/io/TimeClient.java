package netty.chap2.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TimeClient {
	
	public static void main(String[] args) {
		int port = 8080;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", port);
			//读写操作都是阻塞的 取决于对方io线程的处理速度和网络io的传输速度
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			int i = 0;
			while (i++ < 10) {
				out.println("QUERY! HELLO!");
				out.flush();
				TimeUnit.SECONDS.sleep(1);
			}
			String resp = in.readLine();
			System.out.println(resp);
		} catch (Exception e) {
			//handle
		} finally {
		
		}
	}
	
}
