package com.example.grus95.networdlibrary.error;

/**
 * Created by grus95 on 16/8/31
 */
public class NetCode {

	public static final int OK = 0;
	public static final int NET_ERROR = -1;
	public static final int TIMEOUT_ERROR = -2;
	public static final int SERVER_ERROR = -3;
	public static final int UNKNOW_ERROR = -4;

	public int code;
	public String msg;

	public NetCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public NetCode(int code){

	}
	
	public NetCode(Exception e){
		String msg = "";
		if(e != null){
			msg = e.getMessage();
		}
//		if (e instanceof NoConnectionError) {
//			this.code = NET_ERROR;
//			this.msg = "无法访问网络,请打开网络连接";
//		} else if (e instanceof TimeoutError) {
//			this.code = TIMEOUT_ERROR;
//			this.msg = "请求失败(0x0)";
//		} else if (e instanceof ServerError) {
//			this.code = SERVER_ERROR;
//			this.msg = "请求失败(0x1)";
//		} else {
//			this.code = UNKNOW_ERROR;
//			this.msg = msg;
//		}
	}

	@Override
	public String toString() {
		return "NetCode{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				'}';
	}
}
