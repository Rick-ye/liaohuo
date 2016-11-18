<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>手机号注册</title>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
<#include "admin/footer2.ftl" parse=true encoding="utf-8"/>
</head>
<body>

	<p>
		手机号：<input id="phoneNumber" type="text" name="phoneNumber">
		<div class="warning" id="warning_phone"></div>
	</p>
	<p>
		请输入验证码：<input id="veriCode" type="text" name="veriCode"/>
		<input id="btn" type="button" value="获取验证码"/>
		<div class="warning" id="warning_veri"></div>
	</p>
	<p>
		密码：<input id="userPwd" type="password" name="userPwd">
		<div class="warning" id="warning_pwd"></div>
	</p>
	<p>
		确认密码：<input id="userPwd1" type="password" name="userPwd1">
		<div class="warning" id="warning_pwd1"></div>
	</p>
	<p>
		<input id="submit" type="button" value="撩起来">
		<div id="test"></div>
	</p>

<script type="text/javascript">


$(function(){
	$("#submit").click(function(){
		var phoneNumber = $("#phoneNumber").val();
		var veriCode = $("#veriCode").val();
		var userPwd = $("#userPwd").val();
		var userPwd1 = $("#userPwd1").val();
		
		var pass = true;
		//判断手机号
		if(! (/^1[34578]\d{9}$/.test(phoneNumber))){ 
	        pass = false;
	        $("#warning_phone").html("手机号码不正确").show();
	    }
	  	//判断验证码
	  	if(! (/^\d{6}$/.test(veriCode))){
	  		pass = false;
	  		$("#warning_veri").html("验证码不正确").show();
	  	}
		//判断密码
		if (userPwd == ""){
		   pass = false;
		   $("#warning_pwd").html("密码不能为空").show();
		}
		if(userPwd1 == "" || userPwd != userPwd1){
			 pass = false;
		   $("#warning_pwd1").html("两次密码不相等").show();
		}
		if(! pass){
			return pass;
		}
		var data = {'phoneNumber':phoneNumber,
					'veriCode':veriCode,
					'userPwd':userPwd};
		$.ajax({
			url:'${ctx}/consumer/doRegist',
			data:data,
			type:'POST',
			dataType:'text',
			success:function(data){
				var json = $.parseJSON(data);
				if(json.status == 200){
					layer.msg('注册成功!',{icon:1,time:1000});
					<!--setTimeout("javascript:location.href='login.do'", 1000);-->
					window.location.href='login.do?mobileNum='+phoneNumber;
				}else{
					$("#test").html("<font color=\"red\">"+json.message+"</font>");
				}
			}
		});
	});
});


<!-- 获取验证码 -->
$(function(){
	$("#btn").click(function() {
		var phoneNumber = $("#phoneNumber").val();
		var pass = true;
		//判断手机号
		if(! (/^1[34578]\d{9}$/.test(phoneNumber))){ 
	        pass = false;
	        $("#warning_phone").html("手机号码不正确").show();
	    }
	    if(! pass){
	    	return pass;
	    }
	    
		$.ajax({
			url : '${ctx}/consumer/doVeriCode', 
			data : {'phoneNumber' : phoneNumber}, 
			type : "POST", 
			dataType : "text", 
			success : function(data) {
				var json=$.parseJSON(data);
				if(json.status == 200){
					time(this);
					layer.msg('已发送!',{icon:1,time:1000});
					<!--document.getElementById("#button").value = "已发送";-->
				}else{
			     	$("#test").html("<font color=\"red\">"+json.message+"</font>");
			    }
				
			}
		});
	});
});

<!-- 验证码60秒倒计时  -->
var wait=60;  
function time(o) {  
    if (wait == -1) {  
        o.removeAttribute("disabled");       
        wait = 60;  
    } else {  
        o.setAttribute("disabled", true);  
        o.value="重新发送(" + wait + ")";  
        wait--;  
        setTimeout(function() {  
            time(o)  
        },  
        1000)  
    }  
}  
 

</script> 

</body>
</html>

	