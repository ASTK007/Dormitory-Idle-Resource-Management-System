<template>
  <div class="dashboard-container">
    <h1 class="dashboard-title">后台管理系统</h1>
    <div class="admin-info">
      <h2 class="admin-welcome">欢迎您，管理员 {{ adminInfo.userName }}</h2>
      <div class="admin-details">
        <p>当前管理员信息：</p>
        <p>ID: {{ adminInfo.id }}</p>
        <p>用户名: {{ adminInfo.userName }}</p>
        <p>手机号码: {{ adminInfo.userPhonenum || '暂无' }}</p>
        <p>邮箱: {{ adminInfo.userEmail || '暂无' }}</p>
      </div>
    </div>
  </div>
</template>

<script>

import { Main } from 'element-ui';
import { eventBus } from "../../../main"
export default {
  data() {
    return {
      adminInfo: null,
    };
  },

  mounted() {
  // 在组件创建时订阅自定义事件
  eventBus.$on('login-success', this.handleLoginSuccess);

  // 获取管理员信息
  this.retrieveAdminInfo();
},

beforeDestroy() {
  // 在组件销毁前取消订阅自定义事件，防止内存泄漏
  eventBus.$off('login-success', this.handleLoginSuccess);
},
  methods: {
    handleLoginSuccess(adminInfo) {
      // 处理登录成功事件，更新管理员信息
      this.adminInfo = adminInfo;
    },
    retrieveAdminInfo() {
      // 获取管理员信息的逻辑
      const adminInfoString = sessionStorage.getItem("adminInfo");
        this.adminInfo = JSON.parse(adminInfoString);
    },
  },
};
</script>


<style lang="scss" scoped>
.dashboard-container {
  margin: 30px;
  text-align: center;

  .dashboard-title {
    font-size: 60px;
    margin-bottom: 40px;
  }

  .admin-info {
    max-width: 600px;
    margin: 0 auto;

    .admin-welcome {
      font-size: 35px; 
      margin-bottom: 35px;
    }

    .admin-details {
      font-size: 28px; 
      text-align: left;
      p {
        margin-bottom: 20px;
        margin-left: 200px;
      }
    }
  }
}
</style>