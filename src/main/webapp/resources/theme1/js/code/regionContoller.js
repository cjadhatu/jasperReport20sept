app.controller("regionContoller", ['$scope', '$location', 'commonService', "$routeParams", '$filter', 'toaster', 'regionService', '$timeout', function($scope, $location, commonService, $routeParams, $filter, toaster, regionService, $timeout) {
     
    if ($routeParams.regionId) {
        $scope.regionId = $routeParams.regionId;
        delete $location.$$search.regionId;
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
    //-------------------
    $scope.filterOptions = {
        filterText: ''
    };
    $scope.searchText = "";
    $scope.validLocation = [];
    $scope.findChina = ['China']

    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }]
    } else if ($scope.onLoadData.role === "Branch Forecaster") {

    }
    /*
    $scope.showQuickView = function(entity) {
    	$('.quick-view-modal').modal();
    }*/
    $('.quick-view-modal').on('shown.bs.modal', function() {
        $(this).find('.modal-dialog').css('margin-left', ($('.quick-view-modal').width() - $(this).find('.modal-dialog').width()) / 2);
    });
    $scope.gridOptions = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;region&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'region',
                displayName: 'Regions',
                width: 110,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                filter: {
                    term: $scope.filterOptions.filterText
                },
                enableCellEdit: false
            },
            {
                field: 'Go To Forecast',
                displayName: "Go to Forecast",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.redirectForecast(row.entity)"">Forecast</a></div>',
                width: 139,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            },
            {
                field: 'Go To Countries',
                displayName: "Go to Country/Sub region",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.pagelink(row.entity)">{{row.entity.region == "China" ? "Go to Sub regions" : "Go to Countries"}}</a></div>',
                width: 144,
                headerCellClass: 'columnClass',
                enableCellEdit: false,
                cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) 
                {
                  var cellValue = grid.getCellValue(row, col);
                  
                }
                
            },
            {
                field: 'Go To LOBs',
                displayName: "Go to LOB's",
                cellTemplate: '<div class="ui-grid-cell-contents"><a href="" ng-click="grid.appScope.redirectToLob(row.entity)" >Go to LOB&#39;s</a></div>',
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
                width: 180,
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
                displayName: 'Rolling 90 days Unfact.Amt.(USD)',
                width: 155,
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
                width: 110,
                headerCellClass: 'columnClass',
                cellClass: 'allignNumber',
                type: 'numberStr',
                cellTooltip: true,
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
        enableHorizontalScrollbar: 1,
        enableGridMenu: false,
        //showTreeExpandNoChildren: true,
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
    if ($scope.regionId === "all") {
        var finalInput = {
            "regions": []
        };
        commonService.setIsAll("all");
    } else {
        var input = [{
            "name": $scope.regionId,
            "id": 0
        }];
        var finalInput = {
            "regions": input
        }

    }

    //************  GRID CALL ****************** 
    $scope.submitInput = finalInput;
    console.log("fetch region forecaster----" + JSON.stringify(finalInput))
    regionService.callForGrid("getRegionDetails", finalInput).then(function(grid) {
        commonService.hideLoader();
        $scope.isSubmitClickable = grid.isSubmitClickable;

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
            grid.regionData[i].$$treeLevel = 0;
            delete grid.regionData[i].country;
        }
        $scope.gridData = grid.regionData;
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
    };
    //**********Check for china*/
    $scope.pagelink = function(row) {
        var rowData = row
        if (row.region == "China") {
            commonService.setRegionId(row);
            $location.path("/subRegion");
            
        } else {
            commonService.setRegionId(row);
            $location.path("/country");
        }
    }

    //**********Redirect To Country*/
    $scope.gotoCountries = function() {
        var arr = $scope.deleteArr;
        if ($scope.deleteArr[0].region == "China" && $scope.deleteArr.length === 1) {
            commonService.setRegionId($scope.deleteArr);
            $location.path("/subRegion");
        } else {
            commonService.setRegionId(arr);
            $location.path("/country");
        }
    }
    //**********Redirect to forecast link*/
    $scope.redirectForecast = function(data) {
         
        data.screenName = "Regions";
        commonService.setRegionId(data);
        $location.path("/forecast");
    }
    //**********Redirect To Forecast button */
    $scope.gotoForecast = function() {
        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Regions";
        }
        var arr = $scope.deleteArr;
        commonService.setRegionId(arr);
        $location.path("/forecast");
    }
    //**********Redirect to lob*/
    $scope.redirectToLob = function(data) {
         
        data.screenName = "Regions";
        commonService.setRegionId(data);
        $location.path("/lob");

    }
    $scope.redirectToLobButton = function(data) { // ******lob link ******
         
        for (var i in $scope.deleteArr) {
            $scope.deleteArr[i].screenName = "Regions";
        }
        commonService.setRegionId($scope.deleteArr);
        $location.path("/lob");

    }
    //**********Submit to forecast */
    $scope.submitToForecast = function() {
    	if($scope.onLoadData.role !== 'Regional Forecaster'){
    		var scope = $scope;
    		if(scope.isSubmitClickable) {
        		scope.isSubmitClickable = false;
        		$timeout(function(){scope.isSubmitClickable = true;},2100);
        	}
    		toaster.pop('error', "Error", 'User other than "Regional Forecaster" can not submit data.');
    		return;
    	}
        commonService.showLoader();
        regionService.callForGrid("submitForecastOnRegion", $scope.submitInput).then(function(grid) {
            commonService.hideLoader();
            if(grid && grid.submitAllowed === false) {
        		toaster.pop('error', "Error", 'Submit not allowed as lower level data not submitted');
        		return;
        	}
            toaster.pop('success', "Success", "Data submitted successfully");
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

    }

}]);