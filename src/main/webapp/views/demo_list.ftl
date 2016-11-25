<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>DEMO列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> DEMO管理 <span class="c-gray en">&gt;</span> DEMO 列表<a class="btn btn-success btn-refresh radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<@shiro.hasPermission name="adminAdd">
	<a href="javascript:;" onclick="admin_add('添加demo','${ctx}/admin/demoAdd','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加demo</a>
	</span>&nbsp;&nbsp;
	<span class="l" style="color:#FF0000"></span </div>
	</@shiro.hasPermission>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr class="text-c">
				<th >用户ID</th>
				<th >名称</th>
				<th >性别</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list demoList as demo>
				<tr class="text-c">
					<td>${demo.id}</td>
					<td>${demo.name}</td>
					<td>${demo.sex}</td>
					<td class="td-manage">
					   <a title="编辑用户" href="javascript:;" onclick="admin_edit('用户编辑','${ctx}/admin/demoEdit',${demo.id},'800','500')" class="ml-5" style="text-decoration:none">编辑</a>
					   <a title="删除用户" href="javascript:;" onclick="admin_del(this,${demo.id})" class="ml-5" style="text-decoration:none">删除</a>
					</td>
				</tr>
			</#list>
		</tbody>
	</table>
</div>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
<script type="text/javascript">
/*添加demo*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*删除demo*/
function admin_del(obj,id){
	layer.confirm('确认要删除该用户吗？',function(index){
		$.post('${ctx}/admin/demoDel',{id:id},function(data){
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
function admin_edit(title,url,id,w,h){
	layer_show(title,url+"?id="+id,w,h);
}
</script>
</body>
</html>