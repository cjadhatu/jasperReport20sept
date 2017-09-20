<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript">
function forcast(){
	var id = document.querySelector('.check-branch:checked').id;
	debugger; 
	window.location.href = 'getOpps?brId='+id;
}

function greyOut(id){
	debugger;
	var row = document.getElementById(id);
	row.style.color = "#ddd";
	/* window.location.href = 'remove?brId='+id; */	
	}
</script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BE Sales Forecasting</title>    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">       
    
    <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.dataTables.min.css" rel="stylesheet">    
	        
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>  
     
       
    
    
    
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--> 
    
    
    <style type="text/css">
    	body {
  min-height: 100%;
  padding: 0px;
  margin: 0px;
  background: #e4e9f0;
  position: relative;
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  color: #22262e;
  overflow-x: hidden;
}

#wrapper {      
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}
#wrapper.toggled {
  padding-left: 0;
}

.side-nav {
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled .side-nav {
  width: 0;
}


#page-wrapper {
    width: 100%;        
    padding: 0;    
}

@media(min-width:768px) {
    body {
        margin-top: 70px;
    }
    
    #wrapper {
        padding-left: 260px;
    }

    #page-wrapper {
        padding: 22px 10px;
    }
    
    .side-nav {
        position: fixed;
        top: 60px;
        left: 260px;
        width: 260px;
        margin-left: -260px;
        border: none;
        border-radius: 0;        
        overflow-y: auto;
        background-color: #222;        
        bottom: 0;
        overflow-x: hidden;
        padding-bottom: 40px;
    }

    .side-nav>li>a {
        width: 260px;
        border-bottom: 1px solid #444141;
    }

    .side-nav li a:hover,
    .side-nav li a:focus {
        outline: none;
        background-color: #1a242f !important;
    }
    .modal-dialog {
    width: 90%;
    
}
    .modal-body{
        height:500px;
    overflow-y:scroll;
    }
}



.top-nav {
    padding: 0 15px;
    background-color: #ffffff;
}

.top-nav>li {
    display: inline-block;
    float: left;
}

.top-nav>li>a {
   height: 60px;
    line-height: 20px;
    color: #000000;
}

.top-nav>li>a:hover,
.top-nav>li>a:focus,
.top-nav>.open>a,
.top-nav>.open>a:hover,
.top-nav>.open>a:focus {
    color: #000000;
    background-color: #ffffff;
}

.top-nav>.open>.dropdown-menu {
    float: left;
    position: absolute;
    margin-top: 0;
    /*border: 1px solid rgba(0,0,0,.15);*/
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    background-color: #fff;
    -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
    box-shadow: 0 6px 12px rgba(0,0,0,.175);
}

.top-nav>.open>.dropdown-menu>li>a {
    white-space: normal;
}



@media(max-width:768px){
    
    .navbar-header{        
        background-color: #01b7a8 !important;
    }   
    

    .navbar-nav {
        margin-top: 0.5px;
    }
    
    #wrapper.toggled .side-nav {
    width: 768px;
}
    
    

}

.side-nav>li>ul {
    padding: 0;
    border-bottom: 1px rgba(0,0,0,.3) solid;
}

.side-nav>li>ul>li>a {
    display: block;
    padding: 10px 15px 10px 38px;
    text-decoration: none;    
    color: #fff;    
    background-color: #222222;
}

.side-nav>li>ul>li>a:hover {
    color: #fff;
}

.navbar .nav > li > a > .label {
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  position: absolute;
  top: 14px;
  right: 6px;
  font-size: 10px;
  font-weight: normal;
  min-width: 15px;
  min-height: 15px;
  line-height: 1.0em;
  text-align: center;
  padding: 2px;
}

.navbar .nav > li > a:hover > .label {
  top: 10px;
}

.navbar-brand {
    padding: 5px 15px;
    float: left;
    height: 50px;
    padding: 10px 15px;
    font-size: 25px;
    line-height: 30px;
    width: 260px;
    background-color: #01b7a8;
    color: #ffffff !important;
    font-weight: bold;
    height: 60px;
}

.navbar-inverse .navbar-brand:focus, .navbar-inverse .navbar-brand:hover {
    color: #fff;
    background-color: #01b7a8;
}

.navbar-nav>li>a{
    padding-top: 10px;
    padding-bottom: 10px;
    line-height: 13px;
}

.navbar-toggle {
    position: relative;
    float: right;
    padding: 9px 10px;
    margin-top: 12px;
    margin-right: 15px;
    margin-bottom: 12px;
    background-color: #4ae6d8;
    background-image: none;
    border: none;
    border-radius: 25px;
    width: 40px;
    color: #ffffff; 
}
.navbar-toggle a{
    color: #ffffff;
    left: 10px;
}


.navbar-inverse .navbar-toggle:focus, .navbar-inverse .navbar-toggle:hover{    
    background-color: #4ae6d8;
}

.topbar {
    height: 60px;
    background-color: #ffffff;
    position: fixed;
    width: 100%;
    top: 0px;
    z-index: 1040;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.16);
    border-radius: 0;
    border:none;
}


.navbar-inverse .navbar-collapse, .navbar-inverse .navbar-form {
    border:none;
}

#menu-toggle{
    left: -20px;
}


.page-title{
    margin-left: -20px;
}

.mobile-page-title{   
    margin: 135px 0 10px 0;
    border-bottom: none;
}

.banner{
    margin-bottom: 10px;
    position:relative;
}

.banner img{
    width:100%;
    min-height:100px;
    border-bottom: 4px solid #000000;
}

.banner .banner-text{
    position: absolute;
    top:0px;
    padding: 10px;
    font-size: 2vw;
    font-weight: bold;
    color: rgb(18,79,146);
    
}

.banner .banner-text1{
    position: absolute;
    top:10%;
    padding: 10px;
    font-size: 1.2vw;
    font-weight: bold;
    color: #527d03;
    margin-top: 2%;
}

.marquee {
    width: 100%;
    background-color: #ffffff;
    font-size: 16px;
    font-weight: bold;
    white-space: nowrap;
    overflow: hidden;
    box-sizing: border-box;    
    
}

.marquee span {
    display: inline-block;    
    animation: marquee 30s linear infinite;
    -webkit-animation: marquee 30s linear infinite;
}

.marquee span:hover {
    animation-play-state: paused;    
}


@keyframes marquee {
   0% { text-indent: 100%}
   100%{text-indent: -50%}
}

@-webkit-keyframes marquee {
    0% { text-indent: 100% }
    100%{text-indent: -50%}
}

.panel-heading-text {
    color: #ffffff !important;
    background-color: #0180b7 !important;
    border:none !important;
}
.box-content{
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.arrow_box {
	position: relative;
	background: #01b7a8;
    position: absolute;
    height: 42px;    
    width: 48px;
    color: #ffffff;
    font-weight: bold;
    text-align: center;
    font-size: 30px;
    border-top-left-radius: 0px;
    border-bottom-left-radius: 0px;
}
.arrow_box:after {
    content: " ";
    display: block;
    width: 0;
    height: 0;
    border-top: 22px solid transparent;
    border-bottom: 20px solid transparent;
    border-left: 18px solid rgb(1, 183, 168);
    position: absolute;
    top: 50%;
    margin-top: -21px;
    margin-left: 0px;
    left: 100%;
}
/*
table thead th {
    background-color: #c0c0c0;
    color: #000000;
}
*/
@-webkit-keyframes animation-text {
  from {
    opacity: 0;
    -webkit-transform: translate3d(100%, 0, 0);
    transform: translate3d(100%, 0, 0);
  }

  to {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes animation-text {
  from {
    opacity: 0;
    -webkit-transform: translate3d(100%, 0, 0);
    transform: translate3d(100%, 0, 0);
  }

  to {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

.animation-text {
    -webkit-animation-name: animation-text;
    animation-name: animation-text;
    -webkit-animation-delay: 1s;
    animation-delay: 1s;   
}


@-webkit-keyframes animation-description {
  from {
    opacity: 0;
    -webkit-transform: translate3d(100%, 0, 0);
    transform: translate3d(100%, 0, 0);
  }

  to {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes animation-description {
  from {
    opacity: 0;
    -webkit-transform: translate3d(100%, 0, 0);
    transform: translate3d(100%, 0, 0);
  }

  to {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

.animation-description {
  -webkit-animation-name: animation-description;
  animation-name: animation-description;
  -webkit-animation-delay: 2s;
    animation-delay: 2s;    
}


/*
@media only screen and (min-width : 320px) and (max-width : 768px) {
    .banner .banner-text{font-size: 10px;}
    .banner .banner-text1{font-size: 8px;}
}

@media only screen and (min-width : 768px) and (max-width : 1222px) {
    .banner .banner-text{font-size: 14px;}
    .banner .banner-text1{font-size: 12px;}
}

@media only screen and (min-width : 320px) and (max-width : 480px) {
    .banner .banner-text{font-size: 9px;}
    .banner .banner-text1{font-size: 8px;}
}

@media only screen and (min-width : 1224px) {
    .banner .banner-text{font-size: 25px;}
    .banner .banner-text1{font-size: 18px;}
}
*/

.tooltip-inner {
            max-width: none;
            white-space: nowrap;
        }
.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {   
    background-color: #c0c0c0 !important   
}

.nav-tabs>li>a {
    background-color: #175394 !important;
    border-radius: 0px;
    color: #ffffff;
}

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
    color: #000000;
    cursor: default;
    background-color: #fff;
    border: 1px solid #c0c0c0;
    border-bottom-color: transparent;
}



.well{
    border-radius: 0px;
}

.panel{
    border-radius: 0px;
}

.panel-heading {   
    border-radius: 0px;    
}
.back-share{
    margin-top: 5px;
}

.navbar-inverse .navbar-nav>li>a {
    color: #9d9d9d;
    background: #000000;
}

.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:active, .navbar-inverse .navbar-nav>li>a:focus {
    color: #9d9d9d;
    background: #1a242f;
}

#menu-toggle:hover, #menu-toggle:focus{
    background-color: #4ae6d8;
}

.dropdown-menu{
    min-width: 180px;
}


.panel-green {
  border-color: #5cb85c;
}
.panel-green > .panel-heading {
  border-color: #5cb85c;
  color: white;
  background-color: #5cb85c;
}
.panel-green > a {
  color: #5cb85c;
}
.panel-green > a:hover {
  color: #3d8b3d;
}
.panel-red {
  border-color: #d9534f;
}
.panel-red > .panel-heading {
  border-color: #d9534f;
  color: white;
  background-color: #d9534f;
}
.panel-red > a {
  color: #d9534f;
}
.panel-red > a:hover {
  color: #b52b27;
}
.panel-yellow {
  border-color: #f0ad4e;
}
.panel-yellow > .panel-heading {
  border-color: #f0ad4e;
  color: white;
  background-color: #f0ad4e;
}
.panel-yellow > a {
  color: #f0ad4e;
}
.panel-yellow > a:hover {
  color: #df8a13;
}

table.dataTable thead .sorting_asc:after {
  content: "\f0de";
  float: right;
  font-family: fontawesome;
}
table.dataTable thead .sorting_desc:after {
  content: "\f0dd";
  float: right;
  font-family: fontawesome;
}
table.dataTable thead .sorting:after {
  content: "\f0dc";
  float: right;
  font-family: fontawesome;
  color: rgba(50, 50, 50, 0.5);
}
.btn_margin{
    margin-left: 5px;
}

#example-select-all{
    margin-left: 20px;
}


.sorting_disabled{
    padding-right:0px;
}
    </style>
    
    
</head>
<body>   
  <div id="wrapper">        
        <nav class="navbar navbar-inverse navbar-fixed-top topbar" role="navigation">        
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="fa fa-bars" aria-hidden="true"></span>
                </button>
                <a class="navbar-brand" href="">
                    <img src="images/logo.png" class="img-responsive" style="width: 90px;">
                </a>
            </div>        
            <ul class="nav navbar-nav hidden-xs">
                <li class="active" ><button class="navbar-toggle collapse in" data-toggle="collapse" id="menu-toggle"> <a href="#" data-toggle="tooltip" data-placement="right" title="show/hide sidebar"><span class="fa fa-bars" aria-hidden="true"></span></a></button></li>
                <li class="page-title"><h3>Latin America</h3></li>                                    
            </ul>
                   
            <div class="collapse navbar-collapse navbar-ex1-collapse" id="sidebar-wrapper">
                <ul class="nav navbar-nav side-nav">
                    <li class="active"><a href="Index.html"><i class="fa fa-tachometer"></i> Global Group</a></li>
                    <li><a href="#"><i class="fa fa-folder"></i> Region</a></li>
                    <li><a href="#"><i class="fa fa-user"></i> Admin</a></li>                    
                    <li><a href="#"><i class="fa fa-bar-chart"></i> Rreports</a></li>                    
                </ul>
            </div>
    </nav>
        <div id="page-wrapper">
        <div class="container-fluid">
           <div class="row hidden-sm hidden-md hidden-lg">                    
                    <div class="col-md-12">
                        <h3 class="page-header mobile-page-title">Latin America</h3>
                    </div>
           </div> 
           
            <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">                                
                                <h3 class="panel-title pull-left">
                                    Details
                                </h3>

                                <a href="" class="btn btn-primary btn-sm pull-right btn_margin">Un-Submit Region</a>
                                <a onclick="forcast()" class="btn btn-primary btn-sm pull-right btn_margin">Forecast</a>
                                <div class="clearfix"></div>                                
                            </div>
                            <div class="panel-body">                            
                                                            
                                    <div class="table-responsive">
                                    <div class="hidden">
                                    <input type="text" id="oppsTotal" value="${oppsData.oppsTotal}" />
   									<input type="text" id="branchesTotal" value="${oppsData.brTotal}" />
   									</div>
                                        <table class="table table-bordered table-striped display nowrap table-condensed" cellspacing="0" width="100%" id="tblLatinAmerica">
<thead>
    <tr>
	<th></th>
	<th>Oppty Name</th>
	<th>Account Name</th>
	<!-- <th>Plan Date</th> -->
	<th>Estimated Amt</th>
	<th>Lead sales Rep</th>
	<!-- <th>Branch</th>
	<th>VM</th> -->
	
	</tr> 
	 
                                            </thead>
                                            <tbody>
<c:forEach var="sf" items="${oppsData.subRegions}">   
   <tr id="${sf.oppsId}" data-flag="${sf.deleteFlg}">
   <td><input onClick="greyOut(${sf.oppsId})" type="button" value="Remove" class="btn btn-primary"  /></td>
   <td>${sf.oppsName}</td>  
   <td>${sf.accName}</td>
      <%-- <td>${sf.planDt}</td> --%>  
   <td>${sf.estAmt}</td>  
   <td>${sf.leadRep}</td>  
   <%-- <td>${sf.branch}</td> --%>  
   <%-- <td>${sf.vm}</td> --%>
   
   <%--
   <td>${sf.AccName}</td>  
      --%>
      
      
   </tr>  
   </c:forEach>                                                 
                                            </tbody>
                                        </table>
                                    </div>    
                                
                            </div>
                        </div>
                    </div>
            </div>  
            
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Total Count</div>
                        <div class="panel-body">
                            <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</div>






<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>    
<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>  

    
<script type="text/javascript">
	$(function(){
        $('[data-toggle="tooltip"]').tooltip();
        
        $(".side-nav .collapse").on("hide.bs.collapse", function() {                   
            $(this).prev().find(".fa").eq(1).removeClass("fa-angle-right").addClass("fa-angle-down");
        });
        
        $('.side-nav .collapse').on("show.bs.collapse", function() {                        
            $(this).prev().find(".fa").eq(1).removeClass("fa-angle-down").addClass("fa-angle-right");        
        });
        
         $("#menu-toggle").click(function(e) {        
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");        

        });
        
        
        
        var table = $('#tblLatinAmerica').DataTable( {
        
        responsive: true,
        
        });
        
		var oppsTotal = parseInt($("#oppsTotal").val()) ;
		var branchesTotal = parseInt($("#branchesTotal").val());
        
        
        $('#container').highcharts({

            chart: {
                type: 'column',
            },

            title: {
                text: ''
            },
            legend: {
                padding: 0,
                margin: 5
            },
            credits: {
                enabled: true
            },
            tooltip: {
                enabled: true
            },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true,
                        crop: false,
                        overflow: 'none'
                    }
                }
            },
            //colors: ['#E000000', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
            loading: {
                labelStyle: {
                    top: '35%',
                    fontSize: "2em"
                }
            },
            xAxis: {
                categories: ["Opportunities", "Branches"]
            },
            series: [{

                colorByPoint: true,
                "name": "Sale Forecasting",
                "data": [{
                    "y": oppsTotal
                }, {
                    "y": branchesTotal
                }]
            }],



        });

        $('#tblLatinAmerica thead th:first').removeClass('sorting_asc');

        $("#tblLatinAmerica tbody tr").each(function(){var getFlag = $(this).attr('data-flag'); if(getFlag == 'Y'){$(this).css('color','#ddd')}})
        

	    // Handle click on "Select all" control
        $('#example-select-all').on('click', function () {
            // Get all rows with search applied
            var rows = table.rows({ 'search': 'applied' }).nodes();
            // Check/uncheck checkboxes for all rows in the table
            $('input[type="checkbox"]', rows).prop('checked', this.checked);
        });

	    // Handle click on checkbox to set state of "Select all" control
        $('#example tbody').on('change', 'input[type="checkbox"]', function () {
            // If checkbox is not checked
            if (!this.checked) {
                var el = $('#example-select-all').get(0);
                // If "Select all" control is checked and has 'indeterminate' property
                if (el && el.checked && ('indeterminate' in el)) {
                    // Set visual state of "Select all" control 
                    // as 'indeterminate'
                    el.indeterminate = true;
                }
            }
        });

    });       
</script>   
    
</body>
</html>
