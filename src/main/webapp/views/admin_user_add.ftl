<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>添加管理员</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add">
	    <div class="row cl">
	        <div class="formControls col-xs-8 col-sm-9">
			<span id="tipMsg"></span>
			</div>
		</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="手机号码" id="mobileNumber" name="mobileNumber">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>真实姓名：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="真实姓名" id="userName" name="userName">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="@" name="userEmail" id="userEmail">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
		<div class="formControls col-xs-8 col-sm-9"> 
		<span class="select-box" style="width:150px;">
			<select class="select" name="adminRole" id="adminRole" size="1">
			    <option value="" data-val="">选择角色</option>
			    <#list roleList as role>
				<option value="${role.roleId}" data-val="${role.roleDesc}">${role.roleAlias}</option>
				</#list>
			</select>
			</span>
		 </div>
	</div>
	<div id="roleMark" class="row cl" style="display:none">
		<label class="form-label col-xs-4 col-sm-3">角色说明：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<div style="background-color:#eee;border:1px solid #eee" id="mark"></div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
<#include "admin/footer2.ftl" parse=true encoding="utf-8"/>
<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript">
$(function(){
	$("#form-admin-add").validate({
		rules:{
		   mobileNumber:{
				required:true,
				minlength:4,
				maxlength:16
			},
			userName:{
				required:true,
				minlength:2,
				maxlength:16
			},
			userEmail:{
				required:true,
				email:true,
			},
			adminRole:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.post("${ctx}/admin/userDoAdd",
			{mobileNumber:$("#mobileNumber").val(),
			userName:$("#userName").val(),
			userEmail:$("#userEmail").val(),
			adminRole:$("#adminRole").val()},function(data){
			   var json=$.parseJSON(data);
			   if(json.status==200){
			    var index = parent.layer.getFrameIndex(window.name);
			    parent.window.location=parent.window.location.href;
			    parent.layer.close(index);
			   }else{
			     $("#tipMsg").html("<font color=\"red\">"+json.message+"</font>");
			   }
			});
		}
	});
	
	$("#adminRole").change(function(){
	   var dataVal=$(this).find("option:selected").attr("data-val");
	   if(dataVal==""){
	   $("#roleMark").hide();
	   }else{
	   $("#mark").html(dataVal);
	    $("#roleMark").show();
	   }
	});
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>