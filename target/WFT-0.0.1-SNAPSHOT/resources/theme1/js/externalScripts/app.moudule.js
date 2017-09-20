
var app = angular.module('wftApp', ['ngRoute', 'ngTouch','ui.grid', 'ui.grid.selection', 'ui.grid.expandable','ui.grid.pinning','ui.grid.exporter','ui.grid.pagination','ui.grid.autoResize','ui.grid.edit','toaster']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "static/login.html",
        controller: "loginController",
         controllerAs: 'lgCtrl'
        
        
    })
    .when("/region/:regionId", {
        templateUrl : "static/region.html",
          controller: "regionContoller",
    })
     .when("/country/:regionId", {
        templateUrl : "static/country.html",
          controller: "regionContoller",
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
        
    })
     .when("/report", {
        templateUrl : "static/report.html",
        controller:"reportController"
        
    })
    .when("/ht", {
        templateUrl : "WEB-INF/views/demo.html",
        
    })
   
    .when("/forecast", {
        templateUrl : "static/forecast.html",
          controller: "forecastController",
        
      
    })
     .when("/addNewforecast", {
        templateUrl : "static/addNewForecast.html",
         
        
      
    });
    
});