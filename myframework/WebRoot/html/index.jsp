<%@ page trimDirectiveWhitespaces="true"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">		
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<title>登录 talent</title>
	<jsp:include page="/html/resources.jsp"></jsp:include>
	<style type="text/css">
		input {
			padding:2px;
			margin:2px;
		}
	</style>
	</head>
	
	<body align="center">
		<div class="col-md-4 col-md-offset-4">
			<div style="margin-top:30%;max-width:400px;padding:100p 50px 20px 50px;border-radius:10px; border:1px solid #ccc;min-height:200px; background-color:#00a2ca" >
				<div style="margin:5px;font-size:20px;color:white">
					<span style="color:black;">Login </span>		
					<span style="margin-top:3px;font-size:18px" >Talent.WordsTV v0.1.0</span>
				</div>
				<form action="dologin.html" style="text-align:left;color:white">
					<ul style="list-style:none">
						<li>
							<span style="font-size:12px">用户名:</span><br>
							<input name="name" placeholder="name" style="width:90%"/>
						</li>
						
						<li style="margin-top:10px">
							<span style="font-size:12px;">密码:</span><br>
							<input type="password" name="password" placeholder="password" style="width:90%"/>
						</li>
						<li><input class="btn btn-sm btn-default" style="float:right;margin-right:10%"  type="submit" value="登录"/></li>
					</ul>
				</form>
			</div>
		</div>	
	</body>
</html>
