	/*=====tab切换=====*/
/*$(function(){
	$("ul.tabs li,ul.tabs_two li").click(function(){
		$(this).addClass("active").siblings("li").removeClass("active");
		var activeTab=$(this).find("a").attr("tag");
		$("#"+activeTab).show().siblings(".orderListBox").hide();	
	});
});*/

/*=====积分-立即提现按钮======*/
$(function(){
	$(".pointType select").change(function(){
		var moneyOk=$(".pointType select").val();
		if(moneyOk=="可提现积分"){
			$(".pointType .moneyBtn").show();
			$(".pointPrice .moneyBtn").show();	
		}else{
			$(".pointType .moneyBtn").hide();	
			$(".pointPrice .moneyBtn").hide();
		}	
	});
});

/*=====选择训练对象-单选按钮======*/
$(function(){
	$(".reserveChild .reserveCon").click(function(){
		$(".reserveCon .chooseChild i").removeClass("choiced");
		$(".reserveCon .chooseChild input").attr("checked",false);
		$(this).find(".chooseChild i").addClass("choiced");
		$(this).find(".chooseChild input").attr("checked",true);
	});	
});

/*=====评价-单选按钮======*/
$(function(){
	$('[name=attitude],[name=level],[name=satisfy]').parent().click(function(){
		var cked=$(this).find("input");
		if(cked.attr("checked")){
			cked.attr("checked",true);
			cked.siblings("i").addClass("active").removeClass("noActive");
		}else{
			$(this).siblings(".check").find("input").attr("checked",false);
			cked.attr("checked",true);	
			$(this).siblings(".check").find("i").removeClass("active").addClass("noActive");
			cked.siblings("i").addClass("active").removeClass("noActive");
		}
	});
});