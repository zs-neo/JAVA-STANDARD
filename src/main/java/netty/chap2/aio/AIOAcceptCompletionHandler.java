package netty.chap2.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerHandler> {
	
	@Override
	public void completed(AsynchronousSocketChannel result, AIOServerHandler attachment) {
		attachment.asyhChannel.accept(attachment, this);
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		result.read(byteBuffer, byteBuffer, new AIOReadCompletionHandler(result));
	}
	
	@Override
	public void failed(Throwable exc, AIOServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}
}
