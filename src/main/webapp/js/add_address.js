let url_il = window.location.href;
let url_data = getUrlParams(url_il); //商品id
let encrypt_code = url_data.encrypt_code;
let card_id = url_data.card_id;
let openid = url_data.openid;
let code = '';
let codelist = [];
let data_json = {};
let citylist = {};
let provincelist = {};
let regionlist = {};
let provincecode = '';
let citycode = '';
let regioncode = '';
let arealist = {
  '1': {
    name: '浙江',
    child: {
      '1': {
        name: '宁波',
        child: {
          '1': '江东区',
          '2': '江北区',
          '3': '镇海区',
        }
      },
      '2': {
        name: '杭州',
        child: {
          '1': '西湖区',
          '2': '滨江区',
          '3': '拱墅区',
        }
      },
      '3': {
        name: '绍兴',
        child: {
          '1': '越城区',
          '2': '柯桥区',
          '3': '上虞区',
        }
      },
    }
  },
  '2': {
    name: '江苏',
    child: {
      '1': {
        name: '南京',
        child: {
          '1': '鼓楼区',
          '2': '栖霞区',
          '3': '雨花台区',
        }
      },
      '2': {
        name: '徐州',
        child: {
          '1': '鼓楼区',
          '2': '云龙区',
          '3': '贾汪区',
        }
      },
      '3': {
        name: '南京',
        child: {
          '1': '钟楼区',
          '2': '天宁区',
          '3': '新北区',
        }
      }
    }
  },
  '3': {
    name: '上海',
    child: {
      '1': '黄浦区',
      '2': '浦东新区',
      '3': '松江区'
    }
  }
};
$(function () {
  //防止点击穿透
  FastClick.attach(document.body);
  if (isNull(openid)) { //测试
    openid = 'oqG24w-ceQ3wZlOUvj3MtNOLLUpo';
  }
  FastClick.attach(document.body);
  $('#btn_confirm').bind('click', function () {
    //核销
    $.ajax({
      type: "POST",
      url: REQ_URL + "/wexinCard/createComsume.do",
      data: {
        code: codelist
      },
      dataType: "JSON",
      success: function (d) {
        let retCode = d.retCode;
        if (retCode == '10000') {
          let dt = d.data;
        }
        //根据codelist批量修改订单状态
        let codeliststring = "";
        for (let i = 0; i < codelist.length; i++) {
          codeliststring += codelist[i] + ",";
        }
        //codestring
        codeliststring = codeliststring.substr(0, length - 1);
        //地址信息
        let detailaddress = `${$('#user_area').find("option:selected").text()=="请选择"?"":$('#user_area').find("option:selected").text()+"-"}
        ${$('#user_area2').find("option:selected").text()=="请选择"?"":$('#user_area2').find("option:selected").text()+"-"}
        ${$('#user_area3').find("option:selected").text()=="请选择"?"":$('#user_area').find("option:selected").text()+"-"}
        ${$('#user_adr').val()}`;
        let u_name = $('#user_name');
        let u_phone = $('#user_phone');
        let u_remark = $('#user_comment');
        //根据codeliststring 修改订单状态和添加地址信息
        $.ajax({
          type: "POST",
          url: REQ_URL + "/order/updateOrderGoodsList.do",
          data: {
            str: codeliststring,
            detailaddress: detailaddress,
            name: u_name,
            phone: u_phone,
            goodsremark: u_remark,
          },
          dataType: "JSON",
          success: function (d) {
            if (d.retCode == '10000') {
              window.location.href = 'itemlist.html';
            }
          }
        });
      }
    });
    //
  });
  //调用解码接口获取真code
  $.ajax({
    type: "POST",
    url: REQ_URL + "/wexinCard/cardDeciphering.do", ///解码接口
    data: {
      encrypt_code: encrypt_code
    },
    dataType: "JSON",
    success: function (d) {
      let retCode = d.retCode;
      if (retCode == '10000') {
        let dt = d.data;
        code = dt;
        codelist.push(code);
        //根据code获取订单信息
        $.ajax({
          type: "POST",
          url: REQ_URL + "/order/getOrderInfoByWxCode.do", //根据code获取订单信息接口
          data: {
            WXCode: code
          },
          dataType: "JSON",
          success: function (d) {
            let retCode = d.retCode;
            if (retCode == '10000') {
              let dt = d.data;
              if (typeof dt === 'object') {
                $('#itemname_main').html(dt.cardname);
                $('#price_main').html(dt.price);

              }
            }
          }
        });
      }
    }
  });
  //获取config参数
  $.ajax({
    type: "POST",
    url: REQ_URL + "/getSign/getConfig.do",
    data: {
      url: REQ_URL + '/page/add_address.html'
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
            jsApiList: ['chooseCard', 'openAddress'] // 必填，需要使用的JS接口列表  
          });
        }
      }
    }
  });
  //初始化页面商品
  let itemrowlist = $('.card_item_container').find('.item_container');
  for (let i = 0; i < itemrowlist.length; i++) {
    $(itemrowlist[i]).bind('click', function () {
      if ($(this).attr('cs') == 'cs') {
        $(this).find('img').attr('src', '../img/addCard-s2.png');
        $(this).attr('cs', 'uncs');
      } else {
        $(this).find('img').attr('src', '../img/addCard-s1.png');
        $(this).attr('cs', 'cs');
      }
    });
  }
});

wx.ready(function () {
  // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
  // 所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。
  // 对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
  // console.log('success');
  // //获取cardSign等信息
  // $.ajax({
  //   type: "POST",
  //   url: REQ_URL + "getSign/getCardListParam.do", //获取cardSign等信息
  //   data: {

  //   },
  //   dataType: "JSON",
  //   success: function (d) {
  //     let retCode = d.retCode;
  //     if (retCode == '10000') {
  //       let dt = d.data;
  //       if (typeof dt === 'object') {

  //       }
  //     }

  //   }
  // });



});
//初始化地址
function initprovincelist(arealist) {
  for (key in arealist) {
    let str_p = `<option class="form-control" province='${key}' onclick="initcitylist('${key}')">${arealist[key].name}</option>`;
    document.getElementById('user_area').innerHTML += str_p;
  }
}

function initcitylist(provincecode_) {
  citylist = {};
  regionlist = {};
  provincecode = provincecode_;
  document.getElementById('user_area2').innerHTML = `<option class="form-control" city='-1' selected="selected">请选择</option>`;
  if (provincecode != '-1') {
    for (key in arealist) {
      if (key == provincecode) {
        citylist = arealist[key]['child'];
        break;
      }
    }
    for (key in citylist) {
      let str_c = `<option class="form-control" city='${key}' onclick="initregionlist('${key}')">${citylist[key].name}</option>`;
      document.getElementById('user_area2').innerHTML += str_c;
    }
  }
}

function initregionlist(citycode_) {
  regionlist = {};
  citycode = citycode_;
  document.getElementById('user_area3').innerHTML = `<option class="form-control" region='-1' selected="selected">请选择</option>`;
  if (citycode != '-1') {
    for (key in citylist) {
      if (key == citycode) {
        regionlist = citylist[key]['child'];
        break;
      }
    }
    for (key in regionlist) {
      let str_r = `<option class="form-control" region='${key}')">${regionlist[key].name}</option>`;
      document.getElementById('user_area3').innerHTML += str_r;
    }
  }
}
//拉取用户地址列表
wx.openAddress({
  success: function (res) {
    $('#user_name').val(res.userName); // 收货人姓名
    $('#user_phone').val(res.telNumber); // 收货人手机号码

    $('#user_adr').val(res.provinceName + " " + res.cityName + " " + res.countryName + " " + res.detailInfo); // 详细收货地址信息
    //根据微信地址耦合级联下拉框
    //获取省
    for (key in arealist) {
      if (arealist[key] == res.provinceName) {
        provincecode = key;
        citylist = arealist[key]['child'];
        break;
      }
    }
    //选择省，并且初始化市列表
    $("#user_area").find(`option:contains('${res.provinceName}')`).attr("selected", true);
    for (key in citylist) {
      let str_c = `<option class="form-control" city='${key}' onclick="initregionlist('${key}')">${citylist[key].name}</option>`;
      document.getElementById('user_area2').innerHTML += str_c;
    }
    //获取市
    for (key in citylist) {
      if (citylist[key] == res.cityName) {
        citycode = key;
        regionlist = citylist[key]['child'];
        break;
      }
    }
    //选择市，并且初始化区
    $("#user_area2").find(`option:contains('${res.cityName}')`).attr("selected", true);
    for (key in regionlist) {
      let str_r = `<option class="form-control" region='${key}')">${regionlist[key].name}</option>`;
      document.getElementById('user_area3').innerHTML += str_r;
    }
    //获取区
    for (key in regionlist) {
      if (regionlist[key] == res.countryName) {
        regioncode = key;
        break;
      }
    }
    //选择区
    $("#user_area3").find(`option:contains('${res.countryName}')`).attr("selected", true);

    // var postalCode = res.postalCode; // 邮编
    // var nationalCode = res.nationalCode; // 收货地址国家码

  }
});
//添加卡券方法
function addcard() {
  $.ajax({
    type: "POST",
    url: REQ_URL + "/getSign/getCardListParam.do",
    data: {
      card_id: card_id
    },
    dataType: "JSON",
    success: function (d) {
      if (d.retCode == '10000') {
        let dt = d.data;
        //拉取用户所有卡券
        wx.chooseCard({
          shopId: '', // 门店Id
          cardType: '', // 卡券类型
          cardId: dt.cardId, // 卡券Id
          timestamp: dt.timestamp, // 卡券签名时间戳
          nonceStr: dt.nonceStr, // 卡券签名随机串
          signType: dt.signType, // 签名方式，默认'SHA1'
          cardSign: dt.cardSign, // 卡券签名
          success: function (res) {
            var cardList = res.cardList; // 用户选中的卡券列表信息
            alert(JSON.stringify(cardList));
            // for (let i = 0; i < cardList.length; i++) {
            //   let encrypt_code_this = cardList[i].encrypt_code;
            //   let card_id_this = cardList[i].card_id;
            //   //解码code
            //   $.ajax({
            //     type: "POST",
            //     url: REQ_URL + "/wexinCard/cardDeciphering.do",
            //     data: {
            //       encrypt_code: encrypt_code_this
            //     },
            //     dataType: "JSON",
            //     success: function (d) {
            //       let retCode = d.retCode;
            //       if (retCode == '10000') {
            //         let dt = d.data;
            //         if (typeof dt === 'object') {
            //           let code_this = dt.code;
            //           //根据code获取订单信息
            //           $.ajax({
            //             type: "POST",
            //             url: REQ_URL + "/order/getOrderInfoByWxCode.do", //根据code获取订单信息接口
            //             data: {
            //               WXCode: code_this
            //             },
            //             dataType: "JSON",
            //             success: function (d) {
            //               let retCode = d.retCode;
            //               if (retCode == '10000') {
            //                 let dt = d.data;
            //                 if (typeof dt === 'object') {
            //                   data_json[dt.wxcode] = {
            //                     "name": dt.cardname,
            //                     "price": dt.price
            //                   };
            //                   let str = '';
            //                   str = `<div class="row item_container" code='${dt.wxcode}'  onclick='choosecard(${dt.wxcode})'>
            //             <div class="col-xs-6 item_row">
            //                 <span>${dt.cardname}</span>
            //             </div>
            //             <div class="col-xs-6 item_row text-right col-xs-pull-1">
            //                 <img src="../img/addCard-s2.png">
            //             </div>
            //             <div class="col-xs-12 price_container">
            //               <span>零售价：${dt.price}元</span>
            //             </div>
            //         </div>`;
            //                   document.getElementById('cardlist_container').innerHTML += str;
            //                 }
            //               }
            //             }
            //           });
            //         }
            //       }
            //     }
            //   });

            // }
          }
        });
      }
    }
  });

//   let str = `<div class="row item_row">
//   <div class="col-xs-6 col-xs-push-1">
//       <span>商品名AAAAAAA</span>
//   </div>
//   <div class="col-xs-6">
//       x
//       <span>1</span>
//   </div>
//   <div class="col-xs-5 col-xs-push-1 price_container">
//       零售价：
//       <span>123元</span>
//   </div>
// </div>`;

}

function chooseCard(code_) {
  codelist.push(code_);
  let str_n = `<div class="row item_row" code='${code_}'>
  <div class="col-xs-6 col-xs-push-1">
      <span>${data_json[code_].name}</span>
  </div>
  <div class="col-xs-6">
      x
      <span>1</span>
  </div>
  <div class="col-xs-5 col-xs-push-1 price_container">
      零售价：
      <span>${data_json[code_].price}元</span>
  </div>
</div>`;
  document.getElementById('card_use_list').innerHTML += str_n;
}

function chooseAddr() {


  $('#user_name').val(userName);
  $('#user_phone').val(telNumber);
  $('#user_adr').val(provinceName + cityName + countryName + detailInfo);
  //后续处理地址信息耦合问题

}
wx.error(function (res) {
  // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，
  // 也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
  console.log(res);
});






////现在已经点击添加卡券完成，之后可能要修改核销接口，传入值直接为解密后的code
///地址要根据实际情况来重写，微信地址现在是有多个，而接口文档没有传list