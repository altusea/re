package org.example.playground.b;

import org.example.playground.a.BaseParam;
import org.example.util.JacksonUtil;

import java.util.UUID;

public class PageParam extends BaseParam {

    private int page;

    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static void main(String[] args) {
        PageParam param = new PageParam();
        param.setTraceId(UUID.randomUUID().toString());
        System.out.println(JacksonUtil.toJson(param));
    }
}
