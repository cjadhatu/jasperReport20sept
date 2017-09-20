<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<table border="2" width="70%" cellpadding="2">
<a href="allOportunities?subRegionId=1">Forecast</a> 
<c:forEach var="sf" items="${salesForcasts}">   
   <tr>  
   <td>${sf.region}</td>  
   <td>${sf.goToRegionfor}</td>  
   <td>${sf.goTobraches}</td>  
   <td>${sf.forcastStatus}</td>  
   <td>${sf.branches}</td>  
   <td>${sf.opportunites}</td>  
   <td>${sf.forecastAmount}</td>  
   <td>${sf.updated}</td>  
   <td>${sf.updatedBy}</td>  
   </tr>  
   </c:forEach>  
 </table> 
</body>
</html>