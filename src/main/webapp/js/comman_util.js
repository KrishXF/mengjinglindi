function getUrlParams(url_) {
    // let arr = url_.split("?")[1];           //根据？跟个url
    // let arr2 = arr[1].split("&");         //根据&重新分割参数
    // let jsonarr_ = {};                    //定义一个json对象放置url  参数
    // for (let i = 0; i < arr2.length; i++) {   //循环将参数放到json里面
    //     jsonarr_[arr2[i].substring(0, 1)] = arr2[i].substring(2, 3);
    // }
    // return jsonarr_;
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
function isNull(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}