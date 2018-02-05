<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>以下资源可供下载</h1>  
    <c:forEach items="${map}" var="me">  
        <c:url value="DownloadServlet" var="url">  
            <c:param name="filename" value="${me.key}"></c:param>  
        </c:url>  
        ${me.value}  <a href="${url}">下载</a><br/>  
    </c:forEach>  

</body>
</html>