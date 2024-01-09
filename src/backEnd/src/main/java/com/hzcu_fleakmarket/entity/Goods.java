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
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品类型
     */
    @TableField("goods_type")
    private String goodsType;

    /**
     * 商品描述
     */
    @TableField("goods_des")
    private String goodsDes;

    /**
     * 商品图片
     */
    @TableField("goods_img")
    private String goodsImg;

    /**
     * 商品状态 上架中/已下架
     */
    @TableField("goods_status")
    private String goodsStatus;

    /**
     * 商品价格
     */
    @TableField("goods_value")
    private Double goodsValue;

    /**
     * 商品数量
     */
    @TableField("goods_num")
    private Integer goodsNum;

    /**
     * 关联用户
     */
    @TableField("uid")
    private Integer uid;
}
