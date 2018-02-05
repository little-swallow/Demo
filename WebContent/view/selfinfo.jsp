<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.demo.bean.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.css" rel="stylesheet">
<style>
	a:hover{ 
		text-decoration: none;
	}

</style>
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
</head>
<body>
	<div class="container selfdiv" style="width: 700px;">
		<div class="headpart">
			<h4 style="text-align:center;margin-top:10px">个人信息</h4>
		</div>
		<div class="bodypart" style="margin-top:20px">
			<form action="../com/demo/controller/UpdateinfoServlet" method="post">
				 <% UserBean userbean = (UserBean)session.getAttribute("Userinfo"); %>
				<div class="form-group infoform">
    				<label for="nameinfo">用户名</label>
    				<input type="text" class="form-control" id="nameinfo"  name="nameinfo" value=<%= userbean.getName()%>>
  				</div>
  				<div class="form-group infoform">
    				<label for="passinfo">密码</label>
    				<input type="text" class="form-control" id="passinfo" name="passinfo" value=<%= userbean.getPwd()%>>
  				</div>	
  				<div class="form-group infoform">
    				<label for="emailinfo">电子邮箱</label>
    				<input type="text" class="form-control" id="emailinfo" name="emailinfo" value=<%= userbean.getEmail()%>>
  				</div>	
  				<div class="form-group infoform">
    				<label for="phoneinfo">联系方式</label>
    				<input type="text" class="form-control" id="phoneinfo"  name="phoneinfo" value=<%= userbean.getPhone()%>>
  				</div>	
  				<div class="form-group infoform" style="margin-top:30px;margin-bottom:30px">
      				<button type="button" class="btn btn-default"><a class="cancel" href="home.jsp" target="right" style="color:black">取消</a></button>
      				<button type="submit" class="btn btn-primary" style="float:right">保存</button>
  				</div>		
			</form>
		</div>
	</div>
</body>
</html>