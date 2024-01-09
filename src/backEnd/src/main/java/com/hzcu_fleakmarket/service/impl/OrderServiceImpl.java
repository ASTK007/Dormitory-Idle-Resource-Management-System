package com.hzcu_fleakmarket.service.impl;

import com.hzcu_fleakmarket.entity.Order;
import com.hzcu_fleakmarket.dao.OrderDao;
import com.hzcu_fleakmarket.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

}
