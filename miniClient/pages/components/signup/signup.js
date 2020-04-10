// pages/components/signup/signup.js
Page({

  /**
   * 页面的初始数据
   */
  // 页面局部变量
 data:{
   username:"",
   phonenumber:"",
   password:"",
   passwordack:"",
 },
//  点击但会登录页面，即上一页
signinResponce:function(e){
  wx.navigateBack({
    data:1
  })
},

// 注册按钮响应，以及判断填写内容是否符合规定
regist:function(e){
  var that = this
  var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})(17[0-9]{1}))+\d{8})$/;
  if (that.data.username == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入用户名！',
      showCancel:false,
      success (res) {
      }
    })
  }
  // 判断手机号的合法性
  else if (that.data.phonenumber == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入手机号！',
      showCancel:false,
      success (res) {
      }
    })
  }else if (that.data.phonenumber.length != 11)
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入手机号长度有误，请重新输入！',
      showCancel:false,
      success (res) {}
    })
  }else if (!myreg.test(that.data.phonenumber)){
    wx.showModal({
      title: '温馨提示！',
      content: '请输入正确的手机号！',
      showCancel:false,
      success (res) {
      }
    })
  }
  
  else if (that.data.password == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入密码！',
      showCancel:false,
      success (res) {
      }
    })
  } else if (that.data.passwordack == "")
  {
    wx.showModal({
      title: '温馨提示！',
      content: '请输入确认密码！',
      showCancel:false,
      success (res) {
      }
    })
  }else if (that.data.password != that.data.passwordack )
  {
    wx.showModal({
      title: '温馨提示！',
      content: '两次输入密码不一致！',
      showCancel:false,
      success (res) {
      }
    })
  } 
  else{
    console.log("success")
  }
},
// 获取用户填写的信息
 usernameInput:function(e){
   console.log(e.detail.value)
   this.data.username = e.detail.value
   console.log(e.detail.value)
 },

 phonenumberInput:function(e){
  this.data.phonenumber = e.detail.value
 },
 passwordInput:function(e){
  this.data. password = e.detail.value
 },
 passwordInputAck:function(e){
  this.data.passwordack = e.detail.value
 },
   /* 生命周期函数--监听页面加载
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