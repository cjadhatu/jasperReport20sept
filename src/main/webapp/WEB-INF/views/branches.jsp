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
<body>   
  <div id="wrapper">        
        <header>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <img src="<c:url value="/images/logo.png"/>" />
                            <span>BE Sales Forecasting</span>
                        </div>
                        <div class="col-md-8">
                            <div class="pull-right">Forecast Period: <span>3/1/2017 to 5/3/2017</span></div>
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
	        <div class="container-fluid">
	            <div class="panel">
	            <div class="panel-head clearfix">
	             <div class="panel-title">Branch Geography</div>
	            </div>
                    <div class="panel-body">
                    <div class="row">
                            <div class="col-md-12">
                                <input type="button" value="Forecast" class="btn btn-primary" data-toggle="modal" data-target="#forecast-panel" data-backdrop="static" />
                                <input type="button" value="Go to LOB's" class="btn btn-primary" data-toggle="modal" data-target="#lob-panel" data-backdrop="static" />
                            </div>
                        </div>
                        <div class="row mt-12">
                            <div class="col-md-12 ">

                                <table id="tblLatinAmerica" class="table table-striped table-bordered dt-responsive nowrap" cellpadding="0" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th class="text-center"><input type="checkbox" name="select_all" value="1" id="example-select-all"></th>                                                 
											<th>Branch Geography #</th>												
											<th>Branch Geography Description</th>
											<th>Go to forecast</th>
											<th>Go to LOB's</th>
											<th>Forecast Status</th>
											<th>Branches</th>
											<th>Opportunities</th>
											<th>Total Factored Amt <span style="font-size:10px;">(USD)</span></th>
											<th>Updated Date</th>
											<th>Updated By</th>                                                     
                                         </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="sf" items="${subRegions}">   
										   <tr id="${sf.regId}">
										   <td>
										   <input type="checkbox" class="check-branch"  onclick="myFunction3(this)" id="${sf.regId}">
										   </td>  
										   <td>${sf.branchgeography}</td>
										   <td>${sf.branchgeodesc}</td>
										   <td><a href="#" data-toggle="modal" data-target="#forecast-panel" data-backdrop="static">Forecast</a></td>
										   <td><a href="#">Go to LOB's</a></td>
										   <td>${sf.forecaststatus}</td>
										   <td class="text-right">${sf.branches}</td>  
										   <td class="text-right">${sf.opps}</td>
										   <td class="text-right numbers">${sf.sumfactamt}</td> 
										   <td>${sf.updated}</td>  
										   <td>${sf.updatedBy}</td> 
										   </tr>  
										   </c:forEach>
										   
										 <!--  <tr>
										    <td><input type="checkbox"></td>
                                            <td>0A28</td>
                                            <td>Union, NJ</td>
                                            <td><a href="#" data-toggle="modal" data-target="#forecast-panel" data-backdrop="static">Forecast</a></td>
                                            <td><a href="#" data-toggle="modal" data-target="#lob-panel" data-backdrop="static">Go to LOB's</a></td>
                                            <td>In Process</td>
                                            <td class="text-right">7</td>
                                            <td class="text-right">103</td>
                                            <td class="text-right">4,777,798</td>
                                            <td>10/15/2013</td>
                                            <td>System</td>                                                  
                                         </tr> -->
                                    </tbody>
                                    
                                </table>

                                <div class="table-footer text-center clearfix">
                                    <div class="col-md-2" >
                                        Total Opportunities
                                        <br/>
                                        <label>103</label>
                                         <!-- <label id = "label1">${salesForcasts.oppsTotal}</label>  -->                                       
                                    </div>
                                    <div class="col-md-2" >
                                        Total UnFactored Amt <span style="font-size:10px;">(USD)</span>
                                        <br/>
                                         <label>4,777,798</label>                                        
                                    </div>
                                    <div class="col-md-2" >
                                        Total Factored Amt <span style="font-size:10px;">(USD)</span>
                                        <br/>
                                         <label>4,777,798</label>                                        
                                    </div>
                                    <div class="col-md-2" >
                                        Branches Not Started
                                        <br/>
                                         <label>2</label>                                        
                                    </div>
                                    <div class="col-md-2" >
                                        Branches In Progress
                                        <br/>
                                         <label>5</label>                                        
                                    </div>
                                    <div class="col-md-2" >
                                        Branches Submitted
                                        <br/>
                                         <label>7</label>                                        
                                    </div>
                                   
                                </div>
                            </div>
                        </div>
                        
                        


                    </div>
                </div>
	        </div>
	        
	        
	        
	        
	        <!-- Forecast Modal -->
            <div id="forecast-panel" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Forecast</h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel" id="">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="button" value="Add New" class="btn btn-primary" data-toggle="modal" data-target="#AddNew" data-backdrop="static" />
                                            <input type="button" value="Filter" class="btn btn-primary" data-toggle="modal" data-target="#Filter" data-backdrop="static" />
                                            <input type="button" value="Submit Forecast" class="btn btn-primary" data-toggle="modal" data-target="#SubmitForecast" data-backdrop="static" />
                                        </div>
                                    </div>
                                    <div class="row mt-12">
                                        <div class="col-md-12">

                                            <table id="forecastTbl" class="table table-striped table-bordered dt-responsive nowrap" cellpadding="0" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Edit</th>
                                                        <th>Account Name</th>
                                                        <th>Opportunity Name</th>
                                                       <th>Plan Date</th>
                                                     
                                                        <th>Estimated Amt <span style="font-size:10px;">(USD)</span></th>
                                                        <th>Factored Amt <span style="font-size:10px;">(USD)</span></th>
                                                       <th>Margin Pct</th>
                                                       <th>Stage</th>
                                                        <th>Lead Sales Rep</th>
                                                        
                                                        <th>Opportunity No.</th>
                                                        <th>LOB</th>
                                                        <th>Country</th>
                                                        
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody id= "tdbody1">
                                                  
                                                    
                                                </tbody>
                                            </table>


                                            <div class="table-footer text-center clearfix">

                                                <div class="col-md-6">
                                                    Opportunities
                                                    <br>
                                                     <label for="male" id= "label2">2,099</label>
                                                    
                                                </div>
                                                <div class="col-md-6">
                                                    Factored Amt <span style="font-size:10px;">(USD)</span>
                                                    <br>
                                                    <label for="male" class="numbers" id= "label3">27,001,579</label>
                                                    <p></p>
                                                </div>
                                                <!-- <div class="col-md-2">
                                                    Unfactored GP <span style="font-size:10px;">(USD)</span>
                                                    <p>27,001,579</p>
                                                </div>-->
                                               <!-- <div class="col-md-4">
                                                    Factored Amt <span style="font-size:10px;">(USD)</span>
                                                    <br>
                                                    <label class="numbers" id= "forecast-fct-amt"></label>
                                                    
                                                </div>-->
                                                <!-- <div class="col-md-2">
                                                    Unfactored GP <span style="font-size:10px;">(USD)</span>
                                                    <p>845,000 </p>
                                                </div>-->
                                                <!-- <div class="col-md-2">
                                                    <input type="button" value="More Totals" class="btn btn-primary" />
                                                </div>-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            
            
            
            <!-- LOB Modal -->
            <div id="lob-panel" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Branch LOB</h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel" id="">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="button" value="Refresh Data" class="btn btn-primary"/>
                                            <input type="button" value="Forecast" class="btn btn-primary"/>
                                            <input type="button" value="Re-Submit" class="btn btn-primary"/>
                                        </div>
                                    </div>
                                    <div class="row mt-12">
                                        <div class="col-md-12">

                                            <table id="lobTbl" class="table table-striped table-bordered dt-responsive nowrap" cellpadding="0" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox"></th>   
                                                        <th>Branch #</th>
                                                        <th>Branch</th>
                                                        <th>Branch Geography #</th>
                                                        <th>Branch Geography</th>
                                                        <th>Forecast Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>                                                  
                                                    <tr>
                                                      <td><input type="checkbox"></td> 
                                                      <td>411</td>
                                                      <td><a href="#">Baltimore EQ</a></td>
                                                      <td>0N27</td>
                                                      <td>Baltimore,MD</td>
                                                      <td>Not Started</td> 
                                                    </tr> 
                                                </tbody>
                                            </table>



                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
	        
        </section>
        
</div>




<script src="<c:url value="/resources/js/jquery-1.12.4.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script> 
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script> 
<script src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script> 
<script src="<c:url value="/resources/js/dataTables.responsive.js" />"></script> 
<script src="<c:url value="/resources/js/responsive.bootstrap.js" />"></script>

<script src="<c:url value="/resources/js/highcharts.js" />"></script> 
<script src="<c:url value="/resources/js/data.js" />"></script> 
<script src="<c:url value="/resources/js/drilldown.js" />"></script> 
<script src="<c:url value="/resources/js/exporting.js" />"></script>  
    
<script type="text/javascript">
	$(function(){
       
        
        
        
        var table = $('#tblLatinAmerica').DataTable( {
        
        responsive: true,
        'columnDefs': [{
            'targets': 0,
            'searchable': false,
            'orderable': false,  
            "scrollY": "194px",
            "scrollCollapse": true,
            "paging": true,          
            'render': function (data, type, full, meta) {
                return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
            }
        }]
        });

        $(".numbers").digits();

        $('#tblLatinAmerica').on('draw.dt', function() {
     	   $(".numbers").digits();
     	});
        
		var oppsTotal = parseInt($("#oppsTotal").val()) ;
		var branchesTotal = parseInt($("#branchesTotal").val());
        
        
        
        $('#tblLatinAmerica thead th:first').removeClass('sorting_asc');
        

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

        $('#forecast-panel').on('shown.bs.modal', function (e) {
            //Call your Function here 
            nonDel = 0;// total no of opportunities
            estimatedSum = 0;
            forecastFactorededSum = 0;
              $.ajax({
                url: "getOpps?brId="+selectedRowId,
                method: 'POST',
                contentType: "application/json",
                 success: function(data){
               debugger;
                var data = JSON.parse(data)
                console.log(data);
               $('#thetable tr').not(':first').not(':last').remove();
               var html = '';
               for(var i = 0; i < data.subRegions.length; i++){
                   if( data.subRegions[i].deleteFlg==='N'){
                      
                	   nonDel++;
                	   estimatedSum = estimatedSum +parseInt(data.subRegions[i].factoredAmt);
                	   forecastFactorededSum = forecastFactorededSum +parseInt(data.subRegions[i].factoredAmt);
                	   
                		  	 html += '<tr ><td><a class="fa fa-edit margin-right4" onclick="myFunction7(this)"></a><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close" onclick="myFunction1(this)" id ="'+data.subRegions[i].oppsId+'"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td class="plan-date">' + data.subRegions[i].planDt + '</td><td class="text-right"><input type="text" class="numbers" disabled value="' + data.subRegions[i].estAmt + '"/></td><td class="text-right"><input type="text" disabled value="' + data.subRegions[i].factoredAmt + '"/></td><td class="text-right"><input type="text" disabled value="' + data.subRegions[i].marginPct + '"/></td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                    	 
                	  
                       }
                   else{
					   var a = data.subRegions[i].planDate;
						   a.substr(0,13)
                	   var d1 = new Date ( a.substr(0,12));// server
                       var d2 = new Date ();// today
                       d3 = d2.toString();
                       d3= d3.substr(0,15);
                       var d4 = new Date (d3);
                       var result = d1<d4;
                	 
                	   if(result){// is server date is less color red
                           html += '<tr style="background-color:#d2d2d2;opacity:0.6;"><td><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td  style="color:red">' + data.subRegions[i].planDt + '</td><td class="text-right numbers">' + data.subRegions[i].estAmt + '</td><td class="text-right numbers">' + data.subRegions[i].factoredAmt + '</td><td class="text-right">' + data.subRegions[i].marginPct + '</td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                	   }
                	   else
                    	   {
                		   html += '<tr style="background-color:#d2d2d2;opacity:0.6;"><td><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td>' + data.subRegions[i].planDt + '</td><td class="text-right numbers">' + data.subRegions[i].estAmt + '</td><td class="text-right numbers">' + data.subRegions[i].factoredAmt + '</td><td class="text-right">' + data.subRegions[i].marginPct + '</td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                    	   }
                   }  
               }  
               $('#tdbody1').html(html);
               $('#label2').text(nonDel);
               $('#label3').text(estimatedSum);
               $('#forecast-fct-amt').text(forecastFactorededSum);
               $('#forecastTbl').dataTable({
                   "scrollY": "194px",
                   "scrollCollapse": true,
                   "paging": true,
                  
               });
               
               $(".numbers").digits();

               $('#forecastTbl').on('draw.dt', function() {
            	   $(".numbers").digits();
            	});
                          }});
            if ($.fn.DataTable.isDataTable("#forecastTbl")) {
                $('#forecastTbl').DataTable().destroy();
            }


            $(window).bind('resize', function () {
                $('#forecastTbl').dataTable().fnAdjustColumnSizing();
            });
        });

        $('.forecastbtn').click(function () {
            $('#branches-panel').modal('hide');
            //$('#forecast-panel').modal('show');
            $('#forecast-panel').modal({backdrop: 'static', keyboard: false})
        })
        
        
        $('#lob-panel').on('shown.bs.modal', function (e) {
            //Call your Function here 
             if ($.fn.DataTable.isDataTable("#lobTbl")) {
                $('#lobTbl').DataTable().destroy();
            }
            $('#lobTbl').dataTable({
                   "scrollY": "194px",
                   "scrollCollapse": true,
                   "paging": true,
                   "aoColumnDefs" : [ {
                       'bSortable' : false,
                       'aTargets' : [ 0 ]
                   } ]
               });
           


            $(window).bind('resize', function () {
                $('#lobTbl').dataTable().fnAdjustColumnSizing();
            });
        });
        
      
      
        $('#branches-panel').on('shown.bs.modal', function (e) {
            //Call your Function here 
            
              $.ajax({
            url: "getBranches?subRegId="+selectedSubReg,
            method: 'POST',
            contentType: "application/json",
             success: function(data){
           debugger;
            var data = JSON.parse(data)
            console.log(data);
           $('#branchesTbl tr').not(':first').not(':last').remove();
           var html = '';
            opp1= 0;
            sum1T = 0;
           for(var i = 0; i < data.subRegions.length; i++){
        	   opp1 = opp1+data.subRegions[i].brOpps;
           sum1T= sum1T+ data.subRegions[i].fcAmt;
                       html += '<tr><td class="brclick"><input type="checkbox"  onclick="myFunction(this)" class = "brclick" id ="'+data.subRegions[i].brId+'"></td><td>' + data.subRegions[i].brName + '</td><td>' + data.subRegions[i].brFcStatus + '</td><td class="text-right ">' + data.subRegions[i].brOpps + '</td><td class="text-right numbers">' + data.subRegions[i].fcAmt + '</td>';
             }       
           $('#tdbody').html(html);
           $('#label7').text(opp1);
           $('#label8').text(sum1T);
           $('#branchesTbl').dataTable({
               "scrollY": "194px",
               "scrollCollapse": true,
               "paging": true,
               "bAutoWidth": false,
               "aoColumns": [
                  { "sWidth": "5%" }, 
                  { "sWidth": "30%" }, 
                  { "sWidth": "23%" },
                  { "sWidth": "23%" }, 
                  { "sWidth": "23%" }
               ] 
           });
           $(".numbers").digits();
           
           $('#branchesTbl').on('draw.dt', function() {
        	   $(".numbers").digits();
        	});
        }});
     
            if ($.fn.DataTable.isDataTable("#branchesTbl")) {
                $('#branchesTbl').DataTable().destroy();
            }


       
            $(window).bind('resize', function () {
                $('#branchesTbl').dataTable().fnAdjustColumnSizing();
            });
        });

    });
    
    
    function myFunction7(data){
    	debugger
    	 $(data).parent().parent().find("input").removeAttr('disabled');

        } 
    function myFunction1(data){
        debugger;
        oppId = parseInt(data.id);
        nonDel = 0;// total no of opportunities
            estimatedSum = 0;
        $.ajax({
            url: "remove?brId="+oppId,
            method: 'GET',
            contentType: "application/json",
             success: function(data){
           debugger;
            //var data = JSON.parse(data)
            console.log(data);
            $.ajax({
                url: "getOpps?brId="+selectedRowId,
                method: 'POST',
                contentType: "application/json",
                 success: function(data){
               debugger;
                var data = JSON.parse(data)
                console.log(data);
                nonDel =0;
                estimatedSum =0;
               $('#thetable tr').not(':first').not(':last').remove();
               var html = '';
               for(var i = 0; i < data.subRegions.length; i++){
                   if( data.subRegions[i].deleteFlg==='N'){
                      
                	   nonDel++;
                	   estimatedSum = estimatedSum +parseInt(data.subRegions[i].factoredAmt);
                	   
                		  	 html += '<tr ><td><a class="fa fa-edit margin-right4" onclick="myFunction7(this)"></a><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close" onclick="myFunction1(this)" id ="'+data.subRegions[i].oppsId+'"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td>' + data.subRegions[i].planDt + '</td><td class="text-right"><input type="text" disabled value="' + data.subRegions[i].estAmt + '"/></td><td class="text-right"><input type="text" disabled value="' + data.subRegions[i].factoredAmt + '"/></td><td class="text-right"><input type="text" disabled value="' + data.subRegions[i].marginPct + '"/></td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                    	 
                	  
                       }
                   else{
					   var a = data.subRegions[i].planDate;
						   a.substr(0,13)
                	   var d1 = new Date ( a.substr(0,12));// server
                       var d2 = new Date ();// today
                       d3 = d2.toString();
                       d3= d3.substr(0,15);
                       var d4 = new Date (d3);
                       var result = d1<d4;
                	 
                	   if(result){// is server date is less color red
                           html += '<tr style="background-color:#d2d2d2;opacity:0.6;"><td><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td  style="color:red">' + data.subRegions[i].planDt + '</td><td class="text-right numbers">' + data.subRegions[i].estAmt + '</td><td class="text-right numbers">' + data.subRegions[i].factoredAmt + '</td><td class="text-right">' + data.subRegions[i].marginPct + '</td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                	   }
                	   else
                    	   {
                		   html += '<tr style="background-color:#d2d2d2;opacity:0.6;"><td><a class="fa fa-exclamation-circle margin-right4"></a><a class="fa fa-close"></a></td><td>' + data.subRegions[i].AccName + '</td><td>' + data.subRegions[i].oppsName + '</td><td>' + data.subRegions[i].planDt + '</td><td class="text-right numbers">' + data.subRegions[i].estAmt + '</td><td class="text-right numbers">' + data.subRegions[i].factoredAmt + '</td><td class="text-right">' + data.subRegions[i].marginPct + '</td><td>' + data.subRegions[i].stage + '</td><td>' + data.subRegions[i].leadRep + '</td><td>' + data.subRegions[i].oppNumber + '</td><td>' + data.subRegions[i].lob + '</td><td>' + data.subRegions[i].country + '</td>';
                    	   }
                   }  
               }  
               $('#tdbody1').html(html);
               $('#label2').text(nonDel);
               $('#label3').text(estimatedSum);
               if ($.fn.DataTable.isDataTable("#forecastTbl")) {
                   $('#forecastTbl').DataTable().destroy();
               }
               $('#forecastTbl').dataTable({
                   "scrollY": "194px",
                   "scrollCollapse": true,
                   "paging": true,
                   "bAutoWidth": false,
                   "aoColumns": [
                      { "sWidth": "5%" }, 
                      { "sWidth": "11%" }, 
                      { "sWidth": "20%" },
                      { "sWidth": "11%" }, 
                      { "sWidth": "11%" }, 
                      { "sWidth": "11%" },
                      { "sWidth": "11%" }, 
                      { "sWidth": "8%" }, 
                      { "sWidth": "11%" } 
                   ] 
               });

               $(".numbers").digits();
               $('#forecastTbl').on('draw.dt', function() {
            	   $(".numbers").digits();
            	});
            }});

            if ($.fn.DataTable.isDataTable("#forecastTbl")) {
                $('#forecastTbl').DataTable().destroy();
            }


            $(window).bind('resize', function () {
                $('#forecastTbl').dataTable().fnAdjustColumnSizing();
            });
          
           
        }});
     
        }
	   function myFunction(data){
           debugger

           selectedRowId = parseInt(data.id);
           
           
           }       
       function myFunction3(data)
       {
    	   selectedSubReg =null;
           selectedSubReg =  parseInt(data.id);
           
           }

       $('#forecast-panel').on('hidden.bs.modal',function(e){
    	// do something...
    	   debugger;
    	   location.reload();
    	   }) 
    	   
    	   $.fn.digits = function(){ 
    return this.each(function(){ 
        $(this).text( $(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") ); 
    })
}

</script> 


    
</body>
</html>
