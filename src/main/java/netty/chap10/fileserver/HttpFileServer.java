package netty.chap10.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
	
	private static final String DEFAULT_URL = "/src/com/phei/netty/";
	
	public void run(final int port, final String url) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
							channel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
							channel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
							channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							channel.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
						}
					});
			ChannelFuture future = bootstrap.bind("127.0.0.1", port).sync();
			System.out.println("Server init!");
			future.channel().closeFuture();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		String url = "/test";
		new HttpFileServer().run(port, url);
	}
	
}
