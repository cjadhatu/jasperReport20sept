app.controller("branchController", ['$scope', '$location', 'commonService', "$routeParams", '$filter', 'toaster', 'regionService', '$timeout', function($scope, $location, commonService, $routeParams, $filter, toaster, regionService, $timeout) {

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
    //variable for footer object
    $scope.footerObj;
    //---------Footer Variable ----------
    $scope.totalOpp = "";
    $scope.totalUnfactAmt = ""
    $scope.totalFactAmt = "";
    $scope.notStarted = "";
    $scope.inProgress = "";
    $scope.submitted = "";
    //variable for footer object
    $scope.footerObj;
    $scope.validLocation = [];
    $scope.submitInput = "";

    $scope.checkClickNavigation = function(data) {

        if (data === "Region") {
            if ($scope.regionId.constructor !== Array) {
                commonService.setRegionId($scope.regionId.region);
            } else {
                commonService.setRegionId($scope.regionId[0].region);
            }
            $location.path("/region");
        } else if (data === "Country") {
            commonService.setRegionId($scope.regionId);
            $location.path("/country");
        } else if (data === "Login") {
            $location.path("/");
        } else if (data === "Home") {
            $location.path("/newlanding");
        } else if (data === "Sub Region") {
            commonService.setRegionId($scope.regionId);
            $location.path("/subRegion");
        }
    }
    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {

        if (($scope.regionId).region === "China") {
            $scope.validLocation = [{
                    "name": "Log Out",
                    "link": "logout"
                },
                {
                    "name": "Home",
                    "link": "newlanding"
                },


            ]
            commonService.setRegionId($scope.regionId);
        } else {
            $scope.validLocation = [{
                    "name": "Log Out",
                    "link": "logout"
                },
                {
                    "name": "Home",
                    "link": "newlanding"
                }



            ]
            commonService.setRegionId($scope.regionId);
        }

    } else if ($scope.onLoadData.role === "Branch Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }];
    } else if ($scope.onLoadData.role === "Sub Region Forecaster") {
        $scope.validLocation = [{
                "name": "Log Out",
                "link": "logout"
            }, {
                "name": "Home",
                "link": "newlanding"
            }

        ];
        commonService.setRegionId($scope.regionId.region);
    } else if ($scope.onLoadData.role === "Country Forecaster") {

        $scope.validLocation = [{
                "name": "Log Out",
                "link": "logout"
            },
            {
                "name": "Home",
                "link": "newlanding"
            }

        ]
        commonService.setRegionId($scope.regionId);
    }
    $scope.filterOptions = {
        filterText: ''
    };
    $scope.searchText = "";
    $scope.gridOptions = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;branch&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'brName',
                displayName: 'Branch Geography #',
                width: 170,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                filter: {
                    term: $scope.filterOptions.filterText //DOES NOT WORK... BUT WHY
                },
                enableFiltering: true,
                enableCellEdit: false
            },

            {
                field: 'Go To Forecast',
                displayName: "Go to Forecast",
                cellTemplate: '<div class="ui-grid-cell-contents"><a  href="" ng-click="grid.appScope.redirectForecast(row.entity)"">Forecast</a></div>',
                width: 139,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'Go To LOBs',
                displayName: "Go to LOB's",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.redirectToLob(row.entity)"  >Go to LOB&#39;s</a></div>',
                width: 125,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'brFcStatus',
                displayName: 'Forecast Status',
                width: 144,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            //{ field: 'brOpps' ,displayName: 'Opportunities',width:170 ,headerCellClass: 'columnClass',cellClass :'allignNumber',cellTooltip: true, headerTooltip: true,enableCellEdit: false},

            {
                field: 'unfactoredAmt',
                displayName: 'Unfactored Amt.(USD)',
                width: 180,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'fcAmt',
                displayName: 'Factored Amt.(USD)',
                width: 150,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                type: 'numberStr',
                headerTooltip: true,
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
                displayName: 'Rolling 90 days Fact.Amt. (USD) ',
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
                width: 110,
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
        //-----Expandable Code -------
        //   expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" style="height:80px;"></div>',
        // expandableRowHeight: 80,
        // //subGridVariable will be available in subGrid scope
        // expandableRowScope: {
        //   subGridVariable: 'subGridScopeVariable'
        // },
        //------------------------------
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),

    };

    // **** Grid Call **** 

    commonService.showLoader();
    var subGrid = [];
    //variation of input on the basis of user role .
    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {
        $scope.regionId = $scope.regionId;
        if ($scope.regionId.constructor !== Array) {
            var subReg = "";
            var country = null;
            var input = [{
                "name": $scope.regionId.region,
                "id": 0
            }];
            if ($scope.regionId.subRegion && $scope.regionId.subRegion !== "NA") {
                subReg = [{
                    "name": $scope.regionId.subRegion,
                    "id": 0
                }];
                var finalInput = {
                    "regions": input,
                    "subregions": subReg
                }
            } else {
                var finalInput = {
                    "regions": input,
                    "countries": [{
                        "name": $scope.regionId.country,
                        "id": 0
                    }]
                }
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
                var country = {
                    "name": $scope.regionId[i].country,
                    "id": 0
                }
                var subRegion = {
                    "name": $scope.regionId[i].subRegion,
                    "id": 0
                }
                var branch = {
                    "name": $scope.regionId[i].brName,
                    "id": 0
                }

                regionArr.push(region);
                countryArr.push(country);
                branchArr.push(branch);
                subArr.push(subRegion);
            }
            var finalInput = {
                "regions": regionArr,
                "countries": countryArr
            }

        }
    } else if ($scope.onLoadData.role === "Country Forecaster") {
        $scope.regionId = $scope.regionId;
        if ($scope.regionId.constructor !== Array) {
            var input = [{
                "name": $scope.regionId.region,
                "id": 0
            }];
            var finalInput = {
                "regions": input,
                "countries": [{
                    "name": $scope.regionId.country,
                    "id": 0
                }],
            }
        } else {
            // if clicked on button with multiple 
            var regionArr = [];
            var countryArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                }
                var country = {
                    "name": $scope.regionId[i].country,
                    "id": 0
                }


                regionArr.push(region);
                countryArr.push(country);

            }
            var finalInput = {
                "regions": regionArr,
                "countries": countryArr,
                "subregions": [],
                "branches": []
            }

        }
    } else if ($scope.onLoadData.role === "Branch Forecaster") {
        if ($scope.regionId.constructor !== Array) {
            var input = [{
                "name": $scope.regionId,
                "id": 0
            }];
            var finalInput = {
                "regions": input,
                "countries": [{
                    "name": $scope.onLoadData.userCountry,
                    "id": 0
                }],
                "branches": [{
                    "name": $scope.onLoadData.userBranch,
                    "id": 0
                }],

            }
        } else {
            var input = [{
                "name": $scope.regionId[0].region,
                "id": 0
            }];
            var finalInput = {
                "regions": input,
                "countries": [{
                    "name": $scope.onLoadData.userCountry,
                    "id": 0
                }],
                "branches": [{
                    "name": $scope.onLoadData.userBranch,
                    "id": 0
                }],
            }
        }
    } else if ($scope.onLoadData.role === "Sub Region Forecaster") {
        $scope.regionId = $scope.regionId;
        if ($scope.regionId.constructor !== Array) {
            var input = [{
                "name": $scope.regionId.region,
                "id": 0
            }];
            var finalInput = {
                "regions": input,
                "subregions": [{
                    "name": $scope.regionId.subRegion,
                    "id": 0
                }]
            }
        } else {
            // if clicked on button with multiple 
            var regionArr = [];
            var subArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                }
                var subRegion = {
                    "name": $scope.regionId[i].subRegion,
                    "id": 0
                }


                regionArr.push(region);
                subArr.push(subRegion);
            }
            var finalInput = {
                "regions": regionArr,
                "subregions": subArr
            }
        }
    }
    
    $scope.submitInput = finalInput;
    regionService.callForGrid("getBranchDetails", finalInput).then(function(grid) {
        commonService.hideLoader();
        $scope.isSubmitClickable = grid.isSubmitClickable;
        // for(i = 0; i < grid.BranchData.length; i++){
        //       grid.BranchData[i].subGridOptions = {
        //         columnDefs: [ 
        //           {name:"UnFactored Amt.", field:"unfactoredAmt",cellClass :'allignNumber',width:150},
        //            {name:"Updated Date", field:"updatedDate",cellClass :'allignNumber',width:100},
        //             {name:"Updated By ", field:"updatedBy",width:100},

        //            ],
        //         data:  [
        //           {"unfactoredAmt":grid.regionData[i].unfactoredAmt,
        //           "updatedDate":grid.regionData[i].updatedDate,
        //           "updatedBy":grid.regionData[i].updatedBy,
        //          },

        //           ]

        //       }
        //     }
        if (grid && grid.dynamicSummaryDTO) {
            $scope.footerObj = grid.dynamicSummaryDTO;
        }

        if (grid && grid.dynamicSummaryDTO) {
            $scope.footerObj = grid.dynamicSummaryDTO;
        }

        $scope.gridData = grid.branchData;
        $scope.gridOptions.data = $scope.gridData;
        $scope.totalEstAmt = grid.totalEstAmt;
        $scope.totalFactAmt = grid.totalFactAmt;
        $scope.totalUnfactAmt = grid.totalUnfactAmt;
    });



    //----dyanamic forecast-------

    // **** On Editing Grid **** 
    $scope.gridOptions.onRegisterApi = function(gridApi) {

        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef) {
            var dataExist = _.findWhere($scope.updatedData, {
                "region": rowEntity.brName
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
                region: row.entity.brName
            });
            if (row.isSelected === true && elePresent === undefined) {
                $scope.deleteArr.push(row.entity);
            } else if (row.isSelected === false && typeof elePresent !== undefined) {
                $scope.deleteArr = _.without($scope.deleteArr, _.findWhere($scope.deleteArr, {
                    region: row.entity.region
                }));
            }
        });
    };

    //**********Redirect to lob*/
    $scope.redirectToLob = function(data) {

        data.screenName = "Branch";
        commonService.setRegionId(data);
        $location.path("/lob");

    }
    $scope.redirectToLobButton = function(data) {

        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Branch";
        }
        commonService.setRegionId($scope.deleteArr);
        $location.path("/lob");
    }
    $scope.gotoForecast = function() {
        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Branch";
        }
        var arr = $scope.deleteArr
        commonService.setRegionId(arr);
        $location.path("/forecast");
    };
    //**********Redirect to forecast link*/
    $scope.redirectForecast = function(data) {

        data.screenName = "Branch";
        commonService.setRegionId(data);
        $location.path("/forecast");

    }
    $scope.refreshData = function() {
        $scope.gridOptions.data = $filter('filterData')($scope.gridData, $scope.searchText, undefined);
    };
    $scope.getTableHeight = function() {
    	var calHeight = $(window).height() - 264;
    	return {
            height: calHeight
        }
    };

    //**********Submit to forecast */
    $scope.submitToForecast = function() {
    	if($scope.onLoadData.role !== 'Branch Forecaster'){
    		var scope = $scope;
    		if(scope.isSubmitClickable) {
        		scope.isSubmitClickable = false;
        		$timeout(function(){scope.isSubmitClickable = true;},2100);
        	}
    		toaster.pop('error', "Error", 'User other than "Branch Forecaster" can not submit data.');
    		return;
    	}
        commonService.showLoader();
        regionService.callForGrid("submitForecastOnBranch", $scope.submitInput).then(function(grid) {
            commonService.hideLoader();

            if (grid && grid.dynamicSummaryDTO) {
                $scope.footerObj = grid.dynamicSummaryDTO;
            }

            if (grid && grid.dynamicSummaryDTO) {
                $scope.footerObj = grid.dynamicSummaryDTO;
            }

            $scope.gridData = grid.branchData;
            $scope.gridOptions.data = $scope.gridData;
            $scope.totalEstAmt = grid.totalEstAmt;
            $scope.totalFactAmt = grid.totalFactAmt;
            $scope.totalUnfactAmt = grid.totalUnfactAmt;
            toaster.pop('success', "Success", "Data submitted successfully");
        });
    }



    //--------------------------------
}]);