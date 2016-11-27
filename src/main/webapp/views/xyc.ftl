<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>

	<head>
		<title>许愿池</title>
		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<!-- Bootstrap -->
		<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">

		<script src="${ctx}/static/js/jquery-2.1.3.js"></script>

		<script src="${ctx}/static/js/bootstrap-fp.js"></script>
 
    
      </head>

	   <body style="position:relative; ">
        
      <div class="top1"> 许愿池 <a href="index2.html" class="fhback"><img src="${ctx}/static/img/fhback.png"></a></div>
      <div class="xycbox">
        <div id="cloud1"><img src="${ctx}/static/img/iP6Plus/cloud-big.png" width="119" height="44"></div>
        <div id="cloud2"><img src="${ctx}/static/img/iP6Plus/cloud-small.png" width="61" height="25"></div>
      	<div id="stone01"><img src="${ctx}/static/img/stone01.png"></div>
        <div id="stone02"><img src="${ctx}/static/img/stone02.png"></div>
      	<div id="stone03"><img src="${ctx}/static/img/stone03.png"></div>
      	<div id="stone04"><img src="${ctx}/static/img/stone04.png"></div>
      	<div id="coin"><img src="${ctx}/static/img/jingb.png"></div>
      	<div id="tortoise"><img src="${ctx}/static/img/wugui.png"></div>
      	<div id="crab"><img src="${ctx}/static/img/crab.png"></div>
      	<div id="crab2"><img src="${ctx}/static/img/crab2.png"></div>
      	<div id="aquatic"><img src="${ctx}/static/img/aquatic plants.png"></div>
      	<div id="aquatic2"><img src="${ctx}/static/img/aquatic plants2.png"></div>
      
      	<div id="startbtn">撩够撩妹钱，<a   class="net">开撩！</a> </div>
      	<div id="ldjg1">哇，恭喜您撩到金币，<a   class="jbback">马上领取！</a> </div>
      	<div id="ldjg2">撩到一条小鱼儿，<a   class="fsxy">放生吧</a> </div>
      	<div id="ldjg3">撩到一只螃蟹，<a   class="zlpx">煮了吧</a> </div>
      	<div id="ldjg4">撩到一只海星，<a   class="fshx">放生吧</a> </div>
      	<div id="ldjg5">撩到一只乌龟，<a   class="fswg">放生吧</a> </div>
      	<div id="ldjg6">撩到一只鲨鱼，<a   class="jmsy">救命啦！</a> </div>
      	
      		 <div id="fishnet" class="salvage"> <img src="${ctx}/static/img/fishnet.png"  > </div>
      	   <div class="water"></div>
      <div class="sea" style=" margin-top: 400px; width: 100%; height:100px; z-index: 1019; position: absolute;"></div>
      <div class="prize">
        <img id="prize" src="${ctx}/static/img/salvage_0.png" />
      </div>
        <div class="shazi"></div>
      </div>
   <script src="${ctx}/static/js/bottle.js"></script>   
 <script type="text/javascript">
 
  $(".net").on("click", function(){
  	    
  	  	  $("#startbtn").hide();
  	  	  reset();
  	 
  });
  
   $(".jbback").on("click", function(){
  	      $("#ldjg1").hide();
  	  	  $("#startbtn").show();
  	  	   $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	  
  	  	   
  	 
  });
   $(".fsxy").on("click", function(){
  	      $("#ldjg2").hide();
  	  	  $("#startbtn").show();
  	  	   $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	 
  	  	   
  	 
  });
     $(".zlpx").on("click", function(){
  	    $("#ldjg3").hide();
  	  	  $("#startbtn").show();
  	  	   $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	   
  	 
  });
   $(".fshx").on("click", function(){
  	    $("#ldjg4").hide();
  	  	  $("#startbtn").show();
  	  	   $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	   
  	 
  });
   $(".fswg").on("click", function(){
  	    $("#ldjg5").hide();
  	  	  $("#startbtn").show();
  	  	   $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	   
  	 
  });  $(".jmsy").on("click", function(){
  	    $("#ldjg6").hide();
  	  	  $("#startbtn").show();
  	  	  $("#prize").attr({
  	  	  	"src" : "",
  	  	  });
  	  	   
  	 
  });

 </script>


	
	</body>

</html>