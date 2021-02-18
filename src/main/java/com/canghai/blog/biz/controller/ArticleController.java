package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.Article;
import com.canghai.blog.biz.service.ArticleService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/findByCategory/{id}")
    public GeneralResponse findByCategory(@PathVariable Long id){
        return new GeneralResponse<>(articleService.findByCategory(id));
    }

    @GetMapping("/findByTag/{id}")
    public GeneralResponse findByTag(@PathVariable Long id){
        return new GeneralResponse<>(articleService.findByTag(id));
    }

    @PostMapping("/list")
    public GeneralResponse list(@RequestBody Article article, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(articleService.list(article,queryPage)));
    }

    @GetMapping("/{id}")
    public GeneralResponse findById(@PathVariable Long id){
        return new GeneralResponse<>(articleService.findById(id));
    }

    @PostMapping
    @Log("新增文章")
    public GeneralResponse add(@RequestBody Article article){
        //获取用户名，设置用户名，shiro
        articleService.add(article);
        return new GeneralResponse<>();
    }

    @PutMapping
    @Log("更新文章")
    public GeneralResponse update(@RequestBody Article article){
        articleService.update(article);
        return new GeneralResponse<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除文章")
    public GeneralResponse delete(@PathVariable Long id){
        articleService.delete(id);
        return new GeneralResponse<>();
    }
}
