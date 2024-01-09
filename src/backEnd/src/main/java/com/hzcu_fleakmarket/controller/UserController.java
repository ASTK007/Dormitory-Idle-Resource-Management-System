package com.hzcu_fleakmarket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzcu_fleakmarket.dao.UserDao;
import com.hzcu_fleakmarket.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
@Tag(name = "UserController",description = "操作用户信息的接口")
public class UserController {
    @Autowired
    private UserDao userdao;

    @GetMapping("/user/findAll")    
    public IPage queryUserPage(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "10") int size) {
        Page<User> page = new Page<>(pageNo,size);
        IPage iPage = userdao.selectPage(page, null);
        return iPage;
    }
    @GetMapping("/user/login")
    public boolean userLogin(int id,String password) {
        String passwd = userdao.userLogin(id);
        if(passwd.equals(password)){
          return true;
        }else{
          return false;
        }
    }
    @GetMapping("/admin/login")
    public boolean adminLogin(int id,String password) {
        String passwd = userdao.adminLogin(id);
        if(passwd.equals(password)){
          return true;
        }else{
          return false;
        }
    }
    @GetMapping("/user/new")
    public int getNewUserId() {
        return userdao.getNewUserId();
    }      
    @GetMapping("/user/{id}")
    public User selectById(@PathVariable int id){
        User user = userdao.selectById(id);
        return user;
    }
    @GetMapping("/user/order/{uid}")
    public User selectByOrderId(@PathVariable Integer id) {
        User user = userdao.selectByOrderId(id);
        return user;
    }
        @GetMapping("/user/goods/{id}")
    public User selectByGoodsId(@PathVariable Integer id) {
        User user = userdao.selectByGoodsId(id);
        return user;
    }
        @GetMapping("/user/comment/{id}")
    public User selectByCommId(@PathVariable Integer id) {
        User user = userdao.selectByCommId(id);
        return user;
    }
    @GetMapping("/user/name")
    public IPage selectByNamePage(@RequestParam(defaultValue = "1") Integer pageNo,String name){
        Page<User> page = new Page<>(pageNo,10);
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.like("user_name",name);
        IPage iPage = userdao.selectPage(page, wrapper);
        return iPage;
    }
    @PostMapping("/user")
    public String save(User user) {
        int i = userdao.insert(user);
        if(i > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
    @PutMapping("/user")
    public String update(User user) {
        int i = userdao.updateById(user);
        if(i > 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable Integer id) {
        int i = userdao.deleteById(id);
        if(i > 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}
