import request from '@/utils/request'

  //用户表-查询所有用户
  export function getUserList(params) {
      return request({
        url: '/user/findAll',
        method: 'get',
        params
      })
    }
     //用户表-用户登录
    export function userLogin(params) {
      return request({
        url: '/user/login',
        method: 'get',
        params
      })
    }
    //用户表-管理员登录
    export function adminLogin(params) {
      return request({
        url: '/admin/login',
        method: 'get',
        params
      })
    }
  //用户表-根据id查询用户
    export function UserById(id) {
      return request({
        url: `/user/${id}`,
        method: 'get',
      })
    }

      //用户表-获取新注册用户ID
      export function getNewUserId() {
        return request({
          url: '/user/new',
          method: 'get',
        })
      }

  //用户表-根据商品id查询用户
    export function userByGoodsId(id) {
      return request({
        url: `/user/goods/${id}`,
        method: 'get',
      })
    }
  //用户表-根据评论id查询用户
    export function userByCommId(id) {
      return request({
        url: `/user/comment/${id}`,
        method: 'get',
      })
    }
  //用户表-根据订单id查询用户
    export function userByOrderId(id) {
      return request({
        url: `/user/order/${id}`,
        method: 'get',
      })
    }
  //用户表-根据用户名查询
    export function userByName(params) {
      return request({
        url: '/user/name',
        method: 'get',
        params : params
      })
    }
  //用户表-添加用户
    export function addUser(params) {
      return request({
        url: '/user',
        method: 'post',
        params
      })
    }
  //用户表-更新用户信息
    export function updateUser(params) {
      return request({
        url: '/user',
        method: 'put',
        params : params
      })
    }
  //用户表-删除用户
    export function deleteUser(id) {
      return request({
        url: `/user/${id}`,
        method: 'delete',
      })
    }
  
    //订单表-查询所有订单
    export function getOrderList(params) {
      return request({
        url: '/order/findAll',
        method: 'get',
        params
      })
    }
  
    //订单表-根据id查询订单
    export function orderById(id) {
      return request({
        url: `/order/${id}`,
        method: 'get',
      })
    }
  
    //订单表-根据用户id查询订单
    export function orderByUserId(params) {
      return request({
        url: `/order/user/${params.id}`,
        method: 'get',
        params
      })
    }
  
    //订单表-根据物品id查询订单
    export function orderByGoodsId(id) {
      return request({
        url: `/order/goods/${id}`,
        method: 'get',
      })
    }
  
    //订单表-添加订单
    export function addOrder(params) {
      return request({
        url: '/order',
        method: 'post',
        params
      })
    }
  
      //订单表-更新订单
      export function updateOrder(params) {
        return request({
          url: '/order',
          method: 'put',
          params: params
        })
      }
  
    //订单表-删除订单
    export function deleteOrder(id) {
      return request({
        url: `/order/${id}`,
        method: 'delete',
      })
    }
  
    //商品表-查询所有商品
    export function getGoodsList(params) {
      return request({
        url: '/goods/findAll',
        method: 'get',
        params
      })
    }
  
      //商品表-查询所有在售商品
      export function GoodsOnSale(params) {
        return request({
          url: '/goods/onsale',
          method: 'get',
          params
        })
      }
      //商品表-根据ID查询商品
      export function GoodsById(id) {
        return request({
          url: `/goods/${id}`,
          method: 'get',
        })
      }
      //商品表-根据用户ID查询商品
      export function GoodsByUserId(params) {
        return request({
          url: `/goods/user/${params.id}`,
          method: 'get',
        })
      }
  //商品表-根据订单ID查询商品
      export function GoodsByOrderId(id) {
        return request({
          url: `/goods/order/${id}`,
          method: 'get',
        })
      }
  //商品表-根据商品名称查询商品
  export function GoodsByName(params) {
    return request({
      url: '/goods/name',
      method: 'get',
      params
    })
  }
    //商品表-根据商品名称查询上架商品
    export function onSaleGoodsByName(params) {
      return request({
        url: '/goods/onsale/name',
        method: 'get',
        params
      })
    }
  //商品表-添加商品
  export function addGoods(params) {
    return request({
      url: '/goods',
      method: 'post',
      params
    })
  }

    //商品表-上传图片
    export function uploadImg(params) {
      return request({
        url: '/upload',
        method: 'post',
        data:params
      })
    }
  
  //商品表-更新商品
  export function updateGoods(params) {
    return request({
      url: '/goods',
      method: 'put',
      params
    })
  }
  //商品表-删除商品
  export function deleteGoods(id) {
    return request({
      url: `/goods/${id}`,
      method: 'delete',
    })
  }
  
  //评论表-查询所有评论
  export function getCommentList(params) {
    return request({
      url: `/comment/findAll`,
      method: 'get',
      params
    })
  }
  
  //评论表-根据ID查询评论
  export function commentById(id) {
    return request({
      url: `/comment/${id}`,
      method: 'get',
    })
  }
  
  //评论表-根据用户ID查询评论
  export function commentByUserId(params) {
    return request({
      url: `/comment/user/${params.id}`,
      method: 'get',
      params
    })
  }
  
  //评论表-根据商品ID查询评论
  export function commentByGoodsId(params) {
    return request({
      url: `/comment/goods/${params.id}`,
      method: 'get',
    })
  }
  
  //评论表-添加评论
  export function addComment(params) {
    return request({
      url: '/comment',
      method: 'post',
      params
    })
  }
  
  //评论表-更新评论
  export function updateComment(params) {
    return request({
      url: '/comment',
      method: 'put',
      params
    })
  }
  
  //评论表-删除评论
  export function deleteComment(id) {
    return request({
      url: `/comment/${id}`,
      method: 'delete',
    })
  }