<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>/js/main_text.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.3.1.js"></script>
<!-- 自定义js -->
<script type="text/javascript" src="<%=basePath%>js/main_text.js"></script>
</head>

<body>
	
	<div>
		<!-- <div>
			<textarea cols="79" rows="20" id="show"></textarea>
		</div> -->
		
		<div id="ChatBox" style="width: 800px;height: 300px;background: #FFF5EE">
			<div class="ChatContent">
				<ul id="chatContent">
					<li id="msgtmp" style="display:none;">
						<div>
							<header>
								<span ff="nickname">某人</span>
								<span>&nbsp;发送给&nbsp;</span>
								<span ff="receiveName">接收者</span>
								<time ff="msgdata">2018-6-15 15:41</time>
							</header>
							<div ff="content">此处是消息内容</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<div>
			<textarea rows="2" cols="50" id="text" name="text"></textarea>
			<span>发送给</span>
			<select id="receivePerson" name="receivePerson">
				<option>所有人</option>
				<%
					List onlineUserSet = (List)application.getAttribute("onlineUserSet");
					for(int i = 0;i < onlineUserSet.size();i ++){
						String onlineUsername = (String)onlineUserSet.get(i);
				%>
				<option><%=onlineUsername %></option>
				<%
					}
				%>
			</select>
			<input type="button" id="sendMessage" value="发送" onclick="sendText()">
		</div>
		<div>
			<audio controls autoplay></audio>
			<input onclick="startRecording()" type="button" value="录音" /> <input
				onclick="stopRecording()" type="button" value="停止" /> <input
				onclick="playRecording()" type="button" value="播放" /> <input
				onclick="uploadAudio()" type="button" value="提交" />
		</div>
		<br>

		<script type="text/javascript" src="<%=basePath%>js/HZRecorder.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/myRecorder.js"></script>
	</div>
	
<script type="text/javascript">
	$(function(){
		var socket = new WebSocket(
				"ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket");
		socket.onmessage = function(ev){
			var obj = eval('(' + ev.data + ')');
			addMessage(obj);
		}
		$("#sendMessage").click(
					function(){
						var nickname = "<%=session.getAttribute("username")%>"
						var receiveName = document.getElementById("receivePerson").value;
						var txt = document.getElementById("text").value;
						var obj = JSON.stringify({
							nickname:nickname,
							receiveName:receiveName,
							content:txt
						});
						socket.send(obj);
					});
		
					
	});
	function addMessage(msg){
		var box = $("#msgtmp").clone(); //复制一份模板，取名为box
		box.show(); //设置box状态为显示
		box.appendTo("#chatContent"); //把box追加到聊天面板中
		box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
		box.find('[ff="receiveName"]').html(msg.receiveName);
		box.find('[ff="msgdate"]').html(msg.date); //在box中设置时间
		box.find('[ff="content"]').html(msg.content); //在box中设置内容
	}
</script>
</body>
</html>
