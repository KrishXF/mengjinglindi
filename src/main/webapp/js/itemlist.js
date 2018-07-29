let head_line_array = $('.select_img');
let openid = '';
let cardid = '';
let orderid = '';
let id = '';
//目前数据仅限测试
let url_il = window.location.href;
let wxconfig_value = {};
$(function () {
    //防止点击穿透
    FastClick.attach(document.body);
    //获取config参数
    $.ajax({
        type: "POST",
        url: REQ_URL + "/getSign/getConfig.do",
        data: {
            url: REQ_URL + '/page/itemlist.html'
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
                        jsApiList: ['addCard'] // 必填，需要使用的JS接口列表  //支付
                    });
                }
            }
        }
    });

    //获取用户信息
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
            //读取我的卡券列表
            $.ajax({
                type: "POST",
                url: REQ_URL + "/order/getOderList.do",
                data: {
                    Adid: openid,
                },
                dataType: "JSON",
                success: function (d) {
                    let retCode = d.retCode;
                    if (retCode == '10000') {
                        let dt = d.data;
                        if (typeof dt === 'object') {
                            for (let i = 0; i < dt.length; i++) {
                                let orderstate = dt[i].orderstate; //0:未使用 1:已添加至微信卡券
                                let str_ = '';
                                if (orderstate == '0') {
                                    str_ = `<div class="panel panel-default item_row" onclick="addcard('${dt[i].cardid}','${dt[i].orderid}','${dt[i].id}')">
                                    <div class="panel-body item_container">
                                        <div class="item_left">
                                            <p class='item_sale'>零售价：${dt[i].price}元</p>
                                            <p class='item_name'>${dt[i].cardname}</p>
                                        </div>
                                        <div class="item_right" >
                                            <p class="badge buy">去使用</p>
                                        </div>
                                    </div>
                                </div>`;
                                } else if (orderstate == '1') {
                                    str_ = `<div class="panel panel-default item_row">
                                    <div class="panel-body item_container">
                                        <div class="item_left">
                                            <p class='item_sale'>零售价：${dt[i].price}元</p>
                                            <p class='item_name'>${dt[i].cardname}</p>
                                        </div>
                                        <div class="item_right" >
                                            <p class="badge buy">已添加</p>
                                        </div>
                                    </div>
                                </div>`;
                                }

                                document.getElementById('my_card').innerHTML += str_;
                            }
                        }

                    }
                }
            });
        }
    });
    //读取商城列表
    $.ajax({
        type: "POST",
        url: REQ_URL + "/card/getCardList.do?token=TOKEN",
        data: {

        },
        dataType: "JSON",
        success: function (d) {
            let retCode = d.retCode;
            if (retCode == '10000') {
                let dt = d.data;
                if (typeof dt === 'object') {
                    for (let i = 0; i < dt.length; i++) {
                        let str_ = `<div class="panel panel-default item_row" id=${dt[i].id} onclick='to_shopcard(${dt[i].id})'>
                                                <div class="panel-body item_container">
                                                    <div class="item_left">
                                                        <p class='item_sale'>零售价：${dt[i].price}元</p>
                                                        <p class='item_name'>${dt[i].name}</p>
                                                        <p class='item_timeup'>有效期：${dt[i].enddatestring}</p>
                                                    </div>
                                                    <div class="item_right">
                                                        <p class="badge buy">立即购买</p>
                                                    </div>
                                                </div>
                                            </div>`;
                        document.getElementById('shop_card').innerHTML += str_;
                    }
                }

            }
        }
    });

    //空列表图片高度计算
    let margin_height_ = (window.innerHeight - $('#no_list_img').find('img').height()) / 2;
    $('#no_list_img').css('margin-top', margin_height_);
    //切换标签页方法绑定
    let head_array = $('.head_title');
    for (let i = 0; i < head_array.length; i++) {
        $(head_array[i]).bind('click', function (e) {
            if (!($(this).hasClass('select_title'))) {
                clearallseltitle();
                $(this).addClass('select_title');
                switch (this.id) {
                    case 'shop_':
                        $($(head_line_array[0]).find('img')).removeClass('hidden_item');
                        $($(head_line_array[1]).find('img')).addClass('hidden_item');
                        $('#shop_card').removeClass('hidden_item');
                        $('#my_card').addClass('hidden_item');
                        break;
                    case 'my_':
                        $($(head_line_array[0]).find('img')).addClass('hidden_item');
                        $($(head_line_array[1]).find('img')).removeClass('hidden_item');
                        $('#shop_card').addClass('hidden_item');
                        $('#my_card').removeClass('hidden_item');
                        break;
                    default:
                        break;
                }
            }
        });

    }
});

// function to_mycard(id_) {
//     $.ajax({
//         type: "POST",
//         url: REQ_URL + "/order/orderRedirect.do", //重定向到指定订单券方法
//         data: {
//             id: id_
//         },
//         dataType: "JSON",
//         success: function (d) {

//         }
//     });
// }

function to_shopcard(id_) {
    window.location.href = REQ_URL + "/card/cardRedirect.do?id="+id_;

}

function addcard(cardid_, orderid_, id_) {
    cardid = cardid_;
    orderid = orderid_;
    $.ajax({
        type: "POST",
        url: REQ_URL + "/getSign/getAddCard.do",
        data: {
            card_id: cardid_
        },
        dataType: "JSON",
        success: function (d) {
            let retCode = d.retCode;
            if (retCode == '10000') {
                let dt = d.data;
                if (typeof dt === 'object') {
                    let cardExt = {
                        "timestamp": dt.timestamp,
                        "nonce_str": dt.nonce_str,
                        "signature": dt.signature,
                    };
                    cardExt = JSON.stringify(cardExt);
                    wx.addCard({
                        cardList: [{
                            cardId: cardid,
                            cardExt: cardExt
                        }], // 需要添加的卡券列表
                        success: function (res) {
                            let cardList = res.cardList; // 添加的卡券列表信息



                            //查看数据
                            alert('已添加卡券：' + JSON.stringify(res.cardList));




                            let code = cardList[0].code;
                            //通过cardlist返回code和orderid 修改订单状态为0 
                            $.ajax({
                                type: "POST",
                                url: REQ_URL + "/order/updateOrder.do", //修改状态
                                data: {
                                    Id: id_,
                                    OrderState: '1',
                                },
                                dataType: "JSON",
                                success: function (d) {
                                    let retCode = d.retCode;
                                    if (retCode == '10000') {
                                        console.log('success');
                                    }
                                }
                            });
                            $.ajax({
                                type: "POST",
                                url: REQ_URL + "/order/updateOrder.do", //更新code
                                data: {
                                    orderId: orderid,
                                    WXCode: code, ///////////codecode
                                },
                                dataType: "JSON",
                                success: function (d) {
                                    let retCode = d.retCode;
                                    if (retCode == '10000') {
                                        let dt = d.data;
                                        console.log('success');
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }
    });
}

function clearallseltitle() {
    let head_array = $('.head_title');
    for (let m = 0; m < head_array.length; m++) {
        $(head_array[m]).removeClass('select_title');
    }
}
wx.ready(function () {
});
wx.error(function (res) {
});
function killAll(){
    for(key in group){
        kill(group[key]);
    }
}