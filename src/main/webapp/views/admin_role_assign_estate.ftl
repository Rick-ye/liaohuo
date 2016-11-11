<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>分配楼盘</title>
<style>
.estateItem{
  display:block;
  padding:5px;
  border:1px solid #eee;
  margin:10px;
  float:left;
   border-radius: 3px;
  -webkit-border-radius: 3px;
   -moz-border-radius: 3px;
}
</style>
</head>
<body>
<article class="page-container">
	<form  class="form form-horizontal" id="form-admin-role-assign-estate">
	    <input type="hidden" name="roleId" value="${roleId}" id="roleId"/>
	    <div class="row cl">
	        <div class="formControls col-xs-8 col-sm-9">
			<span id="tipMsg"></span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>楼盘列表：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<#if estateList??>
				   <#list estateList as es>
				   <span class="estateItem"><input type="checkbox"  name="estateId" value="${es.estateId}" <#if es.checked>checked="checked"</#if>/>${es.estateName}</span>
				   </#list>
				</#if>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="submit" class="btn btn-success radius" id="admin-role-assign-estate" name="admin-role-assign-estate"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
	</form>
</article>

<#include "admin/footer.ftl" parse=true encoding="utf-8"/> 
<#include "admin/footer2.ftl" parse=true encoding="utf-8"/>
<!--请在下方写此页面业务相关的脚本-->
<script>
$(function(){
	$("#form-admin-role-assign-estate").validate({
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.post("${ctx}/admin/roleDoAssignEstate",
			{roleId:$("#roleId").val(),
			estateIds:getEstateIds(),
			},
			function(data){
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
function getEstateIds(){
  var estateIds="";
  $(".estateItem input[type=checkbox]").each(function(){ 
      if(this.checked){
      console.log($(this).val());
      estateIds+=$(this).val()+","; 
      }
  }) ;
return estateIds;
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>