package com.hzcu_fleakmarket.dao;

import com.hzcu_fleakmarket.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldk
 * @since 2023年12月23日
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    User selectByOrderId(Integer id);

    User selectByGoodsId(Integer id);

    User selectByCommId(Integer id);

    List<User>selectByUserName(String name);

    String userLogin(int id);

    String adminLogin (int id);

    int getNewUserId();
}
