<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<!DOCTYPE html>
<html  ng-app="wftApp">
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
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <title>BT&S Sales Forecasting</title>    
 
     
       
    
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dataTables.bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/responsive.bootstrap.css" />" rel="stylesheet">
    
       <link href="<c:url value="/resources/js/externalScripts/ui-grid.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/gebtStyle.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/wtfStyle.css" />" rel="stylesheet">
         <link href="<c:url value="/resources/css/toaster.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jquery.multiSelect.css" />" rel="stylesheet">
         <!--<link href="<c:url value="/resources/css/multiSelect.css" />" rel="stylesheet">-->
         <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<!--datetimepicker-->
       <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap-timepicker.min.css" />" rel="stylesheet">
         <link href="<c:url value="/resources/css/datetimepicker.css" />" rel="stylesheet">
          <link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet">
           <link href="<c:url value="/resources/css/jquery-ui-1.8.16.custom.css" />" rel="stylesheet">
          
         
      
    
  <script src="<c:url value="/resources/js/externalScripts/jquery-1.9.1.js"/>"></script>
  <script src="<c:url value="/resources/js/externalScripts/angular.js"/>"></script>
    <script src="<c:url value="/resources/js/externalScripts/angular-route.js"/>"></script>   
    <script src="<c:url value="/resources/js/externalScripts/jquery.multiselect.js"/>"></script>
    <script src="<c:url value="/resources/js/externalScripts/underscore-min.js"/>"></script>
    <script src="<c:url value="/resources/js/externalScripts/loader.js"/>"></script>
    <script src="<c:url value="/resources/js/externalScripts/ui-grid.js"  />"></script>
     <script src="<c:url value="/resources/js/externalScripts/toaster.js"  />"></script>
     <script src="<c:url value="/resources/js/externalScripts/jquery-ui.js"  />"></script>
     <script src="<c:url value="/resources/js/externalScripts/bootstrap.min.js"  />"></script>
      <script src="<c:url value="/resources/js/externalScripts/bootbox.min.js"  />"></script>
     <!--Datepicker-->
      
            <script src="<c:url value="/resources/js/externalScripts/ngTouch.js"  />"></script>
   <script src="<c:url value="/resources/js/externalScripts/ngAnimate.js"  />"></script>
            <script src="<c:url value="/resources/js/externalScripts/ui-bootstrap.js"  />"></script>
   <script src="<c:url value="/resources/js/externalScripts/jqueryDatepicker.js"  />"></script>
                                    
    
</head>

<body ng-controller="mainCtrl">
     <toaster-container toaster-options="{'time-out': 2000}"></toaster-container>
   <div class="wrapper" id="wrapperDiv" disable-right-click>
<div ng-include="'static/header.html'"></div>

    
    <div ng-view></div>
     

   </div>
  <footer class="footerLine text-center clearfix">
     <div class='col-sm-4'>© 2017 Johnson Controls, All rights reserved</div>
     <div class='col-sm-4'>Build_V1.0_28-Jul-2017</div>
     <div class='col-sm-4'>Please do not refresh page or press Ctrl+F5</div>
    </footer>

  <script src="<c:url value="/resources/js/externalScripts/ui-grid.js" />"></script>


     <script src="<c:url value="/resources/js/code/app.moudule.js"  />"></script>
      <script src="<c:url value="/resources/js/code/loginController.js" />"></script>
      <script src="<c:url value="/resources/js/code/logoutController.js" />"></script>
      <script src="<c:url value="/resources/js/code/reportController.js"  />"></script>
       <script src="<c:url value="/resources/js/code/commonService.js"  />"></script>
        <script src="<c:url value="/resources/js/code/landinController.js" />"></script>
         <script src="<c:url value="/resources/js/code/forecastController.js"  />"></script>
          <script src="<c:url value="/resources/js/code/forecastService.js"  />"></script>
          <script src="<c:url value="/resources/js/code/reportingService.js"  />"></script>
           <script src="<c:url value="/resources/js/code/MultiSelectDirective.js" />"></script>
           <script src="<c:url value="/resources/js/code/hiddenRowDirective.js" />"></script>
           <script src="<c:url value="/resources/js/code/forecastdtpDirective.js" />"></script>
           <script src="<c:url value="/resources/js/externalScripts/datetimepicker.js"  />"></script>
            <script src="<c:url value="/resources/js/code/regionContoller.js" />"></script>
            <script src="<c:url value="/resources/js/code/regionService.js"  />"></script>
            <script src="<c:url value="/resources/js/code/subRegionController.js"  />"></script>
            <script src="<c:url value="/resources/js/code/subRegionService.js"  />"></script>
            <script src="<c:url value="/resources/js/code/countryController.js"  />"></script>
            <script src="<c:url value="/resources/js/code/countryService.js"  />"></script>
            <script src="<c:url value="/resources/js/code/branchController.js"  />"></script>
           <script src="<c:url value="/resources/js/code/addNewService.js"  />"></script>
            <script src="<c:url value="/resources/js/code/addNewContoller.js"  />"></script>
              <script src="<c:url value="/resources/js/code/lobController.js"  />"></script>
        
            
<input type="hidden" name="globelId" id="globelId"  class = "globelId" value='${globelId}'> 
<c:set var="jspVariable" value="${globelId}"/>

         <%--   <h3>${globelId}</h3>  --%>
            
         
            

  
  
</body>
</html>
