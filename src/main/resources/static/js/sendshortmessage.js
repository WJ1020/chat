/**
 * Created by WangShiXiang on 2017/4/10.
 *
 */

function sendMessage(context,openid,major,grade) {
    $.ajax({
        type:"post",
        url:"/data/sendshortmessage",
        data:{
            openid:openid,
            major:major,
            grade:grade,
            text:context
        },
        dataType:"text",
        success:function (result) {
            if (result>0){
                $.toast("成功发送了 "+result+" 条短信", "text");
                document.getElementById("context").value="";
                document.getElementById("major").value="";
                document.getElementById("grade").value="";
            }else if (result==0){
                $.toast("没检索到号码", "cancel");
            } else {
                $.toast("发生了不可预知的错误", "forbidden");
            }
        }
    });
}
function getValues() {
    var openid=getCookie("openid");
    var context=document.getElementById("context").value;
    var major=document.getElementById("major").value;
    var grade=document.getElementById("grade").value;
    if (openid==null||openid==""){
        $.toast("禁止操作", "forbidden");
        return ;
    }else if(context==null||major==null||grade==null||context==""||major==""||grade==""){
        $.toast("请补全信息", "cancel");
        return ;
    }
    sendMessage(context,openid,major,grade);
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}