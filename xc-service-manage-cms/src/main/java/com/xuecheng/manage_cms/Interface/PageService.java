package com.xuecheng.manage_cms.Interface;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

public interface PageService {
    //页面查询的方法
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    //
}
