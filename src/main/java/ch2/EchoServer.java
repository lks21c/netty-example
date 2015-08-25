package ch2;

import java.net.InetSocketAddress;


/**
 * Created by lks21c on 15. 8. 25.
 */
public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
		}
		int port = Integer.parseInt(args[0]); // 1
		new EchoServer(port).start(); // 2
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup(); // 3
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group) // 4
					.channel(NioServerSocketChannel.class) // 5
					.localAddress(new InetSocketAddress(port)) // 6
					.childHandler(new ChannelInitializer<SocketChannel>() {// 7
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
			ChannelFuture f = b.bind().sync(); // 8
			f.channel().closeFuture().sync(); // 9
		} finally {
			group.shutdownGracefully().sync(); // 10
		}
	}
}