<#assign ctx=rc.contextPath>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/static/js/html5.js"></script>
<script type="text/javascript" src="${ctx}/static/js/respond.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctx}/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/Hui-iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" href="${ctx}/static/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台管理页面</title>
</head>
<body>
<div class="loginWraper" style=" background:#3283AC url(${ctx}/static/images/admin-login-bg.jpg) no-repeat center">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="${ctx}/admin/doLogin" method="post">
    <div class="row cl">
      <h2>点点后台管理系统</h2>
    </div>
     <#if message??>
    <div class="row cl">
        <div class="formControls col-xs-12">
          <span class="c-red">${message}</span>
        </div>
      </div>
    </#if>
      <div class="row cl">
        <div class="formControls col-xs-12">
          <input id="userName" name="userName" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-12">
          <input id="userPwd" name="userPwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-12 col-xs-offset-3">
          <label for="remindMe">
            <input type="checkbox" name="remindMe" id="remindMe" value="1">
            记住我</label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-12 col-xs-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script> 
<script type="text/javascript" src="${ctx}/static/h-ui/js/H-ui.js"></script> 
</body>
</html>