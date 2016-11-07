package nju.edu.client;

import nju.edu.Hello;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author luxiangfan
 * @date 2016-11-07, 17:25
 */
public class HelloServiceClient {
	/**
	 * 调用 Hello 服务，并处理 null 值问题
	 */
	public static void main(String[] args) {
		try {
			TTransport transport = new TSocket("localhost", 7911);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);

			System.out.println(client.helloString("abc"));
			System.out.println(client.helloInt(123));
			System.out.println(client.helloBoolean(true));
			client.helloVoid();
			System.out.println(client.helloNull());

			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			if (e instanceof TApplicationException
					&& ((TApplicationException) e).getType() == TApplicationException.MISSING_RESULT) {
				System.out.println("The result of helloNull function is NULL");
			}
		}
	}
}