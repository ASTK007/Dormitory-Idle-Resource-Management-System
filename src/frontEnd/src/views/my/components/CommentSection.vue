<template>
  <div class="comment-section">
    <div v-if="list.length === 0">暂无评论</div>
    <div v-else>
      <div v-for="comment in list" :key="comment.id" class="comment-item">
        <p>{{ comment.commentContent }}</p>
        <p>{{ comment.commentTime }}</p>
        <!-- 这里可以添加更多的评论细节，比如用户信息等 -->
      </div>
    </div>

    <div class="comment-input">
      <el-input v-model="newComment" placeholder="输入你的评论"></el-input>
      <el-button type="primary" @click="submitComment()">提交评论</el-button>
    </div>
  </div>
</template>

<script>
import { AppMain } from '@/layout/components';
import { mapGetters, mapMutations } from "vuex";
import dayjs from "dayjs";
import * as api from "@/api/api";

export default {
  props: {
    itemId: {
      type: String,
      required: true
    },
    list: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      newComment: ""
    };
  },
  computed: {
    ...mapGetters(["userinfo"]),
  },
  methods: {
    submitComment() {
      if(this.userinfo.id == null){
        this.$message.error("请先登录")
      }else{
      const newCommentData = {
        uid: this.userinfo.id,
        commentContent: this.newComment,
        commentTime: dayjs(new Date()).format("YYYY-MM-DD hh:mm:ss"), 
        gid: this.itemId
      };
      api.addComment(newCommentData);
      // 更新评论列表
      this.$emit("update-comments", [...this.list, newCommentData]);

      // 清空输入框
      this.newComment = "";
      }

    }
  }
};
</script>

<style scoped>
.comment-section {
  margin-top: 20px;
}

.comment-item {
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}

.comment-input {
  margin-top: 20px;
}
</style>
