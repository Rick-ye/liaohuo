var $salveage = $(".salvage");
var interval;
var prize = new Array();
var isAction=false;
function reset() {
	
	if(isAction){
		alert("正在打捞,请勿重复");
		return false;
	}
	
	isAction=true;
	$salveage.css({
		left : "0px",
		top : "0px",
		width:"166px",
		height:"71px"
	});
	$(".prize").css({
		"z-index" : 1,
		"display" : "none"
	});
	
	track.init($('.salvage'),120,470,190);
	
	 
}
//抛出网兜 旋转加放大的过程
function salveage() {
	
	$salveage.animate({
		left : "0px",
		top : "0px",
		width : "266px",
		height : "171px",
	}, 1000, function () {
		
	});
}
//来回搜索
function search() {
	$salveage.animate({
		left : "50px",
	}, 1000, function () {
		$salveage.animate({
			left : "120px"
		}, 1000, function () {
			
			showPrize();
		})
	});
}
function showPrize() {
	$salveage.css({"-webkit-transform":"rotate(0deg)"});
	$(".sea").css({
		"z-index" : -1
	});
//	$(".water").css({
//		"z-index" : 3,
//		"opacity" : 1,
//		"top" : "150px"
//	});
//	$(".water").animate({
//		top : "220px",
//		opacity : 0
//	}, 500, function () {});

	getPrize();
	$(".prize").css({
		"z-index" : 1,
		"display" : "block"
	});

	$(".sea").css({
		"z-index" : 1019
	});
	isAction=false;
}

function getPrize() {
/*	prize[0] = "salvage_0.png";
	prize[1] = 'salvage_1.png';
	prize[2] = 'salvage_2.png';
	prize[3] = 'salvage_3.png';
	prize[4] = 'salvage_4.png';
	prize[5] = 'salvage_5.png';*/
	var num = Math.round(Math.random() * 5);
	$("#prize").attr("src", "./img/salvage_" + num + ".png");
	var ldjg= $("#prize").attr("src");
	if( ldjg == "./img/salvage_0.png" ){
		 $("#ldjg1").show();	 
	}
	if( ldjg == "./img/salvage_1.png" ){
		 $("#ldjg2").show();
	}
	if( ldjg == "./img/salvage_2.png" ){
		 $("#ldjg3").show();
	}
	if( ldjg == "./img/salvage_3.png" ){
		 $("#ldjg4").show();
	}
	if( ldjg == "./img/salvage_4.png" ){
		 $("#ldjg5").show();
	}
	if( ldjg == "./img/salvage_5.png" ){
		 $("#ldjg6").show();
	}
}

var track={
 interval:0,//定时器
 offsetX:0,//初始坐标x
 offsetY:0,//初始坐标Y
 $box:0,//选定的对象
 angle:0,//初始角度
 R:160,//半径
 w:0,
 h:0,
 rotate:0,
 jump:function(){
 if(track.angle<=-120){
   clearInterval(track.interval);
   track.$box.addClass("rotate");
		$(".sea").css({
			"z-index" : 1019
		});
		
		search();

 }
 
 //快掉落的时候 插入海底
 if(track.angle<-100){
	 $(".sea").css({
		"z-index" :1019
	});
	

	 
 }

 track.w+=0.5;
 track.h+=0.5;
 track.rotate-=0.7;
 track.angle-=1;
 var rotate="rotate("+track.rotate+"deg)";
 var x=track.offsetX+track.R*Math.cos(track.angle*Math.PI/180);
 var y=track.offsetY+track.R*Math.sin(track.angle*Math.PI/180);
   track.$box.css({left:x,top:y,width:track.w,height:track.h, "-webkit-transform":rotate});
 
},
 init:function(obj,x,y,r){
	 track.angle=0;
	 track.rotate=0;
 track.$box=obj;
 track.w=obj.width();
 track.h=obj.height();
 track.offsetX=x;
 track.offsetY=y;
 track.R=r;
 track.interval= setInterval(track.jump,10);
}
}
