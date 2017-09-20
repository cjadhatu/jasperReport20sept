app.controller("loginController", ['$scope', '$location', 'loginService', 'commonService', 'toaster', function($scope, $location, loginService, commonService, toaster) {
    $scope.globalId = "";
    $scope.pswd ="";
    $scope.oldPswd = "";
    $scope.newPswd = "";
    $scope.cnfNewPswd = "";
    $scope.disableGID = false;
    $scope.role = "";
	$scope.disableLogin = true;
	var fromSSO = false;
	var hostname = $location.host();
    if(hostname == 'forecastingtool.herokuapp.com' 
    	|| hostname == 'forecastingtoolqa.herokuapp.com') {
    	fromSSO = true;
    }
    $scope.changePassword = false;
    commonService.hideLoader();
    commonService.setloginDetails('');
    var myElement = angular.element(document.querySelector('#globelId')).val();
    if(fromSSO) {
    	$scope.globalId=myElement;
    	$scope.disableGID = true;
    	$scope.disableLogin = false;
    } else {
    	$scope.$watchGroup(['pswd', 'globalId'], function(newValues, oldValues, scope) {
        	if(newValues[0] && newValues[1]) {
        		$scope.disableLogin = false;
        	} else {
        		$scope.disableLogin = true;
        	}
        });
    }
    //var ele = angular.element(document.getElementsByClassName('globelId'));   
    $scope.showChangePassword = function() {
        $scope.changePassword = true;
    }
    $scope.showLogin = function() {
        $scope.changePassword = false;
    }
    $scope.fncChangePassword = function() {
        if ($scope.newPswd !== $scope.cnfNewPswd) {
            toaster.pop('error', "Error", "New password and confirm new password Should be same.");
            return;
        }
        var regex = /[^a-zA-Z0-9]/;
        if (regex.test($scope.newPswd) || $scope.newPswd.length < 8) {
            toaster.pop('error', "Error", "No special characters, min length 8 characters.");
            return;
        }
        commonService.showLoader();
        loginService.changePassword({
            'globalId': $scope.globalId,
            'oldPassword': $scope.oldPswd,
            'newPassword': $scope.newPswd,
        }).then(function(res) {
            commonService.hideLoader();
            if (res.data.saveMsg == 'Y') {
                toaster.pop('success', "Success", "Password Changed");
            } else {
                toaster.pop('error', "Error", "Username or Password Invalid!  ");
            }
        }, function(res) {
            commonService.hideLoader();
            toaster.pop('error', "Error", "Username or Password Invalid!  ");
        });
    }
    $scope.onSubmit = function() {
        $scope.data = "";
        if ($scope.globalId !== "") {
            var input = {
                "username": $scope.globalId,
                "password": $scope.pswd
            }
            if($scope.disableGID) {
            	input = {
                	"username": $scope.globalId
            	}
            }
            var data1 = '';
            commonService.setloginDetails("");
            loginService.checkUser("login", input).then(
                function(data) {
                    if (data.isValidUser === 'N') {
                        // toaster.pop('error', "Error", "Please check the username ");
                    } else {
                        //commonService.setloginDetails(data1);
                        $scope.period = data.forecastePeriod;
                        // $location.path("/newlanding");
                    }
                },
                function(data) {});
        } else {
            toaster.pop('info', "Info", "Please fill the  username ");
        }

    };
}]);



app.service('loginService', function($q, $http, commonService, $location, toaster) {
    var deferred = $q.defer();
    var saveId;
    this.changePassword = function(options) {
        var def = $q.defer();
        $http({
            method: 'POST',
            url: 'changePassword',
            data: options
        }).then(function successCallback(res) {
            def.resolve(res);
        }, function errorCallback(res) {
            def.reject(res);
        });

        return def.promise;
    }
    this.logout = function(options) {
        var def = $q.defer();
        /*$http({
            method: 'POST',
            url: 'logoutwft',
            data: options
        }).then(function successCallback(res) {
            def.resolve(res);
        }, function errorCallback(res) {
            def.reject(res);
        });*/
        setTimeout(function(){ def.resolve({});  },2000)

        return def.promise;
    }
    this.checkUser = function(url, id) {
        var result = "success";
        var inputData = '';
        var uri = "login";
        saveId = id;
        var dataLogin = '';
        commonService.showLoader();
        $http.post(url, id).success(function(dataLogin) {
                var result = dataLogin;
                deferred.resolve(dataLogin);
                commonService.setloginDetails(dataLogin);
                commonService.hideLoader();
                if (dataLogin.isValidUser === "N") {
                    toaster.pop('error', "Error", "Username or Password Invalid!  ");
                } else {
                    $location.path("/newlanding");
                }

            })
            .error(function(data, status, headers, config) {
                //toaster.pop('error', "Error", "System Error occurred . Please contact system administrator");
                return deferred.reject(data);
            });
        return deferred.promise;
    }
    
    this.getListData = function() {
    	var def = $q.defer();
    	commonService.showLoader();
        $http.post('login', saveId).success(function(dataLogin) {
                var result = dataLogin;
                def.resolve(dataLogin);
                commonService.setloginDetails(dataLogin);
                commonService.hideLoader();
                if (dataLogin.isValidUser === "N") {
                    toaster.pop('error', "Error", "Username or Password Invalid!  ");
                } else {
                    $location.path("/newlanding");
                }
            })
            .error(function(data, status, headers, config) {
                //toaster.pop('error', "Error", "System Error occurred . Please contact system administrator");
                return def.reject(data);
            });
        return def.promise;
    }

});
app.controller("mainCtrl", ['$scope', 'loginService', "commonService", '$rootScope', '$location', '$window', function($scope, loginService, commonService, $rootScope, $location, $window) {
    $scope.role = "";
    $scope.username = "";
    $scope.period = "";
    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    var finalFirstDay = firstDay.getMonth() + 1 + "-" + firstDay.getDate() + "-" + firstDay.getFullYear();
    var finalLastDay = lastDay.getMonth() + 1 + "-" + lastDay.getDate() + "-" + lastDay.getFullYear();
    $scope.period = "Forecast Period (MM-DD-YYYY):" + " " + finalFirstDay + " to " + finalLastDay;
    //  loginService.checkUser("getForecastPeriod").then((data)=>{        
    //         $scope.period= data.name;

    //      })  ;
    $scope.logout = function() {
        $window.location.hash = '/';
    }
    $scope.enableLogout = function() {
        if ($location.$$path !== '/') {
            return true;
        }
        return false;
    }
    var data = commonService.getloginDetails();
    $scope.role = data.role;
    $scope.username = data.userName;

}]);
'use strict';
app.factory('notificationFactory', function(toastr) {
    var logIt;

    toastr.options = {
        "closeButton": true,
        "positionClass": "toast-bottom-right",
        "timeOut": "3000"
    };

    logIt = function(message, type) {
        return toastr[type](message);
    };

    return {
        success: function(message) {
            logIt(message, 'success');
        },
        error: function(message) {
            logIt(message, 'error');
        }
    };
});
app.directive('disableRightClick', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            element.bind('contextmenu', function(e) {
                e.preventDefault();
            })
        }
    }
});
app.controller("oldLandingCtrl", ['$scope', 'loginService', "commonService", function($scope, loginService, commonService) {
    $scope.onLoadData = commonService.getloginDetails();

}]);