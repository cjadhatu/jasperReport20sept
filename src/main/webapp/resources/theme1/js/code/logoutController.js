app.controller("logoutController", ['$scope', '$location', 'loginService', 'commonService', 'toaster', function($scope, $location, loginService, commonService, toaster) {
	commonService.showLoader();
	loginService.logout().then(function(data){
		commonService.hideLoader();
		console.log('logout success',data);
		if(data.saveMsg || true) {
			$location.path('');
			window.onbeforeunload = undefined;
			location.reload();
		}
	},function(data){
		console.log('logout error',data);
	});
}]);