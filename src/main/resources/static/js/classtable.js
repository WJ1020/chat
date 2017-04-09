/**
 * Created by WangShiXiang on 2017/4/8.
 */

function getClassTable() {
    var openid=getCookie("openid");
    if(openid==null||openid==""){
        document.getElementById("div_body").innerHTML="<h1>没有权限!</h1>";
        return;
    }
    $.ajax({
        type:"get",
        url:"/data//findallcoursebyopenid",
        data:{openid:openid},
        dataType:"json",
        success:function (result) {
            createList(result);
        }
    });
}
function createList(result) {
    var div=document.getElementsByClassName("section");
    for(var i=0;i<result.length;i++){
        var str=result[i].name+"<br/>"+result[i].locale;
        var index=result[i].count-1;
        if (index>19){
            continue;
        }
        var arr=new Array(0,5,10,15,1,6,11,16,2,7,12,17,3,8,13,18,4,9,14,19);
        index=arr[index];
        div[index].innerHTML=str;
        div[index].style.backgroundColor="#FF9999";
    }
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
getClassTable();

