app.service('addNewService', function($q,$http,commonService,toaster){
    
     this.checkUser = function (uri,input){
          var deferred = $q.defer();
                var result = "success";
                var inputData = '';
            $http.post(uri).success(function (data) {
                                var result = data;
                               // var values = _.pluck(result, 'name');
                                deferred.resolve(result);
                            })
                                .error(function (data, status, headers, config) {                  
                                    commonService.hideLoader();
                                     toaster.pop('error', "Error", "System Error . Please contact System Administrator ");
                               
                                return deferred.reject(data);
                                });
                                
                return deferred.promise; 
     } 
  
  this.getOnChange =function (uri,input){
                 var deferred = $q.defer();
                var result = "success";
                //var inputData = {"id":0,"name":input};
                    $http.post(uri,input).success(function (subData) {
                               // var result = subData.dropdownValues;
                               // var values = _.pluck(result, 'name');
                                deferred.resolve(subData);
                               
                            })
                                .error(function (data, status, headers, config) {                  
                                     commonService.hideLoader();
                                     toaster.pop('error', "Error", "System Error . Please contact System Administrator ");
                               
                                return deferred.reject(data);
                                });
                                
                return deferred.promise; 
            }   
  
            this.callWithInput= function (uri,input){
                 var deferred = $q.defer();
                var result = "success";
                //var inputData = {"id":0,"name":input};
                    $http.post(uri,input).success(function (subData) {
                                var result = subData.dropdownValues;
                               // var values = _.pluck(result, 'name');
                                deferred.resolve(result);
                               
                            })
                                .error(function (data, status, headers, config) {                  
                                     commonService.hideLoader();
                                     toaster.pop('error', "Error", "System Error . Please contact System Administrator ");
                               
                                return deferred.reject(data);
                                });
                                
                return deferred.promise; 
            }   

             this.callForGrid1= function (uri,input){
                 var deferred = $q.defer();
                 var result = "success";           
                        $http.post(uri,input).success(function (subData) {
                                        var result = subData.detailGrid;
                                        deferred.resolve(subData);                                    
                                    })
                                        .error(function (data, status, headers, config) {                  
                                        alert("SESSION TIMEOUT. PLEASE CLOSE THE BROWSER AND RE-LOGIN THE APPLICATION AGAIN.");
                                        return deferred.reject(data);
                                    });                                
                return deferred.promise; 
            }   
            this.callForGrid2= function (uri,input){
                            var deferred = $q.defer();
                            var result = "success";                      
                        $http.post(uri,input).success(function (subData1) {
                                        var result = subData1.summaryReportDTO;
                                        var r = [];
                                        r.push(result)
                                        deferred.resolve(r);                                      
                                        })
                                        .error(function (data, status, headers, config) {                  
                                        alert("SESSION TIMEOUT. PLEASE CLOSE THE BROWSER AND RE-LOGIN THE APPLICATION AGAIN.");
                                        return deferred.reject(data);
                                        });
                                            
                            return deferred.promise; 
                        } 
            
            this.generateDetailReport= function (uri,input){
           	 var deferred = $q.defer();
               var result = "success";                      
               $http.post(uri, input,{responseType: 'arraybuffer'}
               ).then(function (response) {
               	
                       var header = response.headers('Content-Disposition')
                       var fileName = header.split("=")[1].replace(/\"/gi,'');
                       console.log(fileName);
        
                       var blob = new Blob([response.data],
                           {type : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
                       var objectUrl = (window.URL || window.webkitURL).createObjectURL(blob);
                       var link = angular.element('<a/>');
                       link.attr({
                           href : objectUrl,
                           download : fileName
                       })[0].click();
                       deferred.resolve(result); 
               	
             
                   
                   return deferred.promise;
               });
               
           }
});