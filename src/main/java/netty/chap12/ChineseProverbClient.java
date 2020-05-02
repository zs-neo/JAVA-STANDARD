package netty.chap12;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChineseProverbClient {
	
	public void run(int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new ChineseProverbClientHandler());
			b.bind("10.0.2.2",port).sync().channel().closeFuture().await();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new ChineseProverbClient().run(8080);
	}
	
}
