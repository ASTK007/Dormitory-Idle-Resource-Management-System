package com.hzcu_fleakmarket.dao;

import com.hzcu_fleakmarket.entity.Comment;
import com.hzcu_fleakmarket.entity.Goods;
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
public interface CommentDao extends BaseMapper<Comment> {
    IPage<Comment> loadUserComment(Page<Comment> page, @Param("id") Integer id);
    
    IPage<Comment> selectByGoodsId(Page<Comment> page, @Param("id") Integer id);
}
