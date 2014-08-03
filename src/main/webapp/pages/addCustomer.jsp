<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>New Customer page</title>
</head>
<body>
<h1>New Customer page</h1>
<form:form method="POST"   commandName="customer" action="${pageContext.request.contextPath}/customer/add" >
<table>
  <tbody>
	<tr>
		<td>first name:</td>
		<td><form:input path="firstName" /></td>
		<td><form:errors path="firstName" cssStyle="color: red;"/></td>
	</tr>
<td>last name:</td>
<td><form:input path="lastName" /></td>
<td><form:errors path="lastName" cssStyle="color: red;"/></td>
</tr>
<tr>
<td>Email:</td>
<td><form:input path="emailAddress.email" /></td>
<td><form:errors path="emailAddress.email" cssStyle="color: red;"/></td>
</tr>
<tr>
<td><input type="submit" value="Create" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
<div>${customer.firstName}</div>
</form:form>
</body>
</html>