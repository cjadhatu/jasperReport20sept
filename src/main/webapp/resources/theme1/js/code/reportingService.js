app.service('reportService', function($q, $http, commonService, toaster) {
	var error503Msg = 'System Timeout Error. Please contact Sales Admin to raise a ticket within SFDC';
    this.checkUser = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        var inputData = '';
        $http.post(uri).success(function(data) {
                var result = data;
                // var values = _.pluck(result, 'name');
                deferred.resolve(result);
            })
            .error(function(data, status, headers, config) {
                commonService.hideLoader();
                toaster.pop('error', "Error", "System Error . Please contact System Administrator ");

                return deferred.reject(data);
            });

        return deferred.promise;
    }


    this.callWithInput = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        //var inputData = {"id":0,"name":input};
        $http.post(uri, input).success(function(subData) {
                var result = subData.dropdownValues;
                // var values = _.pluck(result, 'name');
                deferred.resolve(result);

            })
            .error(function(data, status, headers, config) {
                commonService.hideLoader();
                toaster.pop('error', "Error", "System Error . Please contact System Administrator ");

                return deferred.reject(data);
            });

        return deferred.promise;
    }

    this.callWithInputSubRegion = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        //var inputData = {"id":0,"name":input};
        $http.post(uri, input).success(function(subData) {
                var result = subData;
                // var values = _.pluck(result, 'name');
                deferred.resolve(result);

            })
            .error(function(data, status, headers, config) {
                commonService.hideLoader();
                toaster.pop('error', "Error", "System Error . Please contact System Administrator ");

                return deferred.reject(data);
            });

        return deferred.promise;
    }

    this.callForGrid1 = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        $http.post(uri, input).success(function(subData) {
                var result = subData.detailGrid;
                deferred.resolve(subData);
            })
            .error(function(data, status, headers, config) {
                alert("SESSION TIMEOUT. PLEASE CLOSE THE BROWSER AND RE-LOGIN THE APPLICATION AGAIN.");
                return deferred.reject(data);
            });
        return deferred.promise;
    }
    this.callForGrid2 = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        $http.post(uri, input).success(function(subData1) {
                var result = subData1.summaryReportDTO;
                var r = [];
                r.push(result)
                deferred.resolve(r);
            })
            .error(function(data, status, headers, config) {
                alert("SESSION TIMEOUT. PLEASE CLOSE THE BROWSER AND RE-LOGIN THE APPLICATION AGAIN.");
                return deferred.reject(data);
            });

        return deferred.promise;
    }

    this.generateDetailReport = function(uri, input) {
        var deferred = $q.defer();
        var result = "success";
        $http.post(uri, input, {
            responseType: 'arraybuffer'
        }).then(function(response) {

            var header = response.headers('Content-Disposition')
            var fileName = header.split("=")[1].replace(/\"/gi, '');
            console.log(fileName);

            var blob = new Blob([response.data], {
                type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            });
            if(window.navigator.msSaveOrOpenBlob) {
            	window.navigator.msSaveOrOpenBlob(blob, fileName);
            	deferred.resolve(result);
                commonService.hideLoader();
            } else {
            	// FF and chrome
            	var objectUrl = (window.URL || window.webkitURL).createObjectURL(blob);
                var link = $('<a class="new-link-tentative"></a>');
                link.attr({
                    href: objectUrl,
                    download: fileName
                });
                $('body').append(link);
                link[0].click();
                link.remove();
                deferred.resolve(result);
                commonService.hideLoader();
            }
            
        },function(res){
        	commonService.hideLoader();
        	if(res.status === 503) {
        		toaster.pop('error', "Error", error503Msg);
        	} else {
        		toaster.pop('error', "Error", "System Error . Please contact System Administrator ");
        	}
        	
        	deferred.reject(res);
        });
        return deferred.promise;
    }
});