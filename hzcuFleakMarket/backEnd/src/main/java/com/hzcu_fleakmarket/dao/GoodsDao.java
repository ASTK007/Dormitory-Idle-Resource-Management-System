package com.hzcu_fleakmarket.dao;

import com.hzcu_fleakmarket.entity.Goods;
import com.hzcu_fleakmarket.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldk
 * @since 2023年12月23日
 */
@Mapper
public interface GoodsDao extends BaseMapper<Goods> {

    Goods selectByOrderId(Integer id);
    
    IPage<Goods> loadAllUserGoods(Page<Goods> page, @Param("id") Integer id);

    List<Goods> selectByGoodsName(String name);

    List<Goods> selectAllGoodsOnSale();
}
