package com.yasic.diycode.Bean;

import java.util.List;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicDetailBean {
    private String title;
    private String publishTime;
    private String author;
    private String type;
    private String watchedNumber;
    private String article;
    private String headPortrait;
    private String startNumber;
    private List<TopicReplyBean> topicReplyBeanList;

    public TopicDetailBean(String title, String publishTime, String author, String type, String watchedNumber, String article, String headPortrait, String startNumber, List<TopicReplyBean> topicReplyBeanList) {
        this.title = title;
        this.publishTime = publishTime;
        this.author = author;
        this.type = type;
        this.watchedNumber = watchedNumber;
        this.article = article;
        this.headPortrait = headPortrait;
        this.startNumber = startNumber;
        this.topicReplyBeanList = topicReplyBeanList;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getWatchedNumber() {
        return watchedNumber;
    }

    public String getArticle() {
        return article;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public List<TopicReplyBean> getTopicReplyBeanList() {
        return topicReplyBeanList;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWatchedNumber(String watchedNumber) {
        this.watchedNumber = watchedNumber;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setTopicReplyBeanList(List<TopicReplyBean> topicReplyBeanList) {
        this.topicReplyBeanList = topicReplyBeanList;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }
}
