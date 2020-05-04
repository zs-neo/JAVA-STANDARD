package netty.chap14.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {
	
	private Unmarshaller unmarshaller;
	
	public MarshallingDecoder() throws IOException {
		this.unmarshaller = MarshallingCodecFactory.buildUnmarshaller();
	}
	
	protected Object decode(ByteBuf in) throws Exception {
		int objectSize = in.readInt();
		ByteBuf buf = in.slice(in.readerIndex(), objectSize);
		ByteInput input = new ChannelBufferByteInput(buf);
		try {
			unmarshaller.start(input);
			Object object = unmarshaller.readObject();
			unmarshaller.finish();
			in.readerIndex(in.readerIndex() + objectSize);
			return object;
		} finally {
			unmarshaller.close();
		}
	}
}
