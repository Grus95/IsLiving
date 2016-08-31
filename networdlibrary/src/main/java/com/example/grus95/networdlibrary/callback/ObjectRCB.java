package com.example.grus95.networdlibrary.callback;


import com.example.grus95.networdlibrary.error.NetCode;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class ObjectRCB<T> {

	public abstract void onSuccess(T t);

	public abstract void onError(NetCode netCode);

	public void onProgress(int total,int progress){
		
	}
}
