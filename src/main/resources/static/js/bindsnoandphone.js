/**
 * Created by WangShiXiang on 2017/5/17.
 */
function click() {
    var openid=getCookie("openid");
    if (openid===undefined||openid===null){
        $.toast("openid失效,遇到未知错误", "forbidden");
        return;
    }
    var input=$("input");
    var sno=input[0].value;
    var phone=input[1].value;
    if (!isPhone(phone)){
        $.toast("手机号不合法","forbidden");
        return;
    }

    postSnoAndPhone(sno,phone,openid);

}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
function postSnoAndPhone(sno,phone,openid) {
    $.ajax({
        type:"post",
        url:"/data/saveandupdatestudentphone",
        data:{
            sno:sno,
            phone:phone,
            openid:openid
        },
        dataType:"text",
        success:function (result) {
            if (result==="success"){
                $.toast("成功更新数据","success");
            }else if(result==="noFindSno"){
                $.toast("没有找到学号","forbidden");
            }else {
                $.toast("发生了未知的错误","forbidden");
            }
        }
    });
}
function isPhone(value) {
    var pat= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1})|)+\d{8})$/;
    if (pat.exec(value)==null){
        return false;
    }else {
        return true;
    }
}