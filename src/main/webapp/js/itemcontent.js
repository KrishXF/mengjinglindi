let url_il = window.location.href;
let id_data = getUrlParams(url_il); //商品id
let id = id_data.id;
let orderid = '';
let openid = '';
let adid = '';
let cardid = '';
let count = '1';
let price = '';
let totalprice = '';
let remark = '';
let cardname = '';
let cardtype = '1';
$(function () {
  //防止点击穿透
  FastClick.attach(document.body);
  $.ajax({
    type: "POST",
    url: REQ_URL + "/user/getSessionOpenID.do", //获取openid
    data: {

    },
    dataType: "JSON",
    success: function (d) {
      openid = d.data;
      if(isNull(openid)){//测试
				openid = 'oqG24w-ceQ3wZlOUvj3MtNOLLUpo';
			}
      //获取商品信息
      $.ajax({
        type: "POST",
        url: REQ_URL + "/card/getCardInfo.do", //获取商品信息
        data: {
          id: id
        },
        dataType: "JSON",
        success: function (d) {
          let retCode = d.retCode;
          if (retCode == '10000') {
            let dt = d.data;
            if (typeof dt === 'object') {
              $('#item_name').html(dt.name);
              $('#item_sale').html(dt.price + "元");
              $('#item_date').html("有效期：" + dt.enddatestring);
              $('#item_last').html("库存量：" + dt.inventory + "张");
              $('#item_des').html(dt.introduc); //产品介绍
              cardid = dt.cardid;
              adid = openid;
              price = dt.price;
              totalprice = dt.price;
              remark = dt.remarks;
              cardname = dt.name;
            }

          }
        }
      });
    }
  });

  //获取config参数
  $.ajax({
    type: "POST",
    url: REQ_URL + "/getSign/getConfig.do", //获取appid,timestamp,nonceStr,signature
    data: {
      url: REQ_URL + '/page/itemcontent.html'
    },
    dataType: "JSON",
    success: function (d) {
      let retCode = d.retCode;
      if (retCode == '10000') {
        let dt = d.data;
        if (typeof dt === 'object') {
          //初始化微信config
          wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: dt.appId, // 必填，公众号的唯一标识
            timestamp: dt.timestamp, // 必填，生成签名的时间戳
            nonceStr: dt.nonceStr, // 必填，生成签名的随机串
            signature: dt.signature, // 必填，签名
            jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表  //支付
          });
        }
      }
    }
  });


  // //绑定增加数量方法
  // $('#add_').bind('click', function () {
  //   let num_ = $('#num_').html();
  //   num_ = parseInt(num_);
  //   num_++;
  //   $('#num_').html(num_);
  // });
  // //绑定减少数量方法
  // $('#minus_').bind('click', function () {
  //   let num_ = $('#num_').html();
  //   num_ = parseInt(num_);
  //   if (num > 1) {
  //     num_--;
  //     $('#num_').html(num_);
  //   }
  // });
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  //绑定购买方法
  $('#buy_btn').bind('click', function () {
    $.ajax({
      type: "POST",
      url: REQ_URL + "/order/insertOrder.do", //创建订单方法
      data: {
        adid: adid,
        cardid: cardid,
        count: count,
        price: price,
        totalprice: totalprice,
        remark: remark,
        cardname: cardname,
        cardtype: cardtype
      },
      dataType: "JSON",
      success: function (d) {
        let retCode = d.retCode;
        if (retCode == '10000') {
          let dt = d.data;
          orderid = dt;
          //跳转到列表 测试
          window.location.href = 'itemlist.html';
        }

        //微信支付//////////////////////////////////////////流程暂略
        // $.ajax({
        //   type: "POST",
        //   url: REQ_URL + "", //获取signature,prepay_id等
        //   data: {
        //     orderid: orderid
        //   },
        //   dataType: "JSON",
        //   success: function (d) {
        //     ////支付开始调起
        //     wx.chooseWXPay({
        //       timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
        //       nonceStr: '', // 支付签名随机串，不长于 32 位
        //       package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
        //       signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
        //       paySign: '', // 支付签名
        //       success: function (res) {
        //         // 支付成功后的回调函数
        //         //跳转到列表
        //         window.location.href = 'itemlist.html';
        //       }
        //     });
        //   }
        // });
        ////////////////微信支付结束///////////////////////////
      }
    });
  });
});