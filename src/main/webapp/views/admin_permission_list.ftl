<#include "admin/header.ftl" parse=true encoding="utf-8">
<link rel="stylesheet" href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>权限列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 权限列表</nav>
<article class="page-container">
		<div class="row cl">
			     <div class="formControls col-xs-12 col-sm-12">
		              <ul id="treeDemo" class="ztree"></ul>
			     </div>
			</div>
</article>
<#include "admin/footer.ftl" parse=true encoding="utf-8"/> 
<script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.excheck-3.5.js"></script>

<SCRIPT type="text/javascript">

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
	$.post("${ctx}/admin/permissionList2",{parentId:0},function(data){
	         console.log(data);
	         $.fn.zTree.init($("#treeDemo"), setting, $.parseJSON(data)); 
	});

}	
	setPermissionTree();		
	</SCRIPT>
</body>
</html>