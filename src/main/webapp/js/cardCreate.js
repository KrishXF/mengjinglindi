(function setCards() {
    document.getElementById("cardJson").value = "" +
        "{" +
        "  \"card\": {" +
        "      \"card_type\": \"GROUPON\"," +
        "      \"GROUPON\": {" +
        "          \"base_info\": {" +
        //卡券标题
        "              \"brand_name\": \"梦境林地\"," +
        //券类型，不用改动
        "              \"code_type\": \"CODE_TYPE_TEXT\"," +
        //卡券名称
        "              \"title\": \"阳光玫瑰葡萄\"," +
        //颜色
        "              \"color\": \"Color010\"," +
        //注意项
        "              \"notice\": \"请在票券过期前使用\"," +
        //服务电话
        "              \"service_phone\": \"13506746139‬\"," +
        //描述
        "              \"description\": \"不可与其他优惠同享\"," +
        //使用有效期，包括指定有效期和领取后一定天数内有效
        "              \"date_info\": {" +
        //有效期类型
        "                  \"type\": \"DATE_TYPE_FIX_TIME_RANGE\"," +
        //开始时间，时间戳，秒数
        "                  \"begin_timestamp\": 1532620800," +
        //结束时间，时间戳，秒数
        "                  \"end_timestamp\": 1535644800" +
        "              }," +
        "              \"sku\": {" +
        //库存量
        "                  \"quantity\": 500" +
        "              }," +
        //每人限购数量
        "              \"get_limit\": 500," +
        "              \"use_custom_code\": false," +
        "              \"bind_openid\": false," +
        //是否可分享
        "              \"can_share\": true," +
        //是否可以转赠他人
        "              \"can_give_friend\": true," +
        //中间立即使用按钮标题
        "              \"center_title\": \"立即使用\"," +
        //中间立即使用按钮副标题
        "              \"center_sub_title\": \"请填写收货地址\"," +
        //中间立即使用按钮跳转地址
        "              \"center_url\": \"http://demo.steam-steam.cn/page/add_address.html\"," +
        //下方定制入口url
        "              \"custom_url\": \"http://demo.steam-steam.cn/page/add_address.html\"," +
        //下方定制入口名称
        "              \"custom_url_name\": \"立即使用\"," +
        "          }," +
        //高级信息
        "          \"advanced_info\": {" +
        //指定可用的商品类目，仅用于代金券类型 ，填入后将在券面拼写适用于xxx
        "              \"accept_category\": \"\"," +
        //指定不可用的商品类目，仅用于代金券类型 ，填入后将在券面拼写不适用于xxxx
        "              \"reject_category\": \"\"," +
        //满减门槛字段，可用于兑换券和代金券 ，填入后将在全面拼写消费满xx元可用。
        "              \"least_cost\": \"\"," +
        //购买xx可用类型门槛，仅用于兑换 ，填入后自动拼写购买xxx可用。
        "              \"object_use_for\": \"\"," +
        //不可以与其他类型共享门槛 ，填写false时系统将在使用须知里 拼写“不可与其他优惠共享”， 填写true时系统将在使用须知里 拼写“可与其他优惠共享”， 默认为true
        "              \"can_use_with_other_discount\": true," +
        //图文列表，显示在详情内页 ，优惠券券开发者须至少传入 一组图文列表
        "              \"text_image_list\": [" +
        "                  {" +
        "                  \"text\": \"产品特点：阳光玫瑰葡萄本名Shine Muscat，也会被音译为夏音玛斯卡特葡萄。属欧美杂交种，由安芸津21号（Steuben与Muscat of Alexandria的后代）与白南杂交育成。阳光玫瑰葡萄名字里的前缀“Shine”得名于它的果实成熟后完全没有果粉，光滑闪亮，呈现出诱人的光泽。而普通葡萄成熟后外表皮会有果粉，那些蜡制会让果实看起来灰蒙蒙的。阳光玫瑰葡萄的糖度普遍能到达20度，且几乎没有酸质，吃进嘴里满满的都是甜感。在日本有一种流行的吃法：把它丢进速冻里，拿出来后高度甜味配合原本就细腻的果肉会让它吃起来仿佛水果冰沙。\"" +
        "                  }," +
        "                  {" +
        "                  \"text\": \"\"" +
        "                  }," +
        "                  {" +
        "                   \"text\": \"农场信息：成立于2006年6月，占地1200亩，历经12年时间发展，先后通过国家无公害农产品基地、GAP以及HACCP认证，同时被认定为浙江省示范性家庭农场。近年来，与上海交大技术合作，引进种植的阳光玫瑰葡萄（目前慈溪最大的阳光玫瑰种植基地），严格按照无公害栽培技术，果肉细腻，甜度达到22以上，口感风味中带有独特的果香。\"" +
        "                  }" +
    "                   ],"+
        //使用有效期，包括指定有效期和领取后一定天数内有效
    "                  \"abstract\": \"阳光玫瑰葡萄\"," +
        //使用时段限制，包含以下字段
        "              \"time_limit\": {" +
        //限制类型枚举值：支持填入 MONDAY 周一 TUESDAY 周二 WEDNESDAY 周三 THURSDAY 周四 FRIDAY 周五 SATURDAY 周六 SUNDAY 周日 此处只控制显示， 不控制实际使用逻辑，不填默认不显示
        "                  \"type\": 10," +
        //当前type类型下的起始时间（小时） ，如当前结构体内填写了MONDAY， 此处填写了10，则此处表示周一 10:00可用
        "                  \"begin_hour\": 10," +
        //当前type类型下的起始时间（分钟） ，如当前结构体内填写了MONDAY， begin_hour填写10，此处填写了59， 则此处表示周一 10:59可用
        "                  \"begin_minute\": 10," +
        //当前type类型下的结束时间（小时） ，如当前结构体内填写了MONDAY， 此处填写了20， 则此处表示周一 10:00-20:00可用
        "                  \"end_hour\": 10," +
        //当前type类型下的结束时间（分钟） ，如当前结构体内填写了MONDAY， begin_hour填写10，此处填写了59， 则此处表示周一 10:59-00:59可用
        "                  \"end_minute\": 10," +
        "              }" +
        "          }," +
        "      }," +
        "\"deal_detail\": \"价格优惠\""  +
        "  }" +
        "}";
    var baseInfo = document.getElementById("baseInfo").value ="";
    var localInfo = document.getElementById("localJson").value = "{\"Introduc\":\"生产产地：慈溪市新浦镇\n" +
        "农场名称：慈溪市新浦一帆果蔬农场\n" +
        "产品特点：阳光玫瑰葡萄本名Shine Muscat，也会被音译为夏音玛斯卡特葡萄。属欧美杂交种，由安芸津21号（Steuben与Muscat of Alexandria的后代）与白南杂交育成。阳光玫瑰葡萄名字里的前缀“Shine”得名于它的果实成熟后完全没有果粉，光滑闪亮，呈现出诱人的光泽。而普通葡萄成熟后外表皮会有果粉，那些蜡制会让果实看起来灰蒙蒙的。阳光玫瑰葡萄的糖度普遍能到达20度，且几乎没有酸质，吃进嘴里满满的都是甜感。在日本有一种流行的吃法：把它丢进速冻里，拿出来后高度甜味配合原本就细腻的果肉会让它吃起来仿佛水果冰沙。\n" +
        "农场信息：成立于2006年6月，占地1200亩，历经12年时间发展，先后通过国家无公害农产品基地、GAP以及HACCP认证，同时被认定为浙江省示范性家庭农场。近年来，与上海交大技术合作，引进种植的阳光玫瑰葡萄（目前慈溪最大的阳光玫瑰种植基地），严格按照无公害栽培技术，果肉细腻，甜度达到22以上，口感风味中带有独特的果香。\n\",\"Name\":\"阳光玫瑰葡萄\",\"Price\":100,\"Type\":1,\"Inventory\":500,\"Remarks\":\"Remarks\",\"StartDate\":\"2018-8-01\",\"EndDate\":\"2019-8-31\",\"TimeRemarks\":\"周一到周日全天可用\"}"
})();

//图片上传
var xhr;
//上传文件方法
function UpladFile() {
    var f1 = document.getElementById("file").files[0]; // js 获取文件对象
    var b1 = document.getElementById("basefile").files[0]; // js 获取文件对象
    var a1 = document.getElementById("detail1").files[0]; // js 获取文件对象
    var a2 = document.getElementById("detail2").files[0]; // js 获取文件对象
    var a3 = document.getElementById("detail3").files[0]; // js 获取文件对象
    var url =  "http://localhost:8080" + "/wexinCard/createCard.do"; // 接收上传文件的后台地址

    var form = new FormData(); // FormData 对象
    form.append("uploadFile", f1); // 文件对象
    form.append("basefile", b1); // 文件对象
    form.append("detail1", a1); // 文件对象
    form.append("detail2", a2); // 文件对象
    form.append("detail3", a3); // 文件对象
    form.append("cardInfo", document.getElementById("cardJson").value); // 微信卡券对象
    form.append("baseInfo", document.getElementById("baseInfo").value); // 微信卡券对象
    form.append("localJson", document.getElementById("localJson").value); // 本地卡券对象

    xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
    xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
    xhr.onload = uploadComplete; //请求完成
    xhr.onerror =  uploadFailed; //请求失败

    xhr.upload.onprogress = progressFunction;//【上传进度调用方法实现】
    xhr.upload.onloadstart = function(){//上传开始执行方法
        ot = new Date().getTime();   //设置上传开始时间
        oloaded = 0;//设置上传开始时，以上传的文件大小为0
    };

    xhr.send(form); //开始上传，发送form数据
}

//上传成功响应
function uploadComplete(evt) {
    //服务断接收完文件返回的结果

    var data = JSON.parse(evt.target.responseText);
    if(data.retCode == 10000) {
        alert("上传成功！");
    }else{
        alert("上传失败！");
    }

}
//上传失败
function uploadFailed(evt) {
    alert("上传失败！");
}
//取消上传
function cancleUploadFile(){
    xhr.abort();
}


//上传进度实现方法，上传过程中会频繁调用该方法
function progressFunction(evt) {
    var progressBar = document.getElementById("progressBar");
    var percentageDiv = document.getElementById("percentage");
    // event.total是需要传输的总字节，event.loaded是已经传输的字节。如果event.lengthComputable不为真，则event.total等于0
    if (evt.lengthComputable) {//
        progressBar.max = evt.total;
        progressBar.value = evt.loaded;
        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
    }
    var time = document.getElementById("time");
    var nt = new Date().getTime();//获取当前时间
    var pertime = (nt-ot)/1000; //计算出上次调用该方法时到现在的时间差，单位为s
    ot = new Date().getTime(); //重新赋值时间，用于下次计算
    var perload = evt.loaded - oloaded; //计算该分段上传的文件大小，单位b
    oloaded = evt.loaded;//重新赋值已上传文件大小，用以下次计算
    //上传速度计算
    var speed = perload/pertime;//单位b/s
    var bspeed = speed;
    var units = 'b/s';//单位名称
    if(speed/1024>1){
        speed = speed/1024;
        units = 'k/s';
    }
    if(speed/1024>1){
        speed = speed/1024;
        units = 'M/s';
    }
    speed = speed.toFixed(1);
    //剩余时间
    var resttime = ((evt.total-evt.loaded)/bspeed).toFixed(1);
    time.innerHTML = '，速度：'+speed+units+'，剩余时间：'+resttime+'s';
    if(bspeed==0) time.innerHTML = '上传已取消';
}