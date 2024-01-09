<template>
  <div class="userorders">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>我的订单</el-breadcrumb-item>
    </el-breadcrumb>

    <el-tabs v-model="activeName" class="mb20" @tab-click="getData">
      <el-tab-pane label="全部" name=""></el-tab-pane>
    </el-tabs>

    <div class="userorders-row w100 p30 mb20" v-for="item in list" :key="item.orderId">
  <div class="font18 font-grey f-r f-a-c f-j-s mb20">
    <span>订单号: {{item.orderId}}</span>
  </div>
  <div class="f-r f-a-r f-j-s font14  mb20">
    <div>
      {{item.orderTime}}
    </div>
    <div class="font-grey">
      实付金额： <span class="font28 font-black">{{sumPrice(item)}}</span> 元
    </div>
  </div>
  <div class="btn font18 font-grey zs" @click="returnGoods(item)">
          退货
  </div>
  <div class="t-r font-default zs" @click="item.showDetail = !item.showDetail">{{!item.showDetail?'查看详情':'收起'}}</div>
  <div v-show="item.showDetail">
    <el-divider></el-divider>
    <div class="userorders-goods w100 f-r f-j-s">
      <div class="goods-left">
          <img :src="item.goods.goodsImg" style="height:80px;width:80px;">
          <div class="ml20 font14 f-c">
  <div style="margin-bottom: 15px;">
    <span>{{ item.goods.goodsName }}</span><br>
    <span>¥{{ item.goods.goodsValue }} x {{ item.num }}</span>
  </div>
  <div class="seller-info" style="text-align: right;margin-top: -80px;">
  <span style="display: block;">卖家名称： {{ item.seller.userName }}</span>
  <span style="display: block;">联系电话： {{ item.seller.userPhonenum }}</span>
  <span style="display: block;">邮箱： {{ item.seller.userEmail }}</span>
</div>
</div>

      </div>
    </div>
  </div>
</div>


  </div>
</template>

<script>
import * as api from "@/api/api";
import { mapGetters, mapMutations } from "vuex";

export default {
  components: {},
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      activeName: "",
      code: "",
      list: [],
    };
  },
  created() {},
  mounted() {
    this.getData();
  },
  beforeDestroy() {},
  computed: {
    ...mapGetters(["userinfo"]),

   //计算总价
   sumPrice() {
      return (item) => {
        return item.num*item.goods.goodsValue;
      };
    },

  },
  methods: {
    //获取列表
    async getData() {
  try {
    // 获取用户订单信息
    const ordersResponse = await api.orderByUserId({
      id: this.userinfo.id,
      pageNo: 1,
      size: 9999,
    });
      const orders = ordersResponse.records;

      // 获取每个订单的物品信息

      const promises = orders.map(async order => {
        const goodsResponse = await api.GoodsById(order.gid);

          const goodsInfo = goodsResponse;  // 此处可能需要根据实际返回的物品信息结构进行调整

          const sellerInfo = await api.userByGoodsId(goodsInfo.id);
          return {
            showDetail: false,
            orderId: order.id,
            orderTime: order.orderTime,
            num: order.num,
            goods: goodsInfo,
            seller: sellerInfo
          };
      });

      // 等待所有的物品信息获取完成
      const combinedData = await Promise.all(promises);

      // 更新前端状态变量，用于展示处理后的订单和物品数据
      this.list = combinedData;
      this.total = combinedData.length;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
},

    // 退货
    async returnGoods(item) {
      this.$confirm("确认退货吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then( () => {
         api.deleteOrder(item.orderId).then((res) =>{
          if(res = "删除成功"){
            item.goods.goodsNum = item.goods.goodsNum+item.num;
            item.goods.goodsStatus = "上架中"
            api.updateGoods(item.goods).then((response) =>{
              if(response = "更新成功"){
                this.$message.success("退货成功!");
                this.getData()
              }else{
                this.$message.success("退货失败!");
              }
            })
          }else{
            this.$message.success("退货失败!");
          }
        })
      });
    },

  },
};
</script>

<style lang='scss' scoped>
.userorders {
  .logo {
    .search {
      input {
        border-radius: 0px;
      }
      ::v-deep .el-input__inner {
        border-radius: 0px;
      }
      ::v-deep .el-input-group__append {
        background-color: #fff;
        border-radius: 0px;
        button {
          border-radius: 0px;
        }
      }
    }
  }
    .btn {
      text-align: center;
      width: 120px;
      height: 30px;
      line-height: 30px;
      color: #757575;
      border: 1px solid #000;
    }
  .userorders-row {
    border: 1px solid #e0e0e0;
    .userorders-goods {
      .goods-left {
        width: 700px;
      }
    }
  }
}
.login {
  ::v-deep .el-dialog__body {
    padding: 0 0 20px;
  }
}
>>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  border: 1px #c7c7c7 dashed;
  border-radius: 10px;
  font-size: 28px;
  color: #8c939d;
  width: 70px;
  height: 70px;
  line-height: 70px;
  text-align: center;
}
.avatar {
  width: 70px;
  height: 70px;
  display: block;
  object-fit: cover;
}
.noimg {
  width: 70px;
  height: 70px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
}


</style>