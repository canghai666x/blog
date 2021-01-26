package com.canghai.blog.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Md5UtilTest {
    @Autowired
    private Md5Util md5Util;

    @Test
    public void test(){
        String password = "canghai2020";
        String value = md5Util.encryptPassword(password);
        System.out.println(value);
    }
}