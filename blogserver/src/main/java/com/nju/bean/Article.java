package com.nju.bean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Article {
    private Long id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    private Timestamp publishDate;
    private Integer state;
    private Integer pageView;
    private Timestamp editTime;
    private String[] dynamicTags;
    private String nickname;
    private String cateName;
    private List<Tags> tags;
    private String stateStr;

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", summary='" + summary + '\'' +
                ", cid=" + cid +
                ", uid=" + uid +
                ", publishDate=" + publishDate +
                ", state=" + state +
                ", pageView=" + pageView +
                ", editTime=" + editTime +
                ", dynamicTags=" + Arrays.toString(dynamicTags) +
                ", nickname='" + nickname + '\'' +
                ", cateName='" + cateName + '\'' +
                ", tags=" + tags +
                ", stateStr='" + stateStr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(mdContent, article.mdContent) && Objects.equals(htmlContent, article.htmlContent) && Objects.equals(summary, article.summary) && Objects.equals(cid, article.cid) && Objects.equals(uid, article.uid) && Objects.equals(publishDate, article.publishDate) && Objects.equals(state, article.state) && Objects.equals(pageView, article.pageView) && Objects.equals(editTime, article.editTime) && Arrays.equals(dynamicTags, article.dynamicTags) && Objects.equals(nickname, article.nickname) && Objects.equals(cateName, article.cateName) && Objects.equals(tags, article.tags) && Objects.equals(stateStr, article.stateStr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, mdContent, htmlContent, summary, cid, uid, publishDate, state, pageView, editTime, nickname, cateName, tags, stateStr);
        result = 31 * result + Arrays.hashCode(dynamicTags);
        return result;
    }
}
