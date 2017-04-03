/**
 * Created by WangShiXiang on 2017/4/1.
 *
 */
function bind() {
    var name=document.getElementById("bind_openid").value;
    if (name==null||name==""){
        $.alert("请输入你的姓名");
        return;
    }

    $.ajax({//发送查询的请求
        type:"get",
        url:"/view/findteacher",
        data:{name:name},
        dataType:"json",
        success:function (result) {
           var len=result.length;
           if (len==0){
               $.alert("没有检索到你的信息");
           }else if(len==1){
               $.confirm({
                   title: '请确认你的信息',
                   text: '姓名:'+result[0].name+'<br>学院:'+result[0].college,
                   onOK: function () {
                       //点击确认
                       bind_openid(result[0].id);
                   },
                   onCancel: function () {
                       $.toast("取消操作","cancel");
                   }
               });
           }else {
                //如果有重复名字的教师会在次处理业务逻辑
           }
        }
    })
}

function bind_openid(id) {//openid绑定阶段
    var open_id=openid;
    $.ajax({
        type:"post",
        url:"/data/setopenid",
        data:{openid:open_id,id:id},
        dataType:"text",
        success:function (result) {
            if (result>0){
                $.toast("操作成功", function() {
                    location.href="/view/student";
                });
            }
        }
    })
}

var openid=getCookie("openid");
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}