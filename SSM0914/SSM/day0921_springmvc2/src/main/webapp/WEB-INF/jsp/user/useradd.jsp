<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/calendar/WdatePicker.js"></script>
<title>Insert title here</title>
</head>
<body>
<fm:form method="post" modelAttribute="user">
    <fm:errors path="userCode"/> </br>
    编码：<fm:input path="userCode"/></br>
    用户名：<fm:input path="userName"/></br>
    密码：<fm:input path="userPassword"/></br>
    手机号：<fm:input path="phone"/></br>
    地址:<fm:input path="address"/></br>
    性别：<fm:input path="gender"/></br>
    <fm:errors path="birthday"/></br>
    出生日期：<fm:input path="birthday" onclick="WdatePicker();"/></br>
    <input type="submit" value="保存">
</fm:form>
</body>
</html>