'use strict';
define([
  'angular',
  '/module/seasons/services/season-list-service.js'
], function(angular){
    angular
        .module('ui.seasons.controller.season-list-controller',['ui.seasons.services.season-list-service'])
        .controller('SeasonsListingCtrl',
        ['$http','$scope','$state','$stateParams','SeasonListService',
          function($http,$scope,$state,$stateParams,SeasonListService){
						//angular.element(".programs-list").find('li').attr('progid',100).addClass('selected');
            var programId = $stateParams.programId;
						
						//NOTE: TBU: The REST API Should be able to make a call with ProgramID. Which would give only the required result JSON
            SeasonListService.gettingSeasonsList($stateParams.programId).then(function(response) {
              var result = response.data[0].seasonList;
              if(programId == response.data[0]['seasonProgramID']){
                $scope.seasonsList = result;
                $scope.seasonProgramId = response.data[0]['seasonProgramID'];
              }
              else{
								//If there are no results for Seasons, then the seasonList should be emptied
								// NOTE: TBR: The seasons List should be removed
                $scope.seasonsList = [];
                $scope.seasonProgramId = "";
              }
							
							// If there are more than one season lsited, then it should load the next state for "Season Detail"
              if($state.is('home.season') && $scope.seasonsList.length>0){
                $state.go('home.seasonsDetail', { programId: $scope.seasonProgramId, seasonId: $scope.seasonsList[0].seasonId });
              }
            });
            
        }]);
});
