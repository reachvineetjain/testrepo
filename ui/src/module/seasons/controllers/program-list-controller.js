'use strict';
define([
    'angular',
    '/module/seasons/services/program-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.program-list-controller',['ui.seasons.services.program-list-service'])
        .controller('ProgramListingCtrl',['$scope','$state','ProgramListService',function($scope,$state,ProgramListService){
            ProgramListService.gettingProgramList().then(function(response){
                $scope.programsList = response.data;
                //console.log($scope.programsList);
                //$state.go('home.program.season', { programId: $scope.programsList[0].programId });
            });
            //console.log($state); // returns true
        }]);
});
