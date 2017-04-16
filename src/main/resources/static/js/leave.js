/**
 * Created by WangShiXiang on 2017/4/11.
 */

function addEvent() {
    $("#start_time").datetimePicker();
    $("#end_time").datetimePicker();
    var section=document.getElementById("section");
    var instancy_phone=document.getElementById("instancy_phone");
    //监听blur事件
    section.addEventListener("blur",VerificationSection);
    instancy_phone.addEventListener("blur",VerificationPhone)
}

 //验证section的合法性
 function VerificationSection() {
   var sectionValue=section.value;
    if (isNumber(sectionValue)){

    }else {
        section.placeholder="输入的数字不合法";
        section.value=null;
    }
 }
 function VerificationPhone() {
     var phone=instancy_phone.value;
     if (isPhone(phone)){
         // alert(phone);
     }else {
        instancy_phone.placeholder="手机号不合法";
        instancy_phone.value=null;
     }
 }
 function isPhone(value) {
     var pat= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1})|)+\d{8})$/;
     if (pat.exec(value)==null){
         return false;
     }else {
         return true;
     }
 }
 
 function isNumber(value) {
     var pat=/^[0-9]*$/;
     if (pat.exec(value)==null||value==""){
         return false;
     }else {
         return true;
     }
 }
 
 function createJson() {
    var openid=getCookie("openid");
    if (openid==null||openid==""){
        $.toast("请先绑定微信","forbidden");
        return ;
    }
    var cause=document.getElementById("cause").value;
    var type=document.getElementById("type").value;
    var startDate=document.getElementById("start_time").value;
    startDate=getTime(startDate);
    var endDate=document.getElementById("end_time").value;
    endDate=getTime(endDate);
    var section=document.getElementById("section").value;
    var local_1=document.getElementById("local_1").value;
    var local_2=document.getElementById("local_2").value;
    var urgentName=document.getElementById("instancy_name").value;
     //紧急联系人号码
     var urgentPhone=document.getElementById("instancy_phone").value;
     if (openid==null||openid==""||cause==null||cause==""||type==null||type==""||startDate==null||startDate==""||endDate==null||endDate==""||section==null||section==""||local_1==null||local_1==""||local_2==""||local_2==null||urgentName==null||urgentName==""||urgentPhone==null||urgentPhone=="")
     {
         $.toast("请将信息补全","forbidden");
     }else {
         var leave=new Object();
         leave.id=null;
         leave.openid=openid;
         leave.cause=cause;
         leave.type=type;
         leave.startDate=startDate;
         leave.endDate=endDate;
         leave.section=section;
         leave.local_1=local_1;
         leave.local_2=local_2;
         leave.urgentName=urgentName;
         leave.urgentPhone=urgentPhone;
         var json=JSON.stringify(leave);
         postForm(json);
     }
 }
 
 function postForm(json) {
     $.ajax({
         type:"put",
         url:"/data/askforleave",
         contentType: "application/json; charset=utf-8",
         data:json,
         dataType: "text",
         success:function (result) {
             location.href="leave_1";
         }
     });
 }
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
function getTime(str) {
    var str1=str.substring(0,10);
    var str2=str.substring(11,str.length);
    var date="";
    var arr1=new Array();
    var arr2=new Array();
    arr1=str1.split("-");
    arr2=str2.split(":");
    for(var i=0;i<arr1.length;i++){
        date=date+arr1[i]+"-";
    }
    for(var i=0;i<arr2.length;i++){
        date=date+arr2[i]+"-";
    }
    date=date.substring(0,date.length-1);
    return date;
}
function selectLeave() {
    var openid=getCookie("openid");
    if (openid==null||openid==""){
        $.toast("请先绑定微信","forbidden");
        return;
    }
    $.ajax({
        type:"get",
        url:"/data/findleavebyopenid",
        data:{openid:openid},
        dataType:"json",
        success:function (result) {
            createLeaveList(result);
        }
    });
}
function createLeaveList(json) {
    var str="";
    var sta=new Array("审核中","通过","直接拒绝","当面请假","已取消");
    var ty=new Array("病假","事假","其他假");
    for(var i=0;i<json.length;i++){
        str=str+"<div class='weui-form-preview'>\n" +
            "    <div class='weui-form-preview__hd'>\n" +
            "        <label class='weui-form-preview__label'>状态</label>\n" +
            "        <em class='weui-form-preview__value'>"+sta[json[i].state]+"</em>\n" +
            "    </div>\n" +
            "    <div class='weui-form-preview__bd'>\n" +
            "        <div class='weui-form-preview__item'>\n" +
            "            <label class='weui-form-preview__label'>请假类型</label>\n" +
            "            <span class='weui-form-preview__value'>"+ty[json[i].type]+"</span>\n" +
            "        </div>\n" +
            "        <div class='weui-form-preview__item'>\n" +
            "            <label class='weui-form-preview__label'>请假原因</label>\n" +
            "            <span class='weui-form-preview__value'>"+json[i].cause+"</span>\n" +
            "        </div>\n" +
            "        <div class='weui-form-preview__item'>\n" +
            "            <label class='weui-form-preview__label'>外出地点</label>\n" +
            "            <span class='weui-form-preview__value'>"+json[i].local_2+"</span>\n" +
            "        </div>\n" +
            "    </div>\n";

             var ss="    <div class='weui-form-preview__ft'>\n" +
            "        <button  id='"+json[i].id+"'  onclick='removeLeave("+json[i].id+")'  class='weui-form-preview__btn weui-form-preview__btn_primary'>取消请假</button>\n" +
            "    </div>\n" +
            "</div>";
                if (json[i].state==4){
                    str=str+"</div>";
                }else {
                    str=str+ss;
                }
                str=str+"<br/>";
    }
    document.getElementById("body_div").innerHTML=str;
}
function removeLeave(id) {
    $.ajax({
        type:"post",
        url:"/data/updateleavestate",
        data:{id:id,state:4},
        dataType:"text",
        success:function (result) {
            document.getElementById(id).style.display="none";
            $.toast("操作成功");
        }
    });
}
