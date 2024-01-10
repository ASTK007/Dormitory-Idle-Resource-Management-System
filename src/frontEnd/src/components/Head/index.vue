<template>
  <div class="head f-r f-j-c f-a-c">
    <div style="width:1200px" class="f-r f-a-c f-j-s h100">
      <div class="tab-row h100 f-r ">
        <div class="tab mr10 h100 p-r" v-for="(item,index) in tab" :key="index" v-show="isShow(item.login)" @click="$router.push(item.url)">{{item.name}}
        </div>
      </div>

      <div class="font14 login-register f-r f-a-c">
        <div v-if="Object.keys(userinfo).length!=0" class="mr20">欢迎 <span class="font-white">{{userinfo.userName}}</span> </div>
        <div class="zs mr10" v-if="!Object.keys(userinfo).length!=0" @click="$router.push('/login')">后台管理</div>
        <div class="zs" v-if="!Object.keys(userinfo).length!=0" @click="types='登录';show=true">登录</div>
        <div class="zs ml10" v-if="!Object.keys(userinfo).length!=0" @click="types='注册';show=true">注册</div>
        <div class="zs" v-if="Object.keys(userinfo).length!=0" @click="types='修改信息',fullInfo();show=true">修改个人信息</div>
        <div style="margin-right: 10px;"></div>
        <div class="zs" v-if="Object.keys(userinfo).length!=0" @click="loginout">退出</div>
        <div class="zs ml20 f-r f-a-c f-j-c font-snow" v-if="Object.keys(userinfo).length!=0" @click="$router.push('/car')" style="background: #c41a21;width:100px;height:100%">购物车({{goodsNum}})</div>
      </div>

      <el-dialog title="" :visible.sync="show" class="login" width="400px">
        <div v-if="types=='注册'">
          <div class="t-c font17 font-default">注册</div>
          <el-form ref="addForm" :model="addForm" class="addForm pl50 pr50 pb10 pt40" label-position="right" size="small">
            <el-form-item label="">
              <el-input v-model="addForm.userName" maxlength="64" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="" prop="userPasswd" required :rules="passwordRules">
              <el-input v-model="addForm.userPasswd" type="password" maxlength="32" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="" prop="userPasswd" required :rules="passwordRules">
              <el-input v-model="repwd" type="password" maxlength="32" placeholder="重新输入密码"></el-input>
            </el-form-item>
            <el-form-item label="" prop="userPhonenum" required :rules="phoneRules">
              <el-input v-model="addForm.userPhonenum" type="tel" maxlength="11" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="" prop="userEmail" required :rules="emailRules">
              <el-input v-model="addForm.userEmail" type="email" maxlength="32" placeholder="请输入邮箱"></el-input>
            </el-form-item>
          </el-form>
          <div class="font-default font14 pl50 pr50 t-r mb10 zs" @click="types='登录'">已有账号?登录</div>
          <div class="pb40 t-c pl50 pr50">
            <el-button class="w100" @click="register">注册</el-button>
          </div>
        </div>

        <div v-if="types=='登录'">
          <div class="t-c font17 font-default">登录</div>
          <el-form ref="loginForm" :model="addForm" class="addForm pl50 pr50 pt40" label-position="right" size="small">
            <el-form-item label="">
              <el-input v-model="addForm.id" type="number" placeholder="请输入ID"></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input v-model="addForm.userPasswd" type="password" maxlength="32" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-form>
          <div class="font-default font14 pl50 pr50 t-r mb10 zs f-r f-a-c f-j-s mb40">
            <span @click="types='注册'">没有账号?注册</span>
          </div>
          <div class="pb40 t-c pl50 pr50">
            <el-button class="w100" @click="login">登录</el-button>
          </div>
        </div>
        <div v-if="types=='修改信息'">
    <div class="t-c font17 font-default">修改个人信息</div>
    <el-form ref="updateForm" :model="userInfoForm" class="addForm pl50 pr50 pb10 pt40" label-position="right" size="small">
      <el-form-item label="">
        <el-input v-model="userInfoForm.userName" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="" prop="userPasswd" required :rules="passwordRules">
        <el-input v-model="userInfoForm.userPasswd" type="password" maxlength="32" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="" prop="userPasswd" required :rules="passwordRules">
        <el-input v-model="rePasswd" type="password" maxlength="32" placeholder="重新输入密码"></el-input>
      </el-form-item>
      <el-form-item label="" prop="userPhonenum" required :rules="phoneRules">
        <el-input v-model="userInfoForm.userPhonenum" type="tel" maxlength="11" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="" prop="userEmail" required :rules="emailRules">
        <el-input v-model="userInfoForm.userEmail" type="email" maxlength="32" placeholder="请输入邮箱"></el-input>
      </el-form-item>
    </el-form>
    <div class="pb40 t-c pl50 pr50">
      <el-button class="w100" @click="update">确认</el-button>
    </div>
  </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import * as api from "@/api/api";

export default {
  components: {},
  props: ["showSearch", "carNum"],
  data() {
    return {
      url: process.env.VUE_APP_BASE_FILE,
      step: 1,
      types: "注册",
      show: false,
      addForm: {
        id: "",
        userName: "",
        userType: "",
        userPasswd: "",
        userPhonenum: "",
        userEmail: "",
      },
      userInfoForm: {
        userName: "",
        userPasswd: "",
        userPhonenum: "",
        userEmail: "",
      },
      repwd: "",
      rePasswd: "",
      activeTab: this.$route.path,
      isLogin: sessionStorage.getItem("id") != undefined ? true : false,
      name: sessionStorage.getItem("userName"),
      searchName: "",
      tab: [
        {
          name: "首页",
          url: "/home",
        },
        {
          name: "个人中心",
          url: "/my",
          login: true,
        },
      ],
      emailRules: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (value && !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(value)) {
              callback(new Error('请输入有效的邮箱地址'));
            } else {
              callback();
            }
          },
          trigger: ['blur', 'change'],
        },
      ],
      phoneRules: [
        { required: true, message: '请输入手机号', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (value && !/^\d{11}$/.test(value)) {
              callback(new Error('请输入11位数字的手机号'));
            } else {
              callback();
            }
          },
          trigger: ['blur', 'change'],
        },
      ],
      passwordRules: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (value && /^.{3,}$/.test(value)) {
              callback();
            } else {
              callback(new Error('密码至少3位'));
            }
          },
          trigger: 'blur',
        },
      ],
    };
  },
  created() {
    if (localStorage.getItem("userinfo")) {
      this.setUserInfo(JSON.parse(localStorage.getItem("userinfo")));
    }
    if (localStorage.getItem("car")) {
      this.setGoods(JSON.parse(localStorage.getItem("car")));
    }
  },
  mounted() {},
  beforeDestroy() {},
  computed: {
    ...mapGetters(["goods", "goodsNum", "userinfo"]),

    //是否显示tab
    isShow() {
      return (login) => {
        if (login && Object.keys(this.userinfo).length == 0) {
          return false;
        }
        return true;
      };
    },
  },
  methods: {
    ...mapMutations(["setUserInfo", "setGoods"]),
    imgSuccess(file) {
      this.addForm.img = file.data;
    },

    // 登录
    login() {
      if (this.addForm.id == "" || this.addForm.userPasswd == "") {
        this.$message.error("请输入账号密码!");
        return;
      }
      api
        .userLogin({
          id: this.addForm.id,
          password: this.addForm.userPasswd,
        })
        .then((res) => {
          if (res == true) {
            api.UserById(this.addForm.id)
            .then((response) => {
              this.setUserInfo(response);
            })
            this.show = false;
            this.$message.success("登录成功!");
          } else {
            this.$message.error("账号密码错误!");
          }
        });
    },

    // 注册
    async register() {
      // 非空判断
      try {
        if (this.repwd == "") {
          throw new Error("请填写所有信息");
        }
        if(this.addForm.userName == "" || this.addForm.userPasswd == ""){
          this.$message.error("请至少填写用户名和密码");
        }
      } catch (err) {
        this.$message.error("请填写所有信息");
        return;
      }

      //密码
      if (this.addForm.userPasswd != this.repwd) {
        this.$message.error("两次输入密码不一致");
        return;
      }
      this.addForm.userType = "user"
      api.addUser(this.addForm).then((res) => {
        if (res == "插入成功") {
          api.getNewUserId()
          .then((response) => {
            this.$message.success("注册成功!id为:"+response);
            this.addForm.id = response
            })
            this.addForm.userName ="",
            this.addForm.userPasswd = "",
            this.addForm.userPhonenum = "",
            this.addForm.userEmail = "",
          this.repwd = "";
          this.types = "登录";
        } else {
          this.$message.error("注册失败!");
        }
      });
    },
    //自动填写对话框
    fullInfo(){
      this.userInfoForm = { ...this.userinfo };
    },

        // 修改信息
        async update() {
          this.$refs.updateForm.validate(valid => {
        if (valid) {
                //密码
      if (this.userInfoForm.userPasswd != this.rePasswd) {
        this.$message.error("两次输入密码不一致");
        return;
      }
          api.updateUser(this.userInfoForm).then((res) => {
            if (res == "更新成功") {
              this.$message.success("修改成功");
              this.setUserInfo(this.userInfoForm);
            }else{
              this.$message.error("更新失败");
            }
      });
          console.log('表单校验通过');
        } else {
          // 表单校验未通过
          console.log('表单校验未通过');
        }
      });
    },
    loginout() {
      this.setUserInfo({});
      localStorage.clear();
      sessionStorage.clear();
      this.$router.push("/home");
    },
  },
  watch: {
  types() {
      this.addForm.userName ="",
      this.addForm.userPasswd = "",
      this.addForm.userPhonenum = "",
      this.addForm.userEmail = "",
      this.repwd = "";
      this.step = 1;

  },
},

};
</script>

<style lang='scss' scoped>
.head {
  height: 35px;
  background: #f23038;
  width: 100%;
  box-shadow: 0 1px 3px rgba(26, 26, 26, 10%);
  .tab {
    position: relative;
    height: 100%;
    line-height: 35px;
    font-size: 14px;
    cursor: pointer;
    border-bottom: 2px solid transparent;
    color: #ffead0;
  }
  .login-register {
    color: #ffead0;
    height: 100%;
  }
  .tab-active {
    color: #409eff;
    &::before {
      content: " ";
      width: 100%;
      position: absolute;
      font-weight: 600;
      bottom: 0;
      left: 0;
      height: 2px;
      background-color: #409eff;
    }
  }
}

.login {
  ::v-deep .el-dialog__body {
    padding: 0;
    >>> .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader {
      text-align: center;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409eff;
    }
    .avatar-uploader-icon {
      border: 1px #c7c7c7 dashed;
      border-radius: 10px;
      font-size: 28px;
      color: #8c939d;
      width: 100px;
      height: 100px;
      line-height: 100px;
      border-radius: 50%;
      text-align: center;
    }
    .avatar {
      width: 100px;
      height: 100px;
      display: block;
      border-radius: 50%;
    }
  }
}
</style>