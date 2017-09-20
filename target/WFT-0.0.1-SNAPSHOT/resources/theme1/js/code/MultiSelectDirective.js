app.directive("myDiv",function (){
    return {
        templateUrl : 'static/dropDownMultiselect.html'
    };
});

             var DropDownMultiselectDirective = (function () {
            function DropDownMultiselectDirective() {
                this.restrict = 'E';
                this.templateUrl = 'static/dropDownMultiselect.html';
                this.scope = {
                    model: '=',
                    options: '=',
                    selected: '=',
                    preSelectedText: '=',
                    isTrigger: '=',
                    fillData: '&'
                };
                this.replace = true;
                this.link = function (scope, element, attrs) {
                    scope.isAllSelected = false;
                    scope.open = false;
                    var selectAllEle = $(element).find('.select-all');
                    var ele = $(element);
                    scope.openDropdown = function ($event) {
                        var mul = $(".multi-select.open");
                       // $event.stopPropagation();
                        if (mul.prevObject.length> 0) {
                           // mul.toggleClass('open');
                            ele.toggleClass('open');
                        }
                         if (ele.hasClass('custom-readonly')) {
                             ele.toggleClass('open');
                         }
                        
                        
                    };
            angular.element(document).bind('click', function(event) {
                
                var isClickedElementChildOfPopup = element
                .find(event.target)
                .length > 0;
                
              if (isClickedElementChildOfPopup)
                return;
                
              ele.removeClass('open')
              scope.$apply();
                
              });
                    scope.selectAll = function ($event) {
                        $event.stopPropagation();
                        scope.isAllSelected = true;
                        var list = scope.options;
                        //for (var i in list) {
                        //    scope.model.push(this.list);
                        //}
                        scope.model = [];
                        for (var i = 0; i < list.length; i++) {
                            scope.model.push(list[i]);
                        }
                        // scope.model = scope.options;
                        var selectedText = _.pluck(scope.options, 'name');
                        scope.selected = selectedText.toString();
                        selectAllEle.removeClass('glyphicon-unchecked').addClass('glyphicon-check');
                        if (scope.isTrigger) {
                            scope.fillData({ data: scope.model });
                        }
                    };
                    scope.deselectAll = function ($event) {
                        $event.stopPropagation();
                        scope.isAllSelected = false;
                        scope.model = [];
                        scope.selected = "---Please select---";
                        selectAllEle.removeClass('glyphicon-check').addClass('glyphicon-unchecked');
                        if (scope.isTrigger) {
                            scope.fillData({ data: scope.model });
                        }
                    };
                    scope.setSelectedItem = function ($event) {
                        $event.stopPropagation();
                        var id = this.option.id;
                        var name = this.option.name;
                        if (_.findWhere(scope.model, this.option)) {
                            scope.model = _.without(scope.model, this.option);
                            var opt = this.option
                             scope.model= _( scope.model).filter(function(item) {
                            return item.name !== opt.name});
                            if (scope.model.length === 0) {
                                scope.selected = "---Please select---";
                            }
                            else {
                                scope.selected = scope.selected.indexOf(name) === 0 ? scope.selected.replace(name + ",", "") : scope.selected.replace("," + name, "");
                            }
                        }
                        else {
                            scope.model.push(this.option);
                            scope.selected = scope.selected.indexOf("---Please select---") === 0 ? scope.selected.replace("---Please select---", name) : scope.selected + "," + name;
                        }
                        if (scope.model.length === scope.options.length) {
                            scope.isAllSelected = true;
                            selectAllEle.removeClass('glyphicon-unchecked').addClass('glyphicon-check');
                        }
                        else {
                            if (scope.isAllSelected) {
                                scope.isAllSelected = false;
                                selectAllEle.removeClass('glyphicon-check').addClass('glyphicon-unchecked');
                            }
                        }
                        scope.preSelectedText = scope.selected.indexOf("---Please select---") === 0 ? "" : scope.selected;
                        if (scope.isTrigger) {
                            scope.fillData({ data: scope.model });
                        }
                    };
                    scope.checkItem = function (listItem) {
                        delete listItem.$$hashKey;
                        delete listItem.accountName;
                        if (_.findWhere(scope.model, listItem)) {
                            return 'glyphicon-check';
                        }
                        else {
                            return 'glyphicon-unchecked';
                        }
                    };
                };
            }
            return DropDownMultiselectDirective;
        })();
        app.directive("dropdownMultiselect", function () { return new DropDownMultiselectDirective(); });