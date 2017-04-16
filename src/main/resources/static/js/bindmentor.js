/**
 * Created by WangShiXiang on 2017/4/15.
 *
 */

function jump_author() {
    var url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    var appid="wx8d03b75a183070f8";
    var response_type="http://www.wodeschool.cn/view/bindmentor";
    response_type=encodeURIComponent(response_type,"utf-8");
    console.log(response_type);
    url=url.replace("APPID",appid).replace("REDIRECT_URI",response_type).replace("SCOPE","snsapi_userinfo");
    console.log(url);
    window.location.href=url;
}

function bind() {
    var name=document.getElementById("bind_openid").value;
    if (name==null||name==""){
        $.alert("请输入你的姓名");
        return;
    }

    $.ajax({//发送查询的请求
        type:"get",
        url:"/data/findmentorbyname",
        data:{name:name},
        dataType:"json",
        success:function (result) {
            var len=result.length;
            if (len==0){
                $.alert("没有检索到你的信息");
            }else if(len>0){
                $.confirm({
                    title: '请确认你的信息',
                    text: '姓名:'+result[0].name+'<br>手机号:'+result[0].phone,
                    onOK: function () {
                        //点击确认
                        bind_openid(result[0].name);
                    },
                    onCancel: function () {
                        $.toast("取消操作","cancel");
                    }
                });
            }else {

            }
        }
    })
}
function bind_openid(name) {//openid绑定阶段
    var open_id=openid;
    $.ajax({
        type:"post",
        url:"/data/bindmentorbyname",
        data:{openid:open_id,name:name},
        dataType:"text",
        success:function (result) {
            if (result>0){
                $.toast("操作成功", function() {
                });
            }
        }
    })
}

var openid=getCookie("openid");
if (openid==null||openid==""){
    jump_author();
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}