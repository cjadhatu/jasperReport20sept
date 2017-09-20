
var app = angular.module('wftApp', ['ngRoute', 'ngTouch','ui.grid', 'ui.grid.selection',
 'ui.grid.expandable','ui.grid.pinning','ui.grid.exporter','ui.grid.pagination',
 'ui.grid.autoResize','ui.grid.edit','toaster','ui.bootstrap']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "static/login.html",
        controller: "loginController",
         controllerAs: 'lgCtrl'
        
        
    })
    .when("/logout", {
        templateUrl : "static/logout.html",
        controller: "logoutController"
    })
     .when("/forecast/:regionId", {
        templateUrl : "static/forecast.html",
          controller: "forecastController",      
      
    })
     .when("/forecast", {
        templateUrl : "static/forecast.html",
          controller: "forecastController",      
      
    })
     .when("/lob", {
        templateUrl : "static/lob.html",
          controller: "lobController",      
      
    })
    .when("/branch", {
        templateUrl : "static/branch.html",
          controller: "branchController",      
      
    })
    .when("/branch/:regionId", {
        templateUrl : "static/branch.html",
          controller: "branchController",      
      
    })
    .when("/region/:regionId", {
        templateUrl : "static/region.html",
          controller: "regionContoller",
    })
     .when("/region", {
        templateUrl : "static/region.html",
          controller: "regionContoller",
    })
    .when("/subRegion/:regionId", {
        templateUrl : "static/subRegion.html",
          controller: "subRegionController",
    })
    .when("/subRegion", {
        templateUrl : "static/subRegion.html",
          controller: "subRegionController",
    })

     .when("/country", {
        templateUrl : "static/country.html",
          controller: "countryController",
    })
     .when("/country/:regionId", {
        templateUrl : "static/country.html",
          controller: "countryController",
    })
    
    .when("/addNewforecast", {
        templateUrl : "static/addNewForecast.html",
         controller:"addNewContoller"   
      
    })
    .when("/landing", {
        templateUrl : "static/landingPage.html",
        controller: "landinController",
        
    })
    .when("/newlanding", {
        templateUrl : "static/newLanding.html",
        controller: "landinController",
        
    })
     .when("/oldLanding", {
        templateUrl : "static/oldLanding.html",
        controller:"oldLandingCtrl"
        
    })
     .when("/report", {
        templateUrl : "static/report.html",
        controller:"reportController"
        
    })
    .when("/ht", {
        templateUrl : "WEB-INF/views/demo.html",
        
    })
   
   
     
    
});

app.filter('filterData', function() {
	return function(data, searchText, optional2) {
		var output =[],row,isInclude = false;
		for(var i=0; i< data.length;i++) {
			row = data[i];			
			for (var property in row) {
			    if (row.hasOwnProperty(property) && row[property]) {
			    	var strToCompare = row[property].toString().toLowerCase().replace(/\,/g,""),
			    		str1ToCompare = row[property].toString().toLowerCase();
			    	if(typeof(row[property]) == "object" && row[property].name) {
			    		strToCompare = row[property].name.toString().toLowerCase().replace(/\,/g,""),
			    		str1ToCompare = row[property].name.toString().toLowerCase();
			    	}
			    	if(strToCompare.indexOf(searchText.toLowerCase()) !== -1 || str1ToCompare.indexOf(searchText.toLowerCase()) !== -1) {
			    		output.push(row);
			    		break;
			    	}
			    }
			}
		}
		return output;
	}
});