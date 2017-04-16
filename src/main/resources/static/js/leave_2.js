/**
 * Created by WangShiXiang on 2017/4/14.
 * 学生请假页面
 */
function getLeave(page) {

    var openid=getCookie("openid");
    if (openid==null||openid==""){
        location.href="bindmentor";
    }

    $.ajax({
        type:"get",
        url:"/data/findlimitleavelist",
        data:{openid:openid,page:page},
        dataType:"json",
        success:function (result) {
            createList(result);
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            $(document.body).destroyInfinite();
            $("#login_message").remove();
            var str_1="<div class=\"weui-footer\">\n" +
                "  <p class=\"weui-footer__text\">人家是有底线的</p>\n" +
                "</div>";
            $("#div_body").append(str_1);
        }
    });
}
function createList(json) {
    var str="";
    var sta=new Array("审核中","通过","直接拒绝","当面请假","已取消");
    var ty=new Array("病假","事假","其他假");
    var lo=new Array("校内","校外");
    for(var i=0;i<json.length;i++){
        str=str+"<div class=\"weui-form-preview\">\n" +
            "    <div class=\"weui-form-preview__hd\">\n" +
            "        <label class=\"weui-form-preview__label\">状态</label>\n" +
            "        <em class=\"weui-form-preview__value\">"+sta[json[i].leave.state]+"</em>\n" +
            "    </div>\n" +
            "    <div class=\"weui-form-preview__bd\">\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">姓名</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].student.name+"</span>\n" +
            "        </div>\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">专业</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].student.major+"</span>\n" +
            "        </div>\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">年级</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].student.grade+"</span>\n" +
            "        </div>\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">请假类型</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+ty[json[i].leave.type]+"</span>\n" +
            "        </div>\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">外出地点</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+lo[json[i].leave.local_1]+"</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">请假时间</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].leave.startDate+" "+json[i].leave.endDate+"</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">详细原因</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].leave.cause+"</span>\n" +
            "        </div>\n" +
            "        <div class=\"weui-form-preview__item\">\n" +
            "            <label class=\"weui-form-preview__label\">详细地点</label>\n" +
            "            <span class=\"weui-form-preview__value\">"+json[i].leave.local_2+"</span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"weui-form-preview__ft\">\n" +
            "        <a class=\"weui-form-preview__btn weui-form-preview__btn_default\" href=\"javascript: assist("+json[i].leave.id+")\">辅助操作</a>\n" +
            "        <button id='"+json[i].leave.id+"' onclick=\"through("+json[i].leave.id+")\" class=\"weui-form-preview__btn weui-form-preview__btn_primary\" href=\"javascript:\">通过申请</button>\n" +
            "    </div>\n" +
            "</div>";
    }
    $("#div_body").append(str);
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function updateLeaveState(id,state) {
    $.ajax({
        type:"post",
        url:"/data/updateleavestate",
        data:{id:id,state:state},
        dataType:"text",
        success:function (result) {
            if(result>0){
                $.toast("操作成功","success");
            }
        }
    });
}

function assist(id) {//辅助操作
    $.actions({
        actions: [{
            text: "拒绝申请",
            className: "color-warning",
            onClick: function() {
                //do something2
                updateLeaveState(id,2);
            }
        },{
            text: "当面请假",
            className: "color-primary",
            onClick: function() {
                //do something3
                updateLeaveState(id,3);
            }
        }]
    });
}
function through(id) {//通过1
    updateLeaveState(id,1);
}
//滚动加载组件
$(document.body).infinite();
var indexPage=1;
var loading = false;  //状态标记
$(document.body).infinite().on("infinite", function() {
    if(loading) return;
    loading = true;
    setTimeout(function() {
        indexPage++;
        getLeave(indexPage);

        loading = false;
    }, 0);   //模拟延迟
});