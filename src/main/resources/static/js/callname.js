/**
 * Created by WangShiXiang on 2017/4/5.
 */
var studentObject=null;
function getStudent() {
    var openid=getCookie("openid");
    if (openid==null||openid==""){
        $.toast("禁止操作", "forbidden");
        return;
    }
    $.ajax({
        type:"get",
        url:"/data/randomstudent",
        data:{openid:openid},
        dataType:"json",
        success:function (result) {
            studentObject=result;
            var str=result.sno+"<br/>"+result.name+"<br/>"+result.college+"<br/>"+result.major
            $("#studentmsg").html(str);
            startPopup();
        }
    });
}
function setStudentScore(str) {
    var openid=getCookie("openid");
    $.ajax({
        type:"put",
        url:"/data/setstudentscore",
        data:{
            openid:openid,
            student_name:studentObject.name,
            student_sno:studentObject.sno,
            student_score:str
        },
        dataType:"text",
        success:function (result) {
            var arr=new Array("很差","一般","很好","非常好");
            console.log("给学生打分返回的数据为:"+result);
            var count=parseInt(result)+1;
            $.toptip(arr[count], 'success');
            $.closePopup();
        }
    });
}
function startPopup() {
    $("#about").popup();
}
function closePopup() {
    $.closePopup();
}
//用来查询所有的课程列表
function getCourse() {
    var openid=getCookie("openid");
    if (openid==null||openid==""){
        console.log("openid-->"+openid);
        $.toast("没有权限", "forbidden");
        return;
    }
    $.ajax({
        type:"get",
        url:"/data/findcoursebyopenid",
        data:{openid:openid},
        dataType:"json",
        success:function (result) {
            var str="";

            for(var i=0;i<result.length;i++){
                str=str+"    <a  class='weui-cell weui-cell_access' href=\"javascript: jump_list('"+result[i].id+"')\">\n" +
                    "        <div class='weui-cell__bd'>\n" +
                    "            <p>"+result[i].name+"</p>\n" +
                    "        </div>\n" +
                    "        <div class='weui-cell__ft'>\n" +
                    "            查看点名情况\n" +
                    "        </div>\n" +
                    "    </a>";
            }
            document.getElementById("cores_class").innerHTML=str;
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            $.toast("检索不到课程表", "cancel");
        }
    });
}
function jump_list(id) {
    document.cookie="course_id="+id;
    location.href="/view/callnamescore";
}
function getStudentScore() {
    var openid=getCookie("openid");
    var courseId=getCookie("course_id");
   if(openid==null||openid==""||courseId==null||courseId==""){
       $.toast("没有权限","forbidden");
       return ;
   }
   $.ajax({
       type:"get",
       url:"/data/findstudentscore",
       data:{
           course_id:courseId
       },
       dataType:"json",
       success:function (result) {
           var arr=new Array("很差","一般","很好","非常好");
            var str="";
           for(var i=0;i<result.length;i++){
               str=str+"<div class='weui-cell'>\n" +
                   "        <div class='weui-cell__bd'>\n" +
                   "            <p>"+result[i].studentName+"</p>\n" +
                   "        </div>\n" +
                   "        <div class='weui-cell__ft'>"+arr[result[i].courseScore+1]+"</div>\n" +
                   "    </div>";
           }
           document.getElementById("callnameScore").innerHTML=str;
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