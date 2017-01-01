package com.jack.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MeiZhi extends BaseBean {
    private String error;
    private List<MeiZhiBean>  results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MeiZhiBean> getResults() {
        return results;
    }

    public void setResults(List<MeiZhiBean> results) {
        this.results = results;
    }
}
