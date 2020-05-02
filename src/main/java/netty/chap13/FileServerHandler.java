package netty.chap13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.*;

public class FileServerHandler extends SimpleChannelInboundHandler<String> {
	
	private static final String CR = System.getProperty("line.separator");
	
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
		File file = new File(s);
		if (file.exists()) {
			if (!file.isFile()) {
				channelHandlerContext.writeAndFlush("Not a file " + file + CR);
				return;
			}
			channelHandlerContext.write("success!" + file);
			RandomAccessFile randomAccessFile = new RandomAccessFile(s, "r");
			FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
			channelHandlerContext.write(region);
			channelHandlerContext.writeAndFlush(CR);
			randomAccessFile.close();
			return;
		} else {
			channelHandlerContext.writeAndFlush("error" + file + CR);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
