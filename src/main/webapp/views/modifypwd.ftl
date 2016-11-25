<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>
	<head>
		<title>修改密码</title>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<!-- Bootstrap -->
		<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">
		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="${ctx}/static/js/jquery.js"></script>
	    <script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/bootstrap-fp.js"></script>
	</head>
	<body>
	   	<div class="top1"> 修改密码  <a href="index2" class="fhback"><img src="${ctx}/static/img/fhback.png"></a> </div>
	   	<div class="row wsjdbox">
	   		<a href="grxx" class="jd01">1</a>
	   	   	<a href="modifypwd" class="jd03">2</a>
	   	    <a href="address2" class="jd02">3<a>
	   	    <a href="addbank" class="jd02">4</a>
	   	</div> 
	   
		<div class="row" style="background:#fff; padding-top: 20px;">
			<div class="form-group">
				<div class="col-xs-12">
					<input type="text" class="form-control" id="mobileNum" placeholder="请输入手机号码">
				</div>
			</div>
			<div style="height:25px;" id="warning_phone"></div>
			
			<div class="form-group">
				<div class="col-xs-8">
					<input type="text" class="form-control" id="veriCode" placeholder="请输入验证码">
				</div>
				<div class="col-xs-4">
					<input type="button" class="btn btn-default" id="warning_veri" value="获取验证码"/>
				</div>
			</div>
			
			<div style="height:25px;"></div>
			<div class="form-group">
				<label style="padding-left: 12px;"> 登录密码设置</label>
				<div class="col-xs-12">
					<input type="password" class="form-control" id="userPwd" placeholder="请输入密码">
				</div>
			</div>
			<div style="height:25px;" id=""></div>
			<div class="form-group">
				<div class="col-xs-12">
					<input type="password" class="form-control" id="userPwd1" placeholder="确认密码">
				</div>
			</div>
			
			<div style="height:25px;"></div>
			<div class="form-group">
				<label  style="padding-left: 12px;"> 支付密码设置</label>
				<div class="col-xs-12">
					<input type="password" class="form-control" id="payPwd" placeholder="请输入密码">
				</div>
			</div>
			<div style="height:25px;"></div>
			<div class="form-group">
				<div class="col-xs-12">
					<input type="password" class="form-control" id="payPwd1" placeholder="确认密码">
				</div>
			</div>
		</div>
	 	<div class="btn btn-lg btn-pink grxxbtn" id="modify">修改</div>
	    <#include "admin/footer.ftl" parse=true encoding="utf-8"/>
	    <script type="text/javascript">
	    	var wait=60;
	    	function time(o) {  
			    if (wait == -1) { 
			    	o.value="重新发送";
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
	    	
	    	
		    $(function(){
				$("#warning_veri").click(function() {
					var mobileNum = $("#mobileNum").val();
					var pass = true;
					//判断手机号
					if(! (/^1[34578]\d{9}$/.test(mobileNum))){ 
				        pass = false;
				        $("#warning_phone").html("手机号码不正确").show();
				    }
				    if(! pass){
				    	return pass;
				    }
				    
					$.ajax({
						url : '${ctx}/consumer/getvericode', 
						data : {'mobileNum' : mobileNum}, 
						type : 'POST', 
						dataType : 'text', 
						success : function(data) {
							var json=$.parseJSON(data);
							if(json.status == 200){
								time(this);
								layer.msg('已发送!',{icon:1,time:1000});
							}else{
						     	layer.msg(json.message,{icon:1,time:1000});
						    }
							
						}
					});
				});
			});
		    
	    	
			
	    
	    	$(function(){
	    		$('#modify').click(function(){
	    			var mobileNum = $('#mobileNum').val();
	    			var veriCode = $('#veriCode').val();
	    			var userPwd = $('#userPwd').val();
	    			var userPwd1 = $('#userPwd1').val();
	    			var payPwd = $('#payPwd').val();
	    			var payPwd1 = $('#payPwd1').val();
	    			var pass = true;
	    			
					//判断手机号
					if(! (/^1[34578]\d{9}$/.test(mobileNum))){ 
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
					   $("#warning_userPwd").html("密码不能为空").show();
					}
					if(userPwd1 == "" || userPwd != userPwd1){
						 pass = false;
					   $("#warning_userPwd1").html("两次密码不相等").show();
					}
					if(! pass){
						return pass;
					}
					var data = {'mobileNum':mobileNum,
								'veriCode':veriCode,
								'userPwd':userPwd,
								'payPwd':payPwd };
					$.ajax({
						url:'${ctx}/consumer/updatepwd',
						data:data,
						type:'post',
						dateType:'text',
						success:function(data){
							var json = $.parseJSON(data);
							if(json.status == 200){
								layer.msg('修改成功!',{icon:1,time:1000});
								window.location.href='address2';
							}else{
								layer.msg(json.message,{icon:1,time:1000});
							} 
						}
					});
	    		});
	    	});
	 	</script>
	</body>
</html>