package com.example.grus95.networdlibrary.callback;

import com.example.grus95.networdlibrary.error.NetCode;

import java.util.ArrayList;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class ArrayRCB<T> {
	

	public abstract void onSuccess(ArrayList<T> t);

	public abstract void onError(NetCode netCode);


}
