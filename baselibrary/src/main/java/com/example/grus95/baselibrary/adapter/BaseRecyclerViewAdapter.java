package com.example.grus95.baselibrary.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by grus95 on 16/8/31
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    protected Context mContext;

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }



    protected class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        protected View findViewById(@IdRes int idRes){
            return itemView.findViewById(idRes);
        }
    }

}

