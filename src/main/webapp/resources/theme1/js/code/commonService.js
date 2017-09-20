app.service('commonService', function($q,$http){

var loginDetails="";
var routeArr=[] ;
var regionId=[];
var isAll = "";
var addNewToForecast = "";
 return {
        getloginDetails : function () {
            return loginDetails; 
        },
        setloginDetails:function(value){
           loginDetails = value;
            return loginDetails;
        },
    getRouteArr : function () {
            return loginDetails; 
        },
        setRouteArr:function(value){
           loginDetails = value;
            return loginDetails;
        },
         getRegionId : function () {
            return regionId; 
        },
        setRegionId:function(value){
           regionId = value;
            return regionId;
        },
      getIsAll : function () {
            return isAll; 
        },
        setIsAll:function(value){
           isAll = value;
            return isAll;
        },
     getAddNewToForecast : function () {
            return addNewToForecast; 
        },
        setAddNewToForecast:function(value){
           addNewToForecast = value;
            return addNewToForecast;
        },

        showLoader: function(){
          $.loader({ className: "loader-img",
                  content: ''});                                     
            },
        hideLoader: function(){  
          $.loader('close');
        }
}
});