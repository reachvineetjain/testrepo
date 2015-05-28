'use strict';
define([
    'angular',
    '/module/seasons/services/program-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.program-list-controller',['ui.seasons.services.program-list-service'])
        .controller('ProgramListingCtrl',['$scope','$state','ProgramListService',function($scope,$state,ProgramListService){
          	ProgramListService.gettingProgramList().then(function (response) {
              $scope.programsList = response.data;
              $scope.selectedProgram = "";
							// If there are more than one program lsited here, then it should load the next state for "Seasons Listing".
              if ($state.is('home.program') && $scope.programsList.length > 0) {
								$scope.selectedProgram = $scope.programsList[0].programId;
                $state.go('home.season', {programId: $scope.programsList[0].programId});
              }
            });
        }]);
});