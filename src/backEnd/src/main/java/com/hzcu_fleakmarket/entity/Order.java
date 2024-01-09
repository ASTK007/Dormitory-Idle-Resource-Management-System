package com.hzcu_fleakmarket.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ldk
 * @since 2023年12月23日
 */
@Data
@Accessors(chain = true)
@TableName("orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 买家用户ID
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 物品ID
     */
    @TableField("gid")
    private Integer gid;

    /**
     * 商品数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 出售时间
     */
    @TableField("order_time")
    private String orderTime;
}
