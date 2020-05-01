package netty.chap45678.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestUserResq {
	
	private static byte[] encode(UserResp.SubscribeResp req) {
		return req.toByteArray();
	}
	
	private static UserResp.SubscribeResp decode(byte[] body) throws InvalidProtocolBufferException {
		return UserResp.SubscribeResp.parseFrom(body);
	}
	
	private static UserResp.SubscribeResp createSubscribeReq() {
		UserResp.SubscribeResp.Builder builder = UserResp.SubscribeResp.newBuilder();
		builder.setSubResqID(1);
		builder.setResqCode(1);
		builder.setDesc("123");
		return builder.build();
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		UserResp.SubscribeResp req = createSubscribeReq();
		System.out.println("first " + req.toString());
		UserResp.SubscribeResp req2 = decode(encode(req));
		System.out.println("now " + req2.toString());
	}
	
}
