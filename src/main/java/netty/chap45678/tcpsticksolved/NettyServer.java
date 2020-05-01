package netty.chap45678.tcpsticksolved;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		new NettyServer().bind(port);
	}
	
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChildChannelHandler());
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel socketChannel) throws Exception {
			//tcp粘包解决方案一：LineBasedFrameDecoder,它依次遍历bytebuf中的可读字节判断是否有'\n'或'\r\'
//							socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
			
			//tcp粘包解决方法二：DelimiterBasedFrameDecoder,手动添加分隔符
			ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
			socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
			
			//tcp粘包解决方法三：FixedLengthFrameDecoder定长解码器
			socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
			
			socketChannel.pipeline().addLast(new StringDecoder());
			socketChannel.pipeline().addLast(new NettyServerHandler());
		}
	}
	
}
