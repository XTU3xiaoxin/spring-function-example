<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title> customer information</title>

<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script>
   $(document).ready(function(){
	   $('#customers').delegate("click","a",function(event){
		   event.preventDefault();
		   var $tr = $(this).parent().parent();
		   var cid = $tr.children().eq(0).text();
		   $.get("${pageContext.request.contextPath}/customer/delete",{"id":cid},function(data,status) {
			   if("success" == status) {
				   $tr.remove();
				   alert("删除成功");
			   }else {
				   alert("删除出现异常");
			   }
			   
		   });
		   //$tr.remove();
	   });
	   
   });
</script>
</head>
<body>
<h1>customer information</h1>
<table id="customers" style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px">id</th><th width="100px">firstName</th><th width="100px">lastName</th><th width="100px">email</th><th width="50px">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="customer" items="${customerList}">
<tr>
<td>${customer.id}</td>
<td>${customer.firstName}</td>
<td>${customer.lastName}</td>
<td>${customer.emailAddress.email}</td>
<td>
<a href="#l">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="${pageContext.request.contextPath}/customer/add">add Customer</a>
</body>
</html>