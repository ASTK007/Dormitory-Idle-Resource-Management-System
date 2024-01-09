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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzcu_fleakmarket.dao.GoodsDao;
import com.hzcu_fleakmarket.dao.OrderDao;
import com.hzcu_fleakmarket.entity.Goods;
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
@Tag(name = "GoodsController", description = "操作商品信息的接口")
public class GoodsController {
@Autowired
    private GoodsDao goodsdao;

    @GetMapping("/goods/findAll")
    public IPage queryGoodsPage(@RequestParam(defaultValue = "1") Integer pageNo) {
        Page<Goods> page = new Page<>(pageNo,10);
        IPage iPage = goodsdao.selectPage(page, null);
        return iPage;
    }
    @GetMapping("/goods/onsale")
    public IPage<Goods> selectAllGoodsOnSalePage(@RequestParam(defaultValue = "1") Integer pageNo) {
        Page<Goods> page = new Page<>(pageNo,10);
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>();
        wrapper.eq("goods_status", "上架中");
        IPage iPage = goodsdao.selectPage(page, wrapper);
        return iPage;
    }
    @GetMapping("/goods/{id}")
    public Goods selectById(@PathVariable int id){
        Goods goods =goodsdao.selectById(id);
        return goods;
    }
    @GetMapping("/goods/user/{id}")
    public IPage<Goods> loadAllUserGoodsPage(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "10") Integer size,@PathVariable int id){
        Page<Goods> page = new Page<>(pageNo, size);
        IPage<Goods> iPage =goodsdao.loadAllUserGoods(page,id);
        return iPage;
    }
    @GetMapping("/goods/order/{id}")
    public Goods selectByOrderId(@PathVariable int id){
        Goods goods =goodsdao.selectByOrderId(id);
        return goods;
    }
    @GetMapping("/goods/name")
    public IPage<Goods> selectByGoodsNamePage(@RequestParam(defaultValue = "1") Integer pageNo,String name){
        Page<Goods> page = new Page<>(pageNo,10);
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>();
        wrapper.like("goods_name", name);
        IPage iPage = goodsdao.selectPage(page, wrapper);
        return iPage;
    }

    @GetMapping("/goods/onsale/name")
    public IPage<Goods> selectByOnSaleGoodsNamePage(@RequestParam(defaultValue = "1") Integer pageNo,String name){
        Page<Goods> page = new Page<>(pageNo,10);
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>();
        wrapper.like("goods_name", name);
        wrapper.eq("goods_status", "上架中");
        IPage iPage = goodsdao.selectPage(page, wrapper);
        return iPage;
    }
    @PostMapping("/goods")
    public String save(Goods goods) {
        int i = goodsdao.insert(goods);
        if(i > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }
    @PutMapping("/goods")
    public String update(Goods goods) {
        int i = goodsdao.updateById(goods);
        if(i > 0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }
    @DeleteMapping("/goods/{id}")
    public String delete(@PathVariable int id) {
        int i = goodsdao.deleteById(id);
        if(i > 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}
