<template>
  <div>
    <div v-if="notLogin" class="login-area">
      <login @loginsuccess="loginSuccess"></login>
    </div>
    <div v-else>
      <div  class="index-title">

      </div>
      <div style="border-radius: 10px;">

      </div>
    </div>
  </div>
</template>

<script>
import login from "../../components/index/login";
import accounts from "../../components/index/accounts";

export default {
  components: {
    login,
    accounts,
  },

  data () {
    return {
      notLogin:true
      }
    },

  onLoad(option){
    if (option.logout == 'true') {
      this.notLogin = true
    }
  },

  mounted(){
    if (wx.getStorageSync('userInfo')){
      this.notLogin = false
      //this.getReader()
    }else{
      wx.hideTabBar()
    }
  },

  methods :{
    loginSuccess(){
      this.notLogin = false
      wx.showTabBar()
      wx.showToast({
        title:'登录成功',
        icon: 'success',
        duration: 1000
      })
      //this.getReader()
    }
  },

  // getRecord () {
  //   var _this = this
  //   wx.request({
  //     url: config.accountUrl + '/all',
  //     data: _this.accountItem,
  //     method: 'GET',
  //     header: {
  //       'content-type': 'application/json'
  //     },
  //     success (res) {
  //       console.log('get record success')
  //       console.log(res.data)
  //       _this.$refs.accounts.items = res.data.reverse()
  //       _this.$refs.accounts.processItemInfo()
  //     }
  //   })
  // },

}
</script>

<style scoped lang="scss">
.index-title {
  height: 15vh;
  background-color: #322f3b;
  overflow: hidden;
}
.index-title > img {
  width: 100%;
  position: relative;
  top: -96px;
}
.login-area{
  height: 100vh;
  display: flex;
  justify-content: center;
  background-color: #e2e1e4;
}
</style>
