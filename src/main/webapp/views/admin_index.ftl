<#include "admin/header.ftl" parse=true encoding="utf-8">
<title>后台管理系统</title>
</head>
<body>
<#include "admin/nav.ftl" parse=true encoding="utf-8"> 
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<dl id="menu-system-end">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 系统后台管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="${ctx}/admin/demolist" data-title="DEMO" href="javascript:void(0)">DEMO管理</a></li>
					<li><a _href="${ctx}/admin/userList" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
					<li><a _href="${ctx}/admin/roleList" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a _href="article-list.html" data-title="地图管理" href="javascript:void(0)">地图管理</a></li>
					<li><a _href="article-list.html" data-title="消息管理" href="javascript:void(0)">消息管理</a></li>
					<li><a _href="article-list.html" data-title="商品管理" href="javascript:void(0)">商品管理</a></li>
					<li><a _href="article-list.html" data-title="服务管理" href="javascript:void(0)">服务管理</a></li>
					<li><a _href="article-list.html" data-title="商品订单管理" href="javascript:void(0)">商品订单管理</a></li>
					<li><a _href="article-list.html" data-title="服务订单管理" href="javascript:void(0)">服务订单管理</a></li>
				</ul>
			</dd>
		</dl>
		
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${ctx}/welcome.html"></iframe>
		</div>
	</div>
</section>
<#include "admin/footer.ftl" parse=true encoding="utf-8"> 
</body>
</html>