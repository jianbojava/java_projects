 function validate(){
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if($("#username").val()==null||$("#username").val()==""){
		$(".tipRow").html("请输入用户名");
		return 1;
	}else if($("#password").val()==null||$("#password").val()==""){
		$(".tipRow").html("请输入密码");
		return 1;
	}else if($("#passwordtwo").val()==null||$("#passwordtwo").val()==""){
		$(".tipRow").html("请再次输入密码");
		return 1;
	}else if($("#password").val()!=$("#passwordtwo").val()){
		$(".tipRow").html("两次密码输入不一致");
		return 1;
	}else if($("#name").val()==null||$("#name").val()==""){
		$(".tipRow").html("请输入姓名");
		return 1;
	}else if(!reg.test($("#mobile").val())){
		$(".tipRow").html("请输入正确的手机号");
		return 1;
	}else if($("#province5").val()==null||$("#province5").val()==""){
		$(".tipRow").html("请选择省");
		return 1;
	}else if($("#city5").val()==null||$("#city5").val()==""){
		$(".tipRow").html("请选择市");
		return 1;
	}else if($("#district5").val()==null||$("#district5").val()==""){
		$(".tipRow").html("请选择区/县");
		return 1;
	}else if($("#address").val()==null||$("#address").val()==""){
		$(".tipRow").html("请输入详细地址");
		return 1;
	}else{
		$(".tipRow").html("");
		return 1;
	};
};
