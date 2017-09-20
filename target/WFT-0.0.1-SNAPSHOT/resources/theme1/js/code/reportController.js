app.controller("reportController", ['$scope', '$location', 'commonService', 'reportService', 'uiGridConstants', 'toaster', function($scope, $location, commonService, reportService, uiGridConstants, toaster) {
    $scope.regionList = "";
    $scope.subRegionList = "";
    $scope.countryList = "";
    $scope.branchList = "";
    $scope.sourceTypeList = "";
    $scope.lobList = "";

    $scope.onLoadData = commonService.getloginDetails();
    $scope.selectedRegion = [];
    $scope.selectedSubRegion = [];
    $scope.selectedCountry = [];
    $scope.selectedBranch = [];
    $scope.selectedSourceType = [];
    $scope.selectedLob = [];

    $scope.regionName = "---Please select---";
    $scope.subRegionName = "---Please select---";
    $scope.countryName = "---Please select---";
    $scope.branchName = "---Please select---";
    $scope.sourceTypeName = "---Please select---";
    $scope.lobName = "---Please select---";
    $scope.showQuickLinks = false;

    $scope.input1 = "";
    $scope.input3 = "";
    $scope.input2 = "";
    $scope.input4 = "";
    $scope.input5 = "";
    $scope.input6 = "";

    $scope.totalFactoredAmt = "";
    $scope.totalFactoredAmtLocalCur = "";
    $scope.totalunFactoredAmtLocalCur = "";
    $scope.totalunfactoredAmt = "";

    $scope.isTcEnabled = false;
    $scope.isChina = false;

    $scope.validLocation = [{
        "name": "Log Out",
        "link": "logout"
    }, {
        "name": "Home",
        "link": "newlanding"
    }]
    var a = new Date;
    $scope.forMat = a.getDate() + "/" + a.getMonth() + "/" + a.getFullYear() + " " + a.getHours() + " :" + a.getMinutes();


    // **** Source Type DrpDwn ****
    var url = "getDynamicReportData";

    $scope.regionList = [];
    $scope.subRegionList = [];
    $scope.countryList = [];
    $scope.branchList = [];

    $scope.regionName = "---Please select---";
    $scope.subRegionName = "---Please select---";
    $scope.countryName = "---Please select---";
    $scope.branchName = "---Please select---";

    $scope.toggleQuickLinks = function() {
    	$scope.showQuickLinks = !$scope.showQuickLinks;
    }
    commonService.showLoader();
    reportService.checkUser(url, $scope.globalId).then(function(data) {        
        console.log(data);
        commonService.hideLoader();

        $scope.sourceTypeList = data.sourceType;
        $scope.regionList = data.region;
        $scope.countryList = data.country;
        $scope.branchList = data.branch;
        $scope.lobList = data.lob;

        if ($scope.onLoadData.role !== "HQ/Admin Forecaster" && $scope.onLoadData.role !== "Sub Region Forecaster" && $scope.onLoadData.role !== "Regional Forecaster") {

            $scope.sourceTypeName = data.selectedSourceType;
            $scope.selectedSourceType.push({
                "id": 0,
                "name": data.selectedSourceType
            });

            $scope.regionName = data.selectedregion;
            $scope.selectedRegion.push({
                "id": 0,
                "name": data.selectedregion
            });
            $scope.countryName = data.selectedcountry;
            if (data.selectedcountry === "China") {
                $scope.isChina = true;
                $scope.subRegionList = data.subRegion;
                $scope.subRegionName = data.selectedsubRegion;
                $scope.selectedSubRegion.push({
                    "id": 0,
                    "name": data.selectedsubRegion
                });
                $scope.isSREnable = true;
                $scope.subRegionName = "---Please select---";
            }
            $scope.selectedCountry.push({
                "id": 0,
                "name": data.selectedcountry
            });
        } else if ($scope.onLoadData.role === "HQ/Admin Forecaster") {
            $scope.isSTEnable = true;
            $scope.isREnable = true;
            $scope.isCTEnable = true;
        }
        if ($scope.onLoadData.role === "Regional Forecaster") {

            $scope.sourceTypeName = data.selectedSourceType;
            $scope.selectedSourceType.push({
                "id": 0,
                "name": data.selectedSourceType
            });
            $scope.regionName = data.selectedregion;
            $scope.selectedRegion.push({
                "id": 0,
                "name": data.selectedregion
            });
            $scope.countryList = data.country;
            $scope.isCTEnable = true;
            $scope.branchList = "";
            $scope.selectedCountry = [];
            $scope.countryName = "---Please select---";
            var json = angular.toJson($scope.selectedSourceType);
            $scope.input1 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedRegion);
            $scope.input2 = {
                "values": JSON.parse(json)
            };

            if (data.selectedregion === "China") {
                $scope.isChina = true;
                $scope.subRegionList = data.subRegion;
                $scope.branchList = data.branch;
                $scope.isSREnable = true;

            }
            if ($scope.onLoadData.globalId === "jpagar3" || $scope.onLoadData.globalId === "ccutlema") {
                $scope.isREnable = true;
                $scope.selectedRegion = [];
                $scope.regionName = "---Please select---";
                //  var a = data.selectedregion.split(",");
                //  for(var i in a){
                //    $scope.selectedRegion.push({"id":0,"name": a[i]})
                //  }

            }

        }

        if ($scope.onLoadData.role === "Branch Forecaster") {
            $scope.branchName = data.selectedbranch;
            $scope.selectedBranch.push({
                "id": 0,
                "name": data.selectedbranch
            });
            var json = angular.toJson($scope.selectedBranch);
            $scope.input5 = {
                "values": JSON.parse(json)
            }
            $scope.isSTEnable = false;
            $scope.isREnable = false;
            $scope.isCTEnable = false;
            var json = angular.toJson($scope.selectedSourceType);
            $scope.input1 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedRegion);
            $scope.input2 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedSubRegion);
            $scope.input3 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedCountry);
            $scope.input4 = {
                "values": JSON.parse(json)
            }
        }
        if ($scope.onLoadData.role === "Country Forecaster") {

            var json = angular.toJson($scope.selectedBranch);
            $scope.input5 = {
                "values": JSON.parse(json)
            }
            $scope.isSTEnable = false;
            $scope.isREnable = false;
            $scope.isCTEnable = true;
            var json = angular.toJson($scope.selectedSourceType);
            $scope.input1 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedRegion);
            $scope.input2 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedSubRegion);
            $scope.input3 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedCountry);
            $scope.input4 = {
                "values": JSON.parse(json)
            }

            if (data.selectedcountry === "China") {
                $scope.isChina = true;
                $scope.subRegionList = data.subRegion;
                $scope.branchList = data.branch;
            }
        }
        if ($scope.onLoadData.role === "Sub Region Forecaster") {
            $scope.subRegionList = data.subRegion;
            $scope.sourceTypeName = data.selectedSourceType;
            $scope.selectedSourceType.push({
                "id": 0,
                "name": data.selectedSourceType
            });
            $scope.regionName = data.selectedregion;
            $scope.selectedRegion.push({
                "id": 0,
                "name": data.selectedregion
            });
            $scope.countryName = "China";
            $scope.selectedCountry.push({
                "id": 0,
                "name": "China"
            });
            $scope.isChina = true;
            $scope.subRegionName = data.selectedsubRegion;
            $scope.selectedSubRegion.push({
                "id": 0,
                "name": data.selectedsubRegion
            });
            var json = angular.toJson($scope.selectedBranch);
            $scope.input5 = {
                "values": JSON.parse(json)
            }
            $scope.isSTEnable = false;
            $scope.isREnable = false;
            $scope.isCTEnable = true;
            var json = angular.toJson($scope.selectedSourceType);
            $scope.input1 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedRegion);
            $scope.input2 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedSubRegion);
            $scope.input3 = {
                "values": JSON.parse(json)
            };
            var json = angular.toJson($scope.selectedCountry);
            $scope.input4 = {
                "values": JSON.parse(json)
            }
        }


        // $scope.sourceTypeName = _.pluck( $scope.selectedSourceType, 'name').join().slice(1);
    }, function() {})

    // **** Region DrpDwn **** ON CHANGE OF source type
    $scope.getRegion = function(data) {
        if ($scope.onLoadData.role === "HQ/Admin Forecaster") {
            $scope.selectedSourceType = data;
            if (data.length == 0) {

                $scope.regionList = "";
                $scope.subRegionList = "";
                $scope.countryList = "";
                $scope.branchList = "";
                $scope.regionName = "---Please select---";
                $scope.subRegionName = "---Please select---";
                $scope.countryName = "---Please select---";
                $scope.branchName = "---Please select---";
                $scope.selectedRegion = [];
                $scope.selectedSubRegion = [];
                $scope.selectedCountry = [];
                $scope.selectedBranch = [];
                $scope.selectedSourceType = [];
                $scope.selectedLob = [];
            } else {
                commonService.showLoader();
                var url = "getRegions";
                var json = angular.toJson($scope.selectedSourceType);
                $scope.input1 = {
                    "values": JSON.parse(json)
                }
                var input = {
                    "typeSources": $scope.input1.values ? $scope.input1.values : [],
                    "regions": $scope.input2.values ? $scope.input2.values : [],
                    "countries": $scope.input4.values ? $scope.input4.values : [],
                    "subregions": $scope.input3.values ? $scope.input3.values : [],
                    "branches": $scope.input5.values ? $scope.input5.values : []
                }
                reportService.callWithInput(url, input).then(function(data) {
                    commonService.hideLoader();
                    $scope.regionList = data;
                    $scope.sourceTypeName = _.pluck($scope.selectedSourceType, 'name').join();
                    // $scope.regionName = _.pluck($scope.selectedRegion,'name').join().slice(1);

                }, function() {});
            }
        }
    }
    // **** Sub Region DrpDwn ****    on change of region dropdown when china is selected as region  
    $scope.setSubRegion = function(data) {
        if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.globalId === "jpagar3" || $scope.onLoadData.globalId === "ccutlema") {
            $scope.selectedSubRegion = [];
            $scope.selectedCountry = [];
            $scope.selectedRegion = data;
            commonService.showLoader();
            var url = "getSubRegions";
            $scope.subRegionList = [];
            $scope.countryList = [];
            $scope.branchList = [];
            if ($scope.selectedRegion.length == 0) {
                $scope.isChina = false;
            }
            $scope.subRegionName = "---Please select---";
            $scope.countryName = "---Please select---";
            $scope.branchName = "---Please select---";


            var json = angular.toJson($scope.selectedRegion);
            $scope.input2 = {
                "values": JSON.parse(json)
            };

            for (var arr in $scope.selectedRegion) {
                if ($scope.selectedRegion[arr].name === "China") {
                    $scope.isChina = true;
                    break;
                } else {

                    $scope.isChina = false;
                    if ($scope.selectedRegion.length > 0) {
                        $scope.isCTEnable = true;
                    }
                }
            }
            if ($scope.selectedRegion.length === 0) {
                $scope.isCTEnable = true;
            }

            var input = {
                "typeSources": $scope.input1.values ? $scope.input1.values : null,
                "regions": $scope.input2.values ? $scope.input2.values : null,
                "countries": $scope.input4.values ? $scope.input4.values : null,
                "subregions": $scope.input3.values ? $scope.input3.values : null,
                "branches": $scope.input5.values ? $scope.input5.values : null
            }
            if ($scope.isChina === true) {
                var url = "getSubRegions";

                reportService.callWithInputSubRegion(url, input).then(function(regData) {
                    $scope.subRegionList = regData.dropdownValues;
                    $scope.countryList = regData.countriesChina;
                    if ($scope.isChina === true && $scope.selectedRegion.length === 1) {
                        $scope.countryName = regData.countriesChina[0].name;
                        $scope.selectedCountry.push({
                            "id": 0,
                            "name": regData.countriesChina[0].name
                        });
                        $scope.isCTEnable = false;
                    }
                    $scope.regionName = _.pluck($scope.selectedRegion, 'name').join();
                    $scope.isSREnable = true;
                    commonService.hideLoader();
                }, function() {});
            } else {

                var url = "getcountryByRegion";
                var json = angular.toJson($scope.selectedRegion);
                var imme = {
                    "values": JSON.parse(json)
                }

                reportService.callWithInput(url, input).then(function(cntrData) {
                    $scope.countryList = cntrData;
                    $scope.subRegionName = _.pluck($scope.selectedSubRegion, 'name').join();
                    commonService.hideLoader();
                }, function() {})
            }
        }

    }
    // **** Country DrpDwn **** on change of region dropdown
    $scope.setcountryList = function(data) {
        if ($scope.onLoadData.role === "HQ/Admin Forecaster") {
            $scope.selectedSubRegion = data;
            commonService.showLoader();
            var url = "getcountry";
            $scope.countryList = "";
            $scope.branchList = "";
            $scope.countryName = "---Please select---";
            $scope.branchName = "---Please select---";

            var json = angular.toJson($scope.selectedSubRegion);
            $scope.input3 = {
                "values": JSON.parse(json)
            }
            var input = {
                "typeSources": $scope.input1.values ? $scope.input1.values : null,
                "regions": $scope.input2.values ? $scope.input2.values : null,
                "countries": $scope.input4.values ? $scope.input4.values : null,
                "subregions": $scope.input3.values ? $scope.input3.values : null,
                "branches": $scope.input5.values ? $scope.input5.values : null
            }
            reportService.callWithInput(url, input).then(function(cntrData) {
                $scope.countryList = cntrData;
                $scope.subRegionName = _.pluck($scope.selectedSubRegion, 'name').join();
                // $scope.countryName = _.pluck($scope.selectedCountry,'name').join().slice(1);
                commonService.hideLoader();
            }, function() {})
        }
    }
    // **** Branch DrpDwn By Country****
    $scope.setBranchListByCountry = function(data) {
        $scope.selectedCountry = data
        // $scope.selectedSubRegion=data;
        commonService.showLoader();
        var json = angular.toJson($scope.selectedSubRegion);
        $scope.input3 = {
            "values": JSON.parse(json)
        }
        var url = "getbranches";
        var json = angular.toJson($scope.selectedCountry);
        $scope.input4 = {
            "values": JSON.parse(json)
        }
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null
        }
        reportService.callWithInput(url, input).then(function(brnchData) {
            $scope.branchList = brnchData;
            $scope.countryName = _.pluck($scope.selectedCountry, 'name').join();
            // $scope.branchName = _.pluck($scope.selectedBranch,'name').join().slice(1);
            commonService.hideLoader();
        }, function() {})
    }
    // **** Branch DrpDwn by Sub Region ****
    $scope.setBranchListBySubRegion = function(data) {
        //$scope.selectedCountry=data
        $scope.selectedSubRegion = data;
        commonService.showLoader();
        var json = angular.toJson($scope.selectedSubRegion);
        $scope.input3 = {
            "values": JSON.parse(json)
        }
        var url = "getbranches";
        var json = angular.toJson($scope.selectedCountry);
        $scope.input4 = {
            "values": JSON.parse(json)
        }
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null
        }
        reportService.callWithInput(url, input).then(function(brnchData) {
            $scope.branchList = brnchData;
            $scope.countryName = _.pluck($scope.selectedCountry, 'name').join();
            // $scope.branchName = _.pluck($scope.selectedBranch,'name').join().slice(1);
            commonService.hideLoader();
        }, function() {})
    }

    $scope.getTableHeight = function(report) {
        var rowHeight = 80; // your row height
        var headerHeight = 30; // your header height
        if (report == "report1") {
            return {
                height: "350px"
            };
        } else {
            return {
                height: "400px"
            }
        }
    };
    // **** Grid Data ****     


    $scope.gridOptions = {
        showColumnFooter: true,
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;report1&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'sourceType',
                displayName: 'Source Type',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'region',
                displayName: 'Region',
                width: 100,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'subRegion',
                displayName: 'Sub Region',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'country',
                displayName: 'Country',
                width: 80,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'branch',
                displayName: 'Branch ',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'opportunityName',
                displayName: 'Opportunity Name',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'leadSource',
                displayName: 'Lead Source',
                width: 130,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'forecastCloseDate',
                displayName: 'Forecast Closed Date (MM-DD-YYYY) ',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'stage',
                displayName: 'Stage',
                width: 100,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'probability',
                displayName: 'Probability (%)',
                width: 120,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                type: 'numberStr',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'grossMargin',
                displayName: 'Gross Margin (%)',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true
            },
            {
                field: 'accountName',
                displayName: 'Account Name',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'unfactoredAmount',
                displayName: 'Unfactored Amt. (Oppty Cur.)',
                width: 175,
                /*aggregationType: function() {
                    return $scope.totalunFactoredAmtLocalCur
                },
                aggregationLabel: "Total  Amt : ",*/
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                type: 'numberStr',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'factoredAmount',
                displayName: 'Factored Amt. (Oppty Cur.)',
                width: 175,
                headerCellClass: 'columnClass',
                /*aggregationType: function() {
                    return $scope.totalFactoredAmtLocalCur
                },
                aggregationLabel: "Total Amt  :",*/
                cellClass: 'allignNumber',
                type: 'numberStr',
                cellTooltip: true,
                headerTooltip: true
            },
            
            {
                field: 'currencyCode',
                displayName: 'Currency Code',
                width: 140,
                headerCellClass: 'columnClass ',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'salesLead',
                displayName: 'Sales Lead',
                width: 100,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'salesManager',
                displayName: 'Sales Manager',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'convertedunFactoredAmt',
                displayName: 'Unfactored Amt. (USD) ',
                width: 160,
                headerCellClass: 'columnClass ',
                cellClass: 'allignNumber',
                aggregationType: function() {
                    return $scope.totalunfactoredAmt
                },/*
                aggregationLabel: "Total  Amt : ",*/
                type: 'numberStr',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'convertedFactoredAmt',
                displayName: 'Factored Amt. (USD) ',
                width: 160,
                headerCellClass: 'columnClass ',
                aggregationType: function() {
                    return $scope.totalFactoredAmt
                },/*
                aggregationLabel: "Total Amt  :",*/
                cellClass: 'allignNumber',
                type: 'numberStr',
                cellTooltip: true,
                headerTooltip: true
            }
        ],
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 25,
        excessRows: 22,
        exporterMenuPdf: false,
        enableGridMenu: false,
        enableColumnMenus: false,
        enableSelectAll: true,
        exporterCsvFilename: "Opportunities Detail" + " " + $scope.forMat + ".csv",
        exporterPdfDefaultStyle: {
            fontSize: 9
        },
        exporterPdfTableStyle: {
            margin: [30, 30, 30, 30]
        },
        exporterPdfTableHeaderStyle: {
            fontSize: 10,
            bold: true,
            italics: true,
            color: 'red'
        },
        exporterPdfHeader: {
            text: "My Header",
            style: 'headerStyle'
        },
        exporterPdfFooter: function(currentPage, pageCount) {
            return {
                text: currentPage.toString() + ' of ' + pageCount.toString(),
                style: 'footerStyle'
            };
        },
        exporterPdfCustomFormatter: function(docDefinition) {
            docDefinition.styles.headerStyle = {
                fontSize: 22,
                bold: true
            };
            docDefinition.styles.footerStyle = {
                fontSize: 10,
                bold: true
            };
            return docDefinition;
        },
        exporterPdfOrientation: 'portrait',
        exporterPdfPageSize: 'LETTER',
        exporterPdfMaxGridWidth: 500,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
        }
    };
    // $scope.gridOptions.data = $scope.myData;
    $scope.setLob = function(data) {
        $scope.selectedLob = data;
    }
    $scope.setGridData = function(data) {
        $scope.selectedBranch = data;
    }
    $scope.gridOptions2 = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;report2&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'totalRegion',
                displayName: 'Total Regions',
                width: 130,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalSubRegion',
                displayName: 'Total Sub Regions',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalCountry',
                displayName: 'Total Countries',
                width: 130,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalBranch',
                displayName: 'Total Branches',
                width: 130,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalOpp',
                displayName: 'Total Opportunities',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalunfactoredAmount',
                displayName: 'Total Unfactored Amt. (Oppty Cur.) ',
                width: 180,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'totalFactoredAmount',
                displayName: 'Total Factored Amt. (Oppty Cur.) ',
                width: 180,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'convertedunFactoredAmt',
                displayName: 'UnFactored Amt. (USD) ',
                width: 180,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'convertedFactoredAmt',
                displayName: 'Factored Amt. (USD) ',
                width: 178,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            

        ],
        enableHorizontalScrollbar :1,
        enableVerticalScrollbar :0,
        exporterMenuPdf: false,
        excessRows: 22,
        enableGridMenu: false,
        enableSelectAll: true,
        enableColumnMenus: false,
        exporterCsvFilename: "Opportunities Summary" + " " + $scope.forMat + ".csv",
        exporterPdfDefaultStyle: {
            fontSize: 9
        },
        exporterPdfTableStyle: {
            margin: [30, 30, 30, 30]
        },
        exporterPdfTableHeaderStyle: {
            fontSize: 10,
            bold: true,
            italics: true,
            color: 'red'
        },
        exporterPdfHeader: {
            text: "My Header",
            style: 'headerStyle'
        },
        exporterPdfFooter: function(currentPage, pageCount) {
            return {
                text: currentPage.toString() + ' of ' + pageCount.toString(),
                style: 'footerStyle'
            };
        },
        exporterPdfCustomFormatter: function(docDefinition) {
            docDefinition.styles.headerStyle = {
                fontSize: 22,
                bold: true
            };
            docDefinition.styles.footerStyle = {
                fontSize: 10,
                bold: true
            };
            return docDefinition;
        },
        exporterPdfOrientation: 'portrait',
        exporterPdfPageSize: 'LETTER',
        exporterPdfMaxGridWidth: 500,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
        }
    };




    // **** Grid Display function ****   
    $scope.setDyanaminGrid = function(type) {
        if ($scope.selectedSourceType.length !== 0 && $scope.selectedRegion.length !== 0 && $scope.selectedCountry.length !== 0 && $scope.selectedBranch.length !== 0) {
            var json = angular.toJson($scope.selectedBranch);
            $scope.input5 = {
                "values": JSON.parse(json)
            }

            if (type === "summ") {
                var ele1 = angular.element(document.getElementsByClassName('grid')).hide(); //var url = "getReportGrid";            
                var url = "getsummaryReport";
                var input = {
                    "typeSources": $scope.input1.values ? $scope.input1.values : null,
                    "regions": $scope.input2.values ? $scope.input2.values : null,
                    "countries": $scope.input4.values ? $scope.input4.values : null,
                    "subregions": $scope.input3.values ? $scope.input3.values : null,
                    "branches": $scope.input5.values ? $scope.input5.values : null
                }
                commonService.showLoader();
                
                if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
                    input.typeSources = null;
                }
                
                reportService.callForGrid2(url, input).then(function(grid) {
                    commonService.hideLoader();
                    $scope.gridOptions2.data = grid;
                    var ele = angular.element(document.getElementsByClassName('grid2')).show();
                    ele.width = "100%";
                }, function() {});
            } else {
                //Detail Grid Report
                //$scope.result = "detail";
                var ele3 = angular.element(document.getElementsByClassName('grid2')).hide(); //var url = "getReportGrid";            
                var url = "getReportGrid";
                if (typeof $scope.input1.values === 'undefined') {
                    $scope.input1.values = [];
                }
                if (typeof $scope.input2.values === 'undefined') {
                    $scope.input2.values = [];
                }
                if (typeof $scope.input3.values === 'undefined') {
                    $scope.input3.values = [];
                }
                if (typeof $scope.input4.values === 'undefined') {
                    $scope.input4.values = [];
                }
                var json = angular.toJson($scope.selectedBranch);
                $scope.input5 = {
                    "values": JSON.parse(json)
                };
                if (typeof $scope.input5.values === 'undefined') {
                    $scope.input5.values = [];
                }
                var input = {
                    "typeSources": $scope.input1.values,
                    "regions": $scope.input2.values,
                    "countries": $scope.input4.values,
                    "subregions": $scope.input3.values,
                    "branches": $scope.input5.values
                }
                commonService.showLoader();
                
                if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
                    input.typeSources = null;
                }
                
                reportService.callForGrid1(url, input).then(function(grid2) {
                    commonService.hideLoader();
                    $scope.gridOptions.data = grid2.detailGrid;
                    $scope.totalFactoredAmt = grid2.totalFactoredAmt;
                    $scope.totalFactoredAmtLocalCur = grid2.totalFactoredAmtLocalCur;
                    $scope.totalunFactoredAmtLocalCur = grid2.totalunFactoredAmtLocalCur;
                    $scope.totalunfactoredAmt = grid2.totalunfactoredAmt;

                    var ele = angular.element(document.getElementsByClassName('grid')).show();
                    ele.width = "100%";
                }, function() {});
            }
        } else {
            toaster.pop('error', "Error", "Please select all mandatory fields");
        }
    }

    //**** GenerateDetailReport ****   
    $scope.GenerateDetailReport = function(data) {
        var json = angular.toJson($scope.selectedBranch);
        $scope.input5 = {
            "values": JSON.parse(json)
        };
        var url = "getsummeryDetailReport";
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null
        };
        commonService.showLoader();
        reportService.generateDetailReport(url, input, function(data) {
            toaster.pop('success', "Success", "Report Downloaded  successfully");
            commonService.hideLoader();
            var a = data;
        });
        //});
    }
    //**** Forecast Detail Report ****   
    $scope.forecastDetailReport = function(data) {
        if ($scope.selectedSourceType.length !== 0 && $scope.selectedRegion.length !== 0 && $scope.selectedCountry.length !== 0 && $scope.selectedBranch.length !== 0) {
            if ($scope.selectedBranch.length == 0 && $scope.onLoadData.role === "Branch Forecaster") {
                toaster.pop('error', "Error", "Please select branch for downloading the report");
            } else {
                var json = angular.toJson($scope.selectedBranch);
                $scope.input5 = {
                    "values": JSON.parse(json)
                };
                var json1 = angular.toJson($scope.selectedLob);
                $scope.input6 = {
                    "values": JSON.parse(json1)
                };
                var url = "getForecastDetailsreports";
                var input = {
                    "typeSources": $scope.input1.values ? $scope.input1.values : null,
                    "regions": $scope.input2.values ? $scope.input2.values : null,
                    "countries": $scope.input4.values ? $scope.input4.values : null,
                    "subregions": $scope.input3.values ? $scope.input3.values : null,
                    "branches": $scope.input5.values ? $scope.input5.values : null,
                    "lobs": $scope.input6.values ? $scope.input6.values : null,
                };
                commonService.showLoader();
                console.log(input);
                if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
                    input.typeSources = null;
                }
                console.log('actual input');
                console.log(input);
                reportService.generateDetailReport(url, input, function(data) {
                    toaster.pop('success', "Success", "Report Downloaded  Successfully");
                    commonService.hideLoader();
                    var a = data;
                });

            }
            //});
        } else {
            toaster.pop('error', "Error", "Please select all mandatory fields");
        }
    }
    //**** HQ Regional Report ****   
    $scope.regionalReport = function(data) {
        var json = angular.toJson($scope.selectedBranch);
        $scope.input5 = {
            "values": JSON.parse(json)
        };
        var json1 = angular.toJson($scope.selectedLob);
        $scope.input6 = {
            "values": JSON.parse(json1)
        };
        var url = "hqandRegionalReports";
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null,
            "lobs": $scope.input6.values ? $scope.input6.values : null,
        };
        commonService.showLoader();
        console.log(input);
        if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
            input.typeSources = null;
        }
        
        toaster.pop('info', "Info", "Please note only Region Selection will be taken in to account for this Report");
        reportService.generateDetailReport(url, input, function(data) {
            toaster.pop('success', "Success", "Report Downloaded  Successfully");
            commonService.hideLoader();
            var a = data;
        });
        //});
    };
    //**** HQ Regional Report ****   
    $scope.submissionHistory = function(data) {

        var json = angular.toJson($scope.selectedBranch);
        $scope.input5 = {
            "values": JSON.parse(json)
        };
        var json1 = angular.toJson($scope.selectedLob);
        $scope.input6 = {
            "values": JSON.parse(json1)
        };
        var url = "submissionHistoryTrackingReports";
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null,
            "lobs": $scope.input6.values ? $scope.input6.values : null,
        };
        commonService.showLoader();
        
        if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
            input.typeSources = null;
        }
        toaster.pop('info', "Info", "Please note No Dropdown  Selection will be taken in to account for this Report");
        reportService.generateDetailReport(url, input, function(data) {
            toaster.pop('success', "Success", "Report Downloaded  Successfully");
            commonService.hideLoader();
            var a = data;
        });
    };
    //**** Feild History Report ****   
    $scope.feildHistroy = function(data) {
        var json = angular.toJson($scope.selectedBranch);
        $scope.input5 = {
            "values": JSON.parse(json)
        };
        var json1 = angular.toJson($scope.selectedLob);
        $scope.input6 = {
            "values": JSON.parse(json1)
        };
        var url = "getFieldhistoryTrackingreport";
        var input = {
            "typeSources": $scope.input1.values ? $scope.input1.values : null,
            "regions": $scope.input2.values ? $scope.input2.values : null,
            "countries": $scope.input4.values ? $scope.input4.values : null,
            "subregions": $scope.input3.values ? $scope.input3.values : null,
            "branches": $scope.input5.values ? $scope.input5.values : null,
            "lobs": $scope.input6.values ? $scope.input6.values : null,
        };
        commonService.showLoader();
        console.log(input);
        if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
            input.typeSources = null;
        }
        
        reportService.generateDetailReport(url, input, function(data) {
            toaster.pop('success', "Success", "Report Downloaded  Successfully");
            commonService.hideLoader();
            var a = data;
        });
        //});
    };
    //**** Sales Forecast Report **** 
    $scope.salesForecastReport = function() {
        if ($scope.selectedSourceType.length !== 0 && $scope.selectedRegion.length !== 0 && $scope.selectedCountry.length !== 0 && $scope.selectedBranch.length !== 0) {
            var json = angular.toJson($scope.selectedBranch);
            $scope.input5 = {
                "values": JSON.parse(json)
            };
            var json1 = angular.toJson($scope.selectedLob);
            $scope.input6 = {
                "values": JSON.parse(json1)
            };
            var url = "getSalesForecastsummaryreport";
            var input = {
                "typeSources": $scope.input1.values ? $scope.input1.values : null,
                "regions": $scope.input2.values ? $scope.input2.values : null,
                "countries": $scope.input4.values ? $scope.input4.values : null,
                "subregions": $scope.input3.values ? $scope.input3.values : null,
                "branches": $scope.input5.values ? $scope.input5.values : null,
                "lobs": $scope.input6.values ? $scope.input6.values : null,
            };
            commonService.showLoader();
            if ($scope.onLoadData.role !== 'HQ/Admin Forecaster') {
                input.typeSources = null;
            }
            reportService.generateDetailReport(url, input, function(data) {
                toaster.pop('success', "Success", "Report Downloaded  Successfully");
                commonService.hideLoader();
                var a = data;
            });
        } else {
            toaster.pop('error', "Error", "Please select all mandatory fields");
        }

    }


    // **** Multiselect DropDown  ****   
    $scope.roles = [{
            "id": 1,
            "name": "Manager"
        },
        {
            "id": 2,
            "name": "Developer"
        },
        {
            "id": 3,
            "name": "Reporter"
        }
    ];
    $scope.myFun = function() {
        debugger
        return "A";
    }
    $scope.preSelected = "---Please select---";
    $scope.techCenterName = "---Please select---";
    $scope.selectedTechCenterList = [];
}]);