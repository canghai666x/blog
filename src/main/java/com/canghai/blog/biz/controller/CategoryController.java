package com.canghai.blog.biz.controller;

import com.canghai.blog.biz.entity.Category;
import com.canghai.blog.biz.service.CategoryService;
import com.canghai.blog.common.annotation.Log;
import com.canghai.blog.common.common.BaseController;
import com.canghai.blog.common.constants.CommonConstant;
import com.canghai.blog.common.utils.GeneralResponse;
import com.canghai.blog.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(CommonConstant.BASE_API+"/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list")
    public GeneralResponse<Map<String,Object>> list(@RequestBody Category category, QueryPage queryPage){
        return new GeneralResponse<>(super.getDate(categoryService.list(category,queryPage)));
    }

    @PostMapping("/filter/list")
    public GeneralResponse list(@RequestBody Category category){
        return new GeneralResponse<>(categoryService.list(category));
    }

    @GetMapping("/{id}")
    public GeneralResponse findById(@PathVariable Long id){
        return new GeneralResponse<>(categoryService.getById(id));
    }

    @PostMapping
    @Log("添加分类")
    public GeneralResponse save(@RequestBody Category category){
        categoryService.add(category);
        return new GeneralResponse<>();
    }

    @PutMapping
    @Log("更新分类")
    public GeneralResponse update(@RequestBody Category category){
        categoryService.update(category);
        return new GeneralResponse<>();
    }

    @DeleteMapping("/{id}")
    @Log("删除分类")
    public GeneralResponse delete(@PathVariable Long id){
        categoryService.delete(id);
        return new GeneralResponse<>();
    }
}
