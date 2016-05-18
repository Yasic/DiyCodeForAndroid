package com.yasic.diycode.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ESIR on 2016/3/18.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder,T extends Object> extends RecyclerView.Adapter<VH> {
    protected List<T> objectList;
    protected Context context;
    protected OnItemClickListener onItemClickListener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(objectList == null){
            return 0;
        }else {
         return objectList.size();
        }
    }

    /**
     * 提示数据有了变动，刷新数据的方法
     *
     * @param objectList 变动之后的list
     */
    public void refresh(List<T> objectList) {
        this.objectList = objectList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 点击事件的接口
     */
    public interface OnItemClickListener {

        /**
         * 短点击
         *
         * @param v        被点击的对象
         * @param position 被点击的view的位置
         */
        void onItemClick(View v, int position);

        /**
         * 长按
         *
         * @param v        被点击的对象
         * @param position 被点击的view的位置
         */
        void onItemLongCick(View v, int position);
    }
}
