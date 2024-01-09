<template>
  <div class="footer">
    <div class="center">
    </div>
    <div class="project-introduction text-center "> 
        <h2 class="summary">本项目为一个校园二手市场交易平台项目，旨在为校内师生提供一个方便、安全、高效的平台，使学生能够买卖二手物品、交换信息，并建立一个互助分享的社交网络</h2><br><br>
      </div>
    <div class="footer-rows f-r f-j-s center">
      <ul class="f-c f-a-c f-j-c font15 font-black">
        <li class="font-black font18 mb20">帮助中心</li>
        <li class="mb10"><a href="https://www.baidu.com" target="_blank">百度</a></li>
        <li class="mb10"><a href="https://www.google.com" target="_blank">谷歌</a></li>
        <li class="mb10"><a href="https://www.bing.com" target="_blank">Bing</a></li>
      </ul>
      <ul class="f-c f-a-c font15 font-black">
        <li class="font-black font20 mb20">问题反馈</li>
        <li class="mb10"><a href="https://github.com/ASTK007/School-fleak-market.git" target="_blank">GitHub仓库</a></li>
        <li class="mb10">邮箱:154654561@qq.com</li>
        <li class="mb10">凑数用的</li>
      </ul>
      <ul class="f-c f-a-c font15 font-black">
        <li class="font-black font20 mb20">禁止事项</li>
        <li class="mb10">禁止违反法律法规</li>
        <li class="mb10">禁止发布广告</li>
        <li class="mb10">禁止发布违规商品</li>
      </ul>
      <ul class="f-c f-a-c font15 font-black">
        <li class="font-black font20 mb20">开发者们</li>
        <li class="mb10">111</li>
        <li class="mb10">Risona</li>
        <li class="mb10">晚枫〆</li>
      </ul>
    </div>
  </div>
</template>


<script>
//import x from ''
export default {
  components: {},
  data() {
    return {};
  },
  created() {},
  mounted() {},
  beforeDestroy() {},
  computed: {},
  methods: {}
};
</script>

<style lang='scss' scoped>
.footer {
  min-height: 150px;
  background: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 50px; /* Increase the top padding to move the footer up */

  li {
    cursor: pointer;
    &:hover {
      color: rgb(255, 115, 77);
    }
  }

  .center {
    width: 1200px;
    position: relative;
  }

  .footer-rows {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;

    ul {
      margin: 0;
    }
  }

  .project-introduction {
    .summary{
      text-align: center;
  max-width: 1000px; /* Increase the max-width as needed */
  margin: 0 auto;
  padding: 20px;
  font-weight: 30; /* Set a thinner font weight (e.g., 300 for light) */
  font-size: 20px; /* Set a smaller font size */
    }
}
}
</style>
