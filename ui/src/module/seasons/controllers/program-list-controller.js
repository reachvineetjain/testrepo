'use strict';
define([
    'angular',
    '/module/seasons/services/program-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.program-list-controller',['ui.seasons.services'])
        .controller('ProgramListingCtrl',['$scope','$state','SeasonServices',function($scope,$state,SeasonServices){
            SeasonServices.gettingProgramList().then(function(data){
                $scope.programsList = data.data;
                //console.log($scope.programsList);
            });
            //console.log($state); // returns true
        }]);
});