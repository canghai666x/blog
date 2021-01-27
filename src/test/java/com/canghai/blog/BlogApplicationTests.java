package com.canghai.blog;

import com.canghai.blog.biz.entity.Tag;
import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.service.TagService;
import com.canghai.blog.biz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Test
    public void testService(){
        System.out.println("add tag");
        Tag tag = new Tag("learn");
        tagService.add(tag);
        System.out.println("complete add tag");
    }


}
