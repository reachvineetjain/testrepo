'use strict';
define([
    'angular',
    '/module/seasons/services/program-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.program-list-controller',['ui.seasons.services.program-list-service'])
        .controller('ProgramListingCtrl',['$scope','$state','ProgramListService',function($scope,$state,ProgramListService){
          //if($state.is('home.season')) {
            ProgramListService.gettingProgramList().then(function (response) {
              $scope.programsList = response.data;
              console.log('Is the current state home.program = ' + $state.is('home.program'));
              if ($state.is('home.program') && $scope.programsList.length > 0) {
                $state.go('home.season', {programId: $scope.programsList[0].programId});
              }
            });
        }]);
});
