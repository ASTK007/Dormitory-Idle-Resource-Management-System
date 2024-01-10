<template>
  <div class="dashboard-container">
    <el-form ref="searchForm" :model="searchForm" inline size="medium" class="searchForm" label-width="80px">
      <el-form-item label="商品名称:">
        <el-input v-model="searchForm.name" placeholder="" style="width:250px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search()" icon="el-icon-search">查询</el-button>
        <el-button @click="addForm.bid=bid;showDialog=true" icon="el-icon-plus" plain>新增</el-button>
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
        <el-table-column prop="goodsStatus" label="状态" header-align="center" align="center">
          <template slot-scope="scope">
            <div>
              <el-switch :disabled="uid!=''" v-model="scope.row.goodsStatus" active-color="#13ce66" inactive-color="#ff4949" active-value="上架中" inactive-value="已下架" @change="change(scope.row)" />
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="goodsValue" label="价格" header-align="center" align="center" />
        <el-table-column prop="goodsNum" label="数量" header-align="center" align="center" />
        <el-table-column prop="uid" label="关联用户ID" header-align="center" align="center" />
        <el-table-column prop="goods" label="操作" header-align="center" align="center" width="190px">
          <template slot-scope="scope">
            <div>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="edit(scope.row)">编辑</a>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="del(scope.row)">删除</a>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <Pagination :limit.sync="limit" :total="total" :offset.sync="offset" :hidden="hidden" @changePage="getData" />
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
            <el-input v-model="addForm.goodsNum" type="number" placeholder="请输入数量"></el-input>
          </el-form-item>
          <el-form-item label="价格:">
            <el-input v-model="addForm.goodsValue" placeholder="请输入价格"></el-input>
          </el-form-item>
          <el-form-item label="类型:">
            <el-select v-model="addForm.goodsType" placeholder="请选择类型" class="w100" :disabled="uid!=''">
              <el-option v-for="item in typelist" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="关联用户:">
            <el-select v-model="addForm.uid" placeholder="请选择关联用户" class="w100" :disabled="uid!=''">
              <el-option v-for="(item,index) in userlist" :key="index" :label="item.userName" :value="item.id"></el-option>
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
    this.getBusiness();
  },
  mounted() {},
  methods: {
    //获取列表
    getData() {
      api
        .getGoodsList({
          pageNo: this.offset,
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
//搜索商品
    search() {
      api
        .GoodsByName({
          pageNo: this.offset,
          name: this.searchForm.name,
        })
        .then((res) => {
            this.list = res.records;
            this.total = res.total;
        });
    },

    //获取用户列表
    getBusiness() {
      api
        .getUserList({
          pageNo: 1,
          size: 9999,
        })
        .then((res) => {
          this.userlist = res.records;
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
      if(this.addForm.id != null){
        await api.updateGoods(this.addForm).then((res) => {
        if (res == "更新成功") {
          this.$message.success("更新成功!");
          this.showDialog = false;
        } else {
          flag = true
        }
      });
      }else{
        await api.addGoods(this.addForm).then((res) => {
        if (res == "插入成功") {
          this.$message.success("添加成功!");
          this.showDialog = false;
        } else {
          this.$message.error("操作失败");
          flag = true
        }
      });
      }
      await this.getData();
    },

    //编辑
    edit(item) {
      this.addForm = JSON.parse(JSON.stringify(item)); //对象拷贝防止重置对象时污染
      this.showDialog = true;
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
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
