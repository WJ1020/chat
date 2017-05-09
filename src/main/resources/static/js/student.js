// var author=document.getElementById("author");
// author.addEventListener('click',jump_author);
function jump_author() {
    var url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    var appid="wx8d03b75a183070f8";
    var response_type="http://wx.wodeschool.cn/view/student";
    response_type=encodeURIComponent(response_type,"utf-8");
    console.log(response_type);
    url=url.replace("APPID",appid).replace("REDIRECT_URI",response_type).replace("SCOPE","snsapi_userinfo");
    console.log(url);
    window.location.href=url;
}
function postZero_Sno(str) {

    $.ajax({
        type:"post",
        url:"/data/no_subject",
        data:{sno:str,state:0},
        dataType: "text",
        success:function (result) {
            var message="成功将:"+result+"设置为迟到";
            $.toptip(message, 'success');
            var div_element=document.getElementById(str);
            div_element.style.backgroundColor="#B5B5B5";
            var p= $(div_element).find("p")[0];
            $(p).css("color","#2E8B57");
        }
    })
}
function postOne_Sno(str) {

    $.ajax({
        type:"post",
        url:"/data/no_subject",
        data:{sno:str,state:1},
        dataType: "text",
        success:function (result) {
            var message="成功将:"+result+"设置为请假";
            $.toptip(message, 'success');
            var div_element=document.getElementById(str);
            div_element.style.backgroundColor="#B5B5B5";
            var p= $(div_element).find("p")[0];
            $(p).css("color","#FFF");

        }
    })
}
function postTwo_Sno(str) {

    $.ajax({
        type:"post",
        url:"/data/no_subject",
        data:{sno:str,state:2},
        dataType: "text",
        success:function (result) {
            var message="成功将:"+result+"设置为缺课";
            $.toptip(message, 'success');
            var div_element=document.getElementById(str);
            div_element.style.backgroundColor="#B5B5B5";
            var p= $(div_element).find("p")[0];
            $(p).css("color","red");
        }
    })
}
function findStudent() {
    $.ajax({
        type:"get",
        url:"/data/openid",
        data:{openid:openId},
        dataType: "json",
        success:function (result) {

            var str="";
            for(var i=0;i<result.length;i++){
                // str=str+"<div class='weui-cell'><div class='weui-cell__bd'><p>"+result[i].sno+" "+result[i].name+"</p></div><div class='weui-cell__ft'><button value='"+result[i].sno+"' onclick='post_Sno(this.value)'class='weui-btn weui-btn_mini weui-btn_warn'>缺课</button></div></div>";
                str=str+"    <div class='weui-cell' id='"+result[i].sno+"'>\n" +
                    "        <div class='weui-cell__bd' >\n" +
                    "            <p>"+result[i].name+"</p>\n" +
                    "        </div>\n" +
                    "        <div class='weui-cell__ft'>\n" +
                    "            <button  value='"+result[i].sno+"' onclick='postZero_Sno(this.value)' class='weui-btn weui-btn_mini weui-btn_primary'>迟到</button>\n" +
                    "            <button  value='"+result[i].sno+"' onclick='postOne_Sno(this.value)' class='weui-btn weui-btn_mini weui-btn_default'>请假</button>\n" +
                    "            <button  value='"+result[i].sno+"' onclick='postTwo_Sno(this.value)' class='weui-btn weui-btn_mini weui-btn_warn'>缺课</button>\n" +
                    "        </div>\n" +
                    "    </div>";
            }
            document.getElementById("students").innerHTML=str;
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("状态码为:"+XMLHttpRequest.readyState);
                //如果没有信息时怎么做,分为两种情况，现在没课，没有绑定.
                $.modal({
                    title: '没有检索到学生列表',
                    text: '可能出现的原因:当前不在上课时间内；第一次使用本系统' +
                    '未绑定',
                    buttons:[
                        {text:"去绑定",onClick:function() {
                            location.href="/view/bindopenid";
                        }},
                        {text:"扫码",onClick:function () {

                        }},
                        {text:"取消",onClick:function(){
                            window.close();
                        }}
                    ]});
        }
    })
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
var openId=getCookie("openid");
if (openId==null||openId==""){
    jump_author();
}else {
    var date=new Date();
    date.setTime(date.getTime()+60*60*1000*24*20);
    document.cookie="openid="+openId+";expires="+date.toGMTString();
    findStudent();
}

function ScanCode() {
    
}