<template>
        <div class="userhome">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人中心</el-breadcrumb-item>
    </el-breadcrumb>

    <el-tabs class="mb20">
      <el-tab-pane label="全部" name=""></el-tab-pane>
    </el-tabs>

      <el-card class="user-info-card">
        <div slot="header" class="clearfix">
          <span>用户信息</span>
        </div>
        <div>
          <el-row>
            <el-col :span="12">
              <el-form label-position="right">
                <el-form-item label="用户ID">
                  <span>{{ userinfo.id }}</span>
                </el-form-item>
                <el-form-item label="用户名">
                  <span>{{ userinfo.userName }}</span>
                </el-form-item>
                <el-form-item label="手机号码">
                  <span>{{ userinfo.userPhonenum }}</span>
                </el-form-item>
                <el-form-item label="电子邮件">
                  <span>{{ userinfo.userEmail }}</span>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </el-card>
  
      <el-card class="current-time-card">
        <div slot="header" class="clearfix">
          <span>当前时间</span>
        </div>
        <div>
          <el-row>
            <el-col :span="12">
              <div class="current-time">{{ currentTime }}</div>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  import { mapGetters } from "vuex";
  
  export default {
    computed: {
      ...mapGetters(["userinfo"]),
    },
    data() {
      return {
        currentTime: "",
      };
    },
    beforeDestroy() {
  clearInterval(this.timer);
},
    methods: {
      // 更新当前时间的方法
      updateCurrentTime() {
        const now = new Date();
        const hours = now.getHours().toString().padStart(2, "0");
        const minutes = now.getMinutes().toString().padStart(2, "0");
        const seconds = now.getSeconds().toString().padStart(2, "0");
        this.currentTime = `${hours}:${minutes}:${seconds}`;
      },
    },
    mounted() {
  // 在页面加载时获取并显示当前时间
  this.updateCurrentTime();
  // 设置定时器每秒更新一次时间
  this.timer = setInterval(() => {
    this.updateCurrentTime();
  }, 1000);
},
  };
  </script>
  
  <style lang='scss' scoped>
  .userhome {
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
    .goods-right {
      .btn {
        text-align: center;
        width: 120px;
        height: 30px;
        line-height: 30px;
        color: #757575;
        border: 1px solid#e0e0e0;
      }
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