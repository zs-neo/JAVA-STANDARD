package netty.chap14.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

public class ChannelBufferByteOutput implements ByteOutput {
	
	private final ByteBuf byteBuf;
	
	public ChannelBufferByteOutput(ByteBuf byteBuf) {
		this.byteBuf = byteBuf;
	}
	
	@Override
	public void write(int i) throws IOException {
		byteBuf.writeByte(i);
	}
	
	@Override
	public void write(byte[] bytes) throws IOException {
		byteBuf.writeBytes(bytes);
	}
	
	@Override
	public void write(byte[] bytes, int i, int j) throws IOException {
		byteBuf.writeBytes(bytes, i, j);
	}
	
	@Override
	public void close() throws IOException {
	
	}
	
	@Override
	public void flush() throws IOException {
	
	}
	
	public ByteBuf getByteBuf() {
		return byteBuf;
	}
}
