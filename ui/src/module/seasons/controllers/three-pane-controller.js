'use strict';
define([
    'angular'
], function(angular){
    angular
        .module('ui.seasons.controller.three-pane-controller',[])
        .controller('ThreePaneCtrl',['$http','$scope','$state',function($http, $scope, $state){
            $scope.countElem = 1;
            $scope.programsList = [];
						
						// This would trigger the call to the first state in the main.js
            $state.go('home.program');
        }]);
});
// TBD: There should be "link:" function which would essentially handle manipulation of the CSS based on the user-agent for handling the different layouts.