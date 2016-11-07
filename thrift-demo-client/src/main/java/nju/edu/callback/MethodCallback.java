package nju.edu.callback;

import org.apache.thrift.async.AsyncMethodCallback;

/**
 * @author luxiangfan
 * @date 2016-11-07, 17:35
 */
public class MethodCallback implements AsyncMethodCallback {
	Object response = null;

	public Object getResult() {
		return this.response; // 返回结果值
	}

	// 处理服务返回的结果值
	@Override
	public void onComplete(Object response) {
		this.response = response;
	}

	// 处理调用服务过程中出现的异常
	@Override
	public void onError(Exception e) {
	}
}