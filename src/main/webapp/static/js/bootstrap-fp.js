$(function(){
	
	/*欢迎你界面*/
		
	 var  round = $("#round"); //圆
	 round.animate({opacity:"0",top:"80px"},0).animate({opacity:"1",top:"0"},5000);
	  
	  var  txt1 = $("#txt1");	//文字1
	  txt1.animate({opacity:"0",top:"60px"},0).animate({opacity:"1",top:"0"},2000);
	  var  txt2 = $("#txt2");	//文字2
	  txt2.animate({opacity:"0",top:"60px"},0).animate({opacity:"1",top:"0"},2500);
	  var  txtbtn = $("#txtbtn");	//开始按钮
	  txtbtn.animate({opacity:"0",top:"60px"},0).animate({opacity:"1",top:"0"},3000);
	  
	  
	var  cloud1 = $("#cloud1");	//大乌云
	var  cloud2 = $("#cloud2");	//小乌云

	var  tm = $("#cloud1").css("opacity"); //获取乌云透明值
	
	cld();

	
	/*乌云*/
	function cld(){
	cloud1.animate({left:"240px",opacity:"1"},5500).animate({left:"240px",opacity:"1"},3000).animate({left:"-60px",opacity:"0"},1000);
	cloud2.animate({right:"300px",opacity:"1"},5500).animate({right:"300px",opacity:"1"},3000).animate({right:"-80px",opacity:"0.2"},1000);

    
    if( tm == 0 ){
		cld();
		 
	}
    
    }
	
	
	/*购物篮*/
    $(function() {
    	
 		
	$(".add").click(function() {
		var t = $(this).parent().find('input[class*=count-input]');
		t.val(parseInt(t.val()) + 1)
		setTotal();
	})
	
	$(".reduce").click(function(){
		var t=$(this).parent().find('input[class*=count-input]');
		t.val(parseInt(t.val())-1)
		if(parseInt(t.val())<0){
		t.val(0);
		}
		setTotal();
	});
	
	$("input[name='inlinechk1']").click( function(){
		
		setTotal();
	});
	
	
	
	
	
	$("#allchk").click(function(){ 
	    var isChecked = $(this).prop("checked"); 
	    $("input[name='inlinechk1']").prop("checked", isChecked);
	    setTotal();
	});  
	
	function setTotal(){
			var s=0;
			$('.gwclistbox input[type*=checkbox]:checked').parent().siblings().find('.gwcprosl').each(function(){
				
			s+=parseInt($(this).find('input[class*=count-input]').val())*parseFloat($(this).find('span[class*=gwcprojg]').text());
			});
			$(".total").html(s.toFixed(2));
			}
			setTotal();
	
	});
	
	
		/*上传图片*/

var clsbtn = $(".xgpjbox .sctp .tupj .clsbtn")


clsbtn.each(function(){

	   $(this).click( function() {

			$(this).parent().slideUp('slow');
		});
});
	

        $('#d1').click(function(){//给d1绑定一个点击事件;
            
        /*这个判断的意义是,如果d2是隐藏的,那么让它显示出来,并将d1的文本内容替换成收起,
                              如果是显示的,那么就隐藏它并将d1的文本内容替换为展开;*/
        if($('#d2').is(':hidden'))
        {
          $('#d2').slideDown('slow');  
          $(this).empty().append('收起实名认证 <span class="glyphicon glyphicon-chevron-up"></span>');
        }else{
          $('#d2').slideUp('slow');
          $(this).empty().append('展开实名认证 <span class="glyphicon glyphicon-chevron-down"></span>');  
            }
                
         /*这是一个很简单的事件处理,如果还需要跟上图片的变换,就在判断的对应位置写入图片或者背景变换的代码,*/
          });
				
	
 
/*产品详情页*/

var tabs=$("#tab li");

var divs=$("#tab_con div");

for(var i=0;i<tabs.length;i++){

tabs[i].onclick=function(){change(this);}

}

function change(obj){

for(var i=0;i<tabs.length;i++)

{

if(tabs[i]==obj){

tabs[i].className="fli";

divs[i].className="fdiv";

}

else{

tabs[i].className="";

divs[i].className="";

}

}

}


 	 	/*弹出选择商品属性层*/
 $(function(){
	 jQuery.noConflict();
		   $("#xzspsx").modal("hide");
		 
		   $(".btn-ljgm").click(function(){
		   	 $('#xzspsx').modal("show");
			 
			});
		
		/*弹出下单增加地址*/
 
		   $("#xdadd").modal("hide")
		 
		   $(".btn-ljxd").click(function(){
		       $("#xzspsx").modal("hide");
		   	 $('#xdadd').modal("show");
			 
			});
			
 });	
		
		/*色彩性别选择*/	
		$(".clrfl span").click(function() {
				$(this).addClass("on1").siblings().removeClass("on1");
		});
		
		/*大小尺码选择*/	
		$(".codefl span").click(function() {
				$(this).addClass("on2").siblings().removeClass("on2");
		});
		
		
		$(".qrddtjbtn").click(function() {
			  window.location="order.html"; 
		});
		



});

