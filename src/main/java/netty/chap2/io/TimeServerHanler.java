package netty.chap2.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHanler implements Runnable {
	
	private Socket socket;
	
	public TimeServerHanler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream());
			String body = null;
			while (true) {
				body = in.readLine();
				System.out.println(body);
				if (body == null) {
					break;
				}
			}
		} catch (Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ioe1) {
					//exception
				}
			}
			if (out != null) {
				out.close();
				out = null;
			}
			if (this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException ioe2) {
					//exception
				}
				this.socket = null;
			}
		}
	}
}
