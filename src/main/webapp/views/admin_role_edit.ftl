<#include "admin/header.ftl" parse=true encoding="utf-8">
<link rel="stylesheet" href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>角色管理</title>
</head>
<body>
<article class="page-container">
	<form  class="form form-horizontal" id="form-admin-role-edit">
	  <input type="hidden" value="${role.roleId}" name="roleId" id="roleId"/>
	  <input type="hidden" name="permissionIds" id="permissionIds"/>
	  <div class="row cl">
	        <div class="formControls col-xs-8 col-sm-9">
			<span id="tipMsg"></span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  value="${role.roleAlias}" placeholder="创建角色" id="roleAlias" name="roleAlias" datatype="*4-32" nullmsg="角色中文名称不能为空">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色英文名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
			    <select class="select" name="roleName" id="roleName" size="1">
			    <#list userTypeList as type>
				  <option value="${type.code}" <#if role.roleName?contains(type.code)>selected</#if>>${type.text}</option>
				</#list>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">角色描述：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea id="roleDesc" name="roleDesc" cols="30" rows="4" class="textarea"  placeholder="说点什么...128个字符以内" dragonfly="true" onKeyUp="textarealength(this,128)" nullmsg="角色描述不能为空">${role.roleDesc}</textarea>
			<p class="textarea-numberbar"><em class="textarea-length">0</em>/128</p>
		</div>
		</div>
		<#if role.roleName!='RegionReader'&&!role.roleName?contains('CommonReader')>
		<div class="row cl">
		   <label class="form-label col-xs-4 col-sm-3">权限列表</label>
		   <div class="formControls col-xs-8 col-sm-9">
		   <ul id="treeDemo" class="ztree"></ul>
		   </div>
		</div>
		</#if>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
	</form>
</article>

<#include "admin/footer.ftl" parse=true encoding="utf-8"/> 
<#include "admin/footer2.ftl" parse=true encoding="utf-8"/>
<script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
function setPermissionTree(){
	var setting = {
				check: {
					enable: true,
					chkboxType:{"Y" : "ps", "N" : "ps" }
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};	
	$.post("${ctx}/admin/roleOwnPermission",{roleId:${role.roleId}},function(data){
	         $.fn.zTree.init($("#treeDemo"), setting, $.parseJSON(data)); 
	});

}

function setPermissionCheckedNodes(){
     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		 nodes = zTree.getCheckedNodes(true),
		 permissionIds="";
	     for (var i=0, l=nodes.length; i<l; i++) {
				permissionIds+=nodes[i].id+",";
		}
		 $("#permissionIds").val(permissionIds);
}
$(function(){
<#if role.roleName!='RegionReader'&&!role.roleName?contains('CommonReader')>
	setPermissionTree();
	</#if>
	$("#form-admin-role-edit").validate({
		rules:{
			roleName:{
				required:true,
			},
			roleAlias:{
				required:true,
			},
			roleDesc:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
		    <#if role.roleName!='RegionReader'&&!role.roleName?contains('CommonReader')>
		    setPermissionCheckedNodes();
		    </#if>
			$.post("${ctx}/admin/roleDoEdit",
			{roleId:$("#roleId").val(),
			roleName:$("#roleName").val(),
			roleAlias:$("#roleAlias").val(),
			roleDesc:$("#roleDesc").val(),
			permissionIds:$("#permissionIds").val()},function(data){
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