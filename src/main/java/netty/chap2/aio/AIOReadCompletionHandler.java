package netty.chap2.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	
	private AsynchronousSocketChannel asynChannel;
	
	public AIOReadCompletionHandler(AsynchronousSocketChannel asynChannel) {
		if (this.asynChannel == null) {
			this.asynChannel = asynChannel;
		}
	}
	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		try {
			String req = new String(body, "UTF-8");
			System.out.println("server receive " + req);
			doWrite("ResponseFromServer!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			asynChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void doWrite(String body) {
		byte[] bytes = body.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		asynChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (writeBuffer.hasRemaining()) {
					asynChannel.write(attachment, attachment, this);
				}
			}
			
			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					asynChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
