package nju.edu.server;

import nju.edu.Hello;
import nju.edu.impl.HelloServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author luxiangfan
 * @date 2016-11-07, 17:12
 */
public class HelloServiceServer {
	/**
	 * 启动 Thrift 服务器
	 */
	public static void main(String[] args) {
		try {
			// 设置服务端口为 7911
			TServerSocket serverTransport = new TServerSocket(7911);

			// 设置协议工厂为 TBinaryProtocol.Factory
			Factory factory = new TBinaryProtocol.Factory();

			// 关联处理器与 Hello 服务的实现
			TProcessor processor = new Hello.Processor(new HelloServiceImpl());

			// 将上面的配置设置到服务参数中
			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
			tArgs.protocolFactory(factory);
			tArgs.processor(processor);
			TServer server = new TThreadPoolServer(tArgs);

			System.out.println("Start server on port 7911...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}