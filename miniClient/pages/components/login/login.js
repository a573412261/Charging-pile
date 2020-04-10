// pages/login/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
   //登陆成功后，跳转到主页
login:function(options){
  wx.switchTab({
    url: 'pages/components/index/index',
  })
},
//   handleGetUserInfo(e){
//     // console.log(e);

//     const {userInfo} = e.detail;
//     wx.setStorageSync("userinfo", userInfo);
//     wx.navigateBack({
//       delta: 1
//     });
//   }
 })