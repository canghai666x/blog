package com.canghai.blog.common.utils;

import com.canghai.blog.common.properties.BlogProperties;
import com.canghai.blog.common.properties.ShiroProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Md5Util {
    @Autowired
    private BlogProperties blogProperties;

    private static final String ALGORITHM_NAME="MD5";
    private static final int HASH_ITERATIONS=2;

    public String encryptPassword(String password){
        ShiroProperties shiro = blogProperties.getShiro();
        if (null==password)
            return null;
        return new SimpleHash(ALGORITHM_NAME, StringUtils.lowerCase(password),shiro.getCipherKey(),HASH_ITERATIONS).toHex();
    }
}
