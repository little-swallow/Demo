
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="../com/demo/controller/testServlet" method="post" enctype="multipart/form-data">  
        name:<input name="name"/><br/>  
        file1:<input type="file" name="f1"/><br/>  
          
        <input type="submit" value="上传">  
    </form>  
</body>
</html>