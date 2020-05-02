package netty.chap12;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	
	private static final String[] STRING = {"xxx", "阿达", "额发放时"};
	
	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(STRING.length);
		return STRING[quoteId];
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
		String req = datagramPacket.content().toString();
		System.out.println(req);
		channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("结果" + nextQuote(),
				CharsetUtil.UTF_8), datagramPacket.sender()));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}
	
}
