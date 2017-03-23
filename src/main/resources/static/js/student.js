// var author=document.getElementById("author");
// author.addEventListener('click',jump_author);
function jump_author() {
    var url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    var appid="wx8d03b75a183070f8";
    var response_type="http://www.wodeschool.cn/view/student";
    response_type=encodeURIComponent(response_type,"utf-8");
    console.log(response_type);
    url=url.replace("APPID",appid).replace("REDIRECT_URI",response_type).replace("SCOPE","snsapi_userinfo");
    console.log(url);
    window.location.href=url;
}
function post_Sno(str) {

    $.ajax({
        type:"post",
        url:"/no_subject",
        data:{sno:str},
        dataType: "text",
        success:function (result) {
            var message="成功将:"+result+"设置为缺课";
            $.toptip(message, 'success');
        }
    })
}
function findAllStudent() {
    $.ajax({
        type:"get",
        url:"/student",
        dataType: "json",
        success:function (result) {
            var str="";
            for(var i=0;i<result.length;i++){
                str=str+"<div class='weui-cell'><div class='weui-cell__bd'><p>"+result[i].sno+" "+result[i].name+"</p></div><div class='weui-cell__ft'><button value='"+result[i].sno+"' onclick='post_Sno(this.value)'class='weui-btn weui-btn_mini weui-btn_warn'>缺课</button></div></div>";
            }
            document.getElementById("students").innerHTML=str;
        }
    })
}
findAllStudent();
