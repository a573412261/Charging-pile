// pages/user/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userinfo:{},
  },
  onShow(){
    const userinfo = wx.getStorageSync("userinfo");
    this.setData({
      userinfo
    });
  },

  goToLogin:function (options){
    wx.navigateTo({
      url: '/pages/components/login/login',
    });
  }
})