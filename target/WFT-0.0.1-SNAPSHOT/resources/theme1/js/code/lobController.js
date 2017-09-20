app.controller("lobController", ['$scope', '$location', 'commonService', "$routeParams", '$filter', 'toaster', 'regionService', function($scope, $location, commonService, $routeParams, $filter, toaster, regionService) {

    $scope.regionId = commonService.getRegionId();
    if ($scope.regionId.constructor == Array) {
        $scope.regionId.screenName = $scope.regionId[0].screenName
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
    $scope.fetchJson = "";
    //-------------------
    $scope.filterOptions = {
        filterText: ''
    };
    $scope.searchText = "";
    $scope.validLocation = [];

    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }]
    } else if ($scope.onLoadData.role === "Branch Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }]
    } else {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }]
    }
    $scope.gridOptions = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;lob&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'firstColumn',
                suppressRemoveSort: false,
                displayName: $scope.regionId.screenName,
                width: 245,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                filter: {
                    term: $scope.filterOptions.filterText
                },
                enableCellEdit: false
            },
            {
                field: 'lob',
                displayName: 'LOB',
                suppressRemoveSort: false,
                width: 125,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            //{ field: 'forecastStatus',suppressRemoveSort: false,displayName: 'Forecast Status',width:180 ,headerCellClass: 'columnClass',cellClass :'allignNumber',cellTooltip: true, headerTooltip: true,enableCellEdit: false},
            //  New column

            {
                field: 'unfactoredAmt.name',
                suppressRemoveSort: false,
                displayName: 'Unfactored Amt.(USD)',
                width: 195,
                type: 'numberStr',
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },


            {
                field: 'factoredAmt.name',
                suppressRemoveSort: false,
                displayName: 'Factored Amt.(USD)',
                width: 195,
                type: 'numberStr',
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            
            {
                field: 'rolling90DaysUnfactamt.name',
                displayName: 'Rolling 90 days Unfact.Amt.(USD)',
                width: 195,
                type: 'numberStr',
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },

            {
                field: 'rolling90DaysFactamt.name',
                suppressRemoveSort: false,
                displayName: 'Rolling 90 days Fact.Amt.(USD)',
                suppressRemoveSort: false,
                width: 195,
                type: 'numberStr',
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
           
            {
                field: 'updatedDate',
                suppressRemoveSort: false,
                displayName: 'Updated Date',
                width: 140,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'updatedBy',
                suppressRemoveSort: false,
                displayName: 'Updated By',
                width: 133,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },

        ],
        rowHeight: 30,
        excessRows: 22,
        enableHorizontalScrollbar:1,
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
    $scope.regionId = $scope.regionId;
    if ($scope.regionId.constructor !== Array) { // if clicked on link 
        if ($scope.regionId.screenName === "Regions") { //if user redirected from region screen
            var input = {
                "regions": [{
                    "name": $scope.regionId.region,
                    "id": 0
                }]
            };
            var url = "getLobRegionDetails";

            if (commonService.getIsAll() === "all") {
                commonService.setRegionId("all");
            } else {
                commonService.setRegionId($scope.regionId.region);
            }
        } else if ($scope.regionId.screenName === "Country") {
            var input = {
                "regions": [{
                    "name": $scope.regionId.region,
                    "id": 0
                }],
                "countries": [{
                    "name": $scope.regionId.country,
                    "id": 0
                }]
            };
            var url = "getLobCountryDetails";

            commonService.setRegionId($scope.regionId);
        } else if ($scope.regionId.screenName === "Sub Region") {
            var input = {
                "regions": [{
                    "name": $scope.regionId.region,
                    "id": 0
                }],
                "subregions": [{
                    "name": $scope.regionId.subRegion,
                    "id": 0
                }]
            };
            var url = "getLobSubRegionDetails";

            commonService.setRegionId($scope.regionId);
        } else if ($scope.regionId.screenName === "Branch") {
            var input = {
                "regions": [{
                    "name": $scope.regionId.region,
                    "id": 0
                }],
                "countries": [{
                    "name": $scope.regionId.country,
                    "id": 0
                }],
                "branches": [{
                    "name": $scope.regionId.brName,
                    "id": 0
                }],
            };
            var url = "getLobBranchDetails";
            if ($scope.onLoadData.role === "Branch Forecaster") {

                commonService.setRegionId($scope.regionId.region);
            } else {

                commonService.setRegionId($scope.regionId);

            }
        }

    } else {
        if ($scope.regionId[0].screenName === "Regions") {
            // if clicked on button with multiple 
            var regionArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                }
                regionArr.push(region);
            }
            var input = {
                "regions": regionArr
            }
            var url = "getLobRegionDetails";

            commonService.setRegionId("all")
        } else if ($scope.regionId[0].screenName === "Country") {
            var regionArr = [];
            var countryArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                };
                var country = {
                    "name": $scope.regionId[i].country,
                    "id": 0
                };
                regionArr.push(region);
                countryArr.push(country);
            }
            var input = {
                "regions": regionArr,
                "countries": countryArr
            }
            var url = "getLobCountryDetails";

        } else if ($scope.regionId[0].screenName === "Sub Region") {
            var regionArr = [];
            var subArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                };
                var sub = {
                    "name": $scope.regionId[i].subRegion,
                    "id": 0
                };
                regionArr.push(region);
                subArr.push(sub);
            }
            var input = {
                "regions": regionArr,
                "subregions": subArr
            }
            var url = "getLobSubRegionDetails";

        } else if ($scope.regionId[0].screenName === "Branch") {
            var regionArr = [];
            var countryArr = [];
            var branchArr = [];
            for (var i in $scope.regionId) {
                var region = {
                    "name": $scope.regionId[i].region,
                    "id": 0
                };
                var country = {
                    "name": $scope.regionId[i].country,
                    "id": 0
                };
                var branch = {
                    "name": $scope.regionId[i].brName,
                    "id": 0
                };
                regionArr.push(region);
                countryArr.push(country);
                branchArr.push(branch)
            }
            var input = {
                "regions": regionArr,
                "countries": countryArr,
                "branches": branchArr
            }
            var url = "getLobBranchDetails";

        }


    }


    //************  GRID CALL ****************** 
    $scope.submitInput = input;
    console.log("fetch region forecaster----" + JSON.stringify(input))
    regionService.callForGrid(url, input).then(function(grid) {
        commonService.hideLoader();


        $scope.fetchJson = grid;
        for (i in grid.lobData) {

            grid.lobData[i].factoredAmt = {
                "id": parseFloat(grid.lobData[i].factoredAmt.replace(/,/g, "").split("$")[1]),
                "name": grid.lobData[i].factoredAmt
            }
            grid.lobData[i].unfactoredAmt = {
                "id": parseFloat(grid.lobData[i].unfactoredAmt.replace(/,/g, "").split("$")[1]),
                "name": grid.lobData[i].unfactoredAmt
            }
            if (grid.lobData[i].rolling90DaysFactamt !== null) {
                grid.lobData[i].rolling90DaysFactamt = {
                    "id": parseFloat(grid.lobData[i].rolling90DaysFactamt.replace(/,/g, "").split("$")[1]),
                    "name": grid.lobData[i].rolling90DaysFactamt
                }
            } else {
                grid.lobData[i].rolling90DaysFactamt = {
                    "id": "",
                    "name": ""
                }
            }
            if (grid.lobData[i].rolling90DaysUnfactamt !== null) {
                grid.lobData[i].rolling90DaysUnfactamt = {
                    "id": parseFloat(grid.lobData[i].rolling90DaysUnfactamt.replace(/,/g, "").split("$")[1]),
                    "name": grid.lobData[i].rolling90DaysUnfactamt
                }
            } else {
                grid.lobData[i].rolling90DaysUnfactamt = {
                    "id": "",
                    "name": ""
                }
            }

        }
        $scope.gridData = grid.lobData;
        $scope.gridOptions.data = $scope.gridData;
        $scope.totalEstAmt = grid.totalEstAmt;
        $scope.totalFactAmt = grid.totalFactAmt;
        $scope.totalUnfactAmt = grid.totalUnfactAmt;
    });

    $scope.getTableHeight = function() {
    	var calHeight = $(window).height() - 246;
    	return {
            height: calHeight
        }
    };
    //----dyanamic forecast-------
    $scope.refreshData = function() {
        $scope.gridOptions.data = $filter('filterData')($scope.gridData, $scope.searchText, undefined);
    };
    // **** On Editing Grid **** 
    $scope.gridOptions.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef) {
            var dataExist = _.findWhere($scope.updatedData, {
                "region": rowEntity.region
            });
            if (dataExist) {
                dataExist = data.rowEntity;
            } else {
                $scope.updatedData.push(rowEntity);
            }
        });
        // **** On Select All **** 
        gridApi.selection.on.rowSelectionChangedBatch($scope, function(rows) {

            if (rows[0].isSelected === true)
                $scope.deleteArr.push(rows);
            else {
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
    };
    $scope.pagelink = function(row) {
        if (row.region == "China") {
            $location.path("/subRegion/" + row);
        } else {
            $location.path("/country/" + row);
        }

    }
    $scope.gotoForecast = function() {

        var arr = $scope.deleteArr
        $location.path("/forecast/" + arr);
    }
    $scope.gotoCountries = function() {
        var arr = $scope.deleteArr
        if ($scope.deleteArr[0].region == "China") {
            $location.path("/subRegion/" + arr);
        } else {
            $location.path("/country/" + arr);
        }
    }

    $scope.ShowLeaveInfo = function() {
        commonService.showLoader();
        regionService.lobPopUp("getLOBDefination", $scope.submitInput).then(function(popUp) {
            commonService.hideLoader();
            $scope.gridOptions2.data = popUp.definationVOs;

        });
        $('#LeaveDetials').modal('show');
    }
    //**********Submit to forecast */
    $scope.submitToForecast = function() {
        commonService.showLoader();
        regionService.callForGrid("submitForecastOnRegion", $scope.submitInput).then(function(grid) {
            commonService.hideLoader();

            if (grid && grid.dynamicSummaryDTO) {
                $scope.footerObj = grid.dynamicSummaryDTO;
            }
            for (i = 0; i < grid.regionData.length; i++) {
                grid.regionData[i].subGridOptions = {
                    columnDefs: [{
                            name: "UnFactored Amt.",
                            field: "unfactoredAmt",
                            cellClass: 'allignNumber',
                            width: 150
                        },
                        {
                            name: "Updated Date",
                            field: "updatedDate",
                            cellClass: 'allignNumber',
                            width: 100
                        },
                        {
                            name: "Updated By ",
                            field: "updatedBy",
                            width: 100
                        },

                    ],
                    data: [{
                            "unfactoredAmt": grid.regionData[i].unfactoredAmt,
                            "updatedDate": grid.regionData[i].updatedDate,
                            "updatedBy": grid.regionData[i].updatedBy,
                        },

                    ]

                }
                delete grid.regionData[i].country;
            }
            $scope.gridData = grid.regionData;
            $scope.gridOptions.data = $scope.gridData;
            $scope.totalEstAmt = grid.totalEstAmt;
            $scope.totalFactAmt = grid.totalFactAmt;
            $scope.totalUnfactAmt = grid.totalUnfactAmt;
        });
        toaster.pop('success', "success", "Data submitted successfully");
    }
    //pop up
    $scope.gridOptions2 = {
        columnDefs: [

            {
                field: 'lobName',
                displayName: 'LOB',
                width: 180,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'sublobName',
                displayName: 'SUB LOB',
                width: 200,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'forecastLobname',
                displayName: 'FORECAST LOB',
                width: 250,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },

        ],
        rowHeight: 30,
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

}]);