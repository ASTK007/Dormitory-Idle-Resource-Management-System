package com.hzcu_fleakmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.hzcu_fleakmarket.dao.OrderDao;
import com.hzcu_fleakmarket.dao.UserDao;
import com.hzcu_fleakmarket.entity.Order;
import com.hzcu_fleakmarket.entity.User;

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
@Tag(name = "OrderController", description = "操作订单信息的接口")
public class OrderController {
    @Autowired
    private OrderDao orderdao;

    @GetMapping("/order/findAll")
    public IPage queryOrderPage(@RequestParam(defaultValue = "1") Integer pageNo) {
        Page<Order> page = new Page<>(pageNo,10);
        IPage iPage = orderdao.selectPage(page, null);
        return iPage;
    }
    @GetMapping("/order/{id}")
    public Order selectById(@PathVariable int id){
        Order order =orderdao.selectById(id);
        return order;
    }
    @GetMapping("/order/user/{id}")
    public IPage loadUserOrders(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "10")int size,@PathVariable int id) {
            Page<Order> page = new Page<>(pageNo, size);
            IPage<Order> iPage = orderdao.loadUserOrders(page, id);
            return iPage;
        }
    @GetMapping("/order/goods/{id}")
    public List<Order> selectByGoodsId(@PathVariable int id){
        List<Order> list =orderdao.selectByGoodsId(id);
        return list;
    }
    @PostMapping("/order")
    public String save(Order order) {
        int i = orderdao.insert(order);
        if(i > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
    @PutMapping("/order")
    public String update(Order order) {
        int i = orderdao.updateById(order);
        if(i > 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }
    @DeleteMapping("/order/{id}")
    public String delete(@PathVariable int id) {
        int i = orderdao.deleteById(id);
        if(i > 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}
