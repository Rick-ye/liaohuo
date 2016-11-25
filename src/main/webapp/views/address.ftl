<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>

	<head>
		<title>收货地址</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- Bootstrap -->
		<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">
		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="${ctx}/static/js/jquery.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/bootstrap-fp.js"></script>
		<script src="${ctx}/static/js/addr.js"></script>
	</head>

	<body>

		<div class="top1"> 收货地址
			<a href="index2" class="fhback"><img src="${ctx}/static/img/fhback.png"></a>
		</div>
		<div class="row wsjdbox">
			<a href="grxx" class="jd01">1</a>
       	   	<a href="Modifypwd" class="jd03">2</a>
       	    <a href="address2" class="jd03">3<a>
       	    <a href="addbank" class="jd02">4</a>
		</div>

		<div class="row" style="background:#fff; padding-top: 20px;">
			<div class="form-group">
				<label class="col-xs-3 control-label">收货人</label>
				<div class="col-xs-9">
					<input type="text" id="addUserName" class="form-control" placeholder="请输入收货人">
				</div>
			</div>
			<div style="height:25px;"></div>
			<div class="form-group">
				<label class="col-xs-3 control-label">手机号码</label>
				<div class="col-xs-9">
					<input type="text" id="addMobile" class="form-control" placeholder="请输入手机号码">
				</div>
			</div>
			<div style="height:25px;"></div>
			<div class="form-group">
				<label class="col-xs-3 control-label">所在地区</label>
				<div class="col-xs-9" id="main">
					<span class="col-xs-3" style="margin: 0; padding: 0;">	
						<select class="form-control" id="addPrvn">
						</select>
					</span>
					<span class="col-xs-3" style="margin: 0; padding: 0; margin-left: 10px;">
						<select class="form-control" id="addCity">
						</select>
					</span>

					<span class="col-xs-3" style="margin: 0; padding: 0; margin-left: 10px;">
						<select class="form-control" id="addCnty">
						</select>		 
					</span>
				</div>
			</div>

			<div style="height:25px;"></div>
			<div class="form-group">
				<label class="col-xs-3 control-label">详细地址</label>
				<div class="col-xs-9">
					<input type="text" id="addInfo" class="form-control" placeholder="请输入手机号码">
				</div>
			</div>
				<div style="height:25px;"></div>
		</div>
		<div class="btn btn-lg btn-pink grxxbtn" id="saveAddr">保 存</div>
		<div class="btn btn-lg btn-default grxxbtn" id="delAddr">删除</div>
		<#include "admin/footer.ftl" parse=true encoding="utf-8"/>
		<script type="text/javascript">
		
		$(function(){
			$('#saveAddr').click(function(){
				var addUserName = $('#addUserName').val();
				var addMobile = $('#addMobile').val();
				var addPrvn = document.getElementById('addPrvn').value;
				var addCity = document.getElementById('addCity').value;
				var addCnty = document.getElementById('addCnty').value;
				var addInfo = $('#addInfo').val();
				var data = {
						'addUserName':addUserName,
						'addMobile':addMobile,
						'addPrvn':addPrvn,
						'addCity':addCity,
						'addCnty':addCnty,
						'addInfo':addInfo
					};
				$.ajax({
					url:'${ctx}/consumer/saveAddr',
					data:data,
					type:'post',
					dateType:'text',
					success:function(result){
						var json = $.parseJSON(result);
						if(json.status == 200){
							layer.msg('保存成功!',{icon:1,time:1000});
						}else{
							layer.msg(json.message,{icon:1,time:1000});
						}
					}
				});
			});
		});
		
		
		
		<!-- 地址联动 -->
			window.onload = function(){
				var main = document.getElementById("main");
				var selects = main.getElementsByTagName("select");
				<!--console.log(selects);-->
				<!-- 初始化省份，为第一个下拉框添加内容 -->
				for(pro in arrCity){
					createOption(selects[0],arrCity[pro].name);
				}
				function createOption(parent,innerHTML){
					var option = document.createElement("option");
					option.innerHTML = innerHTML;
					parent.appendChild(option);
				}
				var one = 0;
				selects[0].onchange = function(){
					<!-- 清空第二个和第三个下拉框 -->
					selects[1].innerHTML = selects[2].innerHTML = '';
					for(pro in arrCity){
						<!-- 判断选择的省份 -->
						if(this.value == arrCity[pro].name){
							one = pro;
							for(city in arrCity[pro].sub){
								createOption(selects[1],arrCity[pro].sub[city].name);
							}
						}
					}
				}
				selects[1].onchange = function(){
					selects[2].innerHTML='';
					for(city in arrCity[one].sub){
						if(this.value == arrCity[one].sub[city].name){
							for(county in arrCity[one].sub[city].sub){
　　　　　　　　　　　　			createOption(selects[2],arrCity[one].sub[city].sub[county].name);
							}
						}
					}
				}
			}
			
		</script>
	</body>

</html>











