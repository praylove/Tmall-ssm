var t_isAuthenticated = false;
function getNavInfo(){
    	// get user info
    	$.get('../user/username', function(data){
    		var oprationHtml = "";
    		if (data === ""){
    			t_isAuthenticated = false;
    			oprationHtml = '<span style="margin: 0 15px 0 15px;">喵，欢迎来天猫</span><a class="link" href="../login">请登陆</a><a class="link" href="../register">免费注册</a>';
    		} else{
    			oprationHtml = '<a class="link" href="javascript:;">' + escape(data) + '</a><a class="link" href="../logout">退出</a>';
    			t_isAuthenticated = true;
    		}
    		$('#operation').html(oprationHtml);
    	});
    	
    	// get shopping car number
    	$.get('../shoppingcar/count' , function(data){
            $('#carNumber').text(data);
        });
}

function getSerchHot(){
	// get hot word
	$.get(/*get hot word url*/'', function(data){
		var hotHtml = "";
		for (var i in data){
			hotHtml += '<a class="link" href="../categorys/' + data.id + '">' + escape(data.name) + '</a>';
		}
		$('.u-search-hot').html(hotHtml);
	});
}

Date.prototype.format = function(format)
{
 var o = {
 "M+" : this.getMonth()+1, //month
 "d+" : this.getDate(),    //day
 "h+" : this.getHours(),   //hour
 "m+" : this.getMinutes(), //minute
 "s+" : this.getSeconds(), //second
 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
 "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1,
 RegExp.$1.length==1 ? o[k] :
 ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
}

function timeFormat(date){
	return new Date(date).format("yyyy-MM-dd hh :mm:ss");
}
