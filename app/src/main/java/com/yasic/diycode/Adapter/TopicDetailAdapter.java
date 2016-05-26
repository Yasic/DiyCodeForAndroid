package com.yasic.diycode.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.yasic.diycode.Bean.TopicDetailBean;
import com.yasic.diycode.Bean.TopicReplyBean;
import com.yasic.diycode.R;

/**
 * Created by Yasic on 2016/5/23.
 */
public class TopicDetailAdapter extends RecyclerView.Adapter<TopicDetailAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    private TopicDetailBean topicDetailBean = null;

    private View headerView = null;

    private Context context;

    public TopicDetailAdapter(Context context, TopicDetailBean topicDetailBean){
        this.topicDetailBean = topicDetailBean;
        this.context = context;
    }

    public void setHeaderView(View headerView){
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void refreshData(TopicDetailBean topicDetailBean){
        this.topicDetailBean = topicDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER){
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.content_headoftopicdetail, parent, false));
        }
        else {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.content_comment, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER){
            holder.tvTitle.setText(topicDetailBean.getTitle());
            holder.tvType.setText(topicDetailBean.getType());
            holder.tvAuthor.setText(topicDetailBean.getAuthor());
            Uri uri = Uri.parse(topicDetailBean.getHeadPortrait());
            Log.i("uri", uri.toString());
            holder.sdvAuthorHeadPortrait.setImageURI(uri);
            Spanned spanned = Html.fromHtml(topicDetailBean.getArticle(), new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(final String source) {
                    ImageLoader imageLoader = ImageLoader.getInstance();
                    imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                    imageLoader.loadImage(source,
                            new ImageLoadingListener() {
                                @Override
                                public void onLoadingStarted(String s, View view) {

                                }

                                @Override
                                public void onLoadingFailed(String s, View view, FailReason failReason) {

                                }

                                @Override
                                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                                    if (bitmap != null) {
                                        Drawable drawable = new BitmapDrawable(
                                                context.getResources(), bitmap);
                                        drawable.setBounds(0, 0,
                                                500,
                                                300);
                                        holder.tvTopicContent.requestLayout();
                                        System.out.println("loaded");
                                    }
                                }

                                @Override
                                public void onLoadingCancelled(String s, View view) {

                                }
                            });
                    return null;
                    /*Observable.create(new Observable.OnSubscribe<Drawable>() {
                        @Override
                        public void call(Subscriber<? super Drawable> subscriber) {
                            InputStream inputStream = null;
                            try {
                                inputStream = (InputStream)new URL(source).getContent();
                                Drawable d = Drawable.createFromStream(inputStream, "src");
                                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                                inputStream.close();
                                subscriber.onNext(d);
                                subscriber.onCompleted();
                            }catch (Exception e){
                                subscriber.onNext(null);
                                subscriber.onCompleted();
                            }
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Drawable>() {
                                @Override
                                public void call(Drawable drawable) {
                                    holder.tvTopicContent.requestLayout();
                                }
                            });*/
                }
            }, null);
            holder.tvTopicContent.setText(spanned);
            //holder.tvTopicContent.requestLayout();
            //holder.tvTopicContent.setText(topicDetailBean.getArticle());
            return;
        }
        final int pos = getRealPosition(holder);
        final TopicReplyBean topicReplyBean = topicDetailBean.getTopicReplyBeanList().get(pos);
        onBind(holder, pos, topicReplyBean);
    }

    public void onBind(MyViewHolder viewHolder, int RealPosition, TopicReplyBean topicReplyBean) {
        viewHolder.tvReplierNickName.setText(topicReplyBean.getAuthor());
        viewHolder.tvComment.setText(topicReplyBean.getReplyInfo());
        viewHolder.tvPublishTime.setText(topicReplyBean.getPublishTime());
        Uri uri = Uri.parse(topicReplyBean.getHeadPortrait());
        viewHolder.sdvReplierHeadPortrait.setImageURI(uri);
        //viewHolder.btStar.setText(topicReplyBean.getStartNumber());
    }

    @Override
    public int getItemCount() {
        return headerView == null ? topicDetailBean.getTopicReplyBeanList().size() : topicDetailBean.getTopicReplyBeanList().size() + 1;
    }

    /**
     * 获取真实位置
     * @param holder 传入holder
     * @return 如果有头部布局则返回位置减1
     */
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getPosition();
        return headerView == null ? position : position - 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvReplierNickName, tvComment, tvPublishTime, tvTitle, tvType, tvAuthor, tvTopicContent;
        ImageButton btStar;
        SimpleDraweeView sdvReplierHeadPortrait, sdvAuthorHeadPortrait;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvReplierNickName = (TextView) itemView.findViewById(R.id.tv_ReplierNickName);
            tvComment = (TextView) itemView.findViewById(R.id.tv_Comment);
            tvPublishTime = (TextView) itemView.findViewById(R.id.tv_PublishTime);
            btStar = (ImageButton) itemView.findViewById(R.id.bt_Star);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_Title);
            tvType = (TextView) itemView.findViewById(R.id.tv_Type);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_Author);
            tvTopicContent = (TextView) itemView.findViewById(R.id.tv_TopicContent);
            sdvReplierHeadPortrait = (SimpleDraweeView) itemView.findViewById(R.id.sdv_ReplierHeadPortrait);
            sdvAuthorHeadPortrait = (SimpleDraweeView) itemView.findViewById(R.id.sdv_AuthorHeadPortrait);
        }
    }
}
