package com.jack.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/12/6.
 */

public  class  StoriesBean implements Serializable {
    private String id;
    private String ga_prefix;
    private String title;
    private String type;
    private String[] images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" +
                "id='" + id + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", titile='" + title + '\'' +
                ", type='" + type + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
