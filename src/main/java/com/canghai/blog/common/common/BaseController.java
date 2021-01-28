package com.canghai.blog.common.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public Map<String,Object> getDate(IPage<?> iPage){
        Map<String,Object> data = new HashMap<>();
        data.put("rows",iPage.getRecords());
        data.put("total",iPage.getTotal());
        return data;
    }
}
