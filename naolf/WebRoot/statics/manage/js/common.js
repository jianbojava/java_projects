/*变量*/
var dels = "";
/* 分页 */
function pageinit() {
	$(".input-group button").bind("click", function() {
		$("input[name='pageNo']").val(1);
		$("#searchForm").submit();
	})
	$(".pagination li").bind("click",function() {
		if ($(this).attr("class").indexOf("disabled") < 0) {
			var page = $(this).data("page");
			if (page == "prev") {
				$("input[name='pageNo']").val(parseInt($("input[name='pageNo']").val()) - 1);
			} else if (page == "next") {
				$("input[name='pageNo']").val(parseInt($("input[name='pageNo']").val()) + 1);
			} else {
				$("input[name='pageNo']").val($(this).data("page"));
			}
			$("#searchForm").submit();
		}
	})
	$("#pagination select").change(function() {
		$("input[name='pageNo']").val(1);
		$("input[name='pageSize']").val($(this).val());
		$("#searchForm").submit();
	})
}

/*
 * 打开弹出层 参数解释： title 标题 url 请求的url w 弹出层宽度（缺省调默认值） h 弹出层高度（缺省调默认值） full 弹出是否立即全屏
 */
function layer_show(title, url, w, h, full) {
	if (title == null || title == '') {
		title = false;
	}
	if (w == null || w == '') {
		w = 800;
	}
	if (h == null || h == '') {
		h = ($(window).height() - 50);
	}
	var index = layer.open({
		type : 2,
		area : [ w + 'px', h + 'px' ],
		maxmin : true,
		title : title,
		content : url
	});
	if (full) {
		layer.full(index);
	}
}
/* 关闭弹出层 */
function layer_close() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

/*
 * Switchery-iOS 7 http://abpetkov.github.io/switchery/
 */
var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
elems.forEach(function(html) {
	var switchery = new Switchery(html);
});

/*
 * 弹出层操作成功  content 提示内容 type 0：弹出层
 */
function opt_success(content,type) {
	layer.msg(content, {
		icon : 1
	});
	switch (type) {
	case 0:
		//setTimeout("javascript:parent.location.replace(parent.location.href)",1000)
		//setTimeout("javascript:parent.$('.input-group button').click()", 2000)
		setTimeout("javascript:parent.$('#searchForm').submit()", 2000)
		break;
	default:
		//setTimeout("javascript:location.replace(parent.location.href)",1000)
		//setTimeout("javascript:$('.input-group button').click()", 2000)
		setTimeout("javascript:$('#searchForm').submit()", 2000)
		break;
	}
}

/*
 * 弹出层操作成功  刷新
 */
function opt_success_reload(content,url) {
	layer.msg(content, {
		icon : 1
	});
	setTimeout("javascript:location.href='"+url+"'",2000)
}
/*
 * 操作失败 content提示内容
 */
function opt_error(content) {
	layer.msg(content, {
		icon : 2
	});
}

/*
 * 登录超时 自定义601
 */
$.ajaxSetup({
    complete:function(XMLHttpRequest,textStatus){
    	  if(XMLHttpRequest.status==601){
    		  layer.msg('会话超时，请重新登录。', {
    			  icon: 5,
    			  time: 0 //不自动关闭
    			  ,btn: ['立即登录', '取消']
    			  ,yes: function(index){
    			    layer.close(index);
    			    location.href=parent.location.replace(parent.location.href);
    			  }
    			});
    	  }else if(textStatus=="parsererror"){
    		  layer.closeAll('loading'); //关闭加载层
    		  layer.msg("系统繁忙，请稍后再试。", {
    				icon : 2
    			});
    	  }
    }
});

/* iCheck */
var checkAll = $("[name='input']");
//var checkItem = $("[name='input[]']");
var checkItem = $("[name='input[]']").not(':disabled');
checkAll.on('ifChecked ifUnchecked', function(event) {
	if (event.type == 'ifChecked') {
		checkItem.iCheck('check');
	} else {
		checkItem.iCheck('uncheck');
	}
});
checkItem.on('ifChanged', function(event) {
	if (checkItem.filter(':checked').length == checkItem.length) {
		checkAll.prop('checked', true);
	} else {
		checkAll.prop('checked', false);
	}
	checkAll.iCheck('update');
});

//扩展Date的format方法
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1,(this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1,RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
} 

//获取两个日期相差天数
function getIntervalDays(d1, d2) {
	d1 = new Date(d1.replace(/-/g, "/"));
	d2 = new Date(d2.replace(/-/g, "/"));
	var times = d2.getTime()/1000 - d1.getTime()/1000;
	return parseInt(times / (60 * 60 * 24))+1;
}

//获取经纬度之间距离
function Rad(d) {
	return d * Math.PI / 180.0;//经纬度转换成三角函数中度分表形式。
}
function getDistance(lng1, lat1, lng2, lat2) {
	var radLat1 = Rad(lat1);
	var radLat2 = Rad(lat2);
	var a = radLat1 - radLat2;
	var b = Rad(lng1) - Rad(lng2);
	var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
			+ Math.cos(radLat1) * Math.cos(radLat2)
			* Math.pow(Math.sin(b / 2), 2)));
	s = s * 6378.137;// EARTH_RADIUS;
	s = Math.round(s * 10000) / 10000; // 输出为公里
	return s;
}

//随机生成手机号
function create_mobile(){
	var section=['133','153','173','177','180','181','189','134','135','136','137','138','139','150','151','152','157',
	'158','159',' 178','182','183','184','187','188','130','131','132','155','156','175','176','185','186'];
	var sec=section[Math.floor(Math.random()*(section.length))]; 
	for(var i=0;i<8;i++){
		sec+=Math.floor(Math.random()*10);
	}
	return sec;
}