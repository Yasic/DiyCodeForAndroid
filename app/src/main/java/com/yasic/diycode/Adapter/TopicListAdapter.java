package com.yasic.diycode.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yasic.diycode.Bean.TopicItemBean;
import com.yasic.diycode.R;

import java.util.List;

/**
 * Created by ESIR on 2016/3/18.
 */
public class TopicListAdapter extends BaseAdapter<TopicListAdapter.MyViewHolder, TopicItemBean> {

    public TopicListAdapter(Context context, List<TopicItemBean> topicItemBeanList) {
        this.context = context;
        this.objectList = topicItemBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_topiclist,parent,false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.liTopicList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.liTopicList,position);
                }
            });
        }
        holder.tvTitle.setText(objectList.get(position).getTitle());
        holder.tvType.setText(objectList.get(position).getType());
        holder.tvAuthor.setText(objectList.get(position).getAuthor());
        holder.tvLastReply.setText(objectList.get(position).getLastReply());
        holder.tvTotalReply.setText(objectList.get(position).getTotalReply());
        super.onBindViewHolder(holder, position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvType, tvAuthor, tvLastReply, tvTotalReply;
        private RelativeLayout liTopicList;
        public MyViewHolder(View itemView) {
            super(itemView);
            liTopicList = (RelativeLayout) itemView.findViewById(R.id.li_TopicList);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_Title);
            tvType = (TextView) itemView.findViewById(R.id.tv_Type);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_Author);
            tvLastReply = (TextView) itemView.findViewById(R.id.tv_LastReply);
            tvTotalReply = (TextView) itemView.findViewById(R.id.tv_TotalReply);
        }
    }

}
