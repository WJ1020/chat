/**
 * Created by WangShiXiang on 2017/3/22.
 * 微信js_sdk测试接口
 * 没有实际用处。只是用来测试
 */

function js_sdk(str) {
    $.ajax({
        type:"get",
        url:"/view/js",
        data:{u:str},
        dataType: "json",
        success:function (result) {
            wx.config({
                debug: result.debug, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: result.appId, // 必填，公众号的唯一标识
                timestamp: result.timestamp, // 必填，生成签名的时间戳
                nonceStr: result.nonceStr, // 必填，生成签名的随机串
                signature: result.signature,// 必填，签名，见附录1
                jsApiList: [
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'scanQRCode'
                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2mvn
            });
        }
    })
}

var str = window.location.href;
var index=str.indexOf("#");
if (index!=-1){
    str=str.substr(0,index-1);
}
js_sdk(str);
wx.ready(function(){

    wx.onMenuShareTimeline({
        title: '分享标题', // 分享标题
        link: 'http://www.wodeschool.cn/view/test', // 分享链接
        imgUrl: 'https://www.baidu.com/img/baidu_jgylogo3.gif', // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
            alert("分享成功");
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
            alert("分享失败");
        }
    });
    wx.error(function(res){
        alert("失败了")
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    });
    

});
function scan_test() {
    wx.scanQRCode({
        needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
        scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
        success: function (res) {
            var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
            alert(result);
        }
    });
}
