<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>      
    
  
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

/* function goToBranch(){
	var id = document.querySelector('.check-branch:checked').id;
	debugger; 
	window.location.href = 'getBranches?subRegId='+id;
} */
</script>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BE Sales Forecasting</title>    
 
     
       
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dataTables.bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/responsive.bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    
    
    
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--> 
    
    
    
    
</head>
<body class="dashboard-wrapper">
 <header>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <img src="<c:url value="/images/logo.png"/>" />
                            <span>BE Sales Forecasting</span>
                        </div>
                        <div class="col-md-8">
                            <!-- <div class="pull-right">Forecast Period: <span>3/1/2017 to 5/3/2017</span></div> -->
                            <ul class="pull-right">
                                <li><a href="#">Region</a></li>
                                <li><a href="#">Admin</a></li>
                                <li><a href="#">Reports</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        <section>
         <div class="container-fluid mt-12">
            <c:forEach var="region" items="${regionList}">    
             <div class="col-md-3 widget">
              
                <a  href="region?regioname=${region.regionName}">
                  <div class="panel-widget">
	                <div class="clearfix">
		                <div class="col-sm-3 col-lg-5 widget-left">
		                    <i class="fa fa-globe"></i>
		                </div>
		                <div class="col-sm-9 col-lg-7 widget-right">
		                    <div class="large">${region.regionName}</div>
		                </div>
		            </div>
	              </div>
                
                  
                </a>
             </div> 
            </c:forEach>
         </div>
        </section>
hi
<script>
console.log("hi inside admin hq")
</script>
</body>
</html>