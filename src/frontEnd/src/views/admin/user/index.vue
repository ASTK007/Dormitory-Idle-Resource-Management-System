<template>
  <div class="dashboard-container">
    <el-form ref="searchForm" :model="searchForm" inline size="medium" class="searchForm" label-width="80px">
      <el-form-item label="用户名称:">
        <el-input v-model="searchForm.name" placeholder="" style="width:250px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search()" icon="el-icon-search">查询</el-button>
        <el-button @click="showDialog=true" icon="el-icon-plus" plain>新增</el-button>
      </el-form-item>
    </el-form>

    <el-card slot class="content-card" shadow="never">
      <el-table :data="list" stripe border style="width: 100%;min-height: 60vh;">
        <el-table-column prop="id" label="ID" header-align="center" align="center" width="70">
        </el-table-column>
        <el-table-column prop="userName" label="名称" header-align="center" align="center" />
        <el-table-column prop="userType" label="类型" header-align="center" align="center" />
        <el-table-column prop="userPasswd" label="密码" header-align="center" align="center" />
        <el-table-column prop="userPhonenum" label="手机号" header-align="center" align="center" />
        <el-table-column prop="userEmail" label="邮箱" header-align="center" align="center" />
        <el-table-column prop="address" label="操作" header-align="center" align="center" width="190px">
          <template slot-scope="scope">
            <div>
              <a style="color:rgb(31, 144, 254);" @click="edit(scope.row)">编辑</a>
              <a style="margin-left:20px;color:rgb(31, 144, 254);" @click="del(scope.row)">删除</a>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <Pagination :limit.sync="limit" :total="total" :offset.sync="offset" :hidden="hidden" @changePage="getData" />
    </el-card>

    <!-- 增加编辑 -->
    <Dialog :showDialog.sync="showDialog" title="" @cancal="close" @confirm="confirm()">
      <div slot>
        <el-form ref="addForm" :model="addForm" label-width="120px" class="addForm" label-position="right" size="small">
          <el-form-item label="名称:">
            <el-input v-model="addForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="类型:">
            <el-input v-model="addForm.userType"></el-input>
          </el-form-item>
          <el-form-item label="密码:">
            <el-input v-model="addForm.userPasswd" type="password"></el-input>
          </el-form-item>
          <el-form-item label="手机号:">
            <el-input v-model="addForm.userPhonenum"></el-input>
          </el-form-item>
          <el-form-item label="邮箱:">
            <el-input v-model="addForm.userEmail"></el-input>
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
        name: "",
      },
      addForm: {
        userName: "",
        userType: "",
        userPasswd: "",
        userPhonenum: "",
        userEmail: "",
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
        .getUserList({
          pageNo: this.offset
        })
        .then((res) => {
            this.list = res.records;
            this.total = res.total;
        });
    },
    search(){
      if(this.searchForm.name == ""){
        this.getData()
      }else{
        api
        .userByName({
          pageNo: this.offset,
          name: this.searchForm.name,
        })
        .then((res) => {
            this.list = res.records;
            this.total = res.total;
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
        .deleteUser(item.id)
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
        await api.updateUser(this.addForm).then((res) => {
        if (res == "更新成功") {
          this.$message.success("更新成功!");
          this.showDialog = false;
        } else {
          flag = true
        }
      });
      }else{
        await api.addUser(this.addForm).then((res) => {
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
