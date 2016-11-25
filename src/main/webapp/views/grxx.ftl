<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>

<head>
	<title>个人信息</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui">
	<!-- Bootstrap -->
	<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">
	<link href="${ctx}/static/css/jquery.Jcrop.css" rel="stylesheet" media="screen">
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${ctx}/static/js/jquery.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/bootstrap-fp.js"></script>
	<script src="${ctx}/static/js/jquery.Jcrop.js"></script>
	
	 
	
</head>

<body>
	<div class="top1">个人信息 <a href="index2" class="fhback"><img src="${ctx}/static/img/fhback.png"></a> </div>
   	<div class="row wsjdbox">
   	   	<a href="grxx" class="jd01">1</a>
   	   	<a href="modifypwd" class="jd02">2</a>
   	   	<a href="address2" class="jd02">3<a>
   	   	<a href="addbank" class="jd02">4</a>
   	</div> 
   	<div class="row grxxtop">
       	<div class="grxxtx">
       		<#if cutImg ??>
       			<img src="${ctx}/${cutImg}" id="cutImg" class="img-circle">
    		<#else>
    			<img src="${ctx}/static/img/grzxtx.png" id="cutImg" class="img-circle">
    		</#if>
    	</div>
    	<form action="${ctx}/consumer/headImg" method="post" enctype="multipart/form-data">
       		<div class="col-xs-4 col-xs-offset-4 grxxname">
	       		<input type="file" id="uploadImg" name="uploadImg" onchange="readURL(this);"/>
	       		<input type="hidden" id="x" name="x" />
		        <input type="hidden" id="y" name="y" />
		        <input type="hidden" id="w" name="w" />
		        <input type="hidden" id="h" name="h" />
		        <input type="submit" value="上传" />
       		</div>
       	</form>
  	</div> 
  	<form role="form">
	<div class="row" style="background:#fff; padding-top: 20px;">
	
		<div class="form-group ">
		 	<label for="nickName">昵称</label>
		  <input type="text"  class="form-control" id="nickName" placeholder="请输入昵称">
		<p class="help-block">好名字让你的好朋友更容易记住你.</p>
			
		</div>

		<div class="form-group">
		  	<label   class="col-xs-12 control-label">性别</label>
			<div class="col-xs-12">
				<label class="radio-inline">
		  			<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option1"> 男
				</label>
				<label class="radio-inline">
				  	<input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option2"> 女
				</label>
			</div>
		
	  	</div>
	</div>
 	</form>
		<div class="row" style="clear: both; padding-top: 20px; ">
		<div id='d1' style="line-height:50px; text-align: center; color: #999; ">展开实名认证<span class="glyphicon glyphicon-chevron-down"></span></div>
		<div id='d2' style=" display:none; border: 1px solid #E8E8E8;
			border-radius: 4px; margin-left: 2%; width: 96%;">
			<div class="row" style="margin-top:30px;">
				<div class="col-xs-12">
	    			<input type="text" id="cardId" class="form-control" placeholder="请输入身份证号">
	    		</div>
			</div>
			<div class="row" style="padding-top: 10px;">
				<div class="col-xs-2">上传</div>
				<div class="col-xs-4"><img src="${ctx}/static/img/uploadimg.png"></div>
				<div class="col-xs-5"><img src="${ctx}/static/img/sfzzm.png"></div>
			</div>
			<div class="row" style="margin-top: 10px; margin-bottom:30px ;">
				<div class="col-xs-2"></div>
				<div class="col-xs-4"><img src="${ctx}/static/img/uploadimg.png"></div>
				<div class="col-xs-5"><img src="${ctx}/static/img/sfzfm.png"></div>
			</div>
		</div>
	</div>
	<div class="btn btn-lg btn-pink grxxbtn" id="save">保 存</div>
	
	<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
	
	<script type="text/javascript">
	
		function readURL(input) {
			
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.readAsDataURL(input.files[0]);
				reader.onload = function(e) {
				
					<!--jQuery(function($){-->
					
					$('#cutImg').removeAttr('src');
					$('#cutImg').attr('src', e.target.result);	
					<!--jQuery.noConflict();-->
					$('#cutImg').Jcrop({
					    setSelect: [ 20, 20, 200, 200 ],	
					    aspectRatio: 1,			
					    onSelect: updateCoords,
					    onChange: updateCoords	
					});
					<!--});-->
				}
				
			}
			
			function updateCoords(obj) {
				$("#x").val(obj.x);
				$("#y").val(obj.y);
				$("#w").val(obj.w);
				$("#h").val(obj.h);
				
			}
		}
	
		$(function(){
			$('#save').click(function(){
				var cutImgUrl = $('#cutImg').attr('src');
				console.log(cutImgUrl);
				var nickName = $('#nickName').val();
				var sex = $("input[name='inlineRadioOptions']:checked").val();
				var cardId = $('#cardId').val();
				console.log(cardId);
				if (!cardId || typeof(cardId)=="undefined" || cardId==0){
					var data = {
						'cutImgUrl':cutImgUrl,
						'nickName':nickName,
						'sex':sex
					};
				}else{
					var data = {
						'cutImgUrl':cutImgUrl,
						'nickName':nickName,
						'sex':sex,
						'cardId':cardId
					};
				}
				$.ajax({
					url:'${ctx}/consumer/personinfo',
					data:data,
					type:'post',
					dateType:'text',
					success:function(result){
						var json = $.parseJSON(result);
						if(json.status == 200){
							layer.msg('保存成功!',{icon:1,time:1000});
							window.location.href='modifypwd';
						}else{
							layer.msg(json.message,{icon:1,time:1000});
						}
					}
				});
			});
		});
	
	</script>
 
</body>

</html>