package com.hzcu_fleakmarket.service.impl;

import com.hzcu_fleakmarket.entity.User;
import com.hzcu_fleakmarket.dao.UserDao;
import com.hzcu_fleakmarket.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldk
 * @since 2023年12月23日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
