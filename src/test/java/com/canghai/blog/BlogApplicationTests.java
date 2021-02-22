package com.canghai.blog;

import com.canghai.blog.biz.entity.Tag;
import com.canghai.blog.biz.service.TagService;
import com.canghai.blog.common.properties.BlogProperties;
import com.canghai.blog.common.properties.ShiroProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {


    @Autowired
    private TagService tagService;

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void testService(){
        System.out.println("add tag");
        Tag tag = new Tag("learn");
        tagService.add(tag);
        System.out.println("complete add tag");
    }

    @Test
    public void testProperties(){
        ShiroProperties shiroProperties = blogProperties.getShiro();
        System.out.println(shiroProperties.toString());
    }
}
