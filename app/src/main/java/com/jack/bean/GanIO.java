package com.jack.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class GanIO  extends BaseBean {
  private String error;
    private List<GanHuoBean> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<GanHuoBean> getResults() {
        return results;
    }

    public void setResults(List<GanHuoBean> results) {
        this.results = results;
    }
}
