<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>

	<head>
		<title>首页</title>
		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<!-- Bootstrap -->
		<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${ctx}/static/css/bootstrap-pf.css" rel="stylesheet" media="screen">
		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
        <script src="${ctx}/static/js/jquery.js"></script>
		<script src="${ctx}/static/js/bootstrap-fp.js"></script>
		<script src="${ctx}/static/js/hammer.min.js"></script>
		 
      
        </head>

	    <body>
        
       <div class="top"> <a href="#" class="sysbox" ><img src="${ctx}/static/img/sysicon.png"></a> <a href="schye.html" ><img src="img/inputbg.png"></a> <a href="#" class="xxbox"><img src="img/xxicon.png"></a></div>
        	
       <div class="row">
        	<div data-ride="carousel" class="carousel slide" id="carousel-example-generic">
        <ol class="carousel-indicators">
          <li class="" data-slide-to="0" data-target="#carousel-example-generic"></li>
          <li data-slide-to="1" data-target="#carousel-example-generic" class="active"></li>
          <li data-slide-to="2" data-target="#carousel-example-generic" class=""></li>
        </ol>
        <div role="listbox" class="carousel-inner">
          <div class="item">
            <img alt="First slide" src="${ctx}/static/img/topbnr1.png" data-holder-rendered="true">
          </div>
          <div class="item active">
            <img alt="" src="${ctx}/static/img/topbnr1.png" data-holder-rendered="true">
          </div>
          <div class="item">
            <img alt="" src="${ctx}/static/img/topbnr1.png" data-holder-rendered="true">
          </div>
        </div>
        <a data-slide="prev" role="button" href="#carousel-example-generic" class="left carousel-control">
          <span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a data-slide="next" role="button" href="#carousel-example-generic" class="right carousel-control">
          <span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
          <span class="sr-only">Next</span>
        </a>
    </div>
   <!-- 轮播左右滑动-->
 
  <script>
 
          
        
          $(function(){
            var myElement= document.getElementById('carousel-example-generic')
            var hm=new Hammer(myElement);
            hm.on("swipeleft",function(){
                $('#carousel-example-generic').carousel('next')
            })
            hm.on("swiperight",function(){
                $('#carousel-example-generic').carousel('prev')
            })
        })
 
  
  
  </script>
   <div class="row">
   <div class="col-xs-3 lhfl">
   	<a href="#">
   	<div class="lbicon text-center"><img src="${ctx}/static/img/lbicon1.png"></div>
   	<div class="lbname text-center">潮童</div>
   	</a>
   </div>
   <div class="col-xs-3 lhfl">
   	<a href="#">
   	<div class="lbicon text-center"><img src="${ctx}/static/img/lbicon2.png"></div>
   	<div class="lbname text-center">型男装</div>
   	</a>
   </div>
   <div class="col-xs-3 lhfl">
   	<a href="#">
   	<div class="lbicon text-center"> <img src="${ctx}/static/img/lbicon3.png"></div>
   		<div class="lbname text-center">女1号</div>
   	</a>
   </div>
   <div class="col-xs-3 lhfl">
   	<a href="#">
   	<div class="lbicon text-center"><img src="${ctx}/static/img/lbicon4.png"></div>
   	<div class="lbname text-center">香 包</div>
   	</a>
   </div>
   
   </div>
   
      <!--
   	作者：32570732@qq.com
   	时间：2016-10-26
   	描述：活动区
   -->
   <div class="row" style="margin-top: 10px;">
   	
   	<div class="col-xs-6" style="margin: 0; padding-left: 5px; padding-right: 5px;"><img src="${ctx}/static/img/ggtu2.png" class="img-responsive"></div>
   		<div class="col-xs-6" style="margin: 0; padding-left: 5px; padding-right: 5px;"><img src="${ctx}/static/img/ggtu1.png" class="img-responsive"></div>
   </div>
   
   
   
   
   <!--
   	作者：32570732@qq.com
   	时间：2016-10-26
   	描述：1
   -->
   <div class="row">
   	
   	<div class="col-xs-12 flbt"> <a href="prolist.html">潮童</a> </div>
   	
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<a href="Product details.html">
   			<img src="${ctx}/static/img/tu1.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   			</a>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   	
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu2.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   	</div>
   	
   	<!--
   	作者：32570732@qq.com
   	时间：2016-10-26
   	描述：2
   -->
   <div class="row">
   		<div class="col-xs-12 flbt">型男装</div>
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu1.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   	
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu2.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
 
   	
   	</div>
   	
   	
   	<!--
   	作者：32570732@qq.com
   	时间：2016-10-26
   	描述：3
   -->
   <div class="row">
   		<div class="col-xs-12 flbt">女1号</div>
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu1.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   	
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu2.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   
   	
   	</div>
   	
   	
   	<!--
   	作者：32570732@qq.com
   	时间：2016-10-26
   	描述：4
   -->
   <div class="row" style="margin-bottom:60px;">
   <div class="col-xs-12 flbt">香包</div>
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu1.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
   	
   	<div class="col-xs-6 cpzs"> 
   		<div class="probox1 text-center">
   			<img src="${ctx}/static/img/tu2.png" class="img1">
   			<div class="xyjl">
   				<span class="xybox pull-left">信誉：<img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon1.png"><img src="${ctx}/static/img/mjicon2.png"></span>
   				<span class="jlbox pull-right"><img src="${ctx}/static/img/jlicon1.png">&nbsp;0.58km</span>
   			</div>
   	    </div>
   	    
   		<div class="probt text-center" style="clear: both;">2016秋冬季潮流新品包臀套头针织连衣裙长袖打底女装价格  </div>
   		<div class="projg text-center"> ¥ 48.00  </div>
   	</div>
   	
 
   	
   	</div>
   
   
   <div  class="dbnav">
 <ul >
 <li class="dqlm"><a href="index2.html"><span  class="icon1">首页</span></a></li>
 <li><a href="qbpro.html"><span   class="icon2">分类</span></a></li>
 <li><a href="gwc.html"><span  class="icon3">购物篮</span></a></li>
 <li><a href="grsz"><span  class="icon4">我的</span></a></li>
 </ul>
 </div>
   
  
  

	</body>

</html>