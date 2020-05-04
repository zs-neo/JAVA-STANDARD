package netty.chap14.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

public class MarshallingCodecFactory {
	
	public static Marshaller buildMarshaller() throws IOException {
		final MarshallerFactory marshallerFactory = Marshalling
				.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		Marshaller marshaller = marshallerFactory
				.createMarshaller(configuration);
		return marshaller;
	}
	
	public static Unmarshaller buildUnmarshaller() throws IOException {
		final MarshallerFactory marshallerFactory = Marshalling
				.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		Unmarshaller unmarshaller = marshallerFactory
				.createUnmarshaller(configuration);
		return unmarshaller;
	}
	
}
