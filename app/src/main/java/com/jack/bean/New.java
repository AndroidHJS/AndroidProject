package com.jack.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class New extends BaseBean{
    private String date;
    private List<StoriesBean> stories;
    private  List<TopSotriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TopSotriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopSotriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }



}


