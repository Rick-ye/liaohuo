<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>修改密码</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-updatepwd" >
	<input type="hidden" value="${userId}" name="userId"  id="userId"/>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原始密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" placeholder="原始密码" id="oldPwd" name="oldPwd">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" placeholder="新密码" id="newPwd" name="newPwd">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码确认：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text"  name="confirmPwd" id="confirmPwd">
			<span id="tipMsg"></span>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="button"  id="newpwd-save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
<#include "admin/footer2.ftl" parse=true encoding="utf-8"/>
<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript">
$(function(){
    $("form input").focus(function(){
  		$("#tipMsg").html("");
  });
    $("#newpwd-save").click(function(){
      var oldPwd=$("#oldPwd").val(),confirmPwd =$("#confirmPwd").val(),newPwd=$("#newPwd").val();
      if(oldPwd==""){
      $("#tipMsg").html("<font color='red'>请输入原始密码</font>");
        return false;
      }
      if(newPwd==""){
      $("#tipMsg").html("<font color='red'>请输入新密码</font>");
        return false;
      }
      if(confirmPwd==""){
      $("#tipMsg").html("<font color='red'>请输入确认新密码</font>");
        return false;
      }
      if(confirmPwd===newPwd){
      $.post(
				"${ctx}/admin/doUpdatePwd",
				{userId:$("#userId").val(),oldPwd:$("#oldPwd").val(),newPwd:$("#newPwd").val()},
				function(data){
				  var json=$.parseJSON(data);
				  if(json.status==200){
				   layer.msg(json.message,{icon:1,time:1000});
				    parent.window.location="${ctx}/admin/logout";
				   var index = parent.layer.getFrameIndex(window.name);
				   parent.layer.close(index);
				  }else{
				   $("#tipMsg").html("<font color='red'>"+json.message+"</font>");
				  }
				}
			);
      
      }else{
        $("#tipMsg").html("<font color='red'>两次输入密码不一致</font>");
        return false;
      }
    });
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>