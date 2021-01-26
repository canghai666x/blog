package com.canghai.blog;

import com.canghai.blog.biz.entity.User;
import com.canghai.blog.biz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testService(){
        User user = userService.findByName("canghai");
        System.out.println(user);
    }

}
