var _sCurrDevice = "";//全局变量，存储当前设备类型：desktop,tablet,mobile
$(document).ready(function(){
	$(window).trigger("webLoaded", [""]);
	
	//1、打开菜单
	$(".btn-menu").on("click tap",function(){
		$(".menucontainer").removeClass("hide");
		$(".btn-lang-switcher").addClass("hidden");
		$(".btn-close-menu").removeClass("hidden");
		$(".navigate").addClass("moveLeft");
	});
	//2、关闭菜单
	$(".btn-close").on("click tap",function(){
		$(".menucontainer").addClass("hide");
		$(".btn-lang-switcher").removeClass("hidden");
		$(".btn-close-menu").addClass("hidden");
	});
	//3、点击首屏箭头向下滚动
	$(".btn-down-arrow").on("click tap",function(){
		var nScreenHeight = document.body.clientHeight;
		$('html,body').animate({scrollTop: nScreenHeight}, 800);
	});
	//4、浮动窗口关闭按钮点击时，关闭窗口
	$(".btn-close-overlay").on("click tap",function(){
		$(".overlay").addClass("hidden");
		//解除body触摸滚动事件的禁用
		var oDocumentBody = $(document.body);
		oDocumentBody.off("touchmove");
		oDocumentBody.css("overflow","auto");
	});
	
	//5、调整欢迎页窗口尺寸
	var mainPageContainer = $(".page-welcome");
	doResize();
	$(window).on("resize",function(){
		doResize();
	});
	/*窗口尺寸改变时的行为*/
	function doResize(){
		//1、取窗口当前宽度和高度
		var nScreenWidth = document.body.clientWidth;
		var nScreenHeight = window.innerHeight;
		mainPageContainer.height(nScreenHeight);
		//2、判断设备类型，宽度的值和CSS中的设置要一致
		var sDevice = "desktop";
		if(nScreenWidth<=992-16){//减去滚动条宽度
			sDevice = "tablet"
		}
		if(nScreenWidth<=800-16){
			sDevice = "mobile";
		};
		//3、触发设备类型转换事件
		if(_sCurrDevice!=sDevice){
			_sCurrDevice = sDevice;
			$(window).trigger("deviceChange", [_sCurrDevice,nScreenWidth,nScreenHeight]);
		}
	}
	
	//6、滚动到顶部功能
	var offset = 300;//浏览器滚动多远后显示返回顶部按钮。
	var offset_opacity = 1200;//浏览器窗口滚动多远后，图标透明度减小。
	var oBackToTop = $('.btn-scroll-top');//获得滚动条按钮
	$(window).scroll(function(){
		if($(this).scrollTop() > offset){
			oBackToTop.addClass('is-visible')
		}else{
			oBackToTop.removeClass('is-visible fade-out')
		}
		if($(this).scrollTop() > offset_opacity ) { 
			oBackToTop.addClass('fade-out');
		}
	});
	oBackToTop.on('click', function(event){
		event.preventDefault();
		$('body,html').animate({scrollTop:0}, 700);//duration of the top scrolling animation (in ms)
	});
	
});