<template>
  <div class="car bg">

    <Head :showSearch="false"></Head>

    <Middle :showBread="false" :showSearch="false">
      <div slot class="content m20" v-show="tableData.length">
        <el-table :data="tableData" style="width: 100%" header-align="center" height="850px" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column prop="date" label="商品" width="180" align="center">
            <template slot-scope="scope">
              <img :src="scope.row.goodsImg" style="width:150px;height:100px;object-fit:contain" align="center">
            </template>
          </el-table-column>
          <el-table-column prop="goodsName" label="名称" width="180" align="center" />
          <el-table-column prop="goodsType" label="分类" width="180" align="center" />
          <el-table-column prop="goodsValue" label="单价" align="center" />
          <el-table-column prop="date" label="数量" width="190" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.buynum" @change="handleChange(scope.row.buynum,scope.$index)" :min="1" :max="scope.row.goodsNum" label="描述文字"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column prop="price" label="操作" align="center">
            <template slot-scope="scope" class="t-c" align="center">
              <span class="font-default zs" @click="del(scope.$index)">删除</span>
            </template>
          </el-table-column>
        </el-table>

        <div class="f-r f-a-c f-j-s">
          <div class="font-default pl20 zs" @click="delall">
            删除
          </div>
          <div class="f-r f-a-c f-j-r mt20">
            <div class="font14">总计金额：</div>
            <div class="font20 font-red m20">¥ {{sumPrice}}</div>
            <el-button type="danger" size="medium" class="btn" style="width:150px" @click="pay">结算</el-button>
          </div>
        </div>
      </div>

      <div slot class="content m20 f-c f-a-c f-j-c" v-show="!tableData.length" style="height:800px">
        <el-empty description="购物车为空"></el-empty>
        <el-button type="default" @click="$router.push('/')">返回首页</el-button>
      </div>
    </Middle>

    <Footer></Footer>
  </div>
</template>

<script>
import Head from "@/components/Head";
import Footer from "@/components/Footer";
import Middle from "@/components/Middle";
import * as api from "@/api/api";
import { mapGetters, mapMutations } from "vuex";
import { Loading } from "element-ui";

export default {
  components: { Head, Middle, Footer },
  data() {
    return {
      tableData: [],
      show: false,
      multipleSelection: [],
    };
  },
  created() {},
  mounted() {
    if (!this.goods || this.goods.length == 0) {
      this.tableData = [];
    } else {
      this.tableData = this.goods;
    }
  },
  methods: {
    ...mapMutations(["setGoods"]),

    // 全选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    // 删除
    delall() {
      if (!this.multipleSelection.length) {
        this.$message.error("没有选中商品!");
        return;
      }
      let result = [];
      for (let i = 0; i < this.tableData.length; i++) {
        let obj = this.tableData[i];
        let id = obj.id;
        let isExist = false;

        for (let j = 0; j < this.multipleSelection.length; j++) {
          let aj = this.multipleSelection[j];
          let n = aj.id;
          if (n == id) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          result.push(obj);
        }
      }

      this.tableData = result;
      this.setGoods(result);
    },

    //改变购物车数量
    handleChange(num, index) {
      this.tableData[index].buynum = num;
      this.setGoods(this.tableData);
    },

    //删除
    del(index) {
      this.tableData.splice(index, 1);
      this.$message.success("删除成功!");
      this.setGoods(this.tableData);
    },

    // 点击结算
    settlement() {
      this.show = true;
    },

    //下单
    async pay() {
      if (this.goods.length < 0) {
        return;
      }

      if (this.multipleSelection.length == 0) {
        this.$message.error("请选择结算物品!");
        return;
      }

      this.show = false;

      let loading = Loading.service({
        body: true,
        text: "提交订单中",
      });

      let promiseAll = [];
      for (const key in this.multipleSelection) {
        let params = {
          num: this.multipleSelection[key]["buynum"],
          gid: this.multipleSelection[key]["id"],
          uid: this.userinfo.id,
          orderTime: new Date().toISOString().slice(0,16),
        };
        promiseAll.push(await api.addOrder(params));
        const newNum = this.multipleSelection[key]["goodsNum"] - this.multipleSelection[key]["buynum"]
        params={
          id: this.multipleSelection[key]["id"],
          goodsNum:newNum,
          goodsStatus: "上架中"
          }
        if(newNum <= 0){
          params.goodsStatus = "已下架"
        }
        api.updateGoods(params)
      }

      Promise.all(promiseAll).then((res) => {
        let result = [];
        for (let i = 0; i < this.tableData.length; i++) {
          let obj = this.tableData[i];
          let id = obj.id;
          let isExist = false;

          for (let j = 0; j < this.multipleSelection.length; j++) {
            let aj = this.multipleSelection[j];
            let n = aj.id;
            if (n == id) {
              isExist = true;
              break;
            }
          }
          if (!isExist) {
            result.push(obj);
          }
        }

        setTimeout(() => {
          loading.close();
          this.$message.success("下单成功!");
          this.tableData = result;
          this.setGoods(result);
        }, 1500);
      });
    },
  },
  computed: {
    ...mapGetters(["goods", "goodsNum", "userinfo"]),

    sumPrice() {
      let sum = 0;
      if (this.multipleSelection.length == 0) {
        return 0;
      }
      this.multipleSelection.map((item) => {
        sum += item.buynum * item.goodsValue;
      });
      return sum;
    },
  },
};
</script>

<style lang="scss" scoped>
.car {
  background-color: #f6f6f6;
  height: 100%;
}

.bg {
  background-size: 100% 100%;
}
.content {
  padding: 20px;
  width: 1200px;
  position: relative;
  left: 50%;
  margin-left: -600px;
  background-color: #fff;
  // box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  // height: 950px;
  overflow-y: scroll;
}

.btn {
  background-color: #c41a21;
  background: linear-gradient(90deg, #f23038 0%, #c41a21 100%);
  // height: 50px;
  // width: 200px;
}

.addr-row {
  width: 270px;
  height: 180px;
  border: 1px solid #e0e0e0;
}
.addr-active {
  border: 1px solid #c41a21;
}
.default {
  color: #b0b0b0;
}
</style>

