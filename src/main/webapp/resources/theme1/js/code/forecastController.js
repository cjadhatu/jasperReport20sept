app.controller("forecastController", ['$scope', '$location', 'commonService', 'forecastService', '$filter', 'toaster', "$routeParams", "$window", "addNewService", function($scope, $location, commonService, forecastService, $filter, toaster, $routeParams, $window, addNewService) {

    $scope.onLoadData = "";
    $scope.onLoadData = commonService.getloginDetails();
    if ($scope.onLoadData === "") {
        $location.path("/");

    }
    $scope.regionlist = $scope.onLoadData.regionlist; // dyanamic input
    $scope.regionId = commonService.getRegionId();
    commonService.setAddNewToForecast($scope.regionId);
    $scope.gridData = [];
    $scope.searchText = "";
    $scope.totalEstAmt = "";
    $scope.totalUnfactAmt = "";
    $scope.totalCurMonthsUnfactAmt = "";
    $scope.totalCurMonthsfactAmt = "";
    $scope.totalFactAmt;
    $scope.updatedData = [];
    $scope.deleteArr = [];
    $scope.gridDataCopy = [];


    //variable for save changes flag
    $scope.saveChangesFlag = false;
    $scope.validLocation = [];

    if ($scope.onLoadData.role === "HQ/Admin Forecaster" || $scope.onLoadData.role === "Regional Forecaster") {
        $scope.validLocation = [{
            "name": "Log Out",
            "link": "logout"
        }, {
            "name": "Home",
            "link": "newlanding"
        }];

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

    $scope.filterOptions = {
        filterText: ''
    };
    $scope.finalInput = [];
    var rowtpl = '<div ng-class="{ \'grey\':grid.appScope.rowFormatter( row ) }">' +
        '  <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>' +
        '</div>';
    $scope.gridOptions = {
        columnDefs: [{
                cellTemplate: '<div hidden-row class="quick-view-cell ui-grid-cell-contents"><i class="fa fa-plus-circle" ng-click="showHiddenRow(row.entity,&quot;forecast&quot;,rowRenderIndex)" ></i></div>',
                field: 'quickView',
                displayName: '',
                width: 50,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'accName',
                suppressRemoveSort: false,
                displayName: 'Account Name ',
                width: 150,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false,
                enableFiltering: true,
                filter: {
                    term: $scope.filterOptions.filterText //DOES NOT WORK... BUT WHY

                }
            },
            {
                field: 'oppName',
                suppressRemoveSort: false,
                displayName: 'Opportunity Name ',
                width: 200,
                headerCellClass: 'columnClass',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
            	cellTemplate: '<div forecast-dtp ng-dblclick="dblClick(row,rowRenderIndex)" class="ui-grid-cell-contents"><span class="dtp-plan-date">{{row.entity.planDate}}</span></div>',
            	field: 'planDate',
                suppressRemoveSort: false,
                displayName: 'Close Date',
                width: 150,
                cellFilter: 'date:"MM-dd-yyyy"',
                cellTooltip: true,
                enableCellEdit: true,
                cellFilter: 'date:\'MM-dd-yyyy\'', 
                headerCellClass: 'columnClass allignNumber pencil-icon',
                cellClass: 'allignNumber',
                enableCellEdit: false,
                cellEditableCondition: function($scope) {
                    return true
                },
                cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
                    if (row.entity.isRemoved === 'Y') {
                        return 'redee';
                    }
                },
                cellTooltip: true,
                headerTooltip: true,
                type: 'date'
            },




            {
                field: 'unfactAmt',
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "").split("$")[1]) - parseFloat(propB.replace(/,/g, "").split("$")[1])
                    } else {
                        return parseFloat(propA.replace(/,/g, "").split("$")[1]) - parseFloat(propB.replace(/,/g, "").split("$")[1])
                    }
                },
                suppressRemoveSort: false,
                displayName: 'Unfactored Amt. (USD) ',
                width: 250,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellEditableCondition: function($scope) {
                    return $scope.row.entity.isRemoved === 'N';
                },
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },


            {
                field: 'factAmt',
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "").split("$")[1]) - parseFloat(propB.replace(/,/g, "").split("$")[1])
                    } else {
                        return parseFloat(propA.replace(/,/g, "").split("$")[1]) - parseFloat(propB.replace(/,/g, "").split("$")[1])
                    }
                },
                suppressRemoveSort: false,
                displayName: 'Factored Amt. (USD) ',
                width: 250,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                cellEditableCondition: function($scope) {
                    return $scope.row.entity.isRemoved === 'N';
                },
                enableCellEdit: false
            },



            {
                field: 'currency',
                suppressRemoveSort: false,
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return false
                    }
                },
                displayName: 'Currency',
                width: 100,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'unfactAmtLocalCur',
                suppressRemoveSort: false,
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    } else {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    }
                },
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return true
                    }
                },
                validators: {
                    notNull: true
                },
                displayName: 'Unfactored Amt Oppty Cur.',
                width: 230,
                cellClass: 'allignNumber',
                headerCellClass: 'columnClass allignNumber pencil-icon',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'factAmtLocalCur',
                suppressRemoveSort: false,
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    } else {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    }
                },
                cellEditableCondition: false,
                displayName: 'Factored Amt Oppty Cur.',
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return false
                    }
                },
                width: 230,
                cellClass: 'allignNumber',
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
          


            {
                field: 'margin',
                suppressRemoveSort: false,
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    } else {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    }
                },
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return false
                    }
                },
                displayName: 'Margin Pct. ',
                width: 120,
                headerCellClass: 'columnClass allignNumber',
                cellClass: 'allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },

            {
                field: 'stage',
                suppressRemoveSort: false,
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return false
                    }
                },
                displayName: 'Stage ',
                width: 150,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'probability',
                suppressRemoveSort: false,
                sortingAlgorithm: function(propA, propB, rowA, rowB, direction, col) {
                    if (direction === "asc") {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    } else {
                        return parseFloat(propA.replace(/,/g, "")) - parseFloat(propB.replace(/,/g, ""))
                    }
                },
                cellEditableCondition: function($scope) {
                    if ($scope.row.entity.isRemoved === 'Y') {
                        return false
                    } else {
                        return true
                    }
                },
                displayName: 'Probability ',
                width: 170,
                cellClass: 'allignNumber',
                headerCellClass: 'columnClass allignNumber pencil-icon',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'salesLeadRep',
                suppressRemoveSort: false,
                displayName: 'Lead Sales Rep',
                width: 150,
                enableCellEdit: false,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true
            },
            {
                field: 'branch',
                suppressRemoveSort: false,
                displayName: 'Branch ',
                width: 120,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'lob',
                suppressRemoveSort: false,
                displayName: 'LOB',
                width: 80,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'salesManager',
                suppressRemoveSort: false,
                displayName: 'Sales Manager',
                width: 120,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'leadsource',
                suppressRemoveSort: false,
                displayName: 'Lead Source',
                width: 120,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            {
                field: 'mustWinflag',
                suppressRemoveSort: false,
                displayName: 'Must Win Flag',
                width: 120,
                headerCellClass: 'columnClass allignNumber',
                cellTooltip: true,
                headerTooltip: true,
                enableCellEdit: false
            },
            //{ field: 'oppNumber' ,displayName: 'Opp. Number',width:120 ,headerCellClass: 'columnClass allignNumber',cellTooltip: true, headerTooltip: true,enableCellEdit: false},
            {
                field: 'oppNumber',
                name: 'oppNumber',
                suppressRemoveSort: false,
                cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.openItem(row.entity)">{{row.entity.oppNumber}}</a></div>',
                width: 120,
                headerCellClass: 'columnClass',
                enableCellEdit: false
            }
        ],
        exporterMenuCsv: false,
        enableGridMenu: false,
        excessRows: 22,
        rowTemplate: rowtpl,
        //expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions" style="height:80px;"></div>',
        exporterMenuPdf: false,
        enableSelectAll: true,
        showColumnFooter: true,
        enableCellEdit: true,
        enableColumnMenus: false,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location"))
    };
    // **** Grid Call **** 
    commonService.showLoader();
    if ($routeParams.regionId !== "all") {
        //check if button or link.
        $scope.regionId = $scope.regionId;
        if ($scope.regionId.constructor !== Array) { // if clicked on link 
            var input = [{
                "name": $scope.regionId.region,
                "id": 0
            }];
            $scope.finalInput = {
                "regions": input,
                "countries": [{
                    "name": $scope.regionId.country,
                    "id": 0
                }],
                "subregions": [{
                    "name": $scope.regionId.subRegion,
                    "id": 0
                }],
                "branches": [{
                    "name": $scope.regionId.brName,
                    "id": 0
                }],
                "typeSources": [{
                    "name": $scope.regionId.type,
                    "id": 0
                }]
            }
            if ($scope.regionId.screenName === "Regions") { //if user redirected from region screen

            } else if ($scope.regionId.screenName === "Country") {

            } else if ($scope.regionId.screenName === "Sub Region") {

            } else if ($scope.regionId.screenName === "Branch") {
                if ($scope.onLoadData.role === "Branch Forecaster") {

                } else {

                }
            }
        } //link end
        else { // if clicked on button with multiple  array
            var regionArr = [];
            var countryArr = [];
            var branchArr = []
            var subArr = [];
            var typeArr = [];
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
                var sourceType = {
                    "name": $scope.regionId[i].type,
                    "id": 0
                }
                regionArr.push(region);
                countryArr.push(country);
                branchArr.push(branch);
                subArr.push(subRegion);
                typeArr.push(sourceType);
            }
            $scope.finalInput = {
                "regions": regionArr,
                "countries": countryArr,
                "subregions": subArr,
                "branches": branchArr,
                "typeSources": typeArr
            };
            if ($scope.regionId[0].screenName === "Regions") {

            } else if ($scope.regionId[0].screenName === "Country") {

            } else if ($scope.regionId[0].screenName === "Sub Region") {

            } else if ($scope.regionId[0].screenName === "Branch") {

            }
        }
    } else {
        $scope.finalInput = {
            "regions": [],
            "countries": [],
            "subregions": [],
            "branches": []
        }
    }
    console.log("final input for forecast " + JSON.stringify($scope.finalInput))
    forecastService.callForGrid("getDynamicOpportunity", $scope.finalInput).then(function(grid){

        $scope.gridData = grid.oppData;
        forecastService.checkUser("getForecastLock").then(function(lock){
            $scope.isSubmitClickable = lock.isSubmitClickable;
            commonService.hideLoader();
        });
        $scope.gridOptions.data = $scope.gridData;
        $scope.totalEstAmt = grid.totalEstAmt;
        $scope.totalFactAmt = grid.totalFactAmt;
        $scope.totalUnfactAmt = grid.totalUnfactAmt;
        $scope.totalCurMonthsUnfactAmt = grid.totalCurMonthsUnfactAmt;
        $scope.totalCurMonthsfactAmt = grid.totalCurMonthsfactAmt;
        $scope.gridDataCopy = angular.copy(grid.oppData);

    },function(){});
    $scope.refreshData = function() {
        $scope.gridOptions.data = $filter('filterData')($scope.gridData, $scope.searchText, undefined);
    };
    $scope.rowFormatter = function(row) {
        return row.entity.isRemoved == 'Y';
    };
    
    $scope.getTableHeight = function() {
    	var calHeight = $(window).height() - 249;
    	return {
            height: calHeight
        }
    };

    // if(rowEntity.unfactAmtLocalCur.match(/[a-z]/i) && colDef.name ==="unfactAmtLocalCur"){
    // **** On Editing Grid **** 
    $scope.gridOptions.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        var beginCellValue;
        gridApi.edit.on.beginCellEdit($scope, function(rowEntity, colDef) {
            beginCellValue = rowEntity[colDef.field];
            if (rowEntity[colDef.field] < 0) {
                toaster.pop('error', "Error", "No negative allowed");
                rowEntity[colDef.field] *= -1;
            }
        });
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef) {

            var dataExist = _.findWhere($scope.updatedData, {
                "oppId": rowEntity.oppId
            });

            if (colDef.name === "unfactAmtLocalCur" || colDef.name === "probability") {
                if (rowEntity.unfactAmtLocalCur.match(/[a-z]/i) || rowEntity.probability.match(/[a-z]/i)) {
                    toaster.pop('error', "Error", "Please enter numbers only");
                } else {
                    if (parseFloat(rowEntity.probability) > 100) {
                        toaster.pop('error', "Error", "Please enter probability greater than 100");
                    } else {
                        if (rowEntity[colDef.field] < 0 || rowEntity[colDef.field] == '') {
                            toaster.pop('error', "Error", "No negative/Empty  allowed");
                            //rowEntity[colDef.field] *= -1;
                            rowEntity[colDef.field] = beginCellValue.toString();
                        }
                        rowEntity.unfactAmtLocalCur = rowEntity.unfactAmtLocalCur.replace(/,/g, "");
                        rowEntity.factAmtLocalCur = (parseFloat(rowEntity.probability) / 100) * parseFloat(rowEntity.unfactAmtLocalCur);
                        commonService.showLoader();
                        addNewService.getOnChange("getRate", {
                            "sourceType": rowEntity.sourceType,
                            "currency": rowEntity.currency
                        }).then(function(rate){     
                            var intFact = parseFloat(rowEntity.factAmtLocalCur) / parseFloat(rate);
                            rowEntity.factAmt = "$" + intFact.toFixed(2);
                            var intUnfact = parseFloat(rowEntity.unfactAmtLocalCur) / parseFloat(rate);
                            rowEntity.unfactAmt = "$" + intUnfact.toFixed(2);
                            commonService.hideLoader();
                        });
                    }
                }
            }
            if (dataExist) {
                dataExist = rowEntity;
            } else {
                $scope.updatedData.push(rowEntity);
                $scope.saveChangesFlag = true;
            }
            // }
            //else{
            // if(colDef.name ==="unfactAmtLocalCur"){
            //  toaster.pop('error', "Error", "Please enter numbers only");
            // return false;
            //}
            // }
        });
        // **** On Select All **** 
        gridApi.selection.on.rowSelectionChangedBatch($scope, function(rows) {
            debugger;
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
                oppId: row.entity.oppId
            });
            if (row.isSelected === true && elePresent === undefined) {
                $scope.deleteArr.push(row.entity);
            } else if (row.isSelected === false && typeof elePresent !== undefined) {
                $scope.deleteArr = _.without($scope.deleteArr, _.findWhere($scope.deleteArr, {
                    oppId: row.entity.oppId
                }));
            }
        });
    };


    // ****Save  Grid Call **** 
    $scope.saveGrid = function() {
        console.log($scope.updatedData);
        commonService.showLoader();
        var json = angular.toJson($scope.updatedData);
        var formatIn = JSON.parse(json);
        for (i in formatIn) {

            var d = new Date(formatIn[i].planDate);
            var kamDtae = d.getMonth() + 1;
            if (kamDtae >= 10) {
                m = kamDtae.toString();
            } else {
                m = "0" + kamDtae.toString();
            }
            var kamMon = d.getDate();
            if (kamMon >= 10) {
                dd = d.getDate().toString();
            } else {
                dd = "0" + kamMon.toString();
            }
            y = d.getFullYear().toString();
            formatIn[i].planDate = m + "-" + dd + "-" + y
        }
        var input = {
            "oppData": formatIn,
            "fetchJson": $scope.finalInput

        }
        forecastService.editGrid("updateOpportunity", input).then(function(grid){
            $scope.saveChangesFlag = false;
            $scope.deleteArr = [];
            $scope.updatedData = [];
            $scope.gridData = grid.oppData;
            $scope.gridOptions.data = $scope.gridData;
            $scope.totalEstAmt = grid.totalEstAmt;
            $scope.totalFactAmt = grid.totalFactAmt;
            $scope.totalUnfactAmt = grid.totalUnfactAmt;
            $scope.totalCurMonthsUnfactAmt = grid.totalCurMonthsUnfactAmt;
            $scope.totalCurMonthsfactAmt = grid.totalCurMonthsfactAmt;
            $scope.searchText = "";
            commonService.hideLoader();
            toaster.pop('success', "Success", "Data saved successfully");


        });
    }

    // ****delete  Grid Call **** 
    $scope.deleteData = function() {
        bootbox.confirm({
            title: "Delete",
            message: "Are you sure you want to delete selected data ?",
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
                    commonService.showLoader();
                    var json = angular.toJson($scope.deleteArr);
                    var input = {
                        "oppData": JSON.parse(json),
                        "fetchJson": $scope.finalInput
                    }
                    forecastService.editGrid("deleteOpportunity", input).then(function(grid){
                        $scope.deleteArr = [];
                        $scope.gridData = grid.oppData;
                        $scope.gridOptions.data = $scope.gridData;
                        $scope.totalEstAmt = grid.totalEstAmt;
                        $scope.totalFactAmt = grid.totalFactAmt;
                        $scope.totalUnfactAmt = grid.totalUnfactAmt;
                        $scope.totalCurMonthsUnfactAmt = grid.totalCurMonthsUnfactAmt;
                        $scope.totalCurMonthsfactAmt = grid.totalCurMonthsfactAmt;
                        commonService.hideLoader();
                        toaster.pop('success', "Success", "Data deleted successfully");
                    });
                }
            }
        });
    };

    $scope.goBack = function() {


        window.history.back();
    };

    //************ Refresh Data */
    $scope.refresh = function() {
        if ($scope.isSubmitClickable) {
            commonService.showLoader();
            forecastService.callForGrid("getRefresh", $scope.finalInput).then(function(grid){
                commonService.hideLoader();
                $scope.gridData = grid.oppData;
                $scope.gridOptions.data = $scope.gridData;
                $scope.totalEstAmt = grid.totalEstAmt;
                $scope.totalFactAmt = grid.totalFactAmt;
                $scope.totalUnfactAmt = grid.totalUnfactAmt;
                $scope.totalCurMonthsUnfactAmt = grid.totalCurMonthsUnfactAmt;
                $scope.totalCurMonthsfactAmt = grid.totalCurMonthsfactAmt;
                toaster.pop('success', "Success", "Data Refreshed successfully");
            });
        } else {

        }
    }

    $scope.openItem = function(data) {

        var url = data.sfdcUrl;
        if (data.isRedirect === true) {
            $window.open(url, '_blank');
        }
    }


    // ****Add New Redirection  **** 
    $scope.addNew = function() {
        commonService.setRegionId($scope.finalInput);
        $location.path("/addNewforecast");
    }
    // ****Submit Forcast  Grid Call **** 
    $scope.submitForecastGrid = function() {
        
        commonService.showLoader();
        var input = {
            "regions": $scope.finalInput.regions,
            "oppData": $scope.gridData,
            "countries": $scope.finalInput.countries,
            "branches": $scope.finalInput.branches,
            "subregions": $scope.finalInput.subregions
        };
        
        if ($scope.saveChangesFlag) {
            commonService.hideLoader();
            toaster.pop('error', "Error", "Please Save Changes First");

        } else {
            forecastService.editGrid("submitForecast", input).then(function(grid){
            	commonService.hideLoader();
            	if(grid && grid.submitAllowed === false) {
                    toaster.pop('error', "Error", "You are not allowed for submit on this level.");
                    return;
            	}
            	if(grid && grid.lowerSubmit === false) {
                    toaster.pop('error', "Error", "Lower level submission has not been done yet.");
                    return;
            	}
                if (grid && grid.isSaved) {
                    toaster.pop('success', "Success", "Forecast Successfull");
                } else {
                    toaster.pop('success', "Success", "Forecast Successfull");
                }
            });
        }
    }
    // **** RE Submit Forcast **** 
    $scope.reSubmitForecastGrid = function() {
        console.log($scope.updatedData);
        commonService.showLoader();
        var input = {
            "regions": $scope.finalInput.regions,
            "oppData": $scope.gridData,
            "countries": $scope.finalInput.countries,
            "branches": $scope.finalInput.branches,
            "subregions": $scope.finalInput.subregions
        };
        if ($scope.saveChangesFlag) {
            commonService.hideLoader();
            toaster.pop('error', "Error", "Please Save Changes First");

        } else {
            forecastService.editGrid("reSubmitForecast", input).then(function(grid){
                if (grid && grid.isSaved) {
                    commonService.hideLoader();
                    toaster.pop('success', "Success", "Data Saved Successfully");
                } else {
                    commonService.hideLoader();
                    toaster.pop('success', "Success", "Data Saved Successfully");
                }

            });
        }
    }
}]);