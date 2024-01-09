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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;

    /**
     * 用户密码
     */
    @TableField("user_passwd")
    private String userPasswd;

    /**
     * 用户号码
     */
    @TableField("user_phonenum")
    private String userPhonenum;

    /**
     * 用户邮箱
     */
    @TableField("user_email")
    private String userEmail;
}
