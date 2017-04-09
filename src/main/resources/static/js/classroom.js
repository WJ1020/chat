/**
 * Created by WangShiXiang on 2017/4/7.
 */
//查询当时的空闲教室
function getNowFreeClassRoom() {
    $.ajax({
        type:"get",
        url:"/data/findfreeclassroom",
        dataType:"json",
        success:function (result) {
            createList(result);
            // $.toast("当前的空闲教室", "text");
        }
    });
}
function createList(result) {
    var str="";
    var arr=new Array("普通教室","机房","阶梯教室");
    for(var i=0;i<result.length;i++){
        str=str+"<div class='weui-cell'>\n" +
            "            <div class='weui-cell__bd'>\n" +
            "                <p>编号:【"+result[i].number+"】 可容纳(人): "+result[i].seating+"</p>\n" +
            "            </div>\n" +
            "            <div class='weui-cell__ft'>"+arr[result[i].type]+"</div>\n" +
            "        </div>";
    }
    document.getElementById("msgList").innerHTML=str;
}
$("#picker").datetimePicker({
    onClose: getTime
});
//当选择时间玩成时进行查询操作
function getTime() {
    var str=$("#picker").val();
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
    getClassRoom(date);
}
function getClassRoom(date) {
    $.ajax({
        type:"get",
        url:"/data/findspecifytimeclassroom",
        data:{date:date},
        dataType:"json",
        success:function (result) {
            createList(result);
        }
    });
}

