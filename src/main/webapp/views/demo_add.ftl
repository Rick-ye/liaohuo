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
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="名称" id="name" name="name">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" placeholder="性别" id="sex" name="sex">
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
		   name:{
				required:true,
				minlength:1,
				maxlength:16
			},
			sex:{
				required:true,
				minlength:1,
				maxlength:10
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.post("${ctx}/admin/demoDoAdd",
			{name:$("#name").val(),
			sex:$("#sex").val()
			},function(data){
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
	
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>