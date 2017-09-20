app.controller("landinController", ['$scope', '$location', 'commonService', 'loginService', function($scope, $location, commonService, loginService) {	
    $scope.clickable = [];
    $scope.loginData = "";
    $scope.role = "";
    $scope.listData = "";
    $scope.currentForecast = "";
    $scope.lastMonthForecast = "";
    $scope.plannedSalesAmt = "";
    $scope.roleValue = "";
    $scope.rollingTotal = "";
    $scope.superRegion = "";
    $scope.superRegionId = "";
    $scope.userName = "";
    $scope.colors = ['#29b682', '#55b841', "#23a4d2", "#0475b7", "#0655a4", "#01b8e0", ];
    $scope.loginData = commonService.getloginDetails();
    $scope.listData;
    loginService.getListData().then(function(data){
    	$scope.loginData = data; 
    	$scope.listData = data.listData;
    	$scope.currentForecast = $scope.loginData.currentForecast;
        $scope.lastMonthForecast = $scope.loginData.lastMonthForecast;
        $scope.plannedSalesAmt = $scope.loginData.plannedSalesAmt;
        $scope.rollingTotal = $scope.loginData.rollingTotal;
    });
    $scope.role = $scope.loginData.role;
    //$scope.listData = $scope.loginData.listData;
    $scope.currentForecast = $scope.loginData.currentForecast;
    $scope.lastMonthForecast = $scope.loginData.lastMonthForecast;
    $scope.plannedSalesAmt = $scope.loginData.plannedSalesAmt;
    $scope.roleValue = $scope.loginData.roleValue;
    $scope.rollingTotal = $scope.loginData.rollingTotal;
    $scope.superRegion = $scope.loginData.superRegion;
    $scope.superRegionId = $scope.loginData.superRegionId;
    $scope.userName = $scope.loginData.userName;
    console.log($scope.loginData)
    $scope.redirectTo = "";
    $scope.isAdmin = false;

    $scope.isApac = $scope.loginData.isApac;
    if ($scope.role === 'HQ/Admin Forecaster') {
        $scope.isAdmin = true;
    } else {
        $scope.isAdminNot = true;
    }
    if ($scope.role === "Sub Region Forecaster") {
        $scope.redirectTo = "subRegion";
    } else if ($scope.role === "Country Forecaster") {
        if ($scope.loginData.userCountry === "China") {
            $scope.redirectTo = "subRegion";
        } else {
            $scope.redirectTo = "country";
        }
    } else if ($scope.role === "Branch Forecaster") {
        $scope.redirectTo = "branch";
    } else {
        $scope.redirectTo = "region"
    }
    $scope.redirectToRegion = function() {

    }

}])