app.controller("addNewContoller", ['$scope', '$location', 'commonService', 'addNewService', '$filter', 'toaster', "$routeParams", "reportService", function($scope, $location, commonService, addNewService, $filter, toaster, $routeParams, reportService) {

    $scope.onLoadData = commonService.getloginDetails();
    $scope.regionId = commonService.getRegionId();

    $scope.regionList = [];
    $scope.subRegionList = [];
    $scope.countryList = [];
    $scope.branchList = [];
    $scope.sourceTypeList = [];
    $scope.accNameList = [];
    $scope.lobList = [];
    $scope.currencyList = [];
    $scope.fetchJson = "";

    //user input field 
    $scope.forecastDate = "";
    $scope.stage = "";
    $scope.oppName = ""
    $scope.leadSource = "";
    $scope.probability;
    $scope.grossMargin = "";
    $scope.accName = "";
    $scope.factAmtLocal = "";
    $scope.unFactAmtLocal = 0.00;
    $scope.saleLead = "";
    $scope.factAmtUsd = "";
    $scope.unFactAmtUsd = "";
    $scope.mangerName = "";

    $scope.selectedLob = "";
    $scope.accName = "";
    $scope.accId = "";
    $scope.selectedCurr = "";

    $scope.exchange = null;
    $scope.opportunityNumber = "";



    // ******** Navigation pannel **************/
    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }, {
            "name": "Forecast",
            "link": "forecast",
            "data": $routeParams.regionId
        }]
    } else if ($scope.onLoadData.role === "Branch Forecaster") {

    }


    $scope.$on("dateChange", function(event, data) {

        if (data.id == "forecast") {

            var selDate = new Date(data.selectedDate);
            selDate.setHours(0, 0, 0, 0);
            var currentDate = new Date();
            currentDate.setHours(0, 0, 0, 0);
            var firstDayofMonth = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
            var lastDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
            if (selDate.getFullYear() == currentDate.getFullYear() && selDate.getMonth() == currentDate.getMonth() && (selDate.getDate() >= 1 || selDate.getDate() <= lastDate.getDate())) {
                $scope.forecastDate = data.selectedDate;
            } else {
                //toaster.pop('error', "error", "Please select a date inside forecasting period");
                confirm("Please select a date inside forecasting period")
            }
        }


    });
    //******** ON LOAD DATA **************/
    var url = "getAddNewOpportunity";
    commonService.showLoader();
    addNewService.checkUser(url).then(function(data) {
        console.log("Onload data for add forecast --- >  " + JSON.stringify(data))
        $scope.fetchJson = data;
        //dropdown
        $scope.oppNo = data.opportunityNumber;
        $scope.sourceTypeList = data.sourceType;
        $scope.regionList = data.region;
        $scope.subRegionList = data.subRegion;
        $scope.countryList = data.country;
        $scope.branchList = data.branch;
        $scope.lobList = data.getlob.dropdownValues;
        $scope.dropdownValues = data.getCurrency.dropdownValues;
        $scope.currencyList = data.getCurrency.dropdownValues;
        $scope.stageList = data.stageName.dropdownValues;

        //selected values 
        $scope.selectedRegion = {
            "name": data.selectedregion ? data.selectedregion : "---Please Select---",
            "id": 0
        };
        $scope.selectedSubRegion = {
            "name": data.selectedsubRegion ? data.selectedsubRegion : "---Please Select---",
            "id": 0
        };
        $scope.selectedCountry = {
            "name": data.selectedcountry ? data.selectedcountry : "---Please Select---",
            id: 0
        };
        $scope.selectedBranch = {
            "name": data.selectedbranch ? data.selectedbranch : "---Please Select---",
            "id": 0
        };
        $scope.selectedSourceType = {
            "name": data.selectedSourceType ? data.selectedSourceType : "---Please Select---",
            "id": 0
        };
        $scope.selectedLob = {
            "name": data.selectedSourceType ? data.selectedSourceType : "---Please Select---",
            "id": 0
        };
        $scope.saleLead = data.user_name;
        $scope.selectedLob = {
            "name": "---Please Select---",
            "id": 0
        };
        $scope.selectedCurr = {
            "name": "---Please Select---",
            "id": 0
        };
        $scope.opportunityNumber = data.opportunityNumber;
        commonService.hideLoader();

        $scope.sourceTypeList = [{
            "name": "OpenGlobe",
            "id": 0,
            "accountName": null
        }];
        $scope.selectedSourceType = $scope.sourceTypeList[0];
        if ($scope.onLoadData.role === 'HQ/Admin Forecaster' || $scope.onLoadData.isApac === 'Y') {
            $scope.getRegion();
        }
        if (($scope.selectedCountry.name !== 'China' && $scope.selectedCountry.name !== '---Please Select---') || $scope.selectedRegion.name !== 'China' && $scope.selectedRegion.name !== '---Please Select---') {
            $scope.subRegionList = [{
                "id": 0,
                "name": "NA"
            }];
            $scope.selectedSubRegion = {
                "id": 0,
                "name": "NA"
            };
        }
        // $scope.sourceTypeName = _.pluck( $scope.selectedSourceType, 'name').join().slice(1);
    })

    $scope.checkSaveDisable = function() {
        if ($('#forecast1').val() && $('#forecast1').val().length > 0 && $scope.selectedRegion && $scope.selectedCountry && $scope.selectedSubRegion && $scope.selectedBranch && $scope.selectedLob && $scope.selectedStage && $scope.probability && $scope.grossMargin && $scope.oppName && $scope.selectedCurr &&
            $scope.unFactAmtLocal && $scope.saleLead) {
            if ($scope.selectedRegion.name != "---Please Select---" && $scope.selectedCountry.name != "---Please Select---" &&
                $scope.selectedSubRegion.name != "---Please Select---" && $scope.selectedBranch.name != "---Please Select---" &&
                $scope.selectedLob.name != "---Please Select---" && $scope.selectedCurr.name != "---Please Select---" &&
                $scope.selectedStage != "---Please Select---") {
                if ($scope.probability > 0 && $scope.grossMargin > 0 && $scope.oppName.length > 0 &&
                    $scope.saleLead.length > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    $scope.save = function() {
        var url = "SaveAddNewOpportunity";
        if ($scope.selectedLob.name === "---Please Select---") {
            $scope.selectedLob.name = "";
        }
        var saveDate = {
            "forcastCloseDate": $scope.forecastDate,
            "stageName": $scope.stage,
            "oppName": $scope.oppName,
            "leadSource": $scope.leadSource,
            "probability": $scope.probability.toString(),
            "grossMargin": $scope.grossMargin.toString(),
            "factoredAmount": $scope.factAmtLocal,
            "unFactoredAmount": $scope.unFactAmtLocal,
            "saleslead": $scope.saleLead,
            "factAmtUsd": $scope.factAmtUsd.toString(),
            "unFactAmtUsd": $scope.unFactAmtUsd.toString(),
            "region": $scope.selectedRegion.name,
            "subRegion": $scope.selectedSubRegion ? $scope.selectedSubRegion.name : "",
            "jciReportingCountry": $scope.selectedCountry.name,
            "branchCode": $scope.selectedBranch.name ? $scope.selectedBranch.name : "",
            "oppDataSource": $scope.selectedSourceType.name,
            "salesManager": $scope.mangerName,
            "currency": $scope.selectedCurr.name,
            "accountName": $scope.accName,
            "lob": $scope.selectedLob.name,
            "oppNumber": $scope.opportunityNumber.toString(),
            "stageName": $scope.selectedStage.name

        }
        commonService.showLoader();
        addNewService.callWithInput(url, saveDate).then(function(data) {

            commonService.hideLoader();
            commonService.setRegionId(commonService.getAddNewToForecast());
            $location.path("/forecast");
            toaster.pop('success', "Success", "Data saved successfully");
        });

    }

    $scope.setFact = function(data) {
        $scope.factAmtUsd = data / $scope.exchange;
    }

    $scope.setFactLocal = function(data) {
        if ($scope.unFactAmtLocal !== 0 && $scope.unFactAmtLocal !== undefined && $scope.probability !== undefined && $scope.probability !== 0) {
            $scope.factAmtLocal = ($scope.probability / 100) * $scope.unFactAmtLocal;
            $scope.factAmtLocal = $scope.factAmtLocal.toFixed(2);

            $scope.factAmtUsd = $scope.factAmtLocal / $scope.exchange;
            $scope.factAmtUsd = $scope.factAmtUsd.toFixed(2);
            
            $scope.unFactAmtUsd = $scope.unFactAmtLocal / $scope.exchange;
            $scope.unFactAmtUsd = $scope.unFactAmtUsd.toFixed(2);
        } else {
        	$scope.unFactAmtUsd = '';
            $scope.factAmtLocal = '';
            $scope.factAmtUsd = '';
        }
    }
    $scope.setUnfact = function(data) {
        if (data !== undefined && $scope.probability !== 0 && $scope.unFactAmtLocal !== 0 && $scope.probability !== undefined) {
            $scope.unFactAmtUsd = data / $scope.exchange;
            $scope.unFactAmtUsd = $scope.unFactAmtUsd.toFixed(2);

            $scope.factAmtLocal = ($scope.probability / 100) * $scope.unFactAmtLocal;

            $scope.factAmtUsd = $scope.factAmtLocal / $scope.exchange;
            $scope.factAmtUsd = $scope.factAmtUsd.toFixed(2);
            $scope.factAmtLocal = $scope.factAmtLocal.toFixed(2);
        } else {
            $scope.unFactAmtUsd = '';
            $scope.factAmtLocal = '';
            $scope.factAmtUsd = '';
        }
    }
    $scope.goBack = function() {

        bootbox.confirm({
            title: "Cancel",
            message: "Are you sure you want to cancel the process ?",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function(result) {
                if (result === true) {
                    commonService.hideLoader();
                    commonService.setRegionId(commonService.getAddNewToForecast());
                    window.history.back();
                }
            }
        });
    };

    // **** Region DrpDwn ****
    $scope.getRegion = function() {

        $scope.countryList = [];
        $scope.subRegionList = [];
        $scope.branchList = [];
        $scope.regionList = [];
        if ($scope.onLoadData.role === "HQ/Admin Forecaster") {
            commonService.showLoader();
            $scope.selectedSourceType;
            var arr = [];
            arr.push($scope.selectedSourceType);
            var url = "getRegions";
            var input = {
                "typeSources": arr,
            }
            reportService.callWithInput(url, input).then(function(data) {
                commonService.hideLoader();
                $scope.regionList = data;

            })
        }
    }
    // **** Sub Region DrpDwn ****       
    $scope.setSubRegion = function(data) {
        commonService.showLoader();
        if ($scope.onLoadData.role === "HQ/Admin Forecaster") {
            $scope.countryList = [];
            $scope.subRegionList = [];
            $scope.branchList = [];

            if ($scope.selectedRegion.name === "China") {
                var url = "getSubRegions";
                var arrSrc = [];
                var arrREg = [];
                arrSrc.push($scope.selectedSourceType);
                delete $scope.selectedRegion.accountName;
                arrREg.push($scope.selectedRegion);
                $('.branch-col').addClass('add-asteric');
                var input = {
                    "typeSources": arrSrc,
                    "regions": arrREg

                }
                commonService.showLoader();
                reportService.callWithInput(url, input).then(function(regData) {
                    commonService.hideLoader();

                    $scope.subRegionList = regData;
                    $scope.countryList.push({
                        "id": 0,
                        "name": "China"
                    });
                })
            } else {
            	$('.branch-col').removeClass('add-asteric');
                var url = "getcountryByRegion";
                var arrSrc = [];
                var arrREg = [];
                arrSrc.push($scope.selectedSourceType);
                delete $scope.selectedRegion.accountName
                arrREg.push($scope.selectedRegion);
                var input = {
                    "typeSources": arrSrc,
                    "regions": arrREg

                }
                commonService.showLoader();
                reportService.callWithInput(url, input).then(function(regData1) {
                    $scope.countryList = regData1;
                    $scope.subRegionList.push({
                        "id": 0,
                        "name": "NA"
                    });
                    if (($scope.selectedCountry.name !== 'China' && $scope.selectedCountry.name !== '---Please Select---') || $scope.selectedRegion.name !== 'China' && $scope.selectedRegion.name !== '---Please Select---') {
                        $scope.selectedSubRegion = {
                            "id": 0,
                            "name": "NA"
                        };
                    }
                    commonService.hideLoader();
                    console.log('loader hide');
                })
            }
        }
    }
    // **** Branch DrpDwn ****
    $scope.setBranchList = function() {

        if ($scope.onLoadData.role === "HQ/Admin Forecaster" || true) {
            commonService.showLoader();
            var url = "getbranches";
            var arrSrc = [];
            var arrREg = [];
            var countryArr = [];
            var subArr = [];
            arrSrc.push($scope.selectedSourceType);
            delete $scope.selectedRegion.accountName
            arrREg.push($scope.selectedRegion);

            if ($scope.selectedCountry && $scope.selectedCountry.name !== "---Please Select---" && $scope.selectedCountry.name !== "China") {
                if ($scope.subRegionList.length === 1) {
                    if (!($scope.subRegionList[0].name === 'NA' || $scope.subRegionList[0].name === 'null')) {
                        $scope.subRegionList = [];
                    }
                } else {
                    $scope.subRegionList = [];
                }

                delete $scope.selectedCountry.accountName
                countryArr.push($scope.selectedCountry)

                var input = {
                    "typeSources": arrSrc,
                    "regions": arrREg,
                    "countries": countryArr,
                    "subregions": []

                }
            } else if ($scope.selectedSubRegion) {
                delete $scope.selectedSubRegion.accountName
                subArr.push($scope.selectedSubRegion)

                var input = {
                    "typeSources": arrSrc,
                    "regions": arrREg,
                    "subregions": subArr,
                    "countries": []

                }
            }
            if (!input) {
                setTimeout(function() {
                    commonService.hideLoader();
                }, 2000);
                return;
            }
            reportService.callWithInput(url, input).then(function(brnchData) {
                $scope.branchList = brnchData;
                commonService.hideLoader();
            })
        }
    }
    // **** Branch DrpDwn ****
    $scope.getExchangeRate = function(data) {

        commonService.showLoader();
        addNewService.getOnChange("getRate", {
            "sourceType": $scope.selectedSourceType.name,
            "currency": $scope.selectedCurr.name
        }).then(function(rate) {

            commonService.hideLoader();
            $scope.exchange = rate;
        })

    }
}]);



// Adds custom validation test to element
app.directive('emailRegex', [function(ngModel) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, linkElement, linkAttributes, controller) {

            var regex = /^[a-zA-Z0-9-\_]+[a-zA-Z0-9-_\.]*(\.[a-zA-Z0-9-_]+)*([a-zA-Z0-9]+)@[a-zA-Z]+[0-9a-zA-Z-\.]*[0-9a-zA-Z]+\.[a-zA-Z]{2,5}$/;

            function checkValidity(value) {
                var valid = true;

                // This directive should consider an empty value to be valid
                // because the required test, if specified, occurs separately
                if (value != null && value.length > 0) {
                    valid = regex.test(value);
                }

                return valid;
            }

            controller.$parsers.unshift(function(value) {
                // View to model
                var valid = checkValidity(value);

                // Note "emailregex" instead of "emailRegex" - should be all lowercase
                // This allows "$error.emailregex" to work in the DOM
                controller.$setValidity('emailregex', valid);

                // Only return the value to the model if it's valid
                return valid ? value : undefined;
            });

            controller.$formatters.unshift(function(value) {
                // Model to view
                controller.$setValidity('emailregex', checkValidity(value));

                // Return the value regardless of validity,
                // otherwise nothing will appear on the DOM
                return value;
            });
        }
    };
}]);