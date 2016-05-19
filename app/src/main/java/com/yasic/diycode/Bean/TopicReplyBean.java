package com.yasic.diycode.Bean;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicReplyBean {
    private String author;
    private String headPortrait;
    private String replyInfo;
    private String publishTime;
    private String startNumber;

    public TopicReplyBean(String author, String headPortrait, String replyInfo, String publishTime, String startNumber) {
        this.author = author;
        this.headPortrait = headPortrait;
        this.replyInfo = replyInfo;
        this.publishTime = publishTime;
        this.startNumber = startNumber;
    }

    public String getAuthor() {
        return author;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }
}
