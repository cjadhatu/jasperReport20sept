/*------------------------------------------------------------------------------------- 
(C) 2013-2017 Johnson Controls International Plc. 
All rights reserved. This software constitutes the trade secrets and confidential  
and proprietary information of Johnson Controls International Plc. It is intended  
solely for use by Johnson Controls International Plc. This code may not be copied  
or redistributed to third parties without prior written authorization from  
Johnson Controls International Plc. 
-------------------------------------------------------------------------------------*/ 

(function (module) {
    'use strict';
   
    var hiddenRowDirective = function (commonService) {

        var hiddenRowClass = function (scope,element,attrs) {        	
            this.addHandler = function(){
            	$('div.ui-grid-viewport').on('scroll', function() {
            		if($('.fake-child-row').length > 0) {
            			if(($('.fake-child-row').offset().top + $('.fake-child-row').height()) < $('.ui-grid-viewport').offset().top ||
            					$('.fake-child-row').offset().top > ($('.ui-grid-viewport').offset().top + $('.ui-grid-viewport').offset().top) ){
            				element.find('.fa').removeClass('fa-minus-circle').addClass('fa-plus-circle');
                    		$('.fake-child-row, .fake-row-padding').remove();
                    		$('.quick-view-cell .fa.fa-minus-circle').addClass('fa-plus-circle').removeClass('fa-minus-circle');
            			}
            		}
            	});
            }
            scope.showHiddenRow = function(entity,ctrl,index) {
            	var entityObj;
            	$('.fake-child-row, .fake-row-padding').remove();
            	var regID = commonService.getRegionId();
            	if(element.find('.fa').hasClass('fa-plus-circle')){
            		$('.quick-view-cell .fa.fa-minus-circle').addClass('fa-plus-circle').removeClass('fa-minus-circle');
            		if(ctrl == 'region') {
            			entityObj = [
                    		{'name': 'Region', 'value': entity.region},
                    		{'name': 'Forecast Status', 'value': entity.forecastStatus},
                    		{'name': 'Branches', 'value': entity.branchSum},
                    		{'name': 'Opportunities', 'value': entity.opportunitiesSum},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.factoredAmt},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactoredAmt},
                    		{'name': 'Rolling 90 days Fact.Amt. (USD)', 'value': entity.rollingFact},
                    		{'name': 'Rolling 90 days Unfact.Amt. (USD)', 'value': entity.rollingUnfact},
                    		{'name': 'Updated Date', 'value': entity.updatedDate},
                    		{'name': 'Updated By', 'value': entity.updatedBy}
                    	];
            		} else if (ctrl == 'branch') {
            			entityObj = [
                			{'name': 'Branch Geography', 'value': entity.brName},
                    		{'name': 'Forecast Status', 'value': entity.brFcStatus},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.fcAmt},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactoredAmt},
                    		{'name': 'Rolling 90 days Fact.Amt. (USD)', 'value': entity.rollingFact},
                    		{'name': 'Rolling 90 days Unfact.Amt. (USD)', 'value': entity.rollingUnfact},
                    		{'name': 'Updated Date', 'value': entity.updatedDate},
                    		{'name': 'Updated By', 'value': entity.updatedBy}
                    		];
            		} else if (ctrl == 'country') {
            			entityObj = [
            			{'name': 'Country', 'value': entity.country},
                		{'name': 'Forecast Status', 'value': entity.forecastStatus},
                		{'name': 'Branches', 'value': entity.branchSum},
                		{'name': 'Opportunities', 'value': entity.opportunitiesSum},
                		{'name': 'Factored Amt.(USD)', 'value': entity.factoredAmt},
                		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactoredAmt},
                		{'name': 'Rolling 90 days Fact.Amt. (USD)', 'value': entity.rollingFact},
                		{'name': 'Rolling 90 days Unfact.Amt. (USD)', 'value': entity.rollingUnfact},
                		{'name': 'Updated Date', 'value': entity.updatedDate},
                		{'name': 'Updated By', 'value': entity.updatedBy}
                		];
            		} else if (ctrl == 'subregion') {
            			entityObj = [
                			{'name': 'Sub Region', 'value': entity.subRegion},
                    		{'name': 'Forecast Status', 'value': entity.forecastStatus},
                    		{'name': 'Branches', 'value': entity.branchSum},
                    		{'name': 'Opportunities', 'value': entity.opportunitiesSum},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.factoredAmt},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactoredAmt},
                    		{'name': 'Rolling 90 days Fact.Amt. (USD)', 'value': entity.rollingFact},
                    		{'name': 'Rolling 90 days Unfact.Amt. (USD)', 'value': entity.rollingUnfact},
                    		{'name': 'Updated Date', 'value': entity.updatedDate},
                    		{'name': 'Updated By', 'value': entity.updatedBy}
                    		];
            		} else if (ctrl == 'lob') {
            			entityObj = [
                			{'name': 'Sub Region', 'value': entity.firstColumn},
                    		{'name': 'LOB', 'value': entity.lob},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.factoredAmt.name},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactoredAmt.name},
                    		{'name': 'Rolling 90 days Fact.Amt. (USD)', 'value': entity.rolling90DaysFactamt.name},
                    		{'name': 'Rolling 90 days Unfact.Amt. (USD)', 'value': entity.rolling90DaysUnfactamt.name},
                    		{'name': 'Updated Date', 'value': entity.updatedDate},
                    		{'name': 'Updated By', 'value': entity.updatedBy}
                    		];
            			var firstCol = $($('div[role=columnheader]')[1]).find('.ui-grid-header-cell-label').text();
            			entityObj[0] = {'name': firstCol, 'value': entity.firstColumn};
            		} else if (ctrl == 'forecast') {
            			entityObj = [
                			{'name': 'Account Name', 'value': entity.accName},
                    		{'name': 'Opportunity Name', 'value': entity.oppName},
                    		{'name': 'Close Date', 'value': entity.planDate},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.factAmt},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.unfactAmt},
                    		{'name': 'Currency', 'value': entity.currency},
                    		{'name': 'Factored Amt Local Currency', 'value': entity.factAmtLocalCur},
                    		{'name': 'Unfactored Amt Local Currency', 'value': entity.unfactAmtLocalCur},
                    		{'name': 'Margin Pct', 'value': entity.margin},
                    		{'name': 'Stage', 'value': entity.stage},
                    		{'name': 'Probability', 'value': entity.probability},
                    		{'name': 'Lead Sales Rep', 'value': entity.salesLeadRep},
                    		{'name': 'Branch', 'value': entity.branch},
                    		{'name': 'LOB', 'value': entity.lob},
                    		{'name': 'Sales Manager', 'value': entity.salesManager},
                    		{'name': 'Lead Source', 'value': entity.leadsource},
                    		{'name': 'Must Win Flag', 'value': entity.mustWinflag},
                    		{'name': 'Opp. Number', 'value': entity.oppNumber}
                    		];
            		} else if (ctrl == 'report2') {
            			entityObj = [
                			{'name': 'Total Regions', 'value': entity.totalRegion},
                    		{'name': 'Total Sub Regions', 'value': entity.totalSubRegion},
                    		{'name': 'Total Countries', 'value': entity.totalCountry},
                    		{'name': 'Total Branches', 'value': entity.totalBranch},
                    		{'name': 'Total Opportunities', 'value': entity.totalOpp},
                    		{'name': 'Total Unfactored Amt Local Currency', 'value': entity.totalunfactoredAmount},
                    		{'name': 'Total Factored Amt Local Currency', 'value': entity.totalFactoredAmount},
                    		{'name': 'Total Factored Amt.(USD)', 'value': entity.convertedFactoredAmt},
                    		{'name': 'Total Unfactored Amt.(USD)', 'value': entity.convertedunFactoredAmt}
                    		];
            		} else if (ctrl == 'report1') {
            			entityObj = [
                			{'name': 'Source Type', 'value': entity.sourceType},
                    		{'name': 'Region', 'value': entity.region},
                    		{'name': 'Sub Region', 'value': entity.subRegion},
                    		{'name': 'Country', 'value': entity.country},
                    		{'name': 'Branch', 'value': entity.branch},
                    		{'name': 'Opportunity Name', 'value': entity.opportunityName},
                    		{'name': 'Lead Source', 'value': entity.leadSource},
                    		{'name': 'Forecast Close Date', 'value': entity.forecastCloseDate},
                    		{'name': 'Stage', 'value': entity.stage},
                    		{'name': 'Probability', 'value': entity.probability},
                    		{'name': 'Gross Margin', 'value': entity.grossMargin},
                    		{'name': 'Account Name', 'value': entity.accountName},
                    		{'name': 'Factored Amt Local Currency', 'value': entity.factoredAmount},
                    		{'name': 'Unfactored Amt Local Currency', 'value': entity.unfactoredAmount},
                    		{'name': 'Currency', 'value': entity.currencyCode},
                    		{'name': 'Sales Lead', 'value': entity.salesLead},
                    		{'name': 'Sales Manager', 'value': entity.salesManager},
                    		{'name': 'Factored Amt.(USD)', 'value': entity.convertedFactoredAmt},
                    		{'name': 'Unfactored Amt.(USD)', 'value': entity.convertedunFactoredAmt}
                    		];
            		}
            		
            		
                	var childRow = $('<div class="fake-child-row"></div>'), content ='';
                	for(var i=0; i < entityObj.length; i++) {
                		var val = entityObj[i].value ? entityObj[i].value : '';
                		if(i%2 === 0) {
                			content += '<div class="row">';
                		}
                		content += '<div class="col-sm-3"><label>'+ entityObj[i].name +'</label></div><div class="col-sm-3"><label>:&nbsp;</label><span>'+ val +'</span></div>';
                		if(i%2 === 1 || i === entityObj.length-1) {
                			content += '</div>';
                		}                		
                	}
                	childRow.html(content);
                	element.parents('.ui-grid-row').after(childRow);
                	childRow.css('transform', element.parents('.ui-grid-row').css('transform'));
                	element.find('.fa').removeClass('fa-plus-circle').addClass('fa-minus-circle');
            	} else {
            		element.find('.fa').removeClass('fa-minus-circle').addClass('fa-plus-circle');
            	}
            	
            	if($('.ui-grid-pinned-container').length > 0) {
            		$($('.ui-grid-pinned-container .ui-grid-viewport .ui-grid-canvas > div')[index]).after('<div class="fake-row-padding"></div>');
            		$('.fake-row-padding').height($('.fake-child-row').height());
            	}
            }
        }

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
            	var hidRow = new hiddenRowClass(scope,element,attrs);
            	hidRow.addHandler();
            }
        }
    };
    module.directive('hiddenRow', ['commonService', hiddenRowDirective]);

}(angular.module('wftApp')));