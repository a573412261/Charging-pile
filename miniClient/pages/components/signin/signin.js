// pages/components/signin/signin.js
Page({

  /*
   * 页面的初始数据
   */
  
  data:{
    username:"",
    password:"",
  },

  // 登录按钮响应，以及判断填写内容是否符合规定
login:function(e){
  var that = this
  if (that.data.username == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入用户名！',
      showCancel:false,
      success (res) {
      }
    })
  } else if (that.data.password == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入密码！',
      showCancel:false,
      success (res) {
      }
    })
  } else{
    console.log("success")
    //登录成功，跳转至首页
    wx.switchTab({
      url: '/pages/components/index/index',
    })
  }
},
//跳转至注册页面
signupResponce:function(e){
  wx.navigateTo({
    url: '/pages/components/signup/signup',
  })
},
// 用户输入信息
  usernameInput:function(e){
    this.data.username = e.detail.value
    console.log(e.detail.value)
  },

  passwordInput:function(e){
   this.data. password = e.detail.value
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})