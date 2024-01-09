package com.hzcu_fleakmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzcu_fleakmarket.dao.CommentDao;
import com.hzcu_fleakmarket.dao.GoodsDao;
import com.hzcu_fleakmarket.entity.Comment;
import com.hzcu_fleakmarket.entity.Goods;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ldk
 * @since 2023年12月21日
 */
@RestController
@CrossOrigin
@Tag(name = "CommentController", description = "操作评论信息的接口")
public class CommentController {
    @Autowired
    private CommentDao commentdao;

    @GetMapping("/comment/findAll")
    public IPage queryCommentPage(@RequestParam(defaultValue = "1") Integer pageNo) {
        Page<Comment> page = new Page<>(pageNo,10);
        IPage iPage = commentdao.selectPage(page, null);
        return iPage;
    }
    @GetMapping("/comment/{id}")
    public Comment selectById(@PathVariable int id){
        Comment comment =commentdao.selectById(id);
        return comment;
    }
    @GetMapping("/comment/user/{id}")
    public IPage<Comment> loadUserCommentPage(@RequestParam(defaultValue = "1") Integer pageNo,@PathVariable int id){
        Page<Comment> page = new Page<>(pageNo, 10);
        IPage<Comment> iPage =commentdao.loadUserComment(page,id);
        return iPage;
    }
    @GetMapping("/comment/goods/{id}")
    public IPage<Comment> selectByGoodsIdPage(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "10") int size,@PathVariable int id){
        Page<Comment> page = new Page<>(pageNo, size);
        IPage<Comment> iPage =commentdao.selectByGoodsId(page,id);
        return iPage;
    }
    @PostMapping("/comment")
    public String save(Comment comment) {
        int i = commentdao.insert(comment);
        if(i > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
    @PutMapping("/comment")
    public String update(Comment comment) {
        int i = commentdao.updateById(comment);
        if(i > 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }
    @DeleteMapping("/comment/{id}")
    public String delete(@PathVariable int id) {
        int i = commentdao.deleteById(id);
        if(i > 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}
