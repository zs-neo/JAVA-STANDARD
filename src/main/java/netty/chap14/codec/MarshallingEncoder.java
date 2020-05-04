package netty.chap14.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

public class MarshallingEncoder {
	
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	Marshaller marshaller;
	
	public MarshallingEncoder() throws IOException {
		this.marshaller = MarshallingCodecFactory.buildMarshaller();
	}
	
	protected void encode(Object msg, ByteBuf out) throws Exception {
		try {
			int length = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);
			ChannelBufferByteOutput outBuffer = new ChannelBufferByteOutput(out);
			marshaller.start(outBuffer);
			marshaller.writeObject(msg);
			marshaller.finish();
			out.setInt(length, out.writerIndex() - length - 4);
		} finally {
			marshaller.close();
		}
	}
}
