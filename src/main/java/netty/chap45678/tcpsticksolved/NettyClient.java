package netty.chap45678.tcpsticksolved;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClient {
	
	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							//tcp粘包解决方案一：LineBasedFrameDecoder,它依次遍历bytebuf中的可读字节判断是否有'\n'或'\r\'
//							socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
							
							//tcp粘包解决方法二：DelimiterBasedFrameDecoder,手动添加分隔符
//							ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//							socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
							
							//tcp粘包解决方法三：FixedLengthFrameDecoder定长解码器
							socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(5));
							
							socketChannel.pipeline().addLast(new StringDecoder());
							socketChannel.pipeline().addLast(new NettyClientHandler());
						}
					});
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		new NettyClient().connect(port, "127.0.0.1");
	}
	
}
