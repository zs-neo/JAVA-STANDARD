package netty.chap45678.tcpsticksolved;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyClientHandler extends ChannelHandlerAdapter {
	
	private byte[] req;
	
	public NettyClientHandler() {
		//tcp粘包解决方案一：LineBasedFrameDecoder,它依次遍历bytebuf中的可读字节判断是否有'\n'或'\r\'
//		req = ("QUERY" + System.getProperty("line.separator")).getBytes();
		//tcp粘包解决方法二：DelimiterBasedFrameDecoder,手动添加分隔符
		req = ("QUERY" + "$_").getBytes();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for (int i = 0; i < 1000; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("client reveive " + body);
	}
}
