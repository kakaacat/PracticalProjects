<template>
  <div class="edit-container">
    <div class="edit-items" @click="deactivateTools">
      <div class="edit-item-tip">——·   收支金额   ·——</div>
      <div class="edit-item-val-type" @click.stop="callCalculator" @touchstart="touchStart" @touchend="touchEnd">
        <div :class="accountItem.type === 0 ? 'edit-item-type out' : 'edit-item-type in'">{{accountItem.type === 0 ? '支出' : '收入'}}</div>
        <div :class="accountItem.type === 0 ? 'edit-item-value out' : 'edit-item-value in'">{{accountItem.value}}</div>
      </div>

      <div>分类【主分类和子分类】</div>
      <div>描述</div>
      <div>日期</div>
      <div>支付方式</div>
    </div>



    <div class="cal-root">
      <calculator @res="getCalRes" v-if="showCalculator"></calculator>
    </div>

  </div>
</template>

<script>
import {Category} from "../../customConfig/catConfig";
import {payConfig} from "../../customConfig/payConfig";
import calculator from "../../components/add/calculator";

export default {
  name: "index",

  components:{
    calculator,
  },

  data(){
    return{
      payMethod: [],
      selectType:'',
      showCalculator: false,
      showSelector: false,
      accountItem: {
        type: 0,
        value : '0.00',
        category: Category[0].name,
        subCategory: Category[0].subCat[0].name,
        date:'',
        desc:'',
        pay:''
      },
      touchStartX:null
    }
  },

  created () {
    for (const item of payConfig) {
      this.payMethod.push({
        payEn: item.payEn,
        payCn: item.payCn,
        color: item.color,
        isSelected: false
      })
    }
  },

  mounted () {
    this.getToday()
  },

  methods: {

    getCalRes(res) {
      this.accountItem.value = res
    },

    getToday() {
      const today = new Date()
      this.accountItem.date = today.getFullYear() + '/' + (today.getMonth() + 1) + '/' + today.getDate()
    },

    deactivateTools() {
      if (this.showCalculator) {
        this.showCalculator = false
      }
      if (this.showSelector) {
        this.showSelector = false
        this.$refs.selector.close()
      }
    },

    callCalculator() {
      this.showCalculator = !this.showCalculator
      if (this.showCalculator) {
        this.showSelector = false
      }
    },

    touchStart(e) {
      this.touchStartX = e.pageX
      //console.log(e.pageX)
    },

    touchEnd(e){
      //console.log(e.mp.changedTouches[0].pageX)
      if (Math.abs(e.mp.changedTouches[0].pageX - this.touchStartX) > 50) {
        this.accountItem.type = 1 - this.accountItem.type
      }
    },

  },



}
</script>

<style scoped lang="scss">
.line {
  margin: 20px;
  border-bottom: 1px solid #cccccc;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
}
.edit-item-tip {
  text-align: center;
  font-size: 15px;
  color: #cccccc;
}
.edit-item-pay {
  ul {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    .deactivate-pay {
      background-color: rgba(0, 0, 0, 0.3)!important;
    }
    .pay {
      box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
      width: 25%;
      margin: 10px;
      color: white;
      text-align: center;
      border-radius: 4px;
      font-size: 12px;
      padding: 2px;
      font-weight: bolder;
    }
  }
}
.edit-container{
  padding: 10px;
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .edit-items {
    flex: 1 0 auto;
    border-radius: 4px;
    padding: 5px;
    .edit-item-desc {
      width: auto!important;
      margin-left: 20px;
      margin-right: 20px;
      padding: 20px;
      height: 60px;
    }
  }
  .cal-root {
    position: fixed;
    bottom: 10px;
    width: 90%;
    margin: 0 auto;
    left: 0;
    right: 0;
  }
}
.edit-item-val-type {
  margin-top: 10px;
  border: 1px solid white;
  border-radius: 4px;
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  font-weight: bolder;
  text-shadow: 0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
  display: flex;
  .edit-item-type {
    font-size: 12px;
    margin-left: 50px;
  }
  .edit-item-value {
    text-align: center;
    translate: -10%;
    flex: 1;
  }
}
.in {
  color: crimson;
}
.out {
  color: green;
}
.edit-item-cat {
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  :first-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  :last-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  div {
    font-weight: bolder;
    padding: 5px;
    border-radius: 4px;
    width: 30%;
    text-align: center;
    height: 30px;
    line-height: 30px;
  }
}
.picker-view {
  width: 100%;
  height: 300px;
}
.picker-view-col {
  line-height: 50px;
  text-align: center;
}
.log {
  text-align: center;
  border-radius: 4px;
  font-weight: bolder;
  background-color: deepskyblue;
  color: white;
  height: 30px;
  line-height: 30px;
  width: 80%;
  margin: auto;
  margin-top: 20px;
  border: 0;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
}

</style>
