<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="${ResStatic }/static/js/jquery-1.8.3.min.js"></script>
     <!-- 配置文件 -->
    <script type="text/javascript" src="${ctx }/static/plugin/ueditor1_4_3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx }/static/plugin/ueditor1_4_3/ueditor.all.js"></script>
  </head>
  <body>
  <h1>UEditor简单功能</h1>

    <script type="text/javascript">
        UE.getEditor('myEditor',{
            //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
            toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold','test']],
            //focus时自动清空初始化时的内容
            autoClearinitialContent:true,
            //关闭字数统计
            wordCount:true,
            //关闭elementPath
            elementPathEnabled:false,
            //默认的编辑区域高度
            initialFrameHeight:300,
            //更多其他参数，请参考ueditor.config.js中的配置项
            serverUrl: '/server/ueditor/controller.php'
        })
    </script>



<div>
<!--style给定宽度可以影响编辑器的最终宽度-->
    <script type="text/plain" id="myEditor">
        <p>这里我可以写一些输入提示</p>
    </script>
</div>
  </body>
</html>
