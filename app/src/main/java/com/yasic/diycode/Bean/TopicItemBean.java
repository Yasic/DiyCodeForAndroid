package com.yasic.diycode.Bean;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicItemBean {
    private String title;
    private String type;
    private String icon;
    private String author;
    private String lastReply;
    private String totalReply;
    private String articleSequence;

    public TopicItemBean(String title, String type, String icon, String author, String lastReply, String totalReply, String articleSequence) {
        this.title = title;
        this.type = type;
        this.icon = icon;
        this.author = author;
        this.lastReply = lastReply;
        this.totalReply = totalReply;
        this.articleSequence = articleSequence;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getIcon() {
        return icon;
    }

    public String getAuthor() {
        return author;
    }

    public String getLastReply() {
        return lastReply;
    }

    public String getTotalReply() {
        return totalReply;
    }

    public String getArticleSequence() {
        return articleSequence;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLastReply(String lastReply) {
        this.lastReply = lastReply;
    }

    public void setTotalReply(String totalReply) {
        this.totalReply = totalReply;
    }

    public void setArticleSequence(String articleSequence) {
        this.articleSequence = articleSequence;
    }
}
