<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<@shiro.hasPermission name="adminAdd">
	<a href="javascript:;" onclick="admin_add('添加用户','${ctx}/admin/userAdd','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
	</span>&nbsp;&nbsp;
	<span class="l" style="color:#FF0000">新添加用户，默认初始密码 888888，请通知用户自行修改密码</span </div>
	</@shiro.hasPermission>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr class="text-c">
				<th width="40">用户ID</th>
				<th width="130">登录名</th>
				<th width="90">用户名</th>
				<th width="130">角色</th>
				<th width="130">操作</th>
			</tr>
		</thead>
		<tbody>
		<#list userDo as user>
			<tr class="text-c">
				<td>${user.userId}</td>
				<td>${user.mobileNumber}</td>
				<td >
				<a href="javascript:;" id="tips_${user.userId}"  style="text-decoration:none"
				onclick="show_tips('${user.userId}','${user.mobileNumber}','${user.userName}',<#if user.userEmail??>'${user.userEmail}'<#else>''</#if>,'${user.createTime?string("yyyy-MM-dd HH:mm:ss")}','tips_${user.userId}')">
				${user.userName}</a></td>
				<td><#if user.role??>${user.role.roleAlias}</#if></td>
				<td class="td-manage">
				 <#if user.role??&&user.role.roleName!='Administrator'>
				   <a title="编辑用户" href="javascript:;" onclick="admin_edit('用户编辑','${ctx}/admin/userEdit',${user.userId},'800','500')" class="ml-5" style="text-decoration:none">编辑</a>
				   <a title="删除用户" href="javascript:;" onclick="admin_del(this,${user.userId})" class="ml-5" style="text-decoration:none">删除</a>
				  </#if>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除该用户吗？',function(index){
		$.post('${ctx}/admin/userDel',{userId:id},function(data){
		  var json=$.parseJSON(data);
		  if(json.status==200){
		  $(obj).parents("tr").remove();
		  layer.msg('已删除!',{icon:1,time:1000});
		  }else{
		  layer.msg(json.message,{icon:1,time:1000});
		  }
		});
		
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url+"?userId="+id,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		 $.post("${ctx}/admin/userStop",{
            "userId": id
          }, function(data) {
            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		    $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		    $(obj).remove();
		    layer.msg('已停用!',{icon: 5,time:1000});
          });
		
		
	});
}
function admin_merchant_add(title,url,id,w,h){
    layer_show(title,url+"?userId="+id,w,h);
}
/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.post("${ctx}/admin/userStart",{
            "userId": id
          }, function(data) {
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
		});
	});
}
function show_tips(userId,mobileNumber,userName,userEmail,createTime,id){
layer.tips("用&nbsp;&nbsp;户&nbsp;&nbsp;名: "+userId+"<br>登&nbsp;&nbsp;录&nbsp;&nbsp;名: "+mobileNumber+"<br>用户名称: "+userName+" <br>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱: "+userEmail+"<br>创建时间: "+createTime, '#'+id);
} 
</script>
</body>
</html>