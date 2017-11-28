function alertN(msg){
	layui.use(['layer', 'form'], function(){
		  var layer = layui.layer
		  ,form = layui.form();	
		  
			  layer.open({
				  type: 1, 
				  content: msg, 
				  title:false,
				  closeBtn: 0,
				  time:1500,
				  skin: 'active_tip'
				});
				layer.config({
				  skin: 'active_tip'
				})
		});
		
}


function alertS(msg){
	layui.use(['layer', 'form'], function(){
		  var layer = layui.layer
		  ,form = layui.form();	
			  layer.open({
				  type: 1, 
				  content: msg, 
				  title:false,
				  closeBtn: 0,
				  btn:'确定',
				  btnAlign: 'c',
				  shadeClose:true,
				  skin: 'resetPwd_tip'
				});
				layer.config({
				  skin: 'resetPwd_tip'
				})
		});
		
}


function tologin(url,wapuser){
	if(wapuser==null||wapuser==""){
		layui.use(['layer', 'form'], function(){
			  var layer = layui.layer
			  ,form = layui.form();	
					layer.open({
					type: 1,  
					content:'是否立即登录',
					title:false,
					closeBtn: 0,
					btn:'确定',
					yes:function(index){
					   location.href=url;
					},
					btnAlign: 'c',
					shadeClose:true,
					skin: 'resetPwd_tip'
					});
				});
   }else{
	   location.href=url;
   }
}

