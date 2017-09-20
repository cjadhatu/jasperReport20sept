app.controller("subRegionController", ['$scope', '$location', 'commonService', "$routeParams", '$filter', 'toaster', 'subRegionService', '$timeout', function($scope, $location, commonService, $routeParams, $filter, toaster, subRegionService, $timeout) {


    if ($routeParams.regionId) {
        $scope.regionId = $routeParams.regionId;
    } else {
        $scope.regionId = commonService.getRegionId();
    }
    $scope.onLoadData = "";
    $scope.onLoadData = commonService.getloginDetails();
    $scope.regionlist = $scope.onLoadData.regionlist;
    $scope.gridData = [];
    $scope.deleteArr = [];
    //---------Footer Variable ----------
    $scope.totalOpp = "";
    $scope.totalUnfactAmt = ""
    $scope.totalFactAmt = "";
    $scope.notStarted = "";
    $scope.inProgress = "";
    $scope.submitted = "";
    $scope.submitInput = "";
    $scope.validLocation = [];
    $scope.validLocation = [{
        "name": "Log Out",
        "link": "logout"
    }, {
        "name": "Home",
        "link": "newlanding"
    }]
    //-----------------------------------
    //variable for footer object
    $scope.footerObj;
    $scope.filterOptions = {
        filterText: ''
    };
    $scope.searchText = "";
    $scope.gridOptions = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;subregion&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'subRegion',
                displayName: ' Sub Regions',
                width: 180,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                filter: {
                    term: $scope.filterOptions.filterText //DOES NOT WORK... BUT WHY
                },
                enableFiltering: true
            },

            {
                field: 'Go To Forecast',
                displayName: "Go to Forecast",
                cellTemplate: '<div class="ui-grid-cell-contents"><a   href="" ng-click="grid.appScope.redirectForecast(row.entity)"">Forecast</a></div>',
                width: 139,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'Go To Branches',
                displayName: "Go to Branches",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.gotoBranchesLink(row.entity)"">Go to Branches </a></div>',
                width: 144,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'Go To LOBs',
                displayName: "Go to LOB's",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.redirectToLob(row.entity)">Go to LOB&#39;s</a></div>',
                width: 125,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'forecastStatus',
                displayName: 'Forecast Status',
                width: 144,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'branchSum',
                displayName: 'Branches',
                width: 100,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                type: 'numberStr',
                enableCellEdit: false
            },
            {
                field: 'opportunitiesSum',
                displayName: 'Opportunities',
                width: 130,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'unfactoredAmt',
                displayName: 'Unfactored Amt.(USD)',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
            
            {
                field: 'factoredAmt',
                displayName: 'Factored Amt.(USD)',
                width: 180,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                type: 'numberStr',
                enableCellEdit: false
            },
            
            //  New column
            
            {
                field: 'rollingUnfact',
                displayName: 'Rolling 90 days Unfact.Amt. (USD)',
                width: 160,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'rollingFact',
                displayName: 'Rolling 90 days Fact.Amt. (USD)',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
          
            {
                field: 'updatedDate',
                displayName: 'Updated Date',
                width: 140,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'updatedBy',
                displayName: 'Updated By',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },


        ],
        rowHeight: 30,
        excessRows: 22,
        exporterMenuPdf: false,
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

    // **** Grid Call **** 

    commonService.showLoader();
    var subGrid = [];
    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {

        $scope.regionId = $scope.regionId;


        if ($scope.regionId.constructor !== Array) { //if link is clicked 
            var input = [{
                "name": $scope.regionId.region,
                "id": 0
            }];
            var finalInput = {
                "regions": input,
                "subRegions": [],
                "countries": [],
            }
            if (commonService.getIsAll() === "all") {
                commonService.setRegionId("all");
            } else {
                commonService.setRegionId($scope.regionId.region);
            }
        } else {
            // if clicked on button with multiple 
            var regionArr = [];
            var countryArr = [];
            var branchArr = []
            var subArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                }

                regionArr.push(region);
            }
            var finalInput = {
                "regions": regionArr,
                "subRegions": [],
                "countries": [],
            };
            if (commonService.getIsAll() === "all") {
                commonService.setRegionId("all");
            } else {
                commonService.setRegionId($scope.regionId[0].region);
            }

        }
    } else if ($scope.onLoadData.role === "Country Forecaster") {
        $scope.regionId = $scope.regionId;
        var input = [{
            "name": $scope.regionId,
            "id": 0
        }];
        var finalInput = {
            "regions": input,
            "subRegions": [],
            "countries": [],
        }
    } else if ($scope.onLoadData.role === "Sub Region Forecaster") {
        $scope.regionId = $scope.regionId;
        var input = [{
            "name": $scope.onLoadData.userRegion,
            "id": 0
        }];
        var finalInput = {
            "regions": input,
            "subRegions": [{
                "name": $scope.onLoadData.userSubRegion
            }],
            "countries": [{
                "name": $scope.onLoadData.userCountry,
                "id": 0
            }],
        }
    }

    $scope.submitInput = finalInput;
    subRegionService.callForGrid("getSubRegionDetails", finalInput).then(function(grid) {
        if (grid && grid.dynamicSummaryDTO) {
            $scope.footerObj = grid.dynamicSummaryDTO;
        }
        $scope.isSubmitClickable = grid.isSubmitClickable;
        commonService.hideLoader();
        $scope.gridData = grid.subRegionData;
        $scope.gridOptions.data = $scope.gridData;
        $scope.totalEstAmt = grid.totalEstAmt;
        $scope.totalFactAmt = grid.totalFactAmt;
        $scope.totalUnfactAmt = grid.totalUnfactAmt;
    });

    $scope.getTableHeight = function() {
    	var calHeight = $(window).height() - 264;
    	return {
            height: calHeight
        }
    };
    //----dyanamic forecast-------
    // **** On Editing Grid **** 
    $scope.gridOptions.onRegisterApi = function(gridApi) {
        // 
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef) {
            var dataExist = _.findWhere($scope.updatedData, {
                "country": rowEntity.country
            });
            if (dataExist) {
                dataExist = data.rowEntity;
            } else {
                $scope.updatedData.push(rowEntity);
            }
        });
        // **** On Select All **** 
        gridApi.selection.on.rowSelectionChangedBatch($scope, function(rows) {

            if (rows[0].isSelected === true) {
                for (i in rows) {
                    $scope.deleteArr.push(rows[i].entity)
                }
            } else {
                $scope.deleteArr = [];
            }
        });


        // **** On Row Selection **** 
        gridApi.selection.on.rowSelectionChanged($scope, function(row) {

            var elePresent = _.findWhere($scope.deleteArr, {
                region: row.entity.region
            });
            if (row.isSelected === true && elePresent === undefined) {
                $scope.deleteArr.push(row.entity);
            } else if (row.isSelected === false && typeof elePresent !== undefined) {
                $scope.deleteArr = _.without($scope.deleteArr, _.findWhere($scope.deleteArr, {
                    region: row.entity.region
                }));
            }
        });
    }
    $scope.refreshData = function() {
        $scope.gridOptions.data = $filter('filterData')($scope.gridData, $scope.searchText, undefined);
    };
    // **** Go to forecast  **** 
    $scope.gotoForecast = function() {
        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Sub Region";
        }
        commonService.setRegionId($scope.deleteArr);
        $location.path("/forecast");
    }
    //**********Redirect to forecast link*/
    $scope.redirectForecast = function(data) {

        data.screenName = "Sub Region";
        commonService.setRegionId(data);
        $location.path("/forecast");

    }
    //**********Redirect to lob*/
    $scope.redirectToLob = function(data) {

        data.screenName = "Sub Region";
        commonService.setRegionId(data);
        $location.path("/lob");

    }
    $scope.redirectToLobButton = function(data) {

        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Sub Region";
        }
        commonService.setRegionId($scope.deleteArr);
        $location.path("/lob");

    }

    // **** Go to branches **** 
    $scope.gotoBranches = function() {
        var arr = $scope.deleteArr
        commonService.setRegionId(arr);
        $location.path("/branch");
    }
    // **** Go to branches link **** 
    $scope.gotoBranchesLink = function(data) {

        commonService.setRegionId(data);
        $location.path("/branch");
    };
    //**********Submit to forecast */
    $scope.submitToForecast = function() {
    	if($scope.onLoadData.role !== 'Sub Region Forecaster'){
    		var scope = $scope;
    		if(scope.isSubmitClickable) {
        		scope.isSubmitClickable = false;
        		$timeout(function(){scope.isSubmitClickable = true;},2100);
        	}
    		toaster.pop('error', "Error", 'User other than "Regional Forecaster" can not submit data.');
    		return;
    	}
        commonService.showLoader();
        subRegionService.callForGrid("submitForecastOnSubRegion", $scope.submitInput).then(function(grid) {
        	commonService.hideLoader();
        	if(grid && grid.submitAllowed === false) {
        		toaster.pop('error', "Error", 'Submit not allowed as lower level data not submitted');
        		return;
        	}
            if (grid && grid.dynamicSummaryDTO) {
                $scope.footerObj = grid.dynamicSummaryDTO;
            }
            
            $scope.gridData = grid.subRegionData;
            $scope.gridOptions.data = $scope.gridData;
            $scope.totalEstAmt = grid.totalEstAmt;
            $scope.totalFactAmt = grid.totalFactAmt;
            $scope.totalUnfactAmt = grid.totalUnfactAmt;
            toaster.pop('success', "Success", "Data submitted successfully");
        });
    }


}]);