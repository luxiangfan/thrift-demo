package nju.edu.server;

import nju.edu.Hello;
import nju.edu.impl.HelloServiceImpl;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author luxiangfan
 * @date 2016-11-07, 17:33
 * <p>
 * 通过 java.nio.channels.ServerSocketChannel 创建非阻塞的服务器端等待客户端的连接
 */
public class HelloServiceAsyncServer {
	/**
	 * 启动 Thrift 异步服务器
	 */
	public static void main(String[] args) {
		TNonblockingServerTransport serverTransport;
		try {
			serverTransport = new TNonblockingServerSocket(10005);
			Hello.Processor processor = new Hello.Processor(new HelloServiceImpl());

			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
			tArgs.processor(processor);
			TServer server = new TNonblockingServer(tArgs);

			System.out.println("Start server on port 10005 ...");

			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
