'use strict';
define([
    'angular'
], function(angular){
    angular
        .module('ui.seasons.controller.three-pane-controller',[])
        .controller('ThreePaneCtrl',['$http','$scope','$state',function($http, $scope, $state){
            $scope.viewContext = 'SeasonView';
            $scope.programsList = [];
            if($scope.viewContext == 'SeasonView'){
                $state.go('home.program');
            }
        }]);
});