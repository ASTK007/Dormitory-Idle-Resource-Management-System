<template>
  <div class="dashboard-container">
    <el-form ref="searchForm" :model="searchForm" inline size="medium" class="searchForm" label-width="80px">
      <el-form-item label="订单号:">
        <el-input v-model="searchForm.id" placeholder="" style="width:250px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search()" icon="el-icon-search">查询</el-button>
      </el-form-item>
    </el-form>

    <el-card slot class="content-card" shadow="never">
      <el-table :data="list" stripe border style="width: 100%;min-height: 60vh;">
        <el-table-column prop="id" label="ID" header-align="center" align="center" width="70">
        </el-table-column>
        <el-table-column prop="uid" label="用户ID" header-align="center" align="center" />
        <el-table-column prop="gid" label="商品ID" header-align="center" align="center" />
        <el-table-column prop="num" label="数量" header-align="center" align="center" />
        <el-table-column prop="orderTime" label="交易时间" header-align="center" align="center" />
        <el-table-column prop="address" label="操作" header-align="center" align="center" width="190px">
          <template slot-scope="scope">
            <div>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="del(scope.row)">删除</a>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <Pagination :limit.sync="limit" :total="total" :offset.sync="offset" :hidden="hidden" @changePage="getData" />
    </el-card>

  </div>
</template>

<script>
import Middle from "@/components/Middle/index.vue";
import Dialog from "@/components/Dialog/index.vue";
import Pagination from "@/components/Pagination/index.vue";
import * as api from "@/api/api";

export default {
  components: { Middle, Dialog, Pagination },
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      offset: 1, //当前页
      limit: 10, //每页大小
      total: 0, //总记录数
      hidden: false, //分页是否隐藏
      searchForm: {
        id: "",
      },
      list: [],
      showDialog: false,
    };
  },
  created() {
    this.getData();
  },
  mounted() {},
  methods: {
    //获取列表
    getData() {
      api
        .getOrderList({
          pageNo: this.offset
        })
        .then((res) => {
            this.list = res.records;
            this.total = res.total;
        });
    },
    search() {
      if(this.searchForm.id == ""){
        this.getData()
      }else{
        api
        .orderById(this.searchForm.id)
        .then((res) => {
          this.list = [res]
        });
      }
    },
    //模态框-关闭
    close() {
      this.showDialog = false;
      let time = setTimeout(() => {
        this.resetData("addForm", this); //重置数据
      }, 300);
    },

    //删除
    async del(item) {
      await api
        .deleteOrder(item.id)
        .then((res) => {
          if (res == "删除成功") {
            this.$message.success("删除成功!");
          } else {
            this.$message.error("删除失败");
          }
        });

      await this.getData();
    },

  },
  computed: {},
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
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
  width: 178px;
  height: 178px;
  line-height: 178px;
  border-radius: 50%;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  border-radius: 50%;
  display: block;
  object-fit: cover;
}
</style>
