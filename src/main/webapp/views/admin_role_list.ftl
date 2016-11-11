<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>角色管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray"> 
	   <span class="l"> 
	      <#if ud??&&(ud.role.roleName!='Administrator'&&ud.role.roleName!='AccAdmin')>
	       <@shiro.hasPermission name="roleAdd">
	       <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加角色','${ctx}/admin/roleAdd','800','500')">
	       <i class="Hui-iconfont">&#xe600;</i> 
	                        添加角色
	       </a> 
	        </@shiro.hasPermission>
	        </#if>
	       </span> 
	      </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr class="text-c">
				<th width="40">ID</th>
				<th width="200">角色别称</th>
				<#if ud??&&(ud.role.roleName!='Administrator'&&ud.role.roleName!='AccAdmin')>
				<th width="200">角色类别</th>
				<#else>
				<th width="200">角色名称</th>
				</#if>
				<th width="300">描述</th>
				<#if ud??&&(ud.role.roleName!='Administrator'&&ud.role.roleName!='AccAdmin')>
				<th width="70">操作</th>
				</#if>
			</tr>
		</thead>
		<tbody>
		<#if roleDo??>
		<#list roleDo as role>
			<tr class="text-c">
				<td>${role.roleId}</td>
				<td>${role.roleAlias}</td>
				<#if ud??&&(ud.role.roleName!='Administrator'&&ud.role.roleName!='AccAdmin')>
				<td><#if role.roleName?contains('RegionInAdmin')>区域内部管理员<#elseif role.roleName?contains('CommonReader')>区域数据读取人员<#else>区域数据录入人员</#if></td>
				<#else>
				<td>${role.roleName}</td>
				</#if>
				<td>${role.roleDesc}</td>
				<#if ud??&&(ud.role.roleName!='Administrator'&&ud.role.roleName!='AccAdmin')>
				<td class="f-14">
				<a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','${ctx}/admin/roleEdit',${role.roleId})" style="text-decoration:none">编辑</a>
				<a title="删除" href="javascript:;" onclick="admin_role_del(this,${role.roleId})" class="ml-5" style="text-decoration:none">删除</a>
				<#if role.roleName?contains('CommonReader')||role.roleName?contains('Editor')>
				 <a title="分配楼盘" href="javascript:;" onclick="admin_role_assign_estate('分配楼盘','${ctx}/admin/roleAssignEstate',${role.roleId},800,500)" class="ml-5" style="text-decoration:none">分配楼盘</a>
				</#if>
				 </td>
				 </#if>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/> 
<script type="text/javascript">
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-编辑*/
function admin_role_edit(title,url,id,w,h){
	layer_show(title,url+"?roleId="+id,w,h);
}
/*管理员-角色-删除*/
function admin_role_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$.post("${ctx}/admin/roleDoDel",{
            "roleId": id
          }, function(data) {
            $(obj).parents("tr").remove();
		    layer.msg('已删除!',{icon:1,time:1000});
          });
	});
}
function admin_role_assign_estate(title,url,id,w,h){
	layer_show(title,url+"?roleId="+id,w,h);

}
</script>
</body>
</html>