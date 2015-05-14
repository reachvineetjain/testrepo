'use strict';
define([
    'angular'
], function(angular){
    angular
        .module('ui.seasons.controller.three-pane-controller',[])
        .controller('ThreePaneCtrl',['$http','$scope','$state',function($http, $scope, $state){
            $scope.viewContext = 'SeasonView';
            $scope.countElem = 1;
            $scope.programsList = [];
            if($scope.viewContext == 'SeasonView'){
              $state.go('home.program');
              //$state.go('home.program',{'referer':'pane-controller'});
            }
        }]);
});
