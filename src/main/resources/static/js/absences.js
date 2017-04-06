/**
 * Created by WangShiXiang on 2017/4/3.
 *
 */
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
                    "            查看\n" +
                    "        </div>\n" +
                    "    </a>";
            }
            document.getElementById("class_list").innerHTML=str;
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            $.toast("检索不到课程表", "cancel");
        }
    });
}
//用来设置cookie_id
  function jump_list(str) {
         document.cookie="course_id="+str;
        location.href="/view/absencesstudent";
  }

  function findStudentList() {
    var id=getCookie("course_id");
      $.ajax({
          type:"get",
          url:"/data/findabsencesstudent",
          data:{id:id},
          dataType:"",
          success:function (result) {
            var str="";
              var state=new Array("迟到","请假","缺课");
            for(var i=0;i<result.length;i++){
                str=str+"<div class='weui-cell'>\n" +
                    "        <div class='weui-cell__bd'>\n" +
                    "            <p>"+result[i].student.name+"</p>\n" +
                    "        </div>\n" +
                    "        <div class='weui-cell__ft'>"+result[i].date+" "+state[result[i].state]+"</div>\n" +
                    "    </div>";
            }
            document.getElementById("student_list").innerHTML=str;
          },
          error:function (XMLHttpRequest, textStatus, errorThrown){
              $.toast("没有缺课学生", "cancel");
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
