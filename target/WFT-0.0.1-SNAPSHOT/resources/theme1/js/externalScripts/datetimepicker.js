 var  counter = 0;
 var DatePickerDirective = (function () {
            function DatePickerDirective($compile) {
                this.$compile = $compile;
                counter++;
                this.restrict = 'E';
                this. require='ngModel',
                this.templateUrl = 'static/datepicker.html';
                this.scope = {
                    uid: '@',
                    selected: '=',
                    disabled: '@',
                    isStartDate: "="
                };
                this.replace = true;
                this.compile = function (element, attrs) {
                    var ele;
                    return function (scope, element, attrs) {
                        ele = element;
                         var currentDate = new Date();
                          var firstDayofMonth = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
                            var lastDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
        
                        element.attr("id", scope.uid + counter);
                        if (!scope.disabled) {
                            $(element).datepicker({
                                dateFormat: "mm-dd-yy",
                                autoclose: true,
                                showOn: "button",
                                minDate:firstDayofMonth,
                                maxDate:lastDate,
                                buttonImage: "resources/images/calendar.gif",
                                buttonImageOnly: true,
                                buttonText: "Select date",
                                changeMonth: true,
                                changeYear: true,
                                yearRange: "1900:2100",
                                onSelect: function (selectedDate) {
                                    $(ele).removeClass("error");
                                    scope.$emit('dateChange', { "selectedDate": selectedDate, "isStartDate": scope.isStartDate, "id": scope.uid });
                                }
                            });
                        }
                        else {
                            ele.attr("disabled", "disabled");
                        }
                        scope.$watch('selected', function (newData, oldData) {
                            scope.selected = newData;
                            if (newData) {
                                ele.val(newData);
                            }
                            else {
                                ele.val("");
                            }
                        });
                    };
                };
            }
            DatePickerDirective.$inject = ['$compile'];
            return DatePickerDirective;
        })();

		 app.directive("datePicker", ['$compile', function ($compile) { return new DatePickerDirective($compile); }]);
		  