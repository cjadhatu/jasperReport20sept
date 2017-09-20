app.service('countryService', function($q,$http,commonService,toaster){
  
     this.callForGrid = function (uri,input){
           
          var deferred = $q.defer();
                var result = "success";
                var inputData = '';
            $http.post(uri,input).success(function (data) {
                                //var result = data.oppData;
                               // var values = _.pluck(result, 'name');
                                deferred.resolve(data);
                            })
                                .error(function (data, status, headers, config) {                  
                                    commonService.hideLoader();
                                     toaster.pop('error', "Error", "System Error . Please contact System Administrator ");
                                return deferred.reject(data);
                                });
                                
                return deferred.promise; 
     } 
      this.editGrid = function (uri,input){           
          var deferred = $q.defer();
                var result = "success";
                var inputData = '';
            $http.post(uri,input).success(function (data) {
                                deferred.resolve(data);
                            })
                                .error(function (data, status, headers, config) {                  
                             commonService.hideLoader();
                                return deferred.reject(data);
                                });                                
                return deferred.promise; 
     } 
  
});