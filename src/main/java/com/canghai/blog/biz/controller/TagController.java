package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.Tag;
import com.canghai.blog.biz.service.TagService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @PostMapping("/list")
    public GeneralResponse findByPage(@RequestBody Tag tag, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(tagService.list(tag,queryPage)));
    }

    @PostMapping("/filter/list")
    public GeneralResponse list(@RequestBody Tag tag){
        return new GeneralResponse<>(tagService.list(tag));
    }

    @GetMapping("/{id}")
    public GeneralResponse findById(@PathVariable Long id){
        return new GeneralResponse<>(tagService.getById(id));
    }

    @PostMapping
    @Log("添加标签")
    public GeneralResponse save(@RequestBody Tag tag){
        tagService.add(tag);
        return new GeneralResponse<>();
    }

    @PutMapping
    @Log("更新标签")
    public GeneralResponse update(@RequestBody Tag tag){
        tagService.update(tag);
        return new GeneralResponse<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除标签")
    public GeneralResponse delete(@PathVariable Long id){
        tagService.delete(id);
        return new GeneralResponse<>();
    }
}
