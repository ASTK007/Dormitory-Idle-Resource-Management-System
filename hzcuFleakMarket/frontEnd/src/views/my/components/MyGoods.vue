<template>
    
    <div class="usergoods">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
    </el-breadcrumb>

    <el-tabs class="mb20">
      <el-tab-pane label="全部" name=""></el-tab-pane>
    </el-tabs>
      <el-form ref="searchForm" :model="searchForm" inline size="medium" class="searchForm" label-width="80px">
        <el-form-item>
          <el-button @click="showDialog=true" icon="el-icon-plus" plain>新增商品</el-button>
        </el-form-item>
      </el-form>
  
      <el-card slot class="content-card" shadow="never">
        <el-table :data="list" border stripe style="width: 100%;min-height: 60vh;">
            <el-table-column prop="id" label="ID" header-align="center" align="center" width="70">
        </el-table-column>
          <el-table-column prop="goodsName" label="商品名" header-align="center" align="center" />
          <el-table-column prop="goodsType" label="商品类型" header-align="center" align="center" />
          <el-table-column prop="goodsDes" label="商品描述" header-align="center" align="center" />
          <el-table-column prop="goodsImg" label="商品图片" header-align="center" align="center">
            <template slot-scope="scope">
              <div>
                <img :src="scope.row.goodsImg" style="width:100px;height:100px;object-fit: cover;">
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="goodsStatus" label="上架/下架" header-align="center" align="center">
            <template slot-scope="scope">
              <div>
                <el-switch :disabled="uid!=''" v-model="scope.row.goodsStatus" active-color="#13ce66" inactive-color="#ff4949" active-value="上架中" inactive-value="已下架" @change="change(scope.row)" />
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="goodsValue" label="价格" header-align="center" align="center" />
          <el-table-column prop="goodsNum" label="数量" header-align="center" align="center" />
          <el-table-column prop="goods" label="操作" header-align="center" align="center" width="90px">
            <template slot-scope="scope">
              <div>
                <a style="color:rgb(31, 144, 254);" @click="edit(scope.row)">编辑</a>
                <br>
                <a style="color:rgb(31, 144, 254);" @click="del(scope.row)">删除</a>
              </div>
            </template>
          </el-table-column>
        </el-table>

      </el-card>
  
      <!-- 增加编辑 -->
      <Dialog :showDialog.sync="showDialog" title="" @cancal="close" @confirm="confirm" width="850">
        <div slot>
          <el-form ref="addForm" :model="addForm" label-width="120px" class="addForm" label-position="right" size="small">
            <el-form-item label="上传商品图片:">
              <el-upload class="avatar-uploader" :action="uploadUrl" :show-file-list="false" :on-success="imgSuccess" name="photo">
                <img v-if="addForm.goodsImg" :src="addForm.goodsImg" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="名称:">
              <el-input v-model="addForm.goodsName" placeholder="请输入名称"></el-input>
            </el-form-item>
            <el-form-item label="数量:">
              <el-input v-model="addForm.goodsNum" placeholder="请输入数量"></el-input>
            </el-form-item>
            <el-form-item label="价格:">
              <el-input v-model="addForm.goodsValue" placeholder="请输入价格"></el-input>
            </el-form-item>
            <el-form-item label="类型:">
              <el-select v-model="addForm.goodsType" placeholder="请选择类型" class="w100" :disabled="uid!=''">
                <el-option v-for="item in typelist" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="状态:">
              <el-radio label="上架中" :disabled="uid!=''" v-model="addForm.goodsStatus"></el-radio>
              <el-radio label="已下架" :disabled="uid!=''" v-model="addForm.goodsStatus"></el-radio>
            </el-form-item>
            <el-form-item label="商品详情:">
              <el-input v-model="addForm.goodsDes" placeholder="请输入商品详情" rows="8" type="textarea"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </Dialog>
  
    </div>
  </template>
  
  <script>
  import Middle from "@/components/Middle/index.vue";
  import Dialog from "@/components/Dialog/index.vue";
  import Pagination from "@/components/Pagination/index.vue";
  import Enduit from "@/components/WangEnduit/index.vue";
  import * as api from "@/api/api";
  import { mapGetters, mapMutations } from "vuex";

  export default {
    components: { Middle, Dialog, Pagination, Enduit },
    data() {
      return {
        url: process.env.VUE_APP_BASE_FILE,
        uploadUrl: "http://localhost:8081/upload",
        offset: 1, //当前页
        limit: 10, //每页大小
        total: 0, //总记录数
        hidden: false, //分页是否隐藏
        searchForm: {
          name: "",
        },
        typelist: ["数码", "服装", "日用品","家具","食品"],
        userlist: [],
        addForm: {
          goodsName: "",
          goodsType: "",
          goodsDes: "",
          goodsStatus: "已下架",
          goodsImg: "",
          goodsValue: "",
          goodsNum: "",
          uid: "",
        },
        uid: "",
        list: [],
        showDialog: false,
      };
    },
    created() {
      this.uid = sessionStorage.getItem("id")
        ? Number(sessionStorage.getItem("id"))
        : "";
      this.getData();
    },
    mounted() {},
    computed: {
    ...mapGetters(["userinfo"]),
  },
    methods: {
      //获取列表
      getData() {
        api
          .GoodsByUserId({
            pageNo: this.offset,
            size:9999,
            id:this.userinfo.id,
          })
          .then((res) => {
            this.list = res.records;
            this.total = res.total;
          });
      },
  
      // 修改状态
      change(e) {
        api.updateGoods(e).then((res) => {
          if (res == "更新成功") {
            this.$message.success("更新成功!");
            this.showDialog = false;
          } else {
            this.$message.error(res.msg);
          }
        });
      },
  
      //大图上传
      imgSuccess(response) {
        this.addForm.goodsImg = response
      },
  
      //模态框-关闭
      close() {
        this.showDialog = false;
        let time = setTimeout(() => {
          this.urlList = [];
          this.resetData("addForm", this); //重置数据
        }, 300);
      },
  
      //删除
      async del(item) {
        await api
          .deleteGoods(item.id)
          .then((res) => {
            if (res == "删除成功") {
              this.$message.success("删除成功!");
            } else {
              this.$message.error("删除失败");
            }
          });
  
        await this.getData();
      },
  
      // 新增/编辑-提交
      async confirm() {
            this.addForm.uid = this.userinfo.id
          await api.updateGoods(this.addForm).then(async (res) => {
          if (res == "更新成功") {
            this.$message.success("更新成功!");
            this.showDialog = false;
          } else {
            await api.addGoods(this.addForm).then((res) => {
          if (res == "插入成功") {
            this.$message.success("添加成功!");
            this.showDialog = false;
          } else {
            this.$message.error("操作失败");
          }
        });
          }
        });

        await this.getData();
      },
  
      //编辑
      edit(item) {
        this.addForm = JSON.parse(JSON.stringify(item)); //对象拷贝防止重置对象时污染
        this.showDialog = true;
      },
    },

  };
  </script>
  
  <style lang="scss" scoped>
  .usergoods {
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
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  </style>
  