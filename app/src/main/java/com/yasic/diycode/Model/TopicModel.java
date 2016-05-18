package com.yasic.diycode.Model;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yasic.diycode.Bean.CallbackBean;
import com.yasic.diycode.Bean.TopicBean;
import com.yasic.diycode.Util.OkhttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicModel implements ITopicModel {

    @Override
    public CallbackBean getTopicList(int page) {
        OkhttpUtil okhttpUtil = OkhttpUtil.getInstance();
        final Request request = new Request.Builder().url("http://diycode.cc/?page=" + page).build();
        try {
            List<TopicBean> topicBeanList = new ArrayList<>();
            String title, type, author, lastReply, totalReply, icon, articleSequense;
            Response response = okhttpUtil.okHttpClient.newCall(request).execute();
            Document document = Jsoup.parse(response.body().string());
            Element mainElement = document.getElementById("main");
            Elements rowElements = mainElement.getElementsByClass("row");
            Elements colmdElements = rowElements.get(0).getElementsByClass("col-md-9");
            Elements panelBodyElements = colmdElements.get(0).getElementsByClass("panel-body");
            Elements articleElements = panelBodyElements.get(0).select("div.topic");
            for (int i = 0; i < articleElements.size(); i++){
                articleSequense = articleElements.get(i).className();
                title = articleElements.get(i).select("div.infos").get(0).select("div.title").get(0).getElementsByTag("a").text();
                type = articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("node").get(0).text();
                author = articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("hacknews_clear").get(0).text();
                lastReply = "最后由" + articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("hidden-mobile").get(0).getElementsByTag("a").text() + " "
                        + articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("hidden-mobile").get(0).getElementsByTag("abbr").text() + "回复";
                totalReply = articleElements.get(i).select("div.count").get(0).getElementsByTag("a").text();
                if (totalReply == ""){
                    totalReply = "0";
                }
                icon = articleElements.get(0).select("div.avatar").get(0).getElementsByClass("hacknews_clear").get(0).getElementsByTag("img").get(0).attr("src");
                /*Log.i("articleSequence", articleElements.get(i).className());
                Log.i("title", articleElements.get(i).select("div.infos").get(0).select("div.title").get(0).getElementsByTag("a").text());
                Log.i("type", articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("node").get(0).text());
                Log.i("author", articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("hacknews_clear").get(0).text());
                Log.i("lastReply", articleElements.get(i).getElementsByClass("info").get(0).getElementsByClass("hidden-mobile").get(0).text());
                Log.i("totalReply", articleElements.get(i).select("div.count").get(0).getElementsByTag("a").text());
                Log.i("icon", articleElements.get(0).select("div.avatar").get(0).getElementsByClass("hacknews_clear").get(0).getElementsByTag("img").get(0).attr("src"));*/
                topicBeanList.add(new TopicBean(title, type, icon, author, lastReply, totalReply, articleSequense));
            }
            CallbackBean<List<TopicBean>> callbackBean = new CallbackBean<>("0", "", topicBeanList);
            return callbackBean;
        }
        catch (Exception e){
            CallbackBean<List<TopicBean>> callbackBean = new CallbackBean<>("1", e.getMessage(), null);
            Log.i("Exception", e.getMessage());
            return callbackBean;
        }
    }

    @Override
    public CallbackBean getTopicDetail(int topicSequence) {
        return null;
    }
}
