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
    var curEle, curRow;
    var forecastdtpDirective = function (commonService) {
        var dtpClass = function (scope,element,attrs) {
        	var dtpBox, fDtp;
        	scope.dblClick = function(row,index) {
        		fDtp.show();
        		fDtp.offset($(element).offset());
        		fDtp.css('left',fDtp.position().left + 140);
        		fDtp.css('top',fDtp.position().top - 70);
        		$(element).addClass('field-focussed');
        		curEle = element;
        		curRow = row;
        		fDtp.datepicker("setDate", $(element).find('.dtp-plan-date').text());
            }
        	
        	var hideDTP = function() {        		
        		fDtp.hide();
        		$(curEle).removeClass('field-focussed');
        	}
        	
        	var dateSelected = function(dateText, inst) {
        		var appScope = curRow.grid.appScope;
        		
        		curRow.entity.planDate = dateText;
        		$(curEle).find('.dtp-plan-date').text(dateText);
        		
        		var dataExist = _.findWhere(appScope.updatedData, {
                    "oppId": curRow.entity.oppId
                });
        		
        		if(dataExist) {
        			dataExist = curRow.entity; 
        		} else {
        			appScope.updatedData.push(curRow.entity);
        		}
        		
        		hideDTP();
        		console.log(dateText);
        	}
        	
        	var init = function() {
        		fDtp = $('.forecast-dtp-holder');
        		if(!(fDtp.attr('initiated') == 'true')) {
        			fDtp.attr('initiated', 'true');
        			dtpBox = fDtp.datepicker({
        				'dateFormat': 'mm-dd-yy',
        				'onSelect': dateSelected,
        				'changeMonth': true,
        				'changeYear': true
        			});
            	    fDtp.hide();
            	    $(document).on('click scroll',hideDTP);
            	    fDtp.on('click',function(e){ e.stopPropagation(); });
                	$('div.ui-grid-viewport').on('scroll',hideDTP);
        		}
        	}
        	
        	this.addHandler = function(){
            	init();
            }            
        }

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
            	var dtp = new dtpClass(scope,element,attrs);
            	dtp.addHandler();
            }
        }
    };
    module.directive('forecastDtp', ['commonService', forecastdtpDirective]);

}(angular.module('wftApp')));