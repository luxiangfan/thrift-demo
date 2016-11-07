package nju.edu.client;

import nju.edu.Hello;
import nju.edu.callback.MethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;

/**
 * @author luxiangfan
 * @date 2016-11-07, 17:34
 * <p>
 * 通过 java.nio.channels.Socketchannel 创建异步客户端与服务器建立连接
 */
public class HelloServiceAsyncClient {
	/**
	 * 调用 Hello 服务
	 */
	public static void main(String[] args) throws Exception {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket("localhost", 10005);
			TProtocolFactory protocol = new TBinaryProtocol.Factory();
			Hello.AsyncClient asyncClient = new Hello.AsyncClient(protocol, clientManager, transport);
			System.out.println("Client calls .....");

			MethodCallback callBack = new MethodCallback();
			asyncClient.helloString("Hello World", callBack);

			Object res = callBack.getResult();
			while (res == null) { // 等待服务调用后的返回结果
				res = callBack.getResult();
			}

			System.out.println(((Hello.AsyncClient.helloString_call) res).getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}