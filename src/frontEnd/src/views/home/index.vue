<template>
  <div class="home p-r bg">

    <Head @search="getData" :showSearch="true"></Head>
    <div class="home-content">
      <div class="logo mt20 mb20 f-r f-a-c f-j-s center">
        <div class="font-default font30"> </div>
        <el-input placeholder="请输入搜索内容" style="width:400px" v-model="search" class="search">
          <el-button slot="append" icon="el-icon-search" @click="searchGoods()"></el-button>
        </el-input>
      </div>

      <el-carousel height="400px" arrow="never" class="center">
        <el-carousel-item v-for="item in slideList" :key="item">
          <el-image style="width: 100%;height:100%;" :src="item" fit="cover"></el-image>
        </el-carousel-item>
      </el-carousel>

      <div class="row-father center pt20">
        <div class="rows">
          <div v-for="(goods,index) in goodslist" :key="index" class="row zs" @click="$router.push('/goods?id='+goods.id)">
            <div class="row-content pb10 p10">
              <img style="height:200px;width:100%" :src="goods.goodsImg" lazy="loaded">
              <div class="font14 t-c mt20 font-hidden">
                {{goods.goodsName}}
              </div>
              <p class="font14 font-black t-c">{{goods.goodsValue}}<span>元</span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import * as api from "@/api/api";

export default {
  components: { Head, Footer },
  data() {
    return {
      offset: 1, //当前页
      limit: 10, //每页大小
      total: 0, //总记录数
      hidden: false, //分页是否隐藏b
      slideList: [require("@/assets/bg1.jpg"), require("@/assets/bg2.jpg"),require("@/assets/bg3.jpg"),require("@/assets/bg4.jpg")],
      goodslist: [],
      search: "",
    };
  },
  created() {
    this.getData();
  },
  mounted() {},
  methods: {
    //获取商品
    getData() {
      api
        .GoodsOnSale({
          pageNo: this.offset,
        })
        .then((res) => {
            this.goodslist = res.records;
        });
    },

    //搜索商品
    searchGoods() {
      api
        .onSaleGoodsByName({
          pageNo: this.offset,
          name: this.search,
        })
        .then((res) => {
            this.goodslist = res.records;
            this.total = res.total;
        });
    },
  },
  computed: {},
};
</script>

<style lang="scss" scoped>
.center {
  width: 1200px;
  left: 50%;
  margin-left: -600px;
  position: relative;
}
.home {
  background-color: #c41a21;
  .home-content {
    overflow: hidden;
    ::v-deep .el-carousel__button {
      width: 10px;
      height: 10px;
      border-radius: 50%;
    }
    .logo {
      .search {
        input {
          border-radius: 0px;
        }
        ::v-deep .el-input__inner {
          border-top-left-radius: 20px;
          border-bottom-left-radius: 20px;
          background: #f0f0f0;
          border: none;
        }
        ::v-deep .el-input-group__append {
          background: #f0f0f0;
          border-radius: 0px;
          border-top-right-radius: 20px;
          border-bottom-right-radius: 20px;
          border: none;
          button {
            border-radius: 0px;
          }
        }
      }
    }
  }
  .rows {
    overflow: hidden;
    margin-left: -20px;
    .row {
      img {
        object-fit: cover;
      }
      .row-content {
        background: #fff;
      }
      width: 20%;
      float: left;
      padding-left: 20px;
      margin-bottom: 20px;
    }
  }
}
.bor {
  border-bottom: 2px solid #f23038;
}
.types {
  width: 100%;
  overflow-x: scroll;
  &::-webkit-scrollbar {
    width: 7px;
    height: 0px;
  }
}
.type-row {
  height: 50px;
  line-height: 50px;
  min-width: 120px;
}
.type-default {
  color: #ffe8cd;
  &:hover {
    color: #fff;
  }
}
</style>

