<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE HTML>
<html>

	<head>
		<title>添加银行卡</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- Bootstrap -->
		<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">
		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="${ctx}/static/js/jquery.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/bootstrap-fp.js"></script>
	</head>

	<body>
		<div class="top1"> 添加银行卡
			<a href="index2" class="fhback"><img src="${ctx}/static/img/fhback.png"></a>
		</div>
		<div class="row wsjdbox">
			<a href="grxx" class="jd01">1</a>
       	   	<a href="modifypwd" class="jd03">2</a>
       	    <a href="address2" class="jd03">3<a>
       	    <a href="addbank" class="jd03">4</a>
		</div>
		
		<div class="row bankbox" style="margin-top:30px;"> 
			<div class="col-xs-2"><img src="${ctx}/static/img/banklogo.png"></div>	
			<div class="col-xs-10">中国工商银行<br />信用卡</div>
			<div class="col-xs-12 bankcode">**** **** **** 5989</div>
			<div class="col-xs-12 text-right"> <a href="#">编辑</a> <a href="#">删除</a></div>
		</div>

		<div class="row" style="background:#fff; padding-top: 20px;">
            <label style="padding-left: 12px; color: #999999; line-height: 60px;">请绑定持卡人本人的银行卡</label>
			<div class="form-group">
				<label class="col-xs-3 control-label">持卡人</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" placeholder="请输入姓名">
				</div>
			</div>
			<div style="height:25px;"></div>
			<div class="form-group">
				<label class="col-xs-3 control-label">卡号</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" placeholder="请输入银行卡">
				</div>
			</div>
			<div style="height:25px;"></div>
		</div>
		<div class="btn btn-lg btn-pink grxxbtn" >下一步</div>
		
		<script type="text/javascript">
			
		
		
		</script>
	</body>

</html>










