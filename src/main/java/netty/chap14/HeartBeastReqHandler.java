package netty.chap14;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;
import netty.chap14.bean.Header;
import netty.chap14.bean.NettyMessage;

import java.util.concurrent.TimeUnit;

public class HeartBeastReqHandler extends ChannelHandlerAdapter {
	
	private volatile ScheduledFuture<?> heartBeat;
	
	private class HearBeatTask implements Runnable {
		private final ChannelHandlerContext ctx;
		
		public HearBeatTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}
		
		@Override
		public void run() {
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("client send " + heartBeat.toString());
			ctx.writeAndFlush(heartBeat);
		}
		
		private NettyMessage buildHeartBeat() {
			NettyMessage message = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_REQ.value());
			message.setHeader(header);
			return message;
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (heartBeat != null) {
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeastReqHandler.HearBeatTask(ctx),
					0, 5000, TimeUnit.MILLISECONDS);
		} else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
			System.out.println("client receive " + message);
		} else {
			ctx.fireChannelRead(msg);
		}
	}
}
