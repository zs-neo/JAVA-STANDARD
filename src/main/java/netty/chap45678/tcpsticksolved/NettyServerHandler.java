package netty.chap45678.tcpsticksolved;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyServerHandler extends ChannelHandlerAdapter {
	
	private int count = 0;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		count++;
		System.out.println("The time server receive order : " + body + count);
		ByteBuf resp = Unpooled.copiedBuffer("RESPONSE!".getBytes());
		ctx.write(resp);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
}
